package zaid_ali.example.ratar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Firebase auth instance to be created here
        mAuth = FirebaseAuth.getInstance();

        Button signUpBtn = findViewById(R.id.loginBtn); // sign up button

        // Listener for Login button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView usernameField = findViewById(R.id.userField);
                TextView emailField = findViewById(R.id.emailField);
                TextView passField = findViewById(R.id.passField);

                String email = emailField.getText().toString().trim();
                String password = passField.getText().toString().trim();
                String username = usernameField.getText().toString().trim();

                if (areFieldsFilled(usernameField, emailField, passField)) { // if all the fields are filled


                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(SignupActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                        startActivity(intent); // Go to main page

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(SignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });

                }
            }
        });

        // Goes to login page, if already registered
        TextView loginPage = findViewById(R.id.signUpLink);
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    boolean isEmailValid(String email) {   // checks if email is of invalid format
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    boolean areFieldsFilled(TextView userField, TextView emailField, TextView passField) { // checks if all the fields are filled and valid
        Boolean filledFields = true; // if all fields are filled

        if (TextUtils.isEmpty(userField.getText().toString())) { // if username field is empty
            userField.setError("username field can't be empty");
            filledFields = false;
        }

        if (TextUtils.isEmpty(emailField.getText().toString())) { // if email field is empty
            emailField.setError("Email Field can't be empty"); //
            filledFields = false;
        } else if (!isEmailValid(emailField.getText().toString())) { // if email is invalid
            emailField.setError("Invalid Email format");
            filledFields = false;
        }

        if (TextUtils.isEmpty(passField.getText().toString())) { // if email field is empty
            passField.setError("Pass Field can't be empty"); //
            filledFields = false;
        }
        return filledFields;
    }


}