package zaid_ali.example.ratar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HelperVideoActivity extends AppCompatActivity {

    private boolean isMuted = false;
    private ImageView muteBtn;
    private boolean isCalling = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_video);

        muteBtn = findViewById(R.id.helper_btn_mute);
    }


    public void onCallClicked(View view) { // when the end call button is clicked
        if (isCalling) {
            isCalling = false;
//            removeRemoteRender();
//            mSenderHandler.getLooper().quit();
//            mRtcEngine.leaveChannel();
            Intent intent = new Intent(HelperVideoActivity.this, MainActivity.class); // returns to home fragment on main activity
            Toast.makeText(this, "Left Channel", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }else {
            isCalling = true;
//            initRtcEngine();
        }
    }


    public void onSwitchCameraClicked(View view) {
        return;
    }


    public void onLocalAudioMuteClicked(View view) {
        isMuted = !isMuted;
//        mRtcEngine.muteLocalAudioStream(isMuted);
        muteBtn.setImageResource(isMuted ? R.drawable.mute_button_icon : R.drawable.unmute_button_icon);
    }
}