package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import android.app.Activity;


import com.google.firebase.auth.FirebaseAuth;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.LandingPageActivity;
import ca.dal.cs.csci3130.fastmoney.views.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

import android.content.Context;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LandingPage {

    @Rule
    public IntentsTestRule<LandingPageActivity> myRule = new IntentsTestRule<>(LandingPageActivity.class);

    @Test
    public void showsYourProfileButton() {
        onView(withText("YOUR PROFILE")).check(matches(isDisplayed()));
    }

    @Test
    public void showsYourWorkButton() {
        onView(withText("YOUR WORK")).check(matches(isDisplayed()));
    }

    @Test
    public void showsYourHiringButton() {
        onView(withText("YOUR HIRING")).check(matches(isDisplayed()));
    }

    @Test
    public void postAJobButton() {
        onView(withText("POST A JOB")).check(matches(isDisplayed()));
    }

    @Test
    public void showsFindAJobButton() {
        onView(withText("FIND A JOB")).check(matches(isDisplayed()));
    }

    @Test
    public void showsSignOutLink() {
        onView(withSubstring("Sign out")).check(matches(isDisplayed()));
    }

    @Test
    public void signOutLinkHasCorrectName() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withSubstring("Sign out")).check(matches(withText(containsString("Not Jane? Sign out."))));
    }

    @Test
    public void showsWelcomeHeader() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("Welcome, Jane")).check(matches(isDisplayed()));
    }

    @Test
    public void welcomeHeaderHasCorrectName() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.welcomeHeader)).check(matches(withText(containsString("Welcome, Jane"))));
    }
}