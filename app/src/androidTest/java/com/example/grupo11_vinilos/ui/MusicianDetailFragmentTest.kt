package com.example.grupo11_vinilos.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.grupo11_vinilos.R
import kotlinx.coroutines.android.awaitFrame
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MusicianDetailFragmentTest {
    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule<MainActivity>(
        MainActivity::class.java

    )


    @Test
    fun musician_detail_fragment_is_displayed() {
        Thread.sleep(2000)
        onView(allOf(withId(R.id.navButtonMusicians))).perform(click())
        Thread.sleep(2000)
        onView(allOf(withId(R.id.musicianPortrait))).perform(click())
        Thread.sleep(2000)

    }

    @Test
    fun musician_detail_name_is_displayed() {
        Thread.sleep(2000)
        onView(allOf(withId(R.id.navButtonMusicians))).perform(click())
        Thread.sleep(2000)
        onView(allOf(withId(R.id.musicianPortrait))).perform(click())
        Thread.sleep(2000)
        onView(allOf(withId(R.id.musicianDetailName))).check(matches(isDisplayed()))


    }

    @Test
    fun musician_detail_birthdate_is_displayed() {
        Thread.sleep(2000)
        onView(allOf(withId(R.id.navButtonMusicians))).perform(click())
        Thread.sleep(2000)
        onView(allOf(withId(R.id.musicianPortrait))).perform(click())
        Thread.sleep(2000)
        onView(allOf(withId(R.id.musicianDetailBirthDate))).check(matches(isDisplayed()))


    }

    @Test
    fun musician_detail_description_is_displayed() {
        Thread.sleep(2000)
        onView(allOf(withId(R.id.navButtonMusicians))).perform(click())
        Thread.sleep(2000)
        onView(allOf(withId(R.id.musicianPortrait))).perform(click())
        Thread.sleep(2000)
        onView(allOf(withId(R.id.musicianDetailDescription))).check(matches(isDisplayed()))


    }

    @Test
    fun musician_detail_image_is_displayed() {
        Thread.sleep(2000)
        onView(allOf(withId(R.id.navButtonMusicians))).perform(click())
        Thread.sleep(2000)
        onView(allOf(withId(R.id.musicianPortrait))).perform(click())
        Thread.sleep(2000)
        onView(allOf(withId(R.id.musicianDetailImage))).check(matches(isDisplayed()))


    }
}
