package com.pm.lovecalendar_kmm.android.di

import android.app.Application
import com.pm.lovecalendar_kmm.data.util.DatabaseDriverFactory
import com.pm.lovecalendar_kmm.data.repository.SQLDelightMeetingsDataSource
import com.pm.lovecalendar_kmm.database.MeetingsDatabase
import com.pm.lovecalendar_kmm.domain.use_case.FindFirstDayOfMonthPosition
import com.pm.lovecalendar_kmm.domain.use_case.GenerateDates
import com.pm.lovecalendar_kmm.domain.repository.MeetingsDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideMeetingsDataSource(driver: SqlDriver): MeetingsDataSource {
        return SQLDelightMeetingsDataSource(MeetingsDatabase(driver))
    }

    @Provides
    @Singleton
    fun provideGenerateDatesUseCase(): GenerateDates {
        return GenerateDates()
    }

    @Provides
    @Singleton
    fun provideFindFirstDayOfMonthPositionUseCase(): FindFirstDayOfMonthPosition {
        return FindFirstDayOfMonthPosition()
    }
}