package com.example.netflicks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.netflicks.ui.theme.BlueApplication
import com.example.netflicks.ui.theme.NetflicksTheme

class TutorialActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetflicksTheme {
                TutorialScreen()
            }
        }
    }
}

@Composable
fun TutorialScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(BlueApplication)
            .fillMaxSize(),
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTutorialScreen() {
    NetflicksTheme {
        TutorialScreen()
    }
}