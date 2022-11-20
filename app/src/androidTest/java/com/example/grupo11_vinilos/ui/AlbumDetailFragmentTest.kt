package com.example.grupo11_vinilos.ui


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.ui.adapters.AlbumsAdapter
import com.example.grupo11_vinilos.utils.ScreenUtils
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumDetailFragmentTest {
    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule<MainActivity>(
        MainActivity::class.java
    )

    @Test
    fun album_detail_fragment_is_displayed() {
        Thread.sleep(2000)
        onView(withId(R.id.albumsRv)).perform(RecyclerViewActions.actionOnItemAtPosition<AlbumsAdapter.AlbumViewHolder>(0, click()))
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
        onView(withId(R.id.albumsRv)).perform(RecyclerViewActions.actionOnItemAtPosition<AlbumsAdapter.AlbumViewHolder>(0, click()))
        Thread.sleep(2000)
        onView(withId(R.id.tracksTitleCardView)).check(matches(isDisplayed()))
        onView(withId(R.id.tracksCardView)).check(matches(isDisplayed()))
        Thread.sleep(500)
    }

    @Test
    fun album_detail_fragment_comment_list_is_displayed() {
        Thread.sleep(2000)
        onView(withId(R.id.albumsRv)).perform(RecyclerViewActions.actionOnItemAtPosition<AlbumsAdapter.AlbumViewHolder>(0, click()))
        Thread.sleep(2000)
        ScreenUtils.swiper(775, 100, 100);
        Thread.sleep(2000)
        onView(withId(R.id.commentsTitleCardView)).check(matches(isDisplayed()))
        onView(withId(R.id.commentsCardView)).check(matches(isDisplayed()))
        Thread.sleep(500)
    }

    @Test
    fun album_detail_fragment_track_list_is_not_displayed() {
        Thread.sleep(2000)
        onView(withId(R.id.albumsRv)).perform(RecyclerViewActions.actionOnItemAtPosition<AlbumsAdapter.AlbumViewHolder>(1, click()))
        Thread.sleep(2000)
        onView(withId(R.id.tracksTitleCardView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tracksCardView)).check(matches(not(isDisplayed())))
        Thread.sleep(500)
    }

    @Test
    fun album_detail_fragment_comment_list_is_not_displayed() {
        Thread.sleep(2000)
        onView(withId(R.id.albumsRv)).perform(RecyclerViewActions.actionOnItemAtPosition<AlbumsAdapter.AlbumViewHolder>(1, click()))
        Thread.sleep(2000)
        onView(withId(R.id.commentsTitleCardView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.commentsCardView)).check(matches(not(isDisplayed())))
        Thread.sleep(500)
    }
}