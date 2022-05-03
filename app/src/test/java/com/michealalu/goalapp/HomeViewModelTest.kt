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

    /*@ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()*/

    @Mock
    lateinit var competitionObserver: Observer<Resource<GetCompetitions>>


    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    @Inject
    lateinit var teamDao: TeamDao

    @Mock
    lateinit var  apiInterface: ApiInterface

    lateinit var homeViewModel: HomeViewModel


    @Before
    fun setUp() {
        // initialize the ViewModel with a mocked api interface
        //userRepository= UserRepository(api=apiInterface, teamDao = teamDao)
        homeViewModel = HomeViewModel(api=apiInterface, userRepository = userRepository)
    }

    //Case 1: check getAllCompetition endpoint call success and not null
    @ExperimentalCoroutinesApi
    @Test
     fun shouldReturnRequestSuccess_CheckDataNotNull() {

    }


}