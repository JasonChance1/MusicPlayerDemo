package com.example.musicplayerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.example.musicplayerdemo.databinding.ActivityPlayBinding;
import com.example.musicplayerdemo.service.MusicService;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    private ActivityPlayBinding binding;
    private ObjectAnimator rotateAnimator;
    private AnimatorSet animatorSet;
    private ArrayList<Song> songList;
    private int position;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            binder.updateList(songList);
            binder.setPosition(position);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();


    }

    private void initData(){
        rotateAnimator = ObjectAnimator.ofFloat(binding.cover, View.ROTATION,0f,360f);
        animatorSet = new AnimatorSet();

        songList = (ArrayList<Song>) getIntent().getSerializableExtra("songList");
        position = getIntent().getIntExtra("position",0);
    }

    private void rotateCover(){
        rotateAnimator.setDuration(5000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        animatorSet.play(rotateAnimator);
    }

    private void startMusicService(){
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);
    }
}