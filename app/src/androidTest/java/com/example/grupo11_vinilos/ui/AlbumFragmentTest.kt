package com.example.grupo11_vinilos.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
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
class AlbumFragmentTest {
    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun title_album_is_displayed() {
        onView(allOf(withId(R.id.albumTitle), isDisplayed()))
    }

    @Test
    fun genre_album_is_displayed() {
        onView(allOf(withId(R.id.albumGenre), isDisplayed()))
    }

    @Test
    fun year_album_is_displayed() {
        onView(allOf(withId(R.id.albumYear), isDisplayed()))
    }

    @Test
    fun cover_album_is_displayed() {
        onView(allOf(withId(R.id.albumCover), isCompletelyDisplayed()))
    }

    @Test
    fun button_musicians_is_enabled() {
        onView(withId(R.id.navButtonMusicians)).check(ViewAssertions.matches(isEnabled()))
    }

    @Test
    fun button_musicians_is_displayed() {
        onView(withId(R.id.navButtonMusicians)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun button_albums_is_not_displayed() {
        onView(withId(R.id.navButtonAlbums)).check(ViewAssertions.doesNotExist())
    }
}
