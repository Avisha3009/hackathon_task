package com.example.musicplayer;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class AudioData implements Serializable {
    private String name;
    private  String path;
    private String artist;
    private int duration;
    private String albums;

    public AudioData(String name, String path, String artist, int duration, String albums) {
        this.name = name;
        this.path = path;
        this.artist = artist;
        this.duration = duration;
        this.albums = albums;
    }
    private AudioData(Parcel in){
        name = in.readString();
        path=in.readString();
        artist= in.readString();
        albums=in.readString();
        duration= in.readInt();
    }
//
//    public static final Creator<AudioData> CREATOR = new Creator<AudioData>() {
//        @Override
//        public AudioData createFromParcel(Parcel in) {
//            return new AudioData(in);
//        }

//        @Override
//        public AudioData[] newArray(int size) {
//            return new AudioData[size];
//        }
//    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbums() {
        return albums;
    }

    public void setAlbums(String albums) {
        this.albums = albums;
    }

//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        dest.writeString(path);
//        dest.writeString(artist);
//        dest.writeString(albums);
//        dest.writeInt(duration);
//    }
}
