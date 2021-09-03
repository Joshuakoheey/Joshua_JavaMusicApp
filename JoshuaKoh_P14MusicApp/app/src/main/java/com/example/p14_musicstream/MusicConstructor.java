package com.example.p14_musicstream;

public class MusicConstructor
{
    private String ID;
    private String title;
    private String artist;
    private String link;
    private double length; //duration
    private int drawable; //cover image of song

    public MusicConstructor(String Player_id, String Player_title, String Player_artist, String Player_link, double Player_length, int Player_drawable)
    {
        this.ID = Player_id;
        this.title = Player_title;
        this.artist = Player_artist;
        this.link = Player_link;
        this.length = Player_length;
        this.drawable = Player_drawable;
    }

    // makes its values usable for other classes

    public String getID()
    {
        return this.ID;
    }
    public String getTitle()
    {
        return this.title;
    }
    public String getArtist()
    {
        return this.artist;
    }
    public String getLink()
    {
        return this.link;
    }
    public double getLength()
    {
        return this.length;
    }
    public int getDrawable()
    {
        return this.drawable;
    }

    //Allows us to assign and set values to object if we need to without directly accessing a private attribute
    public void setID(String Player_id)
    {
        this.ID = Player_id;
    }
    public void setTitle(String Player_title)
    {
        this.title = Player_title;
    }
    public void setArtist(String Player_artist)
    {
        this.artist = Player_artist;
    }
    public void setLink(String Player_link)
    {
        this.link = Player_link;
    }
    public void setLength(Double Player_length)
    {
        this.length = Player_length;
    }
    public void setDrawable(int Player_drawable)
    {
        this.drawable = Player_drawable;
    }
}
