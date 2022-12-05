package com.example.grupo11_vinilos.ui

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.ui.adapters.AlbumsAdapter
import io.github.serpro69.kfaker.Faker
import org.hamcrest.Matchers.*
import org.junit.Assert.assertEquals
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
        onView(withId(R.id.commentsCardView)).perform(scrollTo())
        Thread.sleep(500)
        onView(withId(R.id.comments)).check(matches(isDisplayed()))
    }

    @Test
    fun album_detail_fragment_create_new_comment() {
        // Init faker bank data and select data to add new comment
        val faker = Faker()
        val newCommentBody = faker.gameOfThrones.quotes()
        // Select a album with few comments
        val albumToSelect: Int = (0..3).random()

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
    fun album_fragment_create_new_album() {

        // Get te initial number of albums
        Thread.sleep(2000)
        var initAlbumsCount = -1
        activityRule.scenario.onActivity { activityScenarioRule ->
            val recyclerView = activityScenarioRule.findViewById<RecyclerView>(R.id.albumsRv)
            initAlbumsCount = recyclerView.adapter?.itemCount!!
            Log.d("InitialCount", initAlbumsCount.toString())
        }

        Thread.sleep(2000)
        // Press the add button album
        onView(withId(R.id.addAlbumButton)).perform(ViewActions.click())
        Thread.sleep(500)

        // Add album name
        onView(withId(R.id.newAlbumName)).perform(ViewActions.typeText("Miss Monique"), ViewActions.closeSoftKeyboard())
        Thread.sleep(500)

        // Add album cover
        onView(withId(R.id.newAlbumCover)).perform(ViewActions.typeText("https://media.istockphoto.com/id/1175435360/es/vector/icono-de-nota-musical-ilustraci%C3%B3n-vectorial.jpg?s=1024x1024&w=is&k=20&c=X4o4H-Q8tntcdvKkwnVeB5uho9EJxLHrk4JdXYqJI7E="), ViewActions.closeSoftKeyboard())
        Thread.sleep(500)

        // Add album release date
        onView(withId(R.id.newAlbumReleaseDate)).perform(ViewActions.typeText("2022-12-02"), ViewActions.closeSoftKeyboard())
        Thread.sleep(500)

        // Add album genre
        onView(withId(R.id.newAlbumGenre)).perform(ViewActions.typeText("Salsa"), ViewActions.closeSoftKeyboard())
        Thread.sleep(500)

        // Add album record
        onView(withId(R.id.newAlbumRecordLabel)).perform(ViewActions.typeText("Elektra"), ViewActions.closeSoftKeyboard())
        Thread.sleep(500)

        // Add album description
        onView(withId(R.id.newAlbumDescription)).perform(ViewActions.typeText("Este es un nuevo algun que acaba de ser lanzado"), ViewActions.closeSoftKeyboard())
        Thread.sleep(500)

        // Add album description
        onView(withId(R.id.buttonSaveAlbumInformation)).perform(ViewActions.click())
        Thread.sleep(2000)
    }
}