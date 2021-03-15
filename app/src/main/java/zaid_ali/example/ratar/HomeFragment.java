package zaid_ali.example.ratar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View HomeView = inflater.inflate(R.layout.fragment_home,null);



        Button joinChannelButton = HomeView.findViewById(R.id.joinChannelBtn);
        Button createChannelButton = HomeView.findViewById(R.id.createChannelBtn);

        joinChannelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView joinCodeField = HomeView.findViewById(R.id.joinCodeField);

                String joinCode = joinCodeField.getText().toString();

                if(areFieldsFilled(joinCodeField)){
                    Intent intent = new Intent(getActivity(), VideoCallActivity.class);
                    startActivity(intent);
                }

            }
        });

        createChannelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VideoCallActivity.class);
                startActivity(intent);
            }
        });

        return HomeView;
    }


    boolean areFieldsFilled(TextView joinCodeField) { // checks if all the fields are filled and valid
        Boolean filledFields = true; // if all fields are filled

        if (TextUtils.isEmpty(joinCodeField.getText().toString())) { // if email field is empty
            joinCodeField.setError("Joining code can't be empty"); //
            filledFields = false;
        }


        return filledFields;
    }
}
