package com.pm.lovecalendar_kmm.di

import com.pm.lovecalendar_kmm.data.util.DatabaseDriverFactory
import com.pm.lovecalendar_kmm.data.repository.SQLDelightMeetingsDataSource
import com.pm.lovecalendar_kmm.database.MeetingsDatabase
import com.pm.lovecalendar_kmm.domain.use_case.FindFirstDayOfMonthPosition
import com.pm.lovecalendar_kmm.domain.use_case.GenerateDates
import com.pm.lovecalendar_kmm.domain.repository.MeetingsDataSource

class AppModule {

    val meetingsDataSource: MeetingsDataSource by lazy {
        SQLDelightMeetingsDataSource(MeetingsDatabase(DatabaseDriverFactory().create()))
    }

    val generateDatesUseCase: GenerateDates by lazy {
        GenerateDates()
    }

    val findFirstDayOfMonthPositionUseCase: FindFirstDayOfMonthPosition by lazy {
        FindFirstDayOfMonthPosition()
    }
}