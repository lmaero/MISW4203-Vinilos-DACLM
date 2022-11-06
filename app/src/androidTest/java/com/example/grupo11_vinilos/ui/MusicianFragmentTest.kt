package com.example.grupo11_vinilos.ui

import androidx.test.espresso.Espresso.onView
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
    var activityRule = ActivityScenarioRule<MainActivity>(
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
}
