package zaid_ali.example.ratar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Firebase auth instance to be created here

        Button signUpBtn = findViewById(R.id.signUpBtn); // sign up button

        // Listener for Login button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView username = findViewById(R.id.userField);
                TextView emailField = findViewById(R.id.emailField);
                TextView passField = findViewById(R.id.passField);

                if(areFieldsFilled(username, emailField,passField)){ // if all the fields are filled
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                }


            }
        });

        // Goes to login page, if already registered
        TextView loginPage = findViewById(R.id.signUpLink);
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignupActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    boolean isEmailValid(String email) {   // checks if email is of invalid format
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    boolean areFieldsFilled(TextView userField, TextView emailField, TextView passField){ // checks if all the fields are filled and valid
        Boolean filledFields = true; // if all fields are filled

        if(TextUtils.isEmpty(userField.getText().toString())){
            userField.setError("username field can't be empty");
            filledFields = false;
        }

        if(TextUtils.isEmpty(emailField.getText().toString())){ // if email field is empty
            emailField.setError("Email Field can't be empty"); //
            filledFields = false;
        }

        else if(!isEmailValid(emailField.getText().toString())){ // if email is invalid
            emailField.setError("Invalid Email format");
            filledFields = false;
        }

        if(TextUtils.isEmpty(passField.getText().toString())){ // if email field is empty
            passField.setError("Pass Field can't be empty"); //
            filledFields = false;
        }
        return filledFields;
    }



}