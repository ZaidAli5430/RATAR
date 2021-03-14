package zaid_ali.example.ratar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button signOutBtn = findViewById(R.id.signOutBtn);
//        signOutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(mAuth.getCurrentUser()!=null){
//                    mAuth.signOut();
//                    Toast.makeText(MainActivity.this, "Logged out successfully",
//                            Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });

        BottomNavigationView bottomNavigator = findViewById(R.id.bottomNav);
        bottomNavigator.setOnNavigationItemSelectedListener(MainActivity.this);

        loadFragment(new HomeFragment());
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer,fragment) // Replace the frame layout (in main activity) with fragment
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.home_nav:
                fragment = new HomeFragment();
                break;
            case R.id.forum_nav:
                fragment = new ForumFragment();
                break;
            case R.id.profile_nav:
                fragment = new ProfileFragment();
                break;

        }
        return loadFragment(fragment);
    }
}