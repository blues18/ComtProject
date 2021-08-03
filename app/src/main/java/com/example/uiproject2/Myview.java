package com.example.uiproject2;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Myview extends RecyclerView.ViewHolder{

    public TextView txtsong;
    public TextView txtartist;
    public ImageView cover;
    public Button removed;

    public Myview(@NonNull View itemView) {
        super(itemView);

        txtsong = itemView.findViewById(R.id.txtsong);
        txtartist = itemView.findViewById(R.id.txtartist);
        cover = itemView.findViewById(R.id.cover);
        removed = itemView.findViewById(R.id.removed);

    }
}
