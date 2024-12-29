package id.elharies.herodota.domain

import id.elharies.herodota.data.model.HeroRes
import kotlinx.coroutines.flow.Flow

interface HeroRepository {
    suspend fun getHeroes(): Flow<MutableList<HeroRes>>
}