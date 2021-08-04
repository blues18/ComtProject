package com.example.uiproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class favourite_activity extends AppCompatActivity {
    RecyclerView favList;
    Songadapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_activity);
        favList = findViewById(R.id.recycleview);

        songAdapter =  new Songadapter(MainActivity.favlist);
        favList.setAdapter(songAdapter);
        favList.setLayoutManager(new LinearLayoutManager(this));
        }

    public void remove_all(View view) {
        MainActivity.favlist.clear();
        songAdapter.notifyDataSetChanged();
    }
}
