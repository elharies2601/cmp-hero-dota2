package id.elharies.herodota.ui.detail

import id.elharies.herodota.data.model.Attribute
import id.elharies.herodota.data.model.HeroRes
import id.elharies.herodota.data.model.HudModel

data class DetailViewState(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val errorMessage: String = "",
    val hero: HeroRes? = null,
    val attributes: MutableList<Attribute> = mutableListOf(),
    val health: HudModel = HudModel(),
    val mana: HudModel = HudModel()
)
