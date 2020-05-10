package com.bytedance.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    VideoView videoView = null;
    Button bnPlay = null;
    Button bnPause = null;
    RelativeLayout mRlVv;
    int width  =1080;
    int height = 1920;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri;
        Intent intent = getIntent();
        if(intent !=null){
            uri = intent.getData();
        }
        else
            uri = Uri.parse("https://vdept.bdstatic.com/4251626e77733251375a6268566d4c4c/43684241536c4d47/9ac8c97036d30e748a4aa1582004149d6f2a33d0f6ff3a31578964879aa89e0669d57425cb7ce709b12fcce988589c05.mp4?auth_key=1589130345-0-0-ffa8b60ad4e8a26650ac57044b4a913a");
        MediaController mC = new MediaController(this);
        mRlVv = findViewById(R.id.rL);
        videoView  =findViewById(R.id.videoView);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mC);
        bnPlay = findViewById(R.id.bnPlay);
        bnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });

        bnPause = findViewById(R.id.bnPause);
        bnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (videoView == null) {
            return;
        }
        if (this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            // 横屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().invalidate();
            mRlVv.getLayoutParams().height = (int) width;
            mRlVv.getLayoutParams().width = (int) height;
            bnPause.setVisibility(View.INVISIBLE);
            bnPlay.setVisibility(View.INVISIBLE);
        } else {
            // 竖屏
            final WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            mRlVv.getLayoutParams().height = (int) 280 * 1080 / 411;
            mRlVv.getLayoutParams().width = (int) 1080;
            bnPause.setVisibility(View.VISIBLE);
            bnPlay.setVisibility(View.VISIBLE);
        }
    }
}
