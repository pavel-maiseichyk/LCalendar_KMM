package com.pm.lovecalendar_kmm.presentation

import com.pm.lovecalendar_kmm.domain.model.DateType
import com.pm.lovecalendar_kmm.domain.util.DateUtil
import com.pm.lovecalendar_kmm.domain.use_case.FindFirstDayOfMonthPosition
import com.pm.lovecalendar_kmm.domain.use_case.GenerateDates
import com.pm.lovecalendar_kmm.domain.model.Meeting
import com.pm.lovecalendar_kmm.domain.repository.MeetingsDataSource
import com.pm.lovecalendar_kmm.domain.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.plus

class MeetingsViewModel(
    private val meetingsDataSource: MeetingsDataSource,
    private val generateDates: GenerateDates,
    private val findFirstDayOfMonthPosition: FindFirstDayOfMonthPosition,
    coroutineScope: CoroutineScope? = null
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private var meetings = emptyList<Meeting>()

    private val _state = MutableStateFlow(MeetingsState())
    val state = combine(_state, meetingsDataSource.getMeetings()) { state, meetings ->
        this.meetings = meetings

        val now = DateUtil.now()
        val firstMonth = now.month
        val secondMonth = now.plus(DatePeriod(months = 1)).month
        val firstYear = now.year
        val secondYear = now.plus(DatePeriod(months = 1)).year

        val nextMeeting = meetings.find { it.date >= now }
        val dateDiff = DateUtil.daysDiff(nextMeeting?.date, now)
        val daysLeftText = when (dateDiff) {
            -1 -> "none"
            0 -> "today"
            1 -> "1 day"
            else -> "$dateDiff days"
        }

        val firstMonthDates = generateDates.execute(
            now = now, month = firstMonth, year = firstYear, meetings = meetings
        )
        val secondMonthDates = generateDates.execute(
            now = now, month = secondMonth, year = secondYear, meetings = meetings
        )

        val firstMonthFirstDayOfWeekPosition = findFirstDayOfMonthPosition.execute(
            month = firstMonth, year = firstYear
        )
        val secondMonthFirstDayOfWeekPosition = findFirstDayOfMonthPosition.execute(
            month = secondMonth, year = secondYear
        )

        val firstMonthEmptyDatesAmount =
            7 - ((firstMonthFirstDayOfWeekPosition + firstMonthDates.size) % 7)
        val secondMonthEmptyDatesAmount =
            7 - ((secondMonthFirstDayOfWeekPosition + secondMonthDates.size) % 7)

        state.copy(
            firstMonth = firstMonth,
            secondMonth = secondMonth,
            firstYear = firstYear,
            secondYear = secondYear,
            firstMonthDates = firstMonthDates,
            secondMonthDates = secondMonthDates,
            firstMonthFirstDayOfWeekPosition = firstMonthFirstDayOfWeekPosition,
            secondMonthFirstDayOfWeekPosition = secondMonthFirstDayOfWeekPosition,
            firstMonthEmptyDatesAmount = firstMonthEmptyDatesAmount,
            secondMonthEmptyDatesAmount = secondMonthEmptyDatesAmount,
            daysLeftText = daysLeftText
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MeetingsState())
        .toCommonStateFlow()

    fun onEvent(event: MeetingsEvent) {
        when (event) {
            is MeetingsEvent.OnDateTap -> {
                viewModelScope.launch {
                    if (state.value.isInEditMode) {
                        when (event.appDate.type) {
                            DateType.TODAY_MEETING, DateType.PAST_MEETING, DateType.FUTURE_MEETING, DateType.SPECIAL_MEETING -> {
                                meetingsDataSource.removeMeeting(Meeting(event.appDate.date))
                            }

                            else -> {
                                meetingsDataSource.addMeeting(Meeting(event.appDate.date))
                            }
                        }
                    }
                }
            }

            MeetingsEvent.ToggleEditingMode -> {
                viewModelScope.launch {
                    _state.update { it.copy(isInEditMode = !state.value.isInEditMode) }
                }
            }
        }
    }
}