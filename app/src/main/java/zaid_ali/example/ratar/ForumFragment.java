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

public class ForumFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ForumView = inflater.inflate(R.layout.fragment_forum,null);

        Button postForumButton = ForumView.findViewById(R.id.postForumBtn);
        postForumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView problemDescriptionField = ForumView.findViewById(R.id.problemDescriptionField);

                String problemDescription = problemDescriptionField.getText().toString();
                if(areFieldsFilled(problemDescriptionField)){
                    Intent intent = new Intent(getActivity(), UserVideoActivity1.class);
                    startActivity(intent);
                }

            }
        });

        return ForumView;
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
