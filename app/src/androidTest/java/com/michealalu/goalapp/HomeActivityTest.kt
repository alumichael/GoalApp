package com.michealalu.goalapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.michealalu.goalapp.ui.activity.FavouriteActivity
import com.michealalu.goalapp.ui.activity.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.concurrent.timer


/**
 * User interface test for home activity
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java, false, false)

    @Test
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun onLaunchToolBarIsDisplayed() {
        ActivityScenario.launch(HomeActivity::class.java)
        onView(withId(R.id.toolbar))
        .check(matches(isDisplayed()))
    }

    @Test
    fun onLaunchMatchRecyclerViewIsDisplayed() {
        ActivityScenario.launch(HomeActivity::class.java)

        onView(withId(R.id.competition_recyclerView))
            .check(matches(isDisplayed()))
    }



    @Test
    fun whenFavBottomIconisPressed() {
        ActivityScenario.launch(HomeActivity::class.java)

        onView(withId(R.id.menu_fav))
            .perform(click())

        ActivityScenario.launch(FavouriteActivity::class.java)
    }

}