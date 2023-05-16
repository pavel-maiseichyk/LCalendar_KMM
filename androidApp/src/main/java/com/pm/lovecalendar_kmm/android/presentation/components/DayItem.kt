package com.pm.lovecalendar_kmm.android.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pm.lovecalendar_kmm.android.R
import com.pm.lovecalendar_kmm.android.theme.futureMeetingColor
import com.pm.lovecalendar_kmm.android.theme.montserrat
import com.pm.lovecalendar_kmm.android.theme.pastMeetingColor
import com.pm.lovecalendar_kmm.android.theme.specialColor
import com.pm.lovecalendar_kmm.domain.model.DateType
import com.pm.lovecalendar_kmm.domain.model.DateType.FUTURE_MEETING
import com.pm.lovecalendar_kmm.domain.model.DateType.NORMAL
import com.pm.lovecalendar_kmm.domain.model.DateType.PAST
import com.pm.lovecalendar_kmm.domain.model.DateType.PAST_MEETING
import com.pm.lovecalendar_kmm.domain.model.DateType.SPECIAL
import com.pm.lovecalendar_kmm.domain.model.DateType.SPECIAL_MEETING
import com.pm.lovecalendar_kmm.domain.model.DateType.TODAY
import com.pm.lovecalendar_kmm.domain.model.DateType.TODAY_MEETING

@Composable
fun DayItem(
    onClick: () -> Unit,
    text: String,
    type: DateType,
    isClickable: Boolean,
    modifier: Modifier = Modifier
) {

    val backgroundColor = when (type) {
        NORMAL -> Color.White
        PAST -> MaterialTheme.colors.surface
        TODAY, TODAY_MEETING -> MaterialTheme.colors.primary
        PAST_MEETING -> pastMeetingColor
        FUTURE_MEETING -> futureMeetingColor
        SPECIAL, SPECIAL_MEETING -> specialColor
    }

    val border = when (type) {
        PAST -> null
        else -> BorderStroke(1.dp, Color.Black)
    }

    Card(
        backgroundColor = backgroundColor,
        contentColor = MaterialTheme.colors.onSurface,
        shape = RoundedCornerShape(12.dp),
        border = border,
        modifier = modifier
            .size(width = 40.dp, height = 40.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = isClickable, onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                fontFamily = montserrat,
                fontSize = 20.sp
            )
            if (type == PAST || type == PAST_MEETING) {
                Image(
                    painter = painterResource(id = R.drawable.before_month),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.7f))
                )
            }
        }
    }
}


@Preview
@Composable
fun DayItemPreview() {
    DayItem(onClick = { }, text = "1", type = NORMAL, isClickable = false)
}