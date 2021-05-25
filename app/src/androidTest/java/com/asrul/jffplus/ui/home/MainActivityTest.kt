package com.asrul.jffplus.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingRegistry
import com.asrul.jffplus.R
import com.asrul.jffplus.core.utils.EspressoIdlingResource

class MainActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_about)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.text_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.text_release)).check(matches(isDisplayed()))
        onView(withId(R.id.text_vcount)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_about)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.text_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.text_release)).check(matches(isDisplayed()))
        onView(withId(R.id.text_vcount)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).check(matches(isDisplayed()))
    }

    @Test
    fun addOrRemoveFavoriteMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).perform(click())
        pressBack()

        onView(withId(R.id.bottom_nav)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_nav)).perform(click())

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).perform(click())
    }

    @Test
    fun addOrRemoveFavoriteTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).perform(click())
        pressBack()

        onView(withId(R.id.bottom_nav)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_nav)).perform(click())

        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).perform(click())
    }
}