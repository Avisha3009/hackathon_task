package com.example.musicplayer;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MusicListAdapter.RecyclerViewClickListener listener;


    Uri uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
    private Button btn;
    ArrayList<AudioData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = loadAudio(this);

        RecyclerView recyclerView = findViewById(R.id.rv);
        btn = findViewById(R.id.Button_playlist);

        setOnClickListener();
        MusicListAdapter adapter = new MusicListAdapter(list,listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator() );
        recyclerView.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Playlist.class);
                startActivity(intent);

            }
        });

    }



     private void setOnClickListener(){
           listener = new MusicListAdapter.RecyclerViewClickListener() {
               @Override
               public void onClick(View v, int position) {

                   Toast.makeText(MainActivity.this, "U Clicked" + list.get(position).getName()+position, Toast.LENGTH_SHORT).show();


                    Intent intent =new Intent(MainActivity.this,PlayerActivity.class);
                   Bundle bundle = new Bundle();
                   bundle.putSerializable("Songs",list);
                   intent.putExtras(bundle);
                   String songname = list.get(position).getName();
                    intent.putExtra("SongName",songname).putExtra("pos",position);
                   startActivity(intent );

               }
           };
    }


    private ArrayList<AudioData> loadAudio(Context context) {
        ArrayList<AudioData> mlist = new ArrayList<>();


        String[] projections = {
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.ARTIST,
                MediaStore.Audio.AudioColumns.DURATION,
                MediaStore.Audio.AudioColumns.ALBUM

        };
        Cursor cursor = context.getContentResolver().query(uri, projections, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                mlist.add(new AudioData(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4)
                ));
            }
            cursor.close();
        }
        return mlist;
    }

//  public void addToFavourite(){
//      Toast.makeText(this, "add to fav clicked", Toast.LENGTH_SHORT).show();
//
//
//  }


}