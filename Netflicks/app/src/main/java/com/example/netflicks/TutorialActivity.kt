package com.example.netflicks

import android.content.Context
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.netflicks.ui.theme.GoldApplication
import com.example.netflicks.ui.theme.NetflicksTheme
import com.example.netflicks.ui.theme.TransparentApplication

private val pages = arrayListOf(
    R.string.tutorial_text_1,
    R.string.tutorial_text_2,
    R.string.tutorial_text_3,
    R.string.tutorial_text_4
)

class TutorialActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetflicksTheme {
                TutorialScreen(this@TutorialActivity)
            }
        }
    }
}

@Composable
fun TutorialScreen(context: Context? = null) {
    var pageNumber by rememberSaveable { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Image(
            modifier = Modifier
                .requiredWidth(1000.dp)
                .requiredHeight(1000.dp),
            painter = painterResource(id = R.drawable.background_2),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .fillMaxHeight(0.7f)
                .background(
                    Brush.verticalGradient(
                        0f to TransparentApplication,
                        0.7f to MaterialTheme.colors.primary,
                        1f to MaterialTheme.colors.primary,
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 150.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.tutorial_title),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(40.dp))

            Crossfade(
                targetState = pageNumber,
                animationSpec = tween(durationMillis = 1000)
            ) { page ->
                DescriptionText(textPrefix = stringResource(pages[page]))
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.width(100.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 0..3) {
                    DrawDot(i == pageNumber, MaterialTheme.colors)
                }
            }
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .height(50.dp)
                .width(300.dp)
                .shadow(10.dp, RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = MaterialTheme.colors.secondary
            ),
            onClick = {
                if (pageNumber < 3) {
                    pageNumber += 1
                } else {
                    context?.startActivity(Intent(context, HomeActivity::class.java))
                }
            }
        ) {
            Text(
                text = stringResource(id = R.string.tutorial_button_text).toUpperCase(Locale.current),
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}

@Composable
fun DescriptionText(textPrefix: String) {
    Text(
        modifier = Modifier.fillMaxWidth(0.8f),
        color = MaterialTheme.colors.onPrimary,
        text = buildAnnotatedString {
            append(textPrefix)
            append(" ")
            withStyle(
                style = SpanStyle(
                    color = GoldApplication
                )
            ) {
                append(stringResource(id = R.string.tutorial_the_best))
            }
            append(" ")
            append(stringResource(id = R.string.tutorial_text_prefix))
        },
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center
    )
}

@Composable
fun DrawDot(selected: Boolean = false, colors: Colors) {
    val color by animateColorAsState(
        targetValue = if (selected) colors.secondary else colors.secondaryVariant,
        tween(durationMillis = 1000)
    )

    Canvas(
        modifier = Modifier.size(10.dp),
        onDraw = { drawCircle(color = color) }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLightTutorialScreen() {
    NetflicksTheme {
        TutorialScreen()
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewDarkTutorialScreen() {
    NetflicksTheme {
        TutorialScreen()
    }
}