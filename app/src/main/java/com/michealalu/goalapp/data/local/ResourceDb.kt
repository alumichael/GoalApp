package com.michealalu.goalapp.data.local


sealed class ResourceDb<out T> {
    data class Success<out T>(val value: T) : ResourceDb<T>()
    data class Failure(
        val errorBody: String?
    ) : ResourceDb<Nothing>()
}