package com.example.netflicks

import android.content.res.Configuration
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
import androidx.compose.ui.layout.ContentScale
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
import com.example.netflicks.ui.theme.NetflicksTheme
import com.example.netflicks.ui.theme.TransparentApplication
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BottomNavigationBar() {
    val items = listOf(
        NavigationItem.Notification,
        NavigationItem.Search,
        NavigationItem.Home,
        NavigationItem.Favorites,
        NavigationItem.More
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.secondary.copy(0.8f),
        contentColor = MaterialTheme.colors.onSecondary,
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                selectedContentColor = MaterialTheme.colors.secondaryVariant,
                unselectedContentColor = MaterialTheme.colors.onSecondary,
                alwaysShowLabel = false,
                selected = false,
                onClick = { /* TODO */ }
            )
        }
    }
}

@Composable
fun HomeScreen(onMovieClicked: (movieId: Int) -> Unit) {
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        backgroundColor = MaterialTheme.colors.primary
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
                            style = MaterialTheme.typography.h1,
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
                    CategoryTrailers(movies, MaterialTheme.colors)
                }
                item {
                    CategoryTexts(textId = R.string.home_category_now_in_cinemas)
                }
                item {
                    CategoryNowInCinema(movies, onMovieClicked)
                }
                item {
                    CategoryTexts(textId = R.string.home_category_coming_soon)
                }
                item {
                    CategoryComingSoon(movies = movies, onMovieClicked)
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
                            1f to MaterialTheme.colors.onPrimary,
                        )
                    )
            )
        }

    }
}

@Composable
fun CategoryNowInCinema(movies: List<Movie>, onMovieClicked: (movieId: Int) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        val filteredList = movies.filter { it.rating != 0F }
        items(filteredList.size) { index ->
            MovieItem(filteredList[index], onMovieClicked)
        }
    }
}

@Composable
fun CategoryComingSoon(movies: List<Movie>, onMovieClicked: (movieId: Int) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        val filteredList = movies.filter { it.rating == 0F }
        items(filteredList.size) { index ->
            MovieItem(filteredList[index], onMovieClicked)
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onMovieClicked: (movieId: Int) -> Unit) {
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
                    onMovieClicked(movie.id)
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
            style = MaterialTheme.typography.body1
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
                style = MaterialTheme.typography.body2
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
fun CategoryTrailers(movies: List<Movie>, colors: Colors) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(movies.size) { index ->
            TrailerItem(index, movies, colors)
        }
    }
}

@Composable
fun TrailerItem(index: Int, movies: List<Movie>, colors: Colors) {
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
                            )
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
                    onDraw = { drawCircle(color = colors.primary.copy(0.5f)) }
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
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = stringResource(id = R.string.home_view_all_text),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNowInCinema() {
    NetflicksTheme {
        CategoryNowInCinema(movies = movies) {}
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLightHomeScreen() {
    NetflicksTheme {
        HomeScreen {}
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDarkHomeScreen() {
    NetflicksTheme {
        HomeScreen {}
    }
}