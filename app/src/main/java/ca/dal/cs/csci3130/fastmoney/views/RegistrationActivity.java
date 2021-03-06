package ca.dal.cs.csci3130.fastmoney.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import ca.dal.cs.csci3130.fastmoney.R;
import ca.dal.cs.csci3130.fastmoney.models.User;

public class RegistrationActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button registerBtn;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        registerBtn = findViewById(R.id.registerRGBtn);
        loginBtn = findViewById(R.id.loginRGBtn);
        final TextInputEditText emailField = this.findViewById(R.id.email_field);
        final TextInputEditText passwordField = this.findViewById(R.id.password_field);
        final TextInputEditText firstNameField = this.findViewById(R.id.first_name_field);
        final TextInputEditText lastNameField = this.findViewById(R.id.last_name_field);

        //if user is already logged in
        //if (fAuth.getCurrentUser() != null) {
        //    startActivity(new Intent(getApplicationContext(), LandingPageActivity.class));
        //    finish();
        //}

        /**
         * Creates a user from the information given in the registration form.
         */
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailField.getText().toString();
                final String password = passwordField.getText().toString();
                final String firstName = firstNameField.getText().toString();
                final String lastName = lastNameField.getText().toString();
                final double employerRating = 0;
                final int employerRatingCount=0;
                final int employeeRatingCount=0;
                final double employeeRating=0;

                if (!User.isValidName(firstName)) {
                    ((LinearLayout)findViewById(R.id.registration_firstNameError)).setVisibility(View.VISIBLE);
                }

                if (!User.isValidName(lastName)) {
                    ((LinearLayout)findViewById(R.id.registration_lastNameError)).setVisibility(View.VISIBLE);
                }

                if (!User.isValidEmail(email)) {
                    ((LinearLayout)findViewById(R.id.registration_emailError)).setVisibility(View.VISIBLE);
                }

                if (password.length() < 1) {
                    ((LinearLayout)findViewById(R.id.registration_passwordError)).setVisibility(View.VISIBLE);
                }

                //Registration with firebase authentication and store data to firebase firestone

                if (!firstName.equals("") && !lastName.equals("")) {
                    fAuth.createUserWithEmailAndPassword(email, password).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String userID = fAuth.getCurrentUser().getUid();
                                        DocumentReference documentReference = fStore.collection("users").document(userID);
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("email", email);
                                        user.put("password", password);
                                        user.put("firstName", firstName);
                                        user.put("lastName", lastName);
                                        user.put("employerRating",employerRating);
                                        user.put("employerRatingCount",employerRatingCount);
                                        user.put("employeeRating",employeeRating);
                                        user.put("employeeRatingCount",employeeRatingCount);

                                        /** Store extra User data to firestone once successful registration to firebase authentication
                                         * */

                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("", "user have been registered");
                                                Toast.makeText(RegistrationActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), LandingPageActivity.class));
                                            }

                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(RegistrationActivity.this, "Error" + e, Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        /* Jump to new Activity*/
                                    } else {
                                        Toast.makeText(RegistrationActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(RegistrationActivity.this, "Error - First name or Last name can not be empty", Toast.LENGTH_SHORT).show();
                }
            }


        });


        /* Login button to the login Page*/
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
            }
        });
    }
}
