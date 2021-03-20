package zaid_ali.example.ratar;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;

public  class UserVideoActivity extends AppCompatActivity {

    private boolean isMuted = false;
    private ImageView muteBtn;
    private boolean isCalling = true;
    private boolean installRequested;
    private Snackbar mMessageSnackbar;

    private Session mSession;
//    private final ObjectRenderer mVirtualObject = new ObjectRenderer();
//    private final ObjectRenderer mVirtualObjectShadow = new ObjectRenderer();
//    private final PlaneRenderer mPlaneRenderer = new PlaneRenderer();
//    private final PointCloudRenderer mPointCloud = new PointCloudRenderer();
//    private final BackgroundRenderer mBackgroundRenderer = new BackgroundRenderer();
//    private PeerRenderer mPeerObject = new PeerRenderer();
    private GLSurfaceView mSurfaceView;

//    private DisplayRotationHelper mDisplayRotationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_video);

        muteBtn = findViewById(R.id.btn_mute);
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (mSession == null) {
            Exception exception = null;
            String message = null;
            try {
                switch (ArCoreApk.getInstance().requestInstall(this, !installRequested)) {
                    case INSTALL_REQUESTED:
                        installRequested = true;
                        return;
                    case INSTALLED:
                        break;
                }

                // ARCore requires camera permissions to operate. If we did not yet obtain runtime
                // permission on Android M and above, now is a good time to ask the user for it.
                if (!CameraPermissionHelper.hasCameraPermission(this)) {
                    CameraPermissionHelper.requestCameraPermission(this);
                    return;
                }

                mSession = new Session(/* context= */ this);
            } catch (UnavailableArcoreNotInstalledException
                    | UnavailableUserDeclinedInstallationException e) {
                message = "Please install ARCore";
                exception = e;
            } catch (UnavailableApkTooOldException e) {
                message = "Please update ARCore";
                exception = e;
            } catch (UnavailableSdkTooOldException e) {
                message = "Please update this app";
                exception = e;
            } catch (Exception e) {
                message = "This device does not support AR";
                exception = e;
            }

            if (message != null) {
                showSnackbarMessage(message, true);
//                Log.e(TAG, "Exception creating session", exception);
                return;
            }

            // Create default config and check if supported.
            Config config = new Config(mSession);
            if (!mSession.isSupported(config)) {
                showSnackbarMessage("This device does not support AR", true);
            }
            mSession.configure(config);
        }

        showLoadingMessage();
        // Note that order matters - see the note in onPause(), the reverse applies here.
//        mSession.resume();
//        mSurfaceView.onResume();
//        mDisplayRotationHelper.onResume();
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        // Note that the order matters - GLSurfaceView is paused first so that it does not try
//        // to query the session. If Session is paused before GLSurfaceView, GLSurfaceView may
//        // still call mSession.update() and get a SessionPausedException.
////        mDisplayRotationHelper.onPause();
//        mSurfaceView.onPause();
//        if (mSession != null) {
//            mSession.pause();
//        }
//    }

//    @Override
//    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
////        GLES20.glClearColor(0.1f,0.1f,0.1f,1.0f);
////
////        // Create the texture and pass it to ARCore session to be filled during update().
////        mBackgroundRenderer.createOnGlThread(/*context=*/ this);
////        if (mSession != null) {
////            mSession.setCameraTextureName(mBackgroundRenderer.getTextureId());
////        }
////
////        // Prepare the other rendering objects.
////        try {
////            mVirtualObject.createOnGlThread(/*context=*/this, "andy.obj", "andy.png");
////            mVirtualObject.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
////
////            mVirtualObjectShadow.createOnGlThread(/*context=*/this,
////                    "andy_shadow.obj", "andy_shadow.png");
////            mVirtualObjectShadow.setBlendMode(ObjectRenderer.BlendMode.Shadow);
////            mVirtualObjectShadow.setMaterialProperties(1.0f, 0.0f, 0.0f, 1.0f);
////        } catch (IOException e) {
////            Log.e(TAG, "Failed to read obj file");
////        }
////        try {
////            mPlaneRenderer.createOnGlThread(/*context=*/this, "trigrid.png");
////        } catch (IOException e) {
////            Log.e(TAG, "Failed to read plane texture");
////        }
////        mPointCloud.createOnGlThread(/*context=*/this);
////
////        try {
////            mPeerObject.createOnGlThread(this);
////        } catch (IOException ex) {
////        }
//    }

    private void showSnackbarMessage(String message, boolean finishOnDismiss) {
        mMessageSnackbar = Snackbar.make(
                UserVideoActivity.this.findViewById(android.R.id.content),
                message, Snackbar.LENGTH_INDEFINITE);
        mMessageSnackbar.getView().setBackgroundColor(0xbf323232);
        if (finishOnDismiss) {
            mMessageSnackbar.setAction(
                    "Dismiss",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMessageSnackbar.dismiss();
                        }
                    });
            mMessageSnackbar.addCallback(
                    new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                        @Override
                        public void onDismissed(Snackbar transientBottomBar, int event) {
                            super.onDismissed(transientBottomBar, event);
                            finish();
                        }
                    });
        }
        mMessageSnackbar.show();
    }

    private void showLoadingMessage() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showSnackbarMessage("Searching for surfaces...", false);
            }
        });
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


//    @Override
//    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
//
//    }
//
//    @Override
//    public void onDrawFrame(GL10 gl10) {
//
//    }
}