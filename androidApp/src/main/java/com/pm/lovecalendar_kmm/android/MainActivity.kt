package com.pm.lovecalendar_kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pm.lovecalendar_kmm.android.presentation.AndroidMeetingsViewModel
import com.pm.lovecalendar_kmm.android.presentation.MeetingsScreen
import com.pm.lovecalendar_kmm.android.theme.AppTheme
import com.pm.lovecalendar_kmm.presentation.MeetingsState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AndroidMeetingsViewModel by viewModels()
    lateinit var state: MeetingsState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            state.firstMonthDates.isEmpty()
        }
        setContent {
            AppTheme {
                state = viewModel.state.collectAsState().value

                val systemUiController = rememberSystemUiController()
                val backgroundColor = MaterialTheme.colors.background

                if (state.firstMonthDates.isNotEmpty()) {
                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = backgroundColor,
                            darkIcons = true
                        )
                        systemUiController.setNavigationBarColor(
                            color = backgroundColor,
                            darkIcons = true
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