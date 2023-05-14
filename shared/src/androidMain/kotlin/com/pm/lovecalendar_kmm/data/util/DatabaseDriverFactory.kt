package com.pm.lovecalendar_kmm.data.util

import android.content.Context
import com.pm.lovecalendar_kmm.database.MeetingsDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            MeetingsDatabase.Schema,
            context,
            "meetings.db"
        )
    }
}