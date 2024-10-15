package com.android.multisys.exam.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.android.multisys.exam.feature.navigation.MultisysAndroidExamApp
import com.android.multisys.exam.ui.theme.MultisysandroidexamTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultisysandroidexamTheme {
                MultisysAndroidExamApp()
            }
        }
    }
}
