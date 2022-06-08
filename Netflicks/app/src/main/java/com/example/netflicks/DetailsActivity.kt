package com.example.netflicks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.netflicks.model.Movie
import com.example.netflicks.model.movies
import com.example.netflicks.ui.theme.*
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

const val EXTRA_MOVIE = "EXTRA_MOVIE"

class DetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movie = Json.decodeFromString<Movie>(intent.getStringExtra(EXTRA_MOVIE)!!)
        setContent {
            NetflicksTheme {
                DetailsScreen(movie)
            }
        }
    }
}

@Composable
fun DetailsScreen(movie: Movie) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueApplication),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            //region Top content (Image, Buttons, Title, Starts, Info)
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    GlideImage(
                        imageModel = movie.imageLandscapeUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(600.dp),
                        contentScale = ContentScale.FillHeight,
                    )
                    CircleIcon(
                        resId = R.drawable.ic_back,
                        Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 20.dp, top = 50.dp)
                    )
                    CircleIcon(
                        resId = R.drawable.ic_heart,
                        Modifier
                            .align(Alignment.TopEnd)
                            .padding(end = 20.dp, top = 50.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .align(Alignment.BottomCenter)
                            .background(
                                Brush.verticalGradient(
                                    0f to TransparentApplication,
                                    0.5f to BlueApplication,
                                    1f to BlueApplication,
                                )
                            )
                    )
                    MovieInfo(boxScope = this, movie = movie)
                }
            }
            //endregion
            //region Storyline text
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.details_storyline),
                    style = TypographyHome.h2,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = movie.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    style = TypographyHome.h4,
                    textAlign = TextAlign.Center
                )
            }
            //endregion
        }
        //region Bottom bar
        if (movie.rating != 0f) {
            BottomBar(boxScope = this)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 60.dp)
                    .align(Alignment.BottomCenter)
                    .height(10.dp)
                    .background(
                        Brush.verticalGradient(
                            0f to TransparentApplication,
                            1f to GoldApplication,
                        )
                    )
            )
        }
        //endregion
    }
}

@Composable
fun MovieInfo(boxScope: BoxScope, movie: Movie) {
    boxScope.apply {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                text = movie.name,
                style = TypographyHome.h2.copy(fontSize = 30.sp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            if (movie.rating != 0f) {
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    DrawStars(
                        movie = movie,
                        modifier = Modifier.size(20.dp),
                        spacerModifier = Modifier.size(10.dp)
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                text = "${movie.year} | ${movie.duration} | ${movie.genre[0]}, ${movie.genre[1]}",
                style = TypographyHome.h3,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CircleIcon(resId: Int, modifier: Modifier) {
    Box(
        modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(50.dp),
            onDraw = { drawCircle(color = WhiteApplication.copy(0.5f)) }
        )
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = resId),
            contentDescription = ""
        )
    }
}

@Composable
fun BottomBar(boxScope: BoxScope) {
    boxScope.apply {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .align(Alignment.BottomCenter)
                .background(GoldApplication)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val tickets = remember {
                    mutableStateOf(0)
                }
                Text(
                    text = stringResource(id = R.string.details_buy_tickets),
                    fontSize = 15.sp,
                    color = BlueApplication,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "-",
                        fontSize = 15.sp,
                        color = BlueApplication,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            if (tickets.value > 0) {
                                tickets.value -= 1;
                            }
                        }
                    )
                    Text(
                        text = tickets.value.toString(),
                        fontSize = 15.sp,
                        color = BlueApplication,
                        fontWeight = FontWeight.Bold,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_ticket),
                        contentDescription = "Ticket"
                    )
                    Text(
                        text = "+",
                        fontSize = 15.sp,
                        color = BlueApplication,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            tickets.value += 1;
                        }
                    )
                }
                Text(
                    text = stringResource(id = R.string.details_tickets_total) + " " + tickets.value + "\$",
                    fontSize = 15.sp,
                    color = BlueApplication,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 222222)
@Composable
fun PreviewMovieInfo() {
    NetflicksTheme {
        Box {
            MovieInfo(this, movies[0])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomBar() {
    NetflicksTheme {
        Box(
            modifier = Modifier
                .background(BlueApplication)
                .fillMaxSize()
        ) {
            BottomBar(this)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDetailsScreen() {
    NetflicksTheme {
        DetailsScreen(movies[0])
    }
}