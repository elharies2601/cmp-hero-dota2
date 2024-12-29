package id.elharies.herodota.navigation

sealed class HeroRoute(val nameScreen: String) {
    data object Home: HeroRoute("home")
    data object Detail: HeroRoute("detail/{hero}") {
        fun createRoute(heroId: Int): String {
            return "detail/$heroId"
        }
    }
}