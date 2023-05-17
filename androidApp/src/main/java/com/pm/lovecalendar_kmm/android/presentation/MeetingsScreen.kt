package com.pm.lovecalendar_kmm.android.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.pm.lovecalendar_kmm.android.presentation.components.DaysLeftItem
import com.pm.lovecalendar_kmm.android.presentation.components.HelperButton
import com.pm.lovecalendar_kmm.android.presentation.components.MonthItem
import com.pm.lovecalendar_kmm.presentation.MeetingsEvent
import com.pm.lovecalendar_kmm.presentation.MeetingsState
import com.pm.lovecalendar_kmm.android.R

@Composable
fun MeetingsScreen(
    state: MeetingsState,
    onEvent: (MeetingsEvent) -> Unit
) {
    val context = LocalContext.current
    val toastMessage = stringResource(R.string.feature_coming_soon)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.padding(top = 16.dp, bottom = 20.dp)
        ) {
            HelperButton(
                onClick = { Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show() },
                drawablePath = R.drawable.settings
            )
            Spacer(modifier = Modifier.weight(1f))
            DaysLeftItem(text = if (state.isInEditMode) stringResource(R.string.edit).uppercase() else state.daysLeftText)
            Spacer(modifier = Modifier.weight(1f))
            HelperButton(
                onClick = { onEvent(MeetingsEvent.ToggleEditingMode) },
                drawablePath = if (state.isInEditMode) R.drawable.done else R.drawable.add
            )
        }
        MonthItem(
            isEditing = state.isInEditMode,
            isCurrent = true,
            month = state.firstMonth.name,
            year = state.firstYear.toString(),
            firstDayPosition = state.firstMonthFirstDayOfWeekPosition,
            emptyDatesAmount = state.firstMonthEmptyDatesAmount,
            dates = state.firstMonthDates,
            onDateTap = { onEvent(MeetingsEvent.OnDateTap(it)) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        MonthItem(
            isEditing = state.isInEditMode,
            isCurrent = false,
            month = state.secondMonth.name,
            year = state.secondYear.toString(),
            firstDayPosition = state.secondMonthFirstDayOfWeekPosition,
            emptyDatesAmount = state.secondMonthEmptyDatesAmount,
            dates = state.secondMonthDates,
            onDateTap = { onEvent(MeetingsEvent.OnDateTap(it)) }
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}