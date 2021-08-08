package com.example.uiproject2;

public class Songcode {
    public String id;
    public String titles;
    private String artiste;
    private String fileLink;
    private double songLength;
    private int drawable;

    public Songcode(String id, String titles,String artiste,String fileLink,double songLength, int drawable) {
        this.id = id;
        this.titles = titles;
        this.artiste = artiste;
        this.fileLink = fileLink;
        this.songLength = songLength;
        this.drawable = drawable;
    }

    public String getId(){ return id;}
    public String getTitles() {return titles;}
    public String getArtiste() {return artiste;}
    public String getFileLink() {return fileLink;}
    public double getSongLength() {return songLength;}
    public int getDrawable() {return drawable;}


}

