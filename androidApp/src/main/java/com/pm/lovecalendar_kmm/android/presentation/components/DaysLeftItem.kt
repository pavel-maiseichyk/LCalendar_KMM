package com.pm.lovecalendar_kmm.android.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pm.lovecalendar_kmm.android.theme.montserrat

@Composable
fun DaysLeftItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text.uppercase(),
        fontFamily = montserrat,
        fontSize = 32.sp,
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(24.dp))
            .background(MaterialTheme.colors.primary)
            .padding(horizontal = 24.dp, vertical = 12.dp)
    )
}