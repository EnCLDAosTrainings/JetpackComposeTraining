package com.example.netflicks

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.netflicks.model.Movie
import com.example.netflicks.model.movies
import com.example.netflicks.ui.theme.NetflicksTheme
import com.example.netflicks.ui.theme.TransparentApplication
import com.example.netflicks.ui.theme.WhiteApplication
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
            .background(MaterialTheme.colors.primary),
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
                                    0.5f to MaterialTheme.colors.primary,
                                    1f to MaterialTheme.colors.primary,
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
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = movie.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onPrimary
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
                            1f to MaterialTheme.colors.secondary,
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
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onPrimary,
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
                style = MaterialTheme.typography.h4,
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
                .background(MaterialTheme.colors.secondary)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var tickets by rememberSaveable {
                    mutableStateOf(0)
                }
                Text(
                    text = stringResource(id = R.string.details_buy_tickets),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSecondary,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "-",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onSecondary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            if (tickets > 0) {
                                tickets -= 1
                            }
                        }
                    )
                    Text(
                        text = tickets.toString(),
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onSecondary,
                        fontWeight = FontWeight.Bold,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_ticket),
                        contentDescription = "Ticket",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSecondary)
                    )
                    Text(
                        text = "+",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onSecondary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            tickets += 1
                        }
                    )
                }
                Text(
                    text = stringResource(id = R.string.details_tickets_total) + " " + tickets + "\$",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSecondary,
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
fun PreviewLightBottomBar() {
    NetflicksTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .fillMaxSize()
        ) {
            BottomBar(this)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLightDetailsScreen() {
    NetflicksTheme {
        DetailsScreen(movies[0])
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewDarkBottomBar() {
    NetflicksTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .fillMaxSize()
        ) {
            BottomBar(this)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewDarkDetailsScreen() {
    NetflicksTheme {
        DetailsScreen(movies[0])
    }
}