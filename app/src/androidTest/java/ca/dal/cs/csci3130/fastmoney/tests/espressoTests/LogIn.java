package ca.dal.cs.csci3130.fastmoney.tests.espressoTests;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.views.LogInActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LogIn {

    @Before
    public void setUp() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
    }

    @Rule
    public IntentsTestRule<LogInActivity> myRule = new IntentsTestRule<>(LogInActivity.class);

    //ensure elements properly showing
    @Test
    public void titleShows(){
        onView(withText("Fast Money")).check(matches(isDisplayed()));
    }

    @Test
    public void emailShows(){
        onView(withHint("Email")).check(matches(isDisplayed()));
    }

    @Test
    public void passwordShows(){
        onView(withHint("Password")).check(matches(isDisplayed()));
    }

    @Test
    public void showSignInButton(){
        onView(withId(R.id.signInButton)).check(matches(isDisplayed()));
    }

    public void showRegistrationButton(){
        onView(withId(R.id.regButton)).check(matches(isDisplayed()));
    }

    //ensure proper error handling for empty sign in attempts
    @Test
    public void emptySignInAttempt(){
        onView(withId(R.id.EmailAddress)).perform(typeText(""));
        onView(withId(R.id.Password)).perform(typeText(""));
        onView(withId(R.id.signInButton)).perform(click());
        onView(withText("Empty email or password")).check(matches(isDisplayed()));
    }

    //ensure proper handling of invalid sign in attempts
    @Test
    public void invalidUserSignIn(){
        onView(withId(R.id.EmailAddress)).perform(replaceText("this-is-not-a-valid-email"), closeSoftKeyboard());
        onView(withId(R.id.Password)).perform(replaceText("~!@#$%^&*"), closeSoftKeyboard());
        onView(withText("Sign In")).perform(closeSoftKeyboard(), click());
        onView(withText("Invalid email or password")).check(matches(isDisplayed()));
    }

}
