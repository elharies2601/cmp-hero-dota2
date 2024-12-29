package id.elharies.herodota.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.touchlab.kermit.Logger
import coil3.request.ImageRequest
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin
import id.elharies.herodota.component.HealthManaBar
import id.elharies.herodota.component.ShimmeringEffect
import id.elharies.herodota.data.model.Attribute
import id.elharies.herodota.data.model.HeroRes
import id.elharies.herodota.data.model.HudModel
import id.elharies.herodota.theme.healthGreenColor
import id.elharies.herodota.theme.manaBlueColor
import id.elharies.herodota.ui.home.paletteTextColor
import id.elharies.herodota.util.Constants
import id.elharies.herodota.util.getImageAttribute
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailHeroScreen(
    navigator: Navigator = rememberNavigator(),
    heroId: Int = 0,
    viewModel: DetailViewModel = koinViewModel<DetailViewModel>()
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchHero(heroId)
    }

    DetailHeroScreenView(viewState = viewState) {
        navigator.goBack()
    }
}

@Composable
private fun DetailHeroScreenView(
    viewState: DetailViewState = DetailViewState(),
    onBackButton: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        if (viewState.isLoading) {
            DetailLoading(modifier = Modifier.statusBarsPadding())
        }

        if (!viewState.isEmpty && viewState.hero != null) {
            DetailsHeader(hero = viewState.hero) {
                onBackButton()
            }
            Spacer(modifier = Modifier.height(8.dp))
            ChipRoles(
                modifier = Modifier.padding(horizontal = 16.dp),
                rolesHero = viewState.hero.roles
            )
            Spacer(modifier = Modifier.height(32.dp))
            ListAttribute(
                modifier = Modifier.padding(horizontal = 16.dp),
                attributes = viewState.attributes
            )
            Spacer(modifier = Modifier.height(40.dp))
            HudHealthMana(
                modifier = Modifier.padding(horizontal = 16.dp),
                dataHealth = viewState.health,
                dataMana = viewState.mana
            )
        }
    }
}

@Composable
private fun DetailsHeader(
    modifier: Modifier = Modifier,
    hero: HeroRes,
    onBackButton: () -> Unit = {}
) {
    var palette by remember { mutableStateOf<Palette?>(null) }
    val backgroundBrush by palette.paletteBackgroundBrush()
    val textColor by palette.paletteTextColor()
    val shape = RoundedCornerShape(bottomStart = 64.dp, bottomEnd = 64.dp)
    val urlImage = "${Constants.BASE_IMAGE_URL}/${hero.name}_png.png"

    Box(
        modifier = modifier.fillMaxWidth().height(290.dp).shadow(elevation = 9.dp, shape = shape)
            .background(brush = backgroundBrush, shape = shape),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp).statusBarsPadding().fillMaxWidth()
        ) {
            IconButton(onClick = onBackButton) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = textColor
                )
            }

            Text(
                text = "Detail",
                color = textColor,
                modifier = Modifier.padding(horizontal = 10.dp).weight(1f),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Image(
                painter = hero.primaryAttr.getImageAttribute(),
                modifier = Modifier.size(25.dp),
                contentDescription = null
            )
        }

        CoilImage(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp).size(190.dp),
            imageModel = { urlImage },
            imageOptions = ImageOptions(contentScale = ContentScale.Inside),
            loading = {
                Box(
                    modifier = Modifier.matchParentSize(),
                ) {
                    ShimmeringEffect(
                        modifier = Modifier.padding(8.dp).fillMaxWidth().height(190.dp).clip(
                            RoundedCornerShape(14.dp)
                        )
                    )
                }
            },
            failure = { f ->
                Box(
                    modifier = Modifier.matchParentSize(),
                ) {
                    ShimmeringEffect(
                        modifier = Modifier.padding(8.dp).fillMaxWidth().height(190.dp).clip(
                            RoundedCornerShape(14.dp)
                        )
                    )
                }
            },
            component = rememberImageComponent {
                +CrossfadePlugin()
                if (!LocalInspectionMode.current) {
                    +PalettePlugin(
                        imageModel = urlImage,
                        useCache = true,
                        paletteLoadedListener = { palette = it },
                    )
                }
            }
        )
    }

    Text(
        hero.localizedName,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(top = 24.dp).fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun ChipRoles(
    modifier: Modifier = Modifier,
    rolesHero: MutableList<String> = mutableListOf()
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items(items = rolesHero, key = { s -> s.hashCode() }) { role ->
            ElevatedSuggestionChip(
                modifier = Modifier.padding(horizontal = 4.dp),
                onClick = {},
                label = {
                    Text(role, color = MaterialTheme.colorScheme.onTertiary)
                },
                shape = RoundedCornerShape(corner = CornerSize(100.dp)),
                colors = SuggestionChipDefaults.elevatedSuggestionChipColors(MaterialTheme.colorScheme.tertiary)
            )
        }
    }
}

@Composable
private fun ListAttribute(
    modifier: Modifier = Modifier,
    attributes: MutableList<Attribute> = mutableListOf()
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(items = attributes, key = { s -> s.label }) { attribute ->
            ItemAttribute(
                label = attribute.label,
                valueAttribute = attribute.valueAttribute,
                modifier = Modifier.width(120.dp).height(140.dp).padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
private fun ItemAttribute(
    modifier: Modifier = Modifier,
    label: String = "Strength",
    valueAttribute: Double = 0.0
) {
    OutlinedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    label,
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = label.getImageAttribute(),
                    modifier = Modifier.size(50.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    valueAttribute.toString(),
                    style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onBackground),
                )
            }
        }
    }
}

@Composable
private fun HudHealthMana(
    modifier: Modifier = Modifier,
    dataHealth: HudModel = HudModel(),
    dataMana: HudModel = HudModel()
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        HealthManaBar(
            modifier = Modifier.fillMaxWidth(),
            background = healthGreenColor,
            valueBar = dataHealth.hudValue.toInt(),
            valueRegen = dataHealth.hudRegen
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp,
            color = Color(0xFF030e1f)
        )
        HealthManaBar(
            modifier = Modifier.fillMaxWidth(),
            background = manaBlueColor,
            valueBar = dataMana.hudValue.toInt(),
            valueRegen = dataMana.hudRegen
        )
    }
}

@Composable
private fun DetailLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp).align(Alignment.Center),
            strokeWidth = 10.dp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
private fun PreviewDetailHeroScreen() {
    DetailHeroScreen()
}