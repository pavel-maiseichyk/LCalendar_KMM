package com.pm.lovecalendar_kmm.domain.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateUtil {

    fun now(): LocalDate {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    fun toEpochMillis(date: LocalDate): Long {
        val dateTime = LocalDateTime(
            date = date,
            time = LocalTime(
                hour = 0,
                minute = 0,
                second = 0,
                nanosecond = 0
            )
        )
        return toEpochMillis(dateTime)
    }

    fun toEpochMillis(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }

    fun formatDate(dateTime: LocalDateTime): String {
        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        val day = if (dateTime.dayOfMonth < 10) "0${dateTime.dayOfMonth}" else dateTime.dayOfMonth
        val year = dateTime.year
        val hour = if (dateTime.hour < 10) "0${dateTime.hour}" else dateTime.hour
        val minute = if (dateTime.minute < 10) "0${dateTime.minute}" else dateTime.minute

        return buildString {
            append(month)
            append(" ")
            append(day)
            append(" ")
            append(year)
            append(", ")
            append(hour)
            append(":")
            append(minute)
        }
    }

    fun daysInMonth(month: Month, year: Int): Int {
        return if (month.number != 12)
            LocalDate(
                year = year,
                monthNumber = month.number + 1,
                dayOfMonth = 1
            ).toEpochDays() -
                    LocalDate(
                        year = year,
                        month = month,
                        dayOfMonth = 1
                    ).toEpochDays()
        else 31
    }

    fun daysDiff(nextMeeting: LocalDate?, now: LocalDate): Int {
        if (nextMeeting == null) return -1
        if (nextMeeting.month.number > now.month.number && nextMeeting.dayOfMonth > now.dayOfMonth) {
            return nextMeeting.minus(now).days + daysInMonth(now.month, now.year)
        }
        if (nextMeeting.month.number > now.month.number && nextMeeting.dayOfMonth == now.dayOfMonth) {
            return daysInMonth(now.month, now.year)
        }
        return nextMeeting.minus(now).days
    }
}