package com.example.grupo11_vinilos.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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
class MusicianFragmentTest {
    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun musician_name_is_displayed() {
        onView(allOf(withId(R.id.musicianName), isDisplayed()))
    }

    @Test
    fun musician_birthdate_is_displayed() {
        onView(allOf(withId(R.id.musicianBirthDate), isDisplayed()))
    }

    @Test
    fun musician_portrait_is_displayed() {
        onView(allOf(withId(R.id.musicianPortrait), isCompletelyDisplayed()))
    }

    @Test
    fun button_musicians_is_enabled() {
        onView(withId(R.id.navButtonMusicians)).check(matches(isEnabled()))
    }

    @Test
    fun button_musicians_is_displayed() {
        onView(withId(R.id.navButtonMusicians)).check(matches(isDisplayed()))
    }

    @Test
    fun button_albums_is_not_displayed() {
        onView(withId(R.id.navButtonAlbums)).check(ViewAssertions.doesNotExist())
    }
}
