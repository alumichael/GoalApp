package com.michealalu.goalapp.data.local

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface SafeDatabaseCall {
    suspend fun <T> safeDbCall(
        dbCall: suspend () -> T
    ): ResourceDb<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResourceDb.Success(dbCall.invoke())
            } catch (throwable: Throwable) {
                throwable.message.let { Log.i("Exception1", it.toString()) }
                ResourceDb.Failure(  throwable.message)
            }
        }
    }

}