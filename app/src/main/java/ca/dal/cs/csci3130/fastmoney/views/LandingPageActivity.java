package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ca.dal.cs.csci3130.fastmoney.R;

public class LandingPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
    }

    public void redirectToWorkPage(View view) {
        Intent redirect = new Intent(this, WorkActivity.class);
        startActivity(redirect);
    }

    public void redirectToLogInPage(View view) {
        Intent redirect = new Intent(this, LogInActivity.class);
        startActivity(redirect);
    }

    public void redirectToFindJobPage(View view) {
        Intent redirect = new Intent(this, FindJobActivity.class);
        startActivity(redirect);
    }

    public void redirectToProfilePage(View view) {
        Intent redirect = new Intent(this, ProfileActivity.class);
        startActivity(redirect);
    }

    public void redirectToJobPage(View view) {
        Intent redirect = new Intent(this, JobActivity.class);
        startActivity(redirect);
    }

    public void redirectToPostJobPage(View view) {
        Intent redirect = new Intent(this, PostJobActivity.class);
        startActivity(redirect);
    }
}
