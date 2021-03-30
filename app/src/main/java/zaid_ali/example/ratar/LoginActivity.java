package zaid_ali.example.ratar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Firebase auth instance to be created here
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        TextView loginBtn = findViewById(R.id.loginBtn); // Login button

        // Listener for Login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Runs when the login button is clicked

                TextView emailField = findViewById(R.id.emailField);
                TextView passField = findViewById(R.id.passField);

                String email = emailField.getText().toString().trim();
                String password = passField.getText().toString().trim();

                if (areFieldsFilled(emailField, passField)) { // if all the fields are filled
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(LoginActivity.this, "Login Success!.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent); // Go to main page
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
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

    boolean areFieldsFilled(TextView emailField, TextView passField) { // checks if all the fields are filled and valid
        Boolean filledFields = true; // if all fields are filled

        if (TextUtils.isEmpty(emailField.getText().toString())) { // if email field is empty
            emailField.setError("Email Field can't be empty"); //
            filledFields = false;
        } else if (!isEmailValid(emailField.getText().toString())) { // if email is invalid
            emailField.setError("Invalid Email format");
            filledFields = false;
        }

        if (TextUtils.isEmpty(passField.getText().toString())) { // if pass field is empty
            passField.setError("Pass Field can't be empty"); //
            filledFields = false;
        }


        return filledFields;
    }
}