package zaid_ali.example.ratar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VideoCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);

        getChannelCode();

        Button endCallButton = findViewById(R.id.endCallBtn);
        endCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VideoCallActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        View decorView = getWindow().getDecorView();
//        // Hide both the navigation bar and the status bar.
//        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
//        // a general rule, you should design your app to hide the status bar whenever you
//        // hide the navigation bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);




    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void getChannelCode(){ // gets the code entered in the join Code field in Home Fragment Activity
        TextView channelId = findViewById(R.id.channelid);
        Intent intent = getIntent();
        String joinCode = intent.getStringExtra("joinCode");
        channelId.setText(joinCode);
    }
}