package id.elharies.herodota.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import id.elharies.herodota.data.model.HeroRes
import id.elharies.herodota.ui.detail.DetailHeroScreen
import id.elharies.herodota.ui.home.HomeScreen
import id.elharies.herodota.util.decode
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun HeroNavigation(navigator: Navigator = rememberNavigator()) {
    NavHost(navigator = navigator, initialRoute = HeroRoute.Home.nameScreen) {
        scene(route = HeroRoute.Home.nameScreen) {
            HomeScreen(navigator = navigator)
        }
        scene(route = HeroRoute.Detail.nameScreen) { backStackEntry ->
            val heroId = backStackEntry.path<Int>("hero") ?: 0
            DetailHeroScreen(navigator = navigator, heroId = heroId)
        }
    }
}