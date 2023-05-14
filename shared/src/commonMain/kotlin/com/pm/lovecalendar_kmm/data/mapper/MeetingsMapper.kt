package com.pm.lovecalendar_kmm.data.mapper

import com.pm.lovecalendar_kmm.domain.model.Meeting
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Long.toMeeting(): Meeting {
    return Meeting(
        date = Instant
            .fromEpochMilliseconds(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
    )
}