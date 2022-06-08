package com.example.netflicks

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.netflicks.model.Movie
import com.example.netflicks.model.movies
import com.example.netflicks.ui.theme.*
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetflicksTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    var items = listOf(
        NavigationItem.Notification,
        NavigationItem.Search,
        NavigationItem.Home,
        NavigationItem.Favorites,
        NavigationItem.More
    )
    BottomNavigation(
        backgroundColor = BlueApplication.copy(0.8f),
        contentColor = WhiteApplication,
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                selectedContentColor = GoldApplication,
                unselectedContentColor = Color.White,
                alwaysShowLabel = false,
                selected = false,
                onClick = { /* TODO */ }
            )
        }
    }
}

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        backgroundColor = BlueApplication
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, bottom = innerPadding.calculateBottomPadding())
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(id = R.string.app_name),
                            style = TypographyHome.h1,
                            textAlign = TextAlign.Center
                        )
                        Image(
                            painter = painterResource(id = R.drawable.photo),
                            contentDescription = "Photo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .align(Alignment.CenterVertically)

                        )
                    }
                }
                item {
                    CategoryTexts(textId = R.string.home_category_trailers)
                }
                item {
                    CategoryTrailers(movies)
                }
                item {
                    CategoryTexts(textId = R.string.home_category_now_in_cinemas)
                }
                item {
                    CategoryNowInCinema(movies)
                }
                item {
                    CategoryTexts(textId = R.string.home_category_coming_soon)
                }
                item {
                    CategoryComingSoon(movies = movies)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(10.dp)
                    .background(
                        Brush.verticalGradient(
                            0f to TransparentApplication,
                            1f to WhiteApplication,
                        )
                    )
            )
        }

    }
}

@Composable
fun CategoryNowInCinema(movies: List<Movie>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        val filteredList = movies.filter { it.rating != 0F }
        items(filteredList.size) { index ->
            MovieItem(filteredList[index])
        }
    }
}

@Composable
fun CategoryComingSoon(movies: List<Movie>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        val filteredList = movies.filter { it.rating == 0F }
        items(filteredList.size) { index ->
            MovieItem(filteredList[index])
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .requiredWidth(150.dp)
            .wrapContentHeight()
            .padding(bottom = 20.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable {
                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE, Json.encodeToString(movie))
                    context.startActivity(intent)
                },
            elevation = 10.dp
        ) {
            GlideImage(
                imageModel = movie.imageUrl,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = movie.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TypographyHome.h4
        )
        Spacer(modifier = Modifier.height(5.dp))
        if (movie.rating != 0F) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                DrawStars(movie = movie, modifier = Modifier.size(10.dp))
            }
        } else {
            Text(
                text = stringResource(id = R.string.home_movie_appearing_in) + " " + movie.year,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                style = TypographyHome.h4
            )
        }
    }
}

@Composable
fun DrawStars(movie: Movie, modifier: Modifier, spacerModifier: Modifier = Modifier) {
    val rating = movie.rating / 10 * 5
    val numbers = rating.toString().split('.')
    val first = numbers[0].toInt()
    val second = numbers[1].toInt()
    var remaining = 5
    if (first != 0) {
        for (i in 0 until first) {
            Image(
                painter = painterResource(id = R.drawable.ic_full_star),
                contentDescription = "Full star",
                modifier = modifier
            )
            Spacer(modifier = spacerModifier)
        }
        remaining -= first
        if (second != 0) {
            Image(
                painter = painterResource(id = R.drawable.ic_half_star),
                contentDescription = "Half star",
                modifier = modifier
            )
            Spacer(modifier = spacerModifier)
            remaining -= 1
        }
        for (i in 0 until remaining) {
            Image(
                painter = painterResource(id = R.drawable.ic_empty_star),
                contentDescription = "Empty star",
                modifier = modifier
            )
            Spacer(modifier = spacerModifier)
        }
    } else {
        if (second != 0) {
            Image(
                painter = painterResource(id = R.drawable.ic_half_star),
                contentDescription = "Half star",
                modifier = modifier
            )
            Spacer(modifier = spacerModifier)

            for (i in 0 until 4) {
                Image(
                    painter = painterResource(id = R.drawable.ic_empty_star),
                    contentDescription = "Empty star",
                    modifier = modifier
                )
                Spacer(modifier = spacerModifier)

            }
        } else {
            for (i in 0 until 5) {
                Image(
                    painter = painterResource(id = R.drawable.ic_empty_star),
                    contentDescription = "Empty star",
                    modifier = modifier
                )
                Spacer(modifier = spacerModifier)
            }
        }
    }
}

@Composable
fun CategoryTrailers(movies: List<Movie>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(movies.size) { index ->
            TrailerItem(index, movies)
        }
    }
}

@Composable
fun TrailerItem(index: Int, movies: List<Movie>) {
    val player = remember { mutableStateOf<YouTubePlayer?>(null) }
    val videoStarted = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .width(270.dp)
            .height(150.dp)
            .clickable {
                if (videoStarted.value) {
                    player.value?.pause()
                    videoStarted.value = false
                }
            },
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                YouTubePlayerView(context).apply {
                    this.addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            player.value = youTubePlayer
                            youTubePlayer.loadVideo(
                                movies[index].videoUrl.substringAfterLast('='),
                                0F
                            );
                            if (!videoStarted.value) {
                                youTubePlayer.pause()
                            }
                        }
                    })
                }
            })
        if (!videoStarted.value) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.clickable {
                    player.value?.play()
                    videoStarted.value = true
                }) {
                GlideImage(
                    imageModel = movies[index].imageLandscapeUrl,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Canvas(
                    modifier = Modifier
                        .size(50.dp),
                    onDraw = { drawCircle(color = BlueApplication.copy(0.5f)) }
                )
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.ic_play_white),
                    contentDescription = "Play"
                )
            }
        }
    }
}

@Composable
fun CategoryTexts(textId: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(id = textId),
            style = TypographyHome.h2,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = stringResource(id = R.string.home_view_all_text),
            style = TypographyHome.h3,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNowInCinema() {
    NetflicksTheme {
        CategoryNowInCinema(movies = movies)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrailerItem() {
    NetflicksTheme {
        TrailerItem(index = 0, movies = movies)
    }
}

@Preview
@Composable
fun PreviewCategoryTexts() {
    NetflicksTheme {
        CategoryTexts(textId = R.string.home_category_trailers)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    NetflicksTheme {
        HomeScreen()
    }
}