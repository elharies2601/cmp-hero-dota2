package id.elharies.herodota.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroStats(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("primary_attr")
    val primaryAttr: String = "",
    @SerialName("attack_type")
    val attackType: String = "",
    @SerialName("roles")
    val roles: MutableList<String> = mutableListOf(),
    @SerialName("img")
    val img: String = "",
    @SerialName("icon")
    val icon: String = "",
    @SerialName("base_health")
    val baseHealth: Double = 0.0,
    @SerialName("base_health_regen")
    val baseHealthRegen: Double = 0.0,
    @SerialName("base_mana")
    val baseMana: Double = 0.0,
    @SerialName("base_mana_regen")
    val baseManaRegen: Double = 0.0,
    @SerialName("base_armor")
    val baseArmor: Double = 0.0,
    @SerialName("base_mr")
    val baseMr: Double = 0.0,
    @SerialName("base_attack_min")
    val baseAttackMin: Double = 0.0,
    @SerialName("base_attack_max")
    val baseAttackMax: Double = 0.0,
    @SerialName("base_str")
    val baseStr: Double = 0.0,
    @SerialName("base_agi")
    val baseAgi: Double = 0.0,
    @SerialName("base_int")
    val baseInt: Double = 0.0,
    @SerialName("str_gain")
    val strGain: Double = 0.0,
    @SerialName("agi_gain")
    val agiGain: Double = 0.0,
    @SerialName("int_gain")
    val intGain: Double = 0.0,
    @SerialName("attack_range")
    val attackRange: Double = 0.0,
    @SerialName("projectile_speed")
    val projectileSpeed: Int = 0,
    @SerialName("attack_rate")
    val attackRate: Double = 0.0,
    @SerialName("base_attack_time")
    val baseAttackTime: Int = 0,
    @SerialName("attack_point")
    val attackPoint: Double = 0.0,
    @SerialName("move_speed")
    val moveSpeed: Int = 0,
    @SerialName("turn_rate")
    val turnRate: Double? = null,
    @SerialName("cm_enabled")
    val cmEnabled: Boolean = false,
    @SerialName("legs")
    val legs: Int = 0,
    @SerialName("day_vision")
    val dayVision: Int = 0,
    @SerialName("night_vision")
    val nightVision: Int = 0,
    @SerialName("localized_name")
    val localizedName: String = "",
    @SerialName("1_pick")
    val pick1: Int = 0,
    @SerialName("1_win")
    val win1: Int = 0,
    @SerialName("2_pick")
    val pick2: Int = 0,
    @SerialName("2_win")
    val win2: Int = 0,
    @SerialName("3_pick")
    val pick3: Int = 0,
    @SerialName("3_win")
    val win3: Int = 0,
    @SerialName("4_pick")
    val pick4: Int = 0,
    @SerialName("4_win")
    val win4: Int = 0,
    @SerialName("5_pick")
    val pick5: Int = 0,
    @SerialName("5_win")
    val win5: Int = 0,
    @SerialName("6_pick")
    val pick6: Int = 0,
    @SerialName("6_win")
    val win6: Int = 0,
    @SerialName("7_pick")
    val pick7: Int = 0,
    @SerialName("7_win")
    val win7: Int = 0,
    @SerialName("8_pick")
    val pick: Int = 0,
    @SerialName("8_win")
    val win: Int = 0,
    @SerialName("turbo_picks")
    val turboPicks: Int = 0,
    @SerialName("turbo_picks_trend")
    val turboPicksTrend: MutableList<Int> = mutableListOf(),
    @SerialName("turbo_wins")
    val turboWins: Int = 0,
    @SerialName("turbo_wins_trend")
    val turboWinsTrend: MutableList<Int> = mutableListOf(),
    @SerialName("pro_pick")
    val proPick: Int = 0,
    @SerialName("pro_win")
    val proWin: Int = 0,
    @SerialName("pro_ban")
    val proBan: Int = 0,
    @SerialName("pub_pick")
    val pubPick: Int = 0,
    @SerialName("pub_pick_trend")
    val pubPickTrend: MutableList<Int> = mutableListOf(),
    @SerialName("pub_win")
    val pubWin: Int = 0,
    @SerialName("pub_win_trend")
    val pubWinTrend: MutableList<Int> = mutableListOf()
)