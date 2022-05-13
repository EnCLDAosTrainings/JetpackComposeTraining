package com.example.netflicks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.netflicks.ui.theme.BlackApplication
import com.example.netflicks.ui.theme.BlueApplication
import com.example.netflicks.ui.theme.NetflicksTheme
import com.example.netflicks.ui.theme.WhiteApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetflicksTheme {
                SplashScreenScreen()
            }
        }
    }
}

@Composable
fun SplashScreenScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    0.0f to BlackApplication,
                    0.3f to BlueApplication,
                    0.4f to WhiteApplication,
                    0.6f to WhiteApplication,
                    0.7f to BlueApplication,
                    1f to BlackApplication,
                )
            )
    ) {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.h1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    NetflicksTheme {
        SplashScreenScreen()
    }
}