package ca.nick.boilerplate.utils

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val throwable: Throwable) : Resource<T>()
}