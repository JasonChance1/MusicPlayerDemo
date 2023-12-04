package com.example.musicplayerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.musicplayerdemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Song> songList;
    private SongAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();

        adapter = new SongAdapter(songList,this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SongAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this,PlayActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("songList",songList);
                startActivity(intent);
            }
        });
    }

    private void initData(){
        songList = new ArrayList<>();
        songList.add(new Song("1",R.raw.msc1));
        songList.add(new Song("2",R.raw.msc2));
        songList.add(new Song("1",R.raw.msc1));
        songList.add(new Song("2",R.raw.msc2));
    }
}