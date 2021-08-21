package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {
    Uri uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
    ImageButton btnNext,btnPrevious, btnPlay;
    TextView songLabel;
    SeekBar songseekbar;
    static MediaPlayer player;
    int position;
    String sname;

    ArrayList<AudioData> mySongs;
    Thread updateSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().setTitle("Now Playing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnNext= findViewById(R.id.Next);
        btnPrevious = findViewById(R.id.Previous);
        btnPlay = findViewById(R.id.Play);
        songLabel= findViewById(R.id.SongName);
        songseekbar= findViewById(R.id.Seekbar);

        updateSeekBar=new Thread(){
            @Override
            public void run() {
                int totalduration = player.getDuration();
                int currentPosition = 0;
                while(currentPosition<totalduration){
                    try{
                        sleep(500);
                        currentPosition=player.getCurrentPosition();
                        songseekbar.setProgress(currentPosition);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        if(player!=null){
            player.stop();;
            player.release();
        }
         Intent intent = this.getIntent();
         Bundle bundle = intent.getExtras();
//        assert bundle != null;
        mySongs = (ArrayList<AudioData>) bundle.getSerializable("Songs");

        sname = mySongs.get(position).getName();

        String songName = getIntent().getStringExtra("SongName");

        songLabel.setText(songName);
        songLabel.setSelected(true);

        position = getIntent().getIntExtra("pos", 0);

        player=MediaPlayer.create(getApplicationContext(),Uri.parse(mySongs.get(position).getPath()));


        if(player == null) {
//            Log.v(Tag, "Create() on MediaPlayer failed.");
        } else {
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mediaplayer) {
                    mediaplayer.stop();
                    mediaplayer.release();
                }
            });
            player.start();
        }


        songseekbar.setMax(player.getDuration());

        updateSeekBar.start();

        songseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songseekbar.setMax(player.getDuration());

                if(player.isPlaying()){
                    btnPlay.setBackgroundResource(R.drawable.play);
                    player.pause();
                }else{
                    btnPlay.setBackgroundResource(R.drawable.pause);
                    player.start();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                player.release();
                position = (position+1) % mySongs.size();

                Uri u = Uri.parse(mySongs.get(position).toString());
                player = MediaPlayer.create(getApplicationContext(), u);
                sname = mySongs.get(position).getName();
                songLabel.setText(sname);


                if(player == null) {
//            Log.v(Tag, "Create() on MediaPlayer failed.");
                } else {
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mediaplayer) {
                            mediaplayer.stop();
                            mediaplayer.release();
                        }
                    });
                    player.start();
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
                player.release();

                position = ((position-1) < 0) ? (mySongs.size()-1):position-1;

                Uri u = Uri.parse(mySongs.get(position).toString());
                player = MediaPlayer.create(getApplicationContext(), u);
                sname = mySongs.get(position).getName();
                songLabel.setText(sname);


                if(player == null) {
//            Log.v(Tag, "Create() on MediaPlayer failed.");
                } else {
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mediaplayer) {
                            mediaplayer.stop();
                            mediaplayer.release();
                        }
                    });
                    player.start();
                }
            }
        });
    }

}