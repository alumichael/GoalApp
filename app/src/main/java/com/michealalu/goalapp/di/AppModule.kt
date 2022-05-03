package com.michealalu.goalapp.di


import android.content.Context
import androidx.room.Room
import com.fbn.fbnquest.data.network.RetrofitClient
import com.michealalu.goalapp.data.local.favourite_db.TeamDatabase
import com.michealalu.goalapp.data.network.ApiInterface

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RetrofitClient {
        return RetrofitClient()
    }

    @Singleton
    @Provides
    fun provideAuthApi(
        retrofitClient: RetrofitClient,
        @ApplicationContext context: Context
    ): ApiInterface {
        return retrofitClient.buildApi(ApiInterface::class.java, context)
    }

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideTeamDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        TeamDatabase::class.java,
        "fav_db"
    ).build()

    @Singleton
    @Provides
    fun provideTeamDao(db: TeamDatabase) = db.teamDao()

    @Singleton
    @Provides
    fun provideApiInterface(api: ApiInterface) = ApiInterface::class.java


}