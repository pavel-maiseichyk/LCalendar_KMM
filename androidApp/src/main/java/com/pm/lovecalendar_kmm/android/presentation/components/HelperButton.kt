package com.pm.lovecalendar_kmm.android.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HelperButton(
    onClick: () -> Unit,
    drawablePath: Int,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.size(64.dp),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Icon(
            painter = painterResource(id = drawablePath),
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier.padding(16.dp)
        )
    }
}