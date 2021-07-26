package zaid_ali.example.ratar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ChatActivity extends AppCompatActivity {
    private FirebaseListAdapter<ChatMessage> adapter;
    ArrayList<ChatMessage> chats=new ArrayList<>();
    ChatMessage chat;
    LinearLayout layout;
    ScrollView scrollView;
    FirebaseAuth mAuth;
    String currentUserEmail;
    ImageView backButton;
    String currentUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        layout = (LinearLayout)findViewById(R.id.layout1);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        EditText inputText=(EditText)findViewById(R.id.messageArea);
        //TextView msg=findViewById(R.id.message_text);

        //TextView userName=findViewById(R.id.message_user);
        ImageView mpostFab  = findViewById(R.id.sendButton);
//        FloatingActionButton mpostFab=findViewById(R.id.fab);
        FirebaseDatabase mDatabase=FirebaseDatabase.getInstance();
        DatabaseReference mReference = mDatabase.getReference("chat");
        mAuth = FirebaseAuth.getInstance();
        currentUserEmail = mAuth.getCurrentUser().getEmail();
        currentUserName = currentUserEmail.substring(0, currentUserEmail.indexOf('@'));
//        Button postForumButton = HelpView.findViewById(R.id.postForumBtn);

        mpostFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView inputText = (TextView)findViewById(R.id.messageArea);
                String inputTextMessage = inputText.getText().toString();
                if (TextUtils.isEmpty(inputTextMessage)==false) {
                    ChatMessage post = new ChatMessage();
                    post.setMessageText(inputText.getText().toString());
                    post.setMessageUser(currentUserName);
                    inputText.setText("");
//                msg.setText(inputTextMessage);
//                if(areFieldsFilled(problemDescriptionField)){
//                    Toast.makeText(getActivity(),"Problem posted!", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), UserVideoActivity1.class);
//                    intent.putExtra("Channelcode",rand+"");
//                    startActivity(intent);
//                }

                    String key = mReference.push().getKey();
                    mReference.child(key).setValue(post);
                }


            }
        });

        chat=new ChatMessage();
//        mReference=mDatabase.getReference("chat");
        mReference.addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                chat=dataSnapshot.getValue(ChatMessage.class);
                chats.add(chat);

                addMessageBox(chat.getMessageUser()+"\n" + chat.getMessageText());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addMessageBox(String message){
        TextView textView = new TextView(ChatActivity.this);
        textView.setText(message);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 10);
        textView.setLayoutParams(lp);
        textView.setBackgroundResource(R.drawable.msg_shape1);

        textView.setTextColor(Color.WHITE);
        textView.setPadding(50,50,50,50);
//        Typeface typeface = getResources().getFont(R.font.montserrat);
//        textView.setTypeface(typeface);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20,10,10,10);
    //    textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        textView.setLayoutParams(params);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        Linkify.addLinks(textView, Linkify.WEB_URLS);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }

//        FloatingActionButton fab =
//                (FloatingActionButton)findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText input = (EditText)findViewById(R.id.input);
//
//                // Read the input field and push a new instance
//                // of ChatMessage to the Firebase database
//                FirebaseDatabase.getInstance()
//                        .getReference()
//                        .push()
//                        .setValue(new ChatMessage(input.getText().toString(),
//                                FirebaseAuth.getInstance()
//                                        .getCurrentUser()
//                                        .getDisplayName())
//                        );
//
//                // Clear the input
//                input.setText("");
//            }
//        });
//        displayChatMessages();
    }

//    public void displayChatMessages(){
//        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);
//        FirebaseDatabase mDatabase=FirebaseDatabase.getInstance();
//        DatabaseReference mReference = mDatabase.getReference("chats");
//        Query query = FirebaseDatabase.getInstance().getReference().child("chats");;
//        FirebaseListOptions<ChatMessage> options =
//                new FirebaseListOptions.Builder<ChatMessage>()
//                        .setQuery(query, ChatMessage.class)
//                        .setLayout(android.R.layout.simple_list_item_1)
//                        .build();
//        adapter = new FirebaseListAdapter<ChatMessage>(options){
//            @Override
//            protected void populateView(View v, ChatMessage model, int position) {
//                // Get references to the views of message.xml
//                TextView messageText = (TextView)v.findViewById(R.id.message_text);
//                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
//                TextView messageTime = (TextView)v.findViewById(R.id.message_time);
//
//                // Set their text
//                messageText.setText(model.getMessageText());
//                messageUser.setText(model.getMessageUser());
//
//                // Format the date before showing it
//                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
//                        model.getMessageTime()));
//            }
//        };
//
//        listOfMessages.setAdapter(adapter);
//    }
