package com.michealalu.goalapp

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.michealalu.goalapp.data.local.favourite_db.TeamDao
import com.michealalu.goalapp.data.network.ApiInterface
import com.michealalu.goalapp.data.network.Resource
import com.michealalu.goalapp.model.competitions.Competition
import com.michealalu.goalapp.model.competitions.GetCompetitions
import com.michealalu.goalapp.ui.activity.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.Response
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject


/**
 * JUnit Test for api request
 */
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var competitionObserver: Observer<Resource<GetCompetitions>>

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var  apiInterface: ApiInterface


    @ExperimentalCoroutinesApi
    @Test
    fun shouldVerifySuccess_CheckDataNotNull() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<GetCompetitions>())
                .`when`(apiInterface)
                .onGetACompetition(2021)

            val homeViewModel = HomeViewModel(api=apiInterface, userRepository = userRepository)
            homeViewModel.oneCompetitionResponse.observeForever(competitionObserver)
            verify(apiInterface).onGetACompetition(2021)

            homeViewModel.oneCompetitionResponse.removeObserver(competitionObserver)
        }
    }



}