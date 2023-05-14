package com.pm.lovecalendar_kmm.domain.repository

import com.pm.lovecalendar_kmm.domain.model.Meeting
import com.pm.lovecalendar_kmm.domain.util.CommonFlow

interface MeetingsDataSource {
    fun getMeetings(): CommonFlow<List<Meeting>>
    suspend fun addMeeting(meeting: Meeting)
    suspend fun removeMeeting(meeting: Meeting)
}