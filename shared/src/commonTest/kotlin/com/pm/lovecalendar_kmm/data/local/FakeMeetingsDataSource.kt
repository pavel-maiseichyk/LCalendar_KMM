package com.pm.lovecalendar_kmm.data.local

import com.pm.lovecalendar_kmm.domain.model.Meeting
import com.pm.lovecalendar_kmm.domain.repository.MeetingsDataSource
import com.pm.lovecalendar_kmm.domain.util.CommonFlow
import com.pm.lovecalendar_kmm.domain.util.toCommonFlow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMeetingsDataSource: MeetingsDataSource {

    private val _data = MutableStateFlow<List<Meeting>>(emptyList())

    override fun getMeetings(): CommonFlow<List<Meeting>> {
        return _data.toCommonFlow()
    }

    override suspend fun addMeeting(meeting: Meeting) {
        _data.value += meeting
    }

    override suspend fun removeMeeting(meeting: Meeting) {
        _data.value.filter { it != meeting }
    }
}