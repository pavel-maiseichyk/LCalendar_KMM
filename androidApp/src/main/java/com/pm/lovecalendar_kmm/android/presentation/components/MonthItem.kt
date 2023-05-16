package com.pm.lovecalendar_kmm.android.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.pm.lovecalendar_kmm.domain.model.AppDate

@Composable
fun MonthItem(
    isEditing: Boolean,
    isCurrent: Boolean,
    month: String,
    year: String,
    firstDayPosition: Int,
    emptyDatesAmount: Int,
    dates: List<AppDate>,
    onDateTap: (AppDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val rows =
        (List(firstDayPosition) { 0 } + dates + List(emptyDatesAmount) { 0 }).chunked(7)

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colors.surface)
            .border(
                border = BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(24.dp)
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 8.dp)
        ) {
            Row(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(
                    text = month, fontFamily = montserrat, fontSize = 28.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = year, fontFamily = montserrat, fontSize = 28.sp
                )
            }
            rows.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowItems.forEach { date ->
                        when {
                            isCurrent && date is Int && rowItems == rows[0] -> {
                                DayBeforeItem(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                )
                            }

                            date is Int -> {
                                DayAfterItem(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                )
                            }

                            date is AppDate -> {
                                DayItem(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f),
                                    onClick = { onDateTap(date) },
                                    text = date.date.dayOfMonth.toString(),
                                    type = date.type,
                                    isClickable = isEditing
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

//    LazyVerticalGrid(
//        columns = GridCells.Fixed(7),
//        userScrollEnabled = false,
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = modifier
//            .clip(RoundedCornerShape(24.dp))
//            .background(MaterialTheme.colors.surface)
//            .border(
//                border = BorderStroke(1.dp, Color.Black),
//                shape = RoundedCornerShape(24.dp)
//            ),
//        contentPadding = PaddingValues(16.dp)
//    ) {
//        item(span = { GridItemSpan(7) }) {
//            Row(modifier = Modifier.padding(bottom = 16.dp)) {
//                Text(
//                    text = month, fontFamily = montserrat, fontSize = 28.sp
//                )
//                Spacer(modifier = Modifier.weight(1f))
//                Text(
//                    text = year, fontFamily = montserrat, fontSize = 28.sp
//                )
//            }
//        }
//        items(firstDayPosition) {
//            if (isCurrent) DayBeforeItem() else DayAfterItem()
//        }
//        items(items = dates) { date ->
//            DayItem(
//                modifier = Modifier.aspectRatio(1f),
//                onClick = { onDateTap(date) },
//                text = date.date.dayOfMonth.toString(),
//                type = date.type,
//                isClickable = isEditing
//            )
//        }
//        if (emptyDatesAmount != 7) {
//            items(emptyDatesAmount) {
//                DayAfterItem()
//            }
//        }
//    }
}