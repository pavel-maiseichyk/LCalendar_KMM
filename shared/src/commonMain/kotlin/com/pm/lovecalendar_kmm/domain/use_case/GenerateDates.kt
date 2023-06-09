package com.pm.lovecalendar_kmm.domain.use_case

import com.pm.lovecalendar_kmm.domain.model.AppDate
import com.pm.lovecalendar_kmm.domain.model.DateType
import com.pm.lovecalendar_kmm.domain.util.DateUtil
import com.pm.lovecalendar_kmm.domain.model.Meeting
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

class GenerateDates {

    fun execute(
        now: LocalDate,
        month: Month,
        year: Int,
        meetings: List<Meeting>
    ): List<AppDate> {
        val days = DateUtil.daysInMonth(month = month, year = year)
        val dates = MutableList(days) { AppDate() }

        for (day in 1..days) {
            val currentDate = LocalDate(
                year = year,
                month = month,
                dayOfMonth = day
            )

            val specialDay = 20

            val type = if (meetings.contains(Meeting(currentDate))) {
                when {
                    currentDate < now -> DateType.PAST_MEETING
                    currentDate > now && currentDate.dayOfMonth == specialDay -> DateType.SPECIAL_MEETING
                    currentDate > now -> DateType.FUTURE_MEETING
                    currentDate == now && currentDate.dayOfMonth == specialDay -> DateType.SPECIAL
                    currentDate == now -> DateType.TODAY_MEETING
                    else -> DateType.TODAY_MEETING
                }
            } else {
                when {
                    currentDate.dayOfMonth == specialDay -> DateType.SPECIAL
                    currentDate == now -> DateType.TODAY
                    currentDate < now -> DateType.PAST
                    else -> DateType.NORMAL
                }
            }

            dates[day - 1] = AppDate(
                date = currentDate,
                type = type
            )
        }
        return dates
    }
}