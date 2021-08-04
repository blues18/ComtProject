package com.example.uiproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Songadapter extends RecyclerView.Adapter<Myview> {

    public Songadapter(List<Songcode> songcodes) {
        this.songcodes = songcodes;
    }
    List<Songcode> songcodes;
    Context context;
    @NonNull
    @Override
    public Myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.item_song,parent,false);
        Myview viewHolder = new Myview(songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myview holder, int position) {
        Songcode songcode =songcodes.get(position);
        TextView txtartist = holder.txtartist;
        txtartist.setText(songcode.getArtiste());
        TextView txtsong = holder.txtsong;
        txtsong.setText(songcode.getTitles());
        int imageId = songcode.getDrawable();
        holder.cover.setImageResource(imageId);
        holder.removed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.favlist.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return songcodes.size();
    }
}
