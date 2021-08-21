package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Playlist extends AppCompatActivity {
        RecyclerView favList;
        MusicListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        favList = findViewById(R.id.rv);
        //adapter = new MusicListAdapter(MainActivity.favList);
    }
}
