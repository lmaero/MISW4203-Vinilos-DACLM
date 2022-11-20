package com.example.grupo11_vinilos.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.grupo11_vinilos.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CollectorFragmentTest {
    @Rule
    @JvmField
    val mActivityTestRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    private fun navigateToCollectors() {
        val collectorsButton = onView(
            allOf(
                withId(R.id.navButtonCollectors),
                ViewMatchers.withText("Coleccionistas"),
                ViewMatchers.isDisplayed()
            )
        )
        collectorsButton.perform(ViewActions.click())
    }

    @Test
    fun collectorTitleIsDisplayed() {
        navigateToCollectors()
        onView(allOf(withId(R.id.collectorTitle), ViewMatchers.isDisplayed()))
    }

    @Test
    fun collectorNameIsDisplayed() {
        navigateToCollectors()
        onView(allOf(withId(R.id.collectorDetailName), ViewMatchers.isDisplayed()))
    }

    @Test
    fun collectorEmailIsDisplayed() {
        navigateToCollectors()
        onView(allOf(withId(R.id.collectorDetailTelephone), ViewMatchers.isDisplayed()))
    }

    @Test
    fun collectorTelephoneIsDisplayed() {
        navigateToCollectors()
        onView(allOf(withId(R.id.collectorDetailEmail), ViewMatchers.isDisplayed()))
    }
}