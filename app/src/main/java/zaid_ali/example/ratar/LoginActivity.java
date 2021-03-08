package zaid_ali.example.ratar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Firebase auth instance to be created here

        TextView login = findViewById(R.id.signUpBtn); // Login button

        // Listener for Login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Runs when the login button is clicked

                TextView emailField = findViewById(R.id.emailField);
                TextView passField = findViewById(R.id.passField);

                if(areFieldsFilled(emailField,passField)){ // if all the fields are filled
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent); // Go to main page
                }
            }
        });


        TextView forgotPass = findViewById(R.id.resetLink); // forgot password link
        // Redirects to reset page if user forgot password
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ResetActivity.class);
                startActivity(intent);
            }
        });

        TextView goToSignUp = findViewById(R.id.signUpLink); // sign up page link
        // Redirects to sign up page if user is not registered
        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


    }

    boolean isEmailValid(String email) {   // checks if email is of invalid format
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    boolean areFieldsFilled(TextView emailField, TextView passField){ // checks if all the fields are filled and valid
        Boolean filledFields = true; // if all fields are filled

        if(TextUtils.isEmpty(emailField.getText().toString())){ // if email field is empty
            emailField.setError("Email Field can't be empty"); //
            filledFields = false;
        }

        else if(!isEmailValid(emailField.getText().toString())){ // if email is invalid
            emailField.setError("Invalid Email format");
            filledFields = false;
        }

        if(TextUtils.isEmpty(passField.getText().toString())){ // if pass field is empty
            passField.setError("Pass Field can't be empty"); //
            filledFields = false;
        }


        return filledFields;
    }
}