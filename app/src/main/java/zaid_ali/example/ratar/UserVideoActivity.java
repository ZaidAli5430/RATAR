package zaid_ali.example.ratar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public  class UserVideoActivity extends AppCompatActivity implements GLSurfaceView.Renderer {

    private boolean isMuted = false;
    private ImageView muteBtn;
    private boolean isCalling = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_video);

        muteBtn = findViewById(R.id.btn_mute);


    }


    public void onCallClicked(View view) {
        if (isCalling) {
            isCalling = false;
//            removeRemoteRender();
//            mSenderHandler.getLooper().quit();
//            mRtcEngine.leaveChannel();
            Intent intent = new Intent(UserVideoActivity.this, MainActivity.class);

            Toast.makeText(this, "Left Channel", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }else {
            isCalling = true;
//            initRtcEngine();
        }
    }


    public void onUserAudioMuteClicked(View view) {
        isMuted = !isMuted;
//        mRtcEngine.muteLocalAudioStream(isMuted);
        muteBtn.setImageResource(isMuted ? R.drawable.mute_button_icon : R.drawable.unmute_button_icon);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

    }

    @Override
    public void onDrawFrame(GL10 gl10) {

    }
}