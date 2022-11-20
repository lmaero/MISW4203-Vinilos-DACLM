package com.example.grupo11_vinilos.ui


import android.app.Instrumentation
import android.os.SystemClock
import android.view.MotionEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.ui.adapters.AlbumsAdapter
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
        swiper(775, 100, 100);
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

    fun swiper(start: Int, end: Int, delay: Int) {
        val downTime = SystemClock.uptimeMillis()
        var eventTime = SystemClock.uptimeMillis()
        val inst: Instrumentation = getInstrumentation()
        var event = MotionEvent.obtain(
            downTime,
            eventTime,
            MotionEvent.ACTION_DOWN,
            500f,
            start.toFloat(),
            0
        )
        inst.sendPointerSync(event)
        eventTime = SystemClock.uptimeMillis() + delay
        event =
            MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, 500f, end.toFloat(), 0)
        inst.sendPointerSync(event)
        event =
            MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 500f, end.toFloat(), 0)
        inst.sendPointerSync(event)
        SystemClock.sleep(2000) //The wait is important to scroll
    }
}