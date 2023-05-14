package com.pm.lovecalendar_kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pm.lovecalendar_kmm.android.presentation.AndroidMeetingsViewModel
import com.pm.lovecalendar_kmm.android.presentation.MeetingsScreen
import com.pm.lovecalendar_kmm.android.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val viewModel = hiltViewModel<AndroidMeetingsViewModel>()
                val state by viewModel.state.collectAsState()

                val systemUiController = rememberSystemUiController()
                val useDarkIcons = MaterialTheme.colors.isLight
                val backgroundColor = MaterialTheme.colors.background

                if (state.firstMonthDates.isNotEmpty()) {
                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = backgroundColor,
                            darkIcons = useDarkIcons
                        )
                        systemUiController.setNavigationBarColor(
                            color = backgroundColor,
                            darkIcons = useDarkIcons
                        )
                    }
                    MeetingsScreen(
                        state = state,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}