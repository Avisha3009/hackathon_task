package com.example.musicplayer;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MyViewHolder> {
    private ArrayList<AudioData> list;

    private RecyclerViewClickListener listener;

    public MusicListAdapter(ArrayList<AudioData> list,RecyclerViewClickListener listener) {
        this.list = list;
        this.listener=listener;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.music_list_layout,
                parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MusicListAdapter.MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getName());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RecyclerViewClickListener {
        void onClick (View v, int position);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv;
        ImageButton imgbutton;
        public ConstraintLayout constraintLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }
}
