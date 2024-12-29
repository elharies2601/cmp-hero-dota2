package id.elharies.herodota.util

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
inline fun <reified T: Any> encode(data: T): String {
    val json = Json.encodeToString(data)
    return Base64.encode(json.encodeToByteArray())
}

@OptIn(ExperimentalEncodingApi::class)
inline fun <reified T: Any> decode(data: String): T {
    val json = Base64.decode(data).decodeToString()
    return Json.decodeFromString(json)
}