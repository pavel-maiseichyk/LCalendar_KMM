package com.pm.lovecalendar_kmm.android.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.pm.lovecalendar_kmm.presentation.util.AppColors

val Cameo = Color(AppColors.Cameo)
val Iron = Color(AppColors.Iron)
val Perano = Color(AppColors.Perano)
val Sidecar = Color(AppColors.Sidecar)
val Sindbad = Color(AppColors.Sindbad)
val WildSand = Color(AppColors.WildSand)
val Cornflower_Lilac = Color(AppColors.Cornflower_Lilac)

val pastMeetingColor = Perano
val futureMeetingColor = Sindbad
val specialColor = Cornflower_Lilac

val appLightColors = lightColors(
    primary = Sidecar,
    secondary = Cameo,
    background = Iron,
    surface = WildSand,
    onSurface = Color.Black
)

val appDarkColors = appLightColors