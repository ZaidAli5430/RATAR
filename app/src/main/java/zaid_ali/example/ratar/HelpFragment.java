package zaid_ali.example.ratar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

//public class HelpFragment extends Fragment {
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View HelpView = inflater.inflate(R.layout.fragment_help,null);
//
//        EditText mTitle=HelpView.findViewById(R.id.titleField);
//        EditText mDescription=HelpView.findViewById(R.id.problemDescriptionField);
//        Button mpostBtn=HelpView.findViewById(R.id.postForumBtn);
//
////        Button postForumButton = HelpView.findViewById(R.id.postForumBtn);
//        mpostBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TextView problemDescriptionField = HelpView.findViewById(R.id.problemDescriptionField);
//
//                String problemDescription = problemDescriptionField.getText().toString();
//                Posts post=new Posts();
//                post.setTitle(mTitle.getText().toString());
//                post.setBody(mDescription.getText().toString());
//                new FirebaseDatabaseHelper().addPosts(post, new FirebaseDatabaseHelper.DataStatus() {
//                    @Override
//                    public void DataIsLoaded(List<Posts> posts, List<String> keys) {
//
//                    }
//
//                    @Override
//                    public void DataIsInserted() {
////                        Toast.makeText(NewPostActivity.this, "Problem Posted", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void DataIsUpdated() {
//
//                    }
//
//                    @Override
//                    public void DataIsDeleted() {
//
//                    }
//                });
//                if(areFieldsFilled(problemDescriptionField)){
//                    Toast.makeText(getActivity(),"Problem posted!", Toast.LENGTH_SHORT).show();
////                    Intent intent = new Intent(getActivity(), UserVideoActivity1.class);
////                    startActivity(intent);
//                }
//
//            }
//        });
//
//        return HelpView;
//    }
//
//
//    boolean areFieldsFilled(TextView problemDescriptionField) { // checks if all the fields are filled and valid
//        Boolean filledFields = true; // if all fields are filled
//
//        if (TextUtils.isEmpty(problemDescriptionField.getText().toString())) { // if email field is empty
//            problemDescriptionField.setError("Please enter a problem description"); //
//            filledFields = false;
//        }
//        return filledFields;
//    }
//}
public class HelpFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View HelpView = inflater.inflate(R.layout.fragment_help,null);

        EditText mTitle=HelpView.findViewById(R.id.titleField);
        EditText mDescription=HelpView.findViewById(R.id.problemDescriptionField);
        Button mpostBtn=HelpView.findViewById(R.id.postForumBtn);
        FirebaseDatabase mDatabase=FirebaseDatabase.getInstance();
        DatabaseReference mReference = mDatabase.getReference("message");
//        Button postForumButton = HelpView.findViewById(R.id.postForumBtn);
        mpostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView problemDescriptionField = HelpView.findViewById(R.id.problemDescriptionField);
                TextView problemTitleField = HelpView.findViewById(R.id.titleField);
                String problemDescription = problemDescriptionField.getText().toString();
                Posts post=new Posts();
                post.setTitle(mTitle.getText().toString());
                post.setBody(mDescription.getText().toString());
                Random rn = new Random();
                int rand = rn.nextInt(999) + 111;
                post.setid(rand);
                if(areFieldsFilled(problemDescriptionField, problemTitleField)){
                    Toast.makeText(getActivity(),"Problem posted!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), UserVideoActivity1.class);
                    intent.putExtra("Channelcode",rand+"");
                    startActivity(intent);
                }

                String key=mReference.push().getKey();
                mReference.child(key).setValue(post);

            }
        });

        return HelpView;
    }


    boolean areFieldsFilled(TextView problemDescriptionField, TextView problemTitleField) { // checks if all the fields are filled and valid
        Boolean filledFields = true; // if all fields are filled
        if (TextUtils.isEmpty(problemTitleField.getText().toString())) { // if email field is empty
            problemTitleField.setError("Please enter a problem title"); //
            filledFields = false;
        }
        else if (TextUtils.isEmpty(problemDescriptionField.getText().toString())) { // if email field is empty
            problemDescriptionField.setError("Please enter a problem description"); //
            filledFields = false;
        }
        return filledFields;
    }
}
