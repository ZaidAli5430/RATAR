package zaid_ali.example.ratar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import zaid_ali.example.ratar.ForumDialog;

public class ForumFragment extends Fragment {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    ArrayList<Posts> posts;
    ListView listView;
    PostAdapter adapter;
    Posts post;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ForumView = inflater.inflate(R.layout.fragment_forum,null);
        ListView listView = ForumView.findViewById(R.id.list_view);
        mDatabase=FirebaseDatabase.getInstance();
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Posts p = posts.get(position);
//                Toast.makeText(getActivity(), p.getTitle(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
        //get forum posts from firebase
        mReference=mDatabase.getReference("message");
        posts=new ArrayList<>();
        adapter=new PostAdapter(getContext(),R.layout.problem,posts);
        post=new Posts();


        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    post=ds.getValue(Posts.class);
                    posts.add(post);
                }
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//
//        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1,
//                                       int position, long arg3) {
//                Posts p = posts.get(position);
//                // TODO Auto-generated method stub
//                Toast.makeText(getContext(), "hallo",
//                       Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//                // TODO Auto-generated method stub
//
//            }
//        });

        ForumDialog alert = new ForumDialog();
        alert.showDialog(getActivity(), "Welcome to Helper's Forum. You can copy the channel code and join the channel from the homepage to help the user.");
        return ForumView;


    }


//    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        List<String> keys=new ArrayList<>();
//        for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
//            keys.add(keyNode.getKey());
//            Posts post = keyNode.getValue(Posts.class);
//            posts.add(post);
//        }
//
//    }
//    public void openForum(){
//        Intent intent=new Intent(getActivity(),PostListActivity.class);
//        startActivity(intent);
//    }

}


//public class ForumFragment extends Fragment {
//
//
//    @Nullable
//    @Override
//
