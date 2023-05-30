package com.pm.lovecalendar_kmm.presentation

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.pm.lovecalendar_kmm.data.local.FakeMeetingsDataSource
import com.pm.lovecalendar_kmm.domain.model.AppDate
import com.pm.lovecalendar_kmm.domain.model.DateType
import com.pm.lovecalendar_kmm.domain.model.Meeting
import com.pm.lovecalendar_kmm.domain.repository.MeetingsDataSource
import com.pm.lovecalendar_kmm.domain.use_case.FindFirstDayOfMonthPosition
import com.pm.lovecalendar_kmm.domain.use_case.GenerateDates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlin.test.BeforeTest
import kotlin.test.Test

class MeetingsViewModelTest {

    private lateinit var viewModel: MeetingsViewModel
    private lateinit var dataSource: MeetingsDataSource

    @BeforeTest
    fun setUp() {
        dataSource = FakeMeetingsDataSource()
        val generateDates = GenerateDates()
        val findFirstDayOfMonthPosition = FindFirstDayOfMonthPosition()

        viewModel = MeetingsViewModel(
            meetingsDataSource = dataSource,
            generateDates = generateDates,
            findFirstDayOfMonthPosition = findFirstDayOfMonthPosition,
            coroutineScope = CoroutineScope(Dispatchers.Default)
        )
    }

    @Test
    fun `MeetingState is updated properly when emitting a value to MeetingDataSource`() =
        runBlocking {
            viewModel.state.test {
                val initialState = awaitItem()
                assertThat(initialState).isEqualTo(MeetingsState())

                val meetingDate = LocalDate(year = 2023, monthNumber = 1, dayOfMonth = 19)
                val newMeeting = Meeting(date = meetingDate)
                dataSource.addMeeting(newMeeting)

                val state = awaitItem()

                val firstMonthItems = (1..14).map {
                    AppDate(
                        date = LocalDate(year = 2023, monthNumber = 1, dayOfMonth = it),
                        type = DateType.PAST
                    )
                } + AppDate(
                    date = LocalDate(year = 2023, monthNumber = 1, dayOfMonth = 15),
                    type = DateType.TODAY
                ) + (16..18).map {
                    AppDate(
                        date = LocalDate(year = 2023, monthNumber = 1, dayOfMonth = it),
                        type = DateType.NORMAL
                    )
                } + AppDate(
                    date = LocalDate(year = 2023, monthNumber = 1, dayOfMonth = 19),
                    type = DateType.FUTURE_MEETING
                ) + AppDate(
                    date = LocalDate(year = 2023, monthNumber = 1, dayOfMonth = 20),
                    type = DateType.SPECIAL
                ) + (21..31).map {
                    AppDate(
                        date = LocalDate(year = 2023, monthNumber = 1, dayOfMonth = it),
                        type = DateType.NORMAL
                    )
                }
                val secondMonthItems = (1..28).map {
                    AppDate(
                        date = LocalDate(year = 2023, monthNumber = 2, dayOfMonth = it),
                        type = DateType.NORMAL
                    )
                }

                val expected = MeetingsState(
                    firstMonth = Month.JANUARY,
                    secondMonth = Month.FEBRUARY,
                    firstYear = 2023,
                    secondYear = 2023,
                    firstMonthDates = firstMonthItems,
                    secondMonthDates = secondMonthItems,
                    firstMonthFirstDayOfWeekPosition = 6,
                    secondMonthFirstDayOfWeekPosition = 2,
                    firstMonthEmptyDatesAmount = 5,
                    secondMonthEmptyDatesAmount = 5,
                    isInEditMode = false,
                    daysLeftText = "4 DAYS"
                )

                assertThat(state).isEqualTo(expected)
            }
        }
}