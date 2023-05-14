package com.pm.lovecalendar_kmm.android.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.core.view.ViewCompat
import com.pm.lovecalendar_kmm.android.R

val montserrat = FontFamily(
    Font(
        resId = R.font.montserrat_normal,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.montserrat_light,
        weight = FontWeight.Light
    ),
    Font(
        resId = R.font.montserrat_semibold,
        weight = FontWeight.SemiBold
    )
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        appDarkColors
    } else {
        appLightColors
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}