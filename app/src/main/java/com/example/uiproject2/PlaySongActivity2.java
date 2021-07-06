package com.example.uiproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class PlaySongActivity2 extends AppCompatActivity {
    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private double songLength = 0.30;
    private int drawable;
    private int currentIndex = 1;

    private MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause = null;
    private Songcollection songcollection = new Songcollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song2);
        btnPlayPause = findViewById(R.id.Playbutton);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("Index");
        Log.d("temasek", "Retrieved position is: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void displaySongBasedOnIndex(int selectedIndex) {
        Songcode song = songcollection.getCurrentSong(selectedIndex);
        title = song.getTitles();
        artiste = song.getArtiste();
        fileLink = song.getFileLink();
        songLength = song.getSongLength();
        drawable = song.getDrawable();
        TextView txtTitle = findViewById(R.id.txtsongtitles);
        txtTitle.setText(title);
        TextView txtArtiste = findViewById(R.id.txtartist);
        txtArtiste.setText(artiste);
        ImageView iCoverArt = findViewById(R.id.txtcoverimage);
        iCoverArt.setImageResource(drawable);


    }

    public void playSong(String songUrl) {
        try {
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            gracefullyStopsWhenMusicEnds();

            btnPlayPause.setText("PAUSE");
            setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playOrPauseMusic(View view) {
        if (player.isPlaying()) {//if player is playing
            player.pause();
            btnPlayPause.setText("PLAY");
        } else {

            player.start();
            btnPlayPause.setText("Pause");
        }
    }
    private void gracefullyStopsWhenMusicEnds() {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getBaseContext(), "The Song has ended and the onCompletelistener is activated\n" +
                        "the button text is changed to play", Toast.LENGTH_LONG).show();
                btnPlayPause.setText("Play");
                }
            });
    }
    public void playNext (View view) {
        currentIndex = songcollection.getNextSong(currentIndex);
        Toast.makeText(this, "After clicking play next,\nthe current index of this song\n" +
                "in the Songcollection array is now;" + currentIndex, Toast.LENGTH_SHORT).show();
        Log.d("temasek","After playNext, the index is now;" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }
    public void playPrevious(View view) {
        currentIndex = songcollection.getPrevSong(currentIndex);
        Toast.makeText(this, "After clicking playPrevious,\nthe current index of this song\n" +
                "in the SongCollection array is now: " + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "after playPrevious, the index is now"+ currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        player.release();
    }

}