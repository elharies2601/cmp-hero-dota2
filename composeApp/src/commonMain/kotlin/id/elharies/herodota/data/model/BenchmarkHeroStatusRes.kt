package id.elharies.herodota.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BenchmarkHeroStatusRes(
    @SerialName("hero_id")
    val heroId: Int = 0,
    @SerialName("result")
    val result: Result = Result()
) {
    @Serializable
    data class Result(
        @SerialName("gold_per_min")
        val goldPerMin: List<GoldPerMin> = listOf(),
        @SerialName("hero_damage_per_min")
        val heroDamagePerMin: List<HeroDamagePerMin> = listOf(),
        @SerialName("hero_healing_per_min")
        val heroHealingPerMin: List<HeroHealingPerMin> = listOf(),
        @SerialName("kills_per_min")
        val killsPerMin: List<KillsPerMin> = listOf(),
        @SerialName("last_hits_per_min")
        val lastHitsPerMin: List<LastHitsPerMin> = listOf(),
        @SerialName("tower_damage")
        val towerDamage: List<TowerDamage> = listOf(),
        @SerialName("xp_per_min")
        val xpPerMin: List<XpPerMin> = listOf()
    ) {
        @Serializable
        data class GoldPerMin(
            @SerialName("percentile")
            val percentile: Int = 0,
            @SerialName("value")
            val value: Int = 0
        )

        @Serializable
        data class HeroDamagePerMin(
            @SerialName("percentile")
            val percentile: Int = 0,
            @SerialName("value")
            val value: Int = 0
        )

        @Serializable
        data class HeroHealingPerMin(
            @SerialName("percentile")
            val percentile: Int = 0,
            @SerialName("value")
            val value: Int = 0
        )

        @Serializable
        data class KillsPerMin(
            @SerialName("percentile")
            val percentile: Int = 0,
            @SerialName("value")
            val value: Int = 0
        )

        @Serializable
        data class LastHitsPerMin(
            @SerialName("percentile")
            val percentile: Int = 0,
            @SerialName("value")
            val value: Int = 0
        )

        @Serializable
        data class TowerDamage(
            @SerialName("percentile")
            val percentile: Int = 0,
            @SerialName("value")
            val value: Int = 0
        )

        @Serializable
        data class XpPerMin(
            @SerialName("percentile")
            val percentile: Int = 0,
            @SerialName("value")
            val value: Int = 0
        )
    }
}