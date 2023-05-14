package com.pm.lovecalendar_kmm.presentation

import com.pm.lovecalendar_kmm.domain.model.AppDate

sealed class MeetingsEvent {
    object ToggleEditingMode: MeetingsEvent()
    data class OnDateTap(val appDate: AppDate): MeetingsEvent()
}
