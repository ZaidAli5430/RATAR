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

public class HelpFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View HelpView = inflater.inflate(R.layout.fragment_help,null);

        Button postForumButton = HelpView.findViewById(R.id.postForumBtn);
        postForumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView problemDescriptionField = HelpView.findViewById(R.id.problemDescriptionField);

                String problemDescription = problemDescriptionField.getText().toString();
                if(areFieldsFilled(problemDescriptionField)){
                    Intent intent = new Intent(getActivity(), UserVideoActivity1.class);
                    startActivity(intent);
                }

            }
        });

        return HelpView;
    }


    boolean areFieldsFilled(TextView problemDescriptionField) { // checks if all the fields are filled and valid
        Boolean filledFields = true; // if all fields are filled

        if (TextUtils.isEmpty(problemDescriptionField.getText().toString())) { // if email field is empty
            problemDescriptionField.setError("Please enter a problem description"); //
            filledFields = false;
        }
        return filledFields;
    }
}
