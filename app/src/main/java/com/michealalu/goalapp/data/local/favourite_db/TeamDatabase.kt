package com.michealalu.goalapp.data.local.favourite_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.michealalu.goalapp.model.FavTeam
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [FavTeam::class],
    version = 1
)
abstract class TeamDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
}