package id.elharies.herodota.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import id.elharies.herodota.component.ShimmeringEffect
import id.elharies.herodota.data.model.HeroRes
import id.elharies.herodota.navigation.HeroRoute
import id.elharies.herodota.util.Constants
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    navigator: Navigator = rememberNavigator()
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchHeroes()
    }

    HomeScreenView(viewState = viewState) {
        navigator.navigate(route = HeroRoute.Detail.createRoute(it.id))
    }
}

@Composable
private fun HomeScreenView(
    viewState: HomeViewState = HomeViewState(),
    onClickDetail: (HeroRes) -> Unit = {}
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        HeroAppBar()
    }) {
        if (viewState.isLoading) {
            HomeLoading(modifier = Modifier.padding(it))
        }

        if (!viewState.isEmpty) {
            HomeContent(modifier = Modifier.padding(it), data = viewState.data) { hero ->
                onClickDetail(hero)
            }
        } else {
            HomeLoading(modifier = Modifier.padding(it))
        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    data: MutableList<HeroRes>,
    onClickDetail: (HeroRes) -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(6.dp)) {
            items(items = data, key = { hero -> hero.id }) { hero ->
                HeroCard(modifier = Modifier, hero = hero) {
                    onClickDetail(hero)
                }
            }
        }
    }
}

@Composable
private fun HomeLoading(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(6.dp)) {
            items(count = 10) {
                ShimmeringEffect(
                    modifier = Modifier.padding(8.dp).fillMaxWidth().height(200.dp).clip(
                        RoundedCornerShape(14.dp)
                    )
                )
            }
        }
    }
}

@Composable
private fun HeroCard(modifier: Modifier = Modifier, hero: HeroRes, onClickDetail: () -> Unit = {}) {
    var palette by remember { mutableStateOf<Palette?>(null) }
    val backgroundColor by palette.paletteBackgroundColor()
    val textColor by palette.paletteTextColor()
    val urlImage = "${Constants.BASE_ICON_URL}/${hero.name}_png.png"

    Card(
        modifier = modifier.padding(8.dp).fillMaxWidth().clickable { onClickDetail() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = backgroundColor,
            disabledContainerColor = backgroundColor,
            disabledContentColor = backgroundColor,
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        CoilImage(
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 20.dp)
                .size(120.dp),
            imageModel = { urlImage },
            imageOptions = ImageOptions(contentScale = ContentScale.Inside),
            loading = {
                Box(modifier = Modifier.matchParentSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }
            },
            component = rememberImageComponent {
                +CrossfadePlugin()
                +ShimmerPlugin(
                    Shimmer.Resonate(
                        baseColor = Color.Transparent,
                        highlightColor = Color.LightGray,
                    ),
                )

                if (!LocalInspectionMode.current) {
                    +PalettePlugin(
                        imageModel = urlImage,
                        useCache = true,
                        paletteLoadedListener = { palette = it },
                    )
                }
            }
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth().padding(12.dp),
            text = hero.localizedName,
            color = textColor,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HeroAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                "Hero Dota2",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors()
            .copy(containerColor = MaterialTheme.colorScheme.primary),
    )
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreenView()
}