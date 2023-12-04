package com.example.musicplayerdemo.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.musicplayerdemo.Song;

import java.util.ArrayList;

/**
 * @author yanglei
 * @version 1.0.0
 * @description:
 * @date :2023/12/4
 */
public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    private ArrayList<Song> songList;
    private int position;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        songList = new ArrayList<>();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void updateList(ArrayList<Song> songList){
        this.songList = songList;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public class MusicBinder extends Binder {
        private MusicService service;

        public MusicBinder(MusicService service){
            this.service = service;
        }
        public void play(){

        }

        public void updateList(ArrayList<Song> songList){
            service.updateList(songList);
        }

        public void setPosition(int position){
            service.setPosition(position);
        }
    }

}
