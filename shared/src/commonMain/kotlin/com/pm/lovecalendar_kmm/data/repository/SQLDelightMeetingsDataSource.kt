package com.pm.lovecalendar_kmm.data.repository

import com.pm.lovecalendar_kmm.data.mapper.toMeeting
import com.pm.lovecalendar_kmm.database.MeetingsDatabase
import com.pm.lovecalendar_kmm.domain.util.DateUtil
import com.pm.lovecalendar_kmm.domain.model.Meeting
import com.pm.lovecalendar_kmm.domain.repository.MeetingsDataSource
import com.pm.lovecalendar_kmm.domain.util.CommonFlow
import com.pm.lovecalendar_kmm.domain.util.toCommonFlow
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map

class SQLDelightMeetingsDataSource(
    db: MeetingsDatabase
): MeetingsDataSource {

    private val queries = db.meetingQueries

    override fun getMeetings(): CommonFlow<List<Meeting>> {
        return queries
            .getMeetings()
            .asFlow()
            .mapToList()
            .map { list ->
                list.map { it.toMeeting() }
            }
            .toCommonFlow()
    }

    override suspend fun addMeeting(meeting: Meeting) {
        queries.addMeeting(DateUtil.toEpochMillis(meeting.date))
    }

    override suspend fun removeMeeting(meeting: Meeting) {
        queries.removeMeeting(DateUtil.toEpochMillis(meeting.date))
    }
}