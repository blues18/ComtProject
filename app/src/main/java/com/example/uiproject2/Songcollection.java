package com.example.uiproject2;

public class Songcollection {
    private Songcode song[] = new Songcode[3];

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


        song[0] = first_song;
        song[1] = Second_song;
        song[2] = thrid_song;

    }

    public Songcode getCurrentSong(int currentSongId) {

        return song[currentSongId];

    }
    public int Search_Song_By_Id(String id) {

        for(int index=0; index < song.length; index++){
            Songcode tempSongs = song[index];
            if (tempSongs.getId().equals(id)) {
                return index;
            }
        }
        return  -1;
    }
    public int getNextSong(int currentSongIndex){
        if (currentSongIndex >= song.length-1){
            return currentSongIndex;
        }
        else{
            return currentSongIndex +1;
        }
    }public int getPrevSong (int currentSongIndex){
        if (currentSongIndex <= 0){
            return currentSongIndex;
        }else{
            return currentSongIndex-1;
        }
    }

}
