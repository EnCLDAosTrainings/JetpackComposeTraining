package com.example.netflicks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.sp
import com.example.netflicks.ui.theme.*

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
    val pageNumber = remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .background(BlueApplication)
            .fillMaxSize(),
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
                        0.7f to BlueApplication,
                        1f to BlueApplication,
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
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.8f),

                text = buildAnnotatedString {
                    append(stringResource(id = pages[pageNumber.value]))
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
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.width(100.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 0..3) {
                    DrawDot(i == pageNumber.value)
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
                backgroundColor = GoldApplication
            ),
            onClick = {
                if (pageNumber.value < 3) {
                    pageNumber.value++
                } else {
                    context?.startActivity(Intent(context, HomeActivity::class.java))
                }
            }
        ) {
            Text(
                text = stringResource(id = R.string.tutorial_button_text).toUpperCase(Locale.current),
                fontSize = 20.sp,
                color = BlackApplication
            )
        }
    }
}

@Composable
fun DrawDot(selected: Boolean = false) {
    Canvas(
        modifier = Modifier.size(10.dp),
        onDraw = { drawCircle(color = if (selected) GoldApplication else WhiteApplication) }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTutorialScreen() {
    NetflicksTheme {
        TutorialScreen()
    }
}