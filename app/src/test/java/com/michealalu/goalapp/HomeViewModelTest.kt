package com.michealalu.goalapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.michealalu.goalapp.data.network.ApiInterface
import com.michealalu.goalapp.data.network.Resource
import com.michealalu.goalapp.model.competitions.GetCompetitions
import com.michealalu.goalapp.ui.activity.HomeViewModel
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import androidx.lifecycle.Observer
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import retrofit2.Response


/**
 * JUnit Test for a testing api request
 */
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    // A JUnit Test Rule that swaps the background executor used by
    // the Architecture Components with a different one which executes each task synchronously.
    // You can use this rule for your host side tests that use Architecture Components.
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()


    @Mock
    lateinit var apiInterface: ApiInterface
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var competitionObserver: Observer<Resource<GetCompetitions>>

    lateinit var homeViewModel: HomeViewModel


    @Before
    fun setUp() {
        // initialize the ViewModel with a mocked api interface
        homeViewModel = HomeViewModel(api=apiInterface, userRepository = userRepository)
    }

    //Case 1: check getAllCompetition endpoint call success and not null
    @Test
    suspend fun shouldReturnRequestSuccess_CheckDataNotNull() {

        doReturn(emptyList<GetCompetitions>())
            .`when`(apiInterface)
            .onGetAllCompetitions()

        homeViewModel.competitionResponse.observeForever(competitionObserver)
        verify(apiInterface).onGetAllCompetitions()
        // assert that the count is not null
        assert(homeViewModel.competitionResponse.value != null)

    }

}