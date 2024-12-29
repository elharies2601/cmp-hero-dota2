package id.elharies.herodota.data.model

sealed interface ResultApi<out T> {
    data object Loading: ResultApi<Nothing>
    data class Error(val message: String): ResultApi<Nothing>
    data class Success<out T>(val data: T): ResultApi<T>
}