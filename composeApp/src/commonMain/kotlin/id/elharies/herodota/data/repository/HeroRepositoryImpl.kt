package id.elharies.herodota.data.repository

import id.elharies.herodota.data.model.HeroRes
import id.elharies.herodota.domain.HeroRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HeroRepositoryImpl(private val httpClient: HttpClient): HeroRepository {
    override suspend fun getHeroes(): Flow<MutableList<HeroRes>> {
        return flow {
            try {
                val response = httpClient.get {
                    url("heroStats")
                }.body<MutableList<HeroRes>>()
                emit(response)
            } catch (e: Exception) {
                emit(mutableListOf())
            }
        }
    }
}