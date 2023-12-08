package com.asser.learningapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

// import androidx.test.espresso.Espresso.onView;

import com.asser.learningapp.ui.MainActivity;

@RunWith(AndroidJUnit4.class)
public class AppNavigationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void navigateThroughApp() {
        // Assuming MainActivity is the entry point of your app
        // Click on the FloatingActionButton to navigate to a new screen

        // onView(ViewMatchers.withId(R.id.fabAddSubject)).perform(ViewActions.click());

        // Add other navigation actions here,
        // such as opening a detail screen and verifying UI elements

        // Use Espresso to find views and perform actions or assertions
    }
}
