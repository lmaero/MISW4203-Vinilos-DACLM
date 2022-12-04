package com.example.grupo11_vinilos.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.ui.adapters.AlbumsAdapter
import com.example.grupo11_vinilos.utils.ScreenUtils
import io.github.serpro69.kfaker.Faker
import junit.framework.Assert.assertEquals
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumDetailFragmentTest {
    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun album_detail_fragment_is_displayed() {
        Thread.sleep(2000)
        onView(withId(R.id.albumsRv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AlbumsAdapter.AlbumViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(2000)
        onView(withId(R.id.albumDetailCover)).check(matches(isDisplayed()))
        onView(withId(R.id.albumDetailName)).check(matches(isDisplayed()))
        onView(withId(R.id.albumDetailPerformer)).check(matches(isDisplayed()))
        onView(withId(R.id.albumDetailGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.albumDetailReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.albumDetailDescription)).check(matches(isDisplayed()))
        Thread.sleep(500)
    }

    @Test
    fun album_detail_fragment_track_list_is_displayed() {
        Thread.sleep(2000)
        onView(withId(R.id.albumsRv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AlbumsAdapter.AlbumViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(2000)
        onView(withId(R.id.tracksTitleCardView)).check(matches(isDisplayed()))
        onView(withId(R.id.tracksCardView)).check(matches(isDisplayed()))
        Thread.sleep(500)
    }

    @Test
    fun album_detail_fragment_comment_list_is_displayed() {
        Thread.sleep(2000)
        onView(withId(R.id.albumsRv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AlbumsAdapter.AlbumViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(2000)
        ScreenUtils.swiper(775, 100, 100)
        Thread.sleep(2000)
        onView(withId(R.id.commentsTitleCardView)).check(matches(isDisplayed()))
        onView(withId(R.id.commentsCardView)).check(matches(isDisplayed()))
        Thread.sleep(500)
    }

    @Test
    fun album_detail_fragment_create_new_comment() {
        // Init faker bank data and select data to add new comment
        val faker = Faker()
        val newCommentBody = faker.gameOfThrones.quotes()
        // Select a album with few comments
        val albumToSelect: Int = (2..3).random()

        Thread.sleep(2000)
        // Select the album to add a new comment
        onView(withId(R.id.albumsRv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AlbumsAdapter.AlbumViewHolder>(
                albumToSelect,
                click()
            )
        )
        Thread.sleep(500)
        // Get te initial number of comments
        var initCommentsCount = -1
        activityRule.scenario.onActivity { activityScenarioRule ->
            val recyclerView = activityScenarioRule.findViewById<RecyclerView>(R.id.comments)
            initCommentsCount = recyclerView.adapter?.itemCount!!
        }
        Thread.sleep(2000)
        // Scroll to the new comment layout
        onView(withId(R.id.newCommentLayout)).perform(scrollTo())
        Thread.sleep(500)
        // Rating the comment with the maximum value
        onView(withId(R.id.ratingValue5)).perform(click())
        Thread.sleep(500)
        // Add new comment with the faker data
        onView(withId(R.id.commentEditText)).perform(typeText(newCommentBody), closeSoftKeyboard())
        Thread.sleep(500)
        // Click on the add comment button
        onView(withId(R.id.createNewCommentButton)).perform(click())
        Thread.sleep(1000)
        // Get the new number of comments
        var finalCommentsCount = -1
        activityRule.scenario.onActivity { activityScenarioRule ->
            val recyclerView = activityScenarioRule.findViewById<RecyclerView>(R.id.comments)
            finalCommentsCount = recyclerView.adapter?.itemCount!!
        }
        // Check if the new number of comments is the initial number of comments plus one
        assertEquals(finalCommentsCount, initCommentsCount + 1)
    }

    @Test
    fun album_detail_fragment_track_list_is_not_displayed() {
        Thread.sleep(2000)
        onView(withId(R.id.albumsRv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AlbumsAdapter.AlbumViewHolder>(
                1,
                click()
            )
        )
        Thread.sleep(2000)
        onView(withId(R.id.tracksTitleCardView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tracksCardView)).check(matches(not(isDisplayed())))
        Thread.sleep(500)
    }

    @Test
    fun album_detail_fragment_comment_list_is_not_displayed() {
        Thread.sleep(2000)
        onView(withId(R.id.albumsRv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AlbumsAdapter.AlbumViewHolder>(
                1,
                click()
            )
        )
        Thread.sleep(2000)
        onView(withId(R.id.commentsTitleCardView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.commentsCardView)).check(matches(not(isDisplayed())))
        Thread.sleep(500)
    }
}