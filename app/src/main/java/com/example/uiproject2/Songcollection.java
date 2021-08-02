package com.example.uiproject2;

import java.util.List;

public class Songcollection {
    public Songcode songs[] = new Songcode[4];
    //private Category_Swap Category[] = new Category_Swap[6];

    public Songcollection() {

        Songcode first_song = new Songcode("S1001",
                "come to light",
                "Jeff Williams",
                "https://p.scdn.co/mp3-preview/a2f1808b814516d0778d1bf41bcd542fa6379135?cid=2afe87a64b0042dabf51f37318616965",
                0.30,
                R.drawable.billie_jean_daniela);
        Songcode Second_song = new Songcode("S1002",
                "Top japanese version",
                "Stray kids",
                "https://p.scdn.co/mp3-preview/d570f1ee586944205307317d5a6d330e221637dd?cid=2afe87a64b0042dabf51f37318616965",
                3.13,
                R.drawable.home);
        Songcode thrid_song = new Songcode("S1003",
                "Bts Butter",
                "Bts",
                "https://p.scdn.co/mp3-preview/edf24f427483d886b640c5ed9944f9291e0976fc?cid=2afe87a64b0042dabf51f37318616965",
                0.30,
                R.drawable.roar);
        Songcode forth_song = new Songcode("S1004",
                "Boombayah",
                "Blackpink",
                "https://p.scdn.co/mp3-preview/e99786dd73e91ef36ec7ff551352a63d5af0e80f?cid=2afe87a64b0042dabf51f37318616965",
                0.30,
                R.drawable.blackpink);


        songs[0] = first_song;
        songs[1] = Second_song;
        songs[2] = thrid_song;
        songs[3] = forth_song;

        //Category_Swap first_Category = new Category_Swap("Category1","Jpop");
        //Category_Swap Second_Category = new Category_Swap("Category2","Kpop");
        //Category_Swap Thrid_Category = new Category_Swap("Category3","Rock");
        //Category_Swap Forth_Category = new Category_Swap("Category4","Chinese");


    }


    public Songcode getCurrentSong(int currentSongId) {

        return songs[currentSongId];

    }

    public int Search_Song_By_Id(String id) {

        for (int index = 0; index < songs.length; index++) {
            Songcode tempSongs = songs[index];
            if (tempSongs.getId().equals(id)) {
                return index;
            }
        }
        return -1;
    }

    public int getNextSong(int currentSongIndex) {
        if (currentSongIndex >= songs.length - 1) {
            return currentSongIndex;
        } else {
            return currentSongIndex + 1;
        }
    }

    public int getPrevSong(int currentSongIndex) {
        if (currentSongIndex <= 0) {
            return currentSongIndex;
        } else {
            return currentSongIndex - 1;
        }
    }
}