package com.example.uiproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Songcollection Songcollection = new Songcollection();

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

}