package com.pm.lovecalendar_kmm.data.util

import com.pm.lovecalendar_kmm.database.MeetingsDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            MeetingsDatabase.Schema,
            "meetings.db"
        )
    }
}