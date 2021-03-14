package zaid_ali.example.ratar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View profileView = inflater.inflate(R.layout.fragment_profile,null);

        Button logOutButton = profileView.findViewById(R.id.logOutBtn);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser()!=null){
                    mAuth.signOut();
                    Toast.makeText(getActivity(), "Logged out successfully",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });


        return profileView;
    }
}
