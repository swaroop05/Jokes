package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.isEmptyString;

/**
 * Created by meets on 12/19/2018.
 */

@RunWith(AndroidJUnit4.class)
public class JokesEndpointsAsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickAndValidateJokeIsDisplayed() throws Exception {
        onView(withId(R.id.tell_joke_btn)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.jokedisplay_tv)).check(matches(withText(not(isEmptyString()))));
    }
}
