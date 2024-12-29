package id.elharies.herodota.ui.home

import id.elharies.herodota.data.model.HeroRes

data class HomeViewState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isEmpty: Boolean = false,
    val data: MutableList<HeroRes> = mutableListOf()
)
