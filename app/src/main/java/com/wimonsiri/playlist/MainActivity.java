package com.wimonsiri.playlist;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements OnCheckedChangeListener, OnClickListener
{
    private int resId = R.raw.music01;
    private MediaPlayer mPlayer;
    private boolean isPause = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroupList);
        group.setOnCheckedChangeListener(this);
        RadioButton music01 = (RadioButton) findViewById(R.id.radio0);
        music01.setChecked(true);
        Button playBtn = (Button) findViewById(R.id.playBtn);
        playBtn.setOnClickListener(this);
        Button stopBtn = (Button) findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(this);
        Button pauseBtn = (Button) findViewById(R.id.puaseBtn);
        pauseBtn.setOnClickListener(this);
        mPlayer = MediaPlayer.create(this, resId);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        switch(checkedId)
        {
            case R.id.radio0 : resId = R.raw.music01; break;
            case R.id.radio1 : resId = R.raw.music02; break;
            case R.id.radio2 : resId = R.raw.music03; break;
            case R.id.radio3 : resId = R.raw.music04; break;
        }
        if ((mPlayer != null) && (mPlayer.isPlaying())) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
        mPlayer = MediaPlayer.create(this, resId);
        isPause = false;
    }
    // when click Button
    public void onClick(View v)
    {
        if (v.getId() == R.id.playBtn ) {
            if (mPlayer == null) {
                mPlayer = MediaPlayer.create(this, resId);
                mPlayer.start();
            }
            else {
                if (!mPlayer.isPlaying()) {
                    if (isPause) {
                        mPlayer.start();
                    }
                    else {

                        mPlayer = MediaPlayer.create(this, resId);
                        mPlayer.start();
                    }
                }
            }
        }
        else if (v.getId() == R.id.stopBtn ) {
            if ((mPlayer != null) && (mPlayer.isPlaying())) {
                mPlayer.stop();
                mPlayer.release();
                mPlayer = null;
                isPause = false;
            }
        }
        else if (v.getId() == R.id.puaseBtn ) {
            if (mPlayer.isPlaying()) {
                mPlayer.pause();
                isPause = true;
            }
        }
    }
}