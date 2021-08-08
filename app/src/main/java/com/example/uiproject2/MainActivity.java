package com.example.uiproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Songcollection Songcollection = new Songcollection();
    static ArrayList<Songcode> favlist = new ArrayList<Songcode>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void sendDataToActivity(int index){
        Intent intent = new Intent(this, PlaySongActivity2.class);
        intent.putExtra("Index", index);
        startActivity(intent);
    }
    public void handleSelection(View myView){
        String resourceId = getResources().getResourceEntryName(myView.getId());
        int currentArrayIndex =Songcollection.Search_Song_By_Id(resourceId);
        Log.d("temasek", "the id of the pressed ImageButton is :" + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);

    }
    public void Add_to_favourite(View view) {

        String songID = view.getContentDescription().toString();
        Songcode songcode = Songcollection.SearchById(songID);
        favlist.add(songcode);
        //Toast.makeText(this, "button isentered", Toast.LENGTH_SHORT).show();
    }

    public void gotoFavouriteActivity(View view) {
        Intent intent = new Intent(this, favourite_activity.class);
        startActivity(intent);

    }
}