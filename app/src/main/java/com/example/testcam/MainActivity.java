package com.example.testcam;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {
    //possible motec cam links
    private String stream_url = "rtsp://192.168.0.75:8554/MCDE3000";
    //private String stream_url = "192.168.0.75:1001/stream.mjpg";

    //Working RTSP link
    //private String stream_url = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mp4";

    private SurfaceHolder sh;
    private SurfaceView sfv;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sfv = (SurfaceView)findViewById(R.id.big_screen);
        player = new MediaPlayer();
        try {
            player.setDataSource(this, Uri.parse(stream_url));
            sh =sfv.getHolder();
            sh.addCallback(new MyCallBack());
            //player.release();
            player.prepare();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                    player.setLooping(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            player.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
}