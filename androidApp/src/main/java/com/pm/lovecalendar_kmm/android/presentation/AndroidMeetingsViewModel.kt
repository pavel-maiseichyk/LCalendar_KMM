package com.pm.lovecalendar_kmm.android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pm.lovecalendar_kmm.domain.use_case.FindFirstDayOfMonthPosition
import com.pm.lovecalendar_kmm.domain.use_case.GenerateDates
import com.pm.lovecalendar_kmm.domain.repository.MeetingsDataSource
import com.pm.lovecalendar_kmm.presentation.MeetingsEvent
import com.pm.lovecalendar_kmm.presentation.MeetingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidMeetingsViewModel @Inject constructor(
    private val meetingsDataSource: MeetingsDataSource,
    private val generateDates: GenerateDates,
    private val findFirstDayOfMonthPosition: FindFirstDayOfMonthPosition
): ViewModel() {

    private val viewModel by lazy {
        MeetingsViewModel(
            meetingsDataSource = meetingsDataSource,
            generateDates = generateDates,
            findFirstDayOfMonthPosition = findFirstDayOfMonthPosition,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: MeetingsEvent) {
        viewModel.onEvent(event)
    }
}