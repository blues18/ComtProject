package com.example.uiproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlaySongActivity2<repeatBtn> extends AppCompatActivity {
    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private double songLength = 0.30;
    private int drawable;
    private int currentIndex = 1;

    private MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause = null;
    private Songcollection songcollection = new Songcollection();
    private Songcollection originalSongcollection = new Songcollection();

    List<Songcode> shuffleList= Arrays.asList(songcollection.songs);

    SeekBar seekBar;
    Handler handler= new Handler();
    Button RepeatBtn;
    boolean RepeatFlag = false;

    Button shuffle_btn;
    boolean shuffleFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song2);
        btnPlayPause = findViewById(R.id.Playbutton);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("Index");
        Log.d("temasek", "Retrieved position is: " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        //playSong(fileLink);
        seekBar = findViewById(R.id.seekBar);   //first implement
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(player != null && player.isPlaying()){
                    player.seekTo(seekBar.getProgress());
                }
            }
        });
        RepeatBtn = findViewById(R.id.RepeatSong);
        shuffle_btn = findViewById(R.id.shuffle_btn);
    }

    Runnable p_bar = new Runnable(){    //first impelment
        @Override
        public void run() {
            if(player != null && player.isPlaying()) {
                seekBar.setProgress(player.getCurrentPosition());
            }
            handler.postDelayed(this, 1000);
        }
    };

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
        if (player.isPlaying()) { player.pause();//if player is playing
            btnPlayPause.setText("PLAY");

        } else {player.start();
            playSong(fileLink);
            seekBar.setMax(player.getDuration());
            handler.removeCallbacks(p_bar);
            handler.postDelayed(p_bar, 1000);
            gracefullyStopsWhenMusicEnds();
            btnPlayPause.setText("Pause");
        }
    }

    private void gracefullyStopsWhenMusicEnds() {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getBaseContext(), "The Song has ended and the onCompletelistener is activated\n" +
                        "the button text is changed to play", Toast.LENGTH_LONG).show();
                if(RepeatFlag){
                    playOrPauseMusic(null);
                }else{
                    btnPlayPause.setText("Play");
                }
            }
        });
    }

    public void playNext(View view) {
        currentIndex = songcollection.getNextSong(currentIndex);
        Toast.makeText(this, "After clicking play next,\nthe current index of this song\n" +
                "in the Songcollection array is now;" + currentIndex, Toast.LENGTH_SHORT).show();
        Log.d("temasek", "After playNext, the index is now;" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void playPrevious(View view) {
        currentIndex = songcollection.getPrevSong(currentIndex);
        Toast.makeText(this, "After clicking playPrevious,\nthe current index of this song\n" +
                "in the SongCollection array is now: " + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "after playPrevious, the index is now" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(p_bar);
        player.release();
    }

    public void RepeatSong(View view) {  //second impelmented
        if (RepeatFlag) {
            RepeatBtn.setBackgroundResource(R.drawable.repeated_off);
        }else{
            RepeatBtn.setBackgroundResource(R.drawable.repeated_on);
        } RepeatFlag = !RepeatFlag;
    }

    public void ShuffleSong(View view) {  //third impelmented
        if (shuffleFlag) {
            shuffle_btn.setBackgroundResource(R.drawable.shuffle_off);
            songcollection = new Songcollection();
        }else{
            shuffle_btn.setBackgroundResource(R.drawable.shuffle_on);
            Collections.shuffle(shuffleList);
            shuffleList.toArray(songcollection.songs);
        } shuffleFlag = !shuffleFlag;
   }
}