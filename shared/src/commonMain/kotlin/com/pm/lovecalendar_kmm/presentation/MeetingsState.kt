package com.pm.lovecalendar_kmm.presentation

import com.pm.lovecalendar_kmm.domain.model.AppDate
import kotlinx.datetime.Month

data class MeetingsState(
    val firstMonth: Month = Month.JANUARY,
    val secondMonth: Month = Month.FEBRUARY,
    val firstYear: Int = 0,
    val secondYear: Int = 0,
    val firstMonthDates: List<AppDate> = emptyList(),
    val secondMonthDates: List<AppDate> = emptyList(),
    val firstMonthFirstDayOfWeekPosition: Int = 0,
    val secondMonthFirstDayOfWeekPosition: Int = 0,
    val firstMonthEmptyDatesAmount: Int = 0,
    val secondMonthEmptyDatesAmount: Int = 0,
    val isInEditMode: Boolean = false,
    val daysLeftText: String = ""
)

