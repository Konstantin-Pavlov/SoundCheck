package com.example.android.soundcheck;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    MediaPlayer mPlayerMinor;
    Button startButton, pauseButton, stopButton, startButtonMinor, pauseButtonMinor, stopButtonMinor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer=MediaPlayer.create(this, R.raw.major_sound);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });


        mPlayerMinor=MediaPlayer.create(this, R.raw.minor_sound);
        mPlayerMinor.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });

        startButton = (Button) findViewById(R.id.start_major);
        pauseButton = (Button) findViewById(R.id.pause_major);
        stopButton = (Button) findViewById(R.id.stop_major);

        startButtonMinor = (Button) findViewById(R.id.start_minor);
        pauseButtonMinor = (Button) findViewById(R.id.pause_minor);
        stopButtonMinor = (Button) findViewById(R.id.stop_minor);



        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);

        pauseButtonMinor.setEnabled(false);
        stopButtonMinor.setEnabled(false);
    }
    private void stopPlay(){
        mPlayer.stop();
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
            startButton.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void play_major(View view){

        mPlayer.start();
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
    }
    public void pause_major(View view){

        mPlayer.pause();
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);
    }
    public void stop_major(View view){
        stopPlay();
    }



    private void stopPlayMinor(){
        mPlayerMinor.stop();
        pauseButtonMinor.setEnabled(false);
        stopButtonMinor.setEnabled(false);
        try {
            mPlayerMinor.prepare();
            mPlayerMinor.seekTo(0);
            startButtonMinor.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void play_minor(View view){

        mPlayerMinor.start();
        startButtonMinor.setEnabled(false);
        pauseButtonMinor.setEnabled(true);
        stopButtonMinor.setEnabled(true);
    }
    public void pause_minor(View view){

        mPlayerMinor.pause();
        startButtonMinor.setEnabled(true);
        pauseButtonMinor.setEnabled(false);
        stopButtonMinor.setEnabled(true);
    }
    public void stop_minor(View view){
        stopPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            stopPlay();
        }
        if (mPlayerMinor.isPlaying()) {
            stopPlay();
        }
    }


}
