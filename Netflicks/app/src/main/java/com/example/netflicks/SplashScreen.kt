package com.example.netflicks

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.netflicks.ui.theme.BlackApplication
import com.example.netflicks.ui.theme.BlueApplication
import com.example.netflicks.ui.theme.NetflicksTheme
import com.example.netflicks.ui.theme.WhiteApplication
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(finishSplashCallback: () -> Unit) {
    LaunchedEffect(true) {
        delay(3000L)
        finishSplashCallback()
    }

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
        GlideImage(
            imageModel = R.raw.intro,
            contentScale = ContentScale.Fit,
            requestListener = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    (resource as GifDrawable).setLoopCount(1)
                    return false
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    NetflicksTheme {
        SplashScreen {}
    }
}