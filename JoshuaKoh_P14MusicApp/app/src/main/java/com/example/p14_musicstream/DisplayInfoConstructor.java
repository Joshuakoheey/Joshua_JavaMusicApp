package com.example.p14_musicstream;

public class DisplayInfoConstructor
{
    private String title;
    private String artist;
    private String drawable; //cover image of song
    private String id;

    public DisplayInfoConstructor(String Display_id, String Display_title, String Display_artist, String Display_drawable)
    {
        this.id = Display_id;
        this.title = Display_title;
        this.artist = Display_artist;
        this.drawable = Display_drawable;
    }

    // makes its values usable for other classes

    public String toString()
    {
        return title;
    }

    public String getId()
    {
        return id;
    }
    public void setID(String Display_id)
    {
        this.id = Display_id;
    }

    public String getTitle()
    {
        return title;
    }
    public void setTitle(String Display_title)
    {
        this.title = Display_title;
    }

    public String getArtist()
    {
        return artist;
    }
    public void setArtist(String Display_artist)
    {
        this.artist = Display_artist;
    }

    public String getDrawable()
    {
        return drawable;
    }
    public void setDrawable(String Display_drawable)
    {
        this.drawable = Display_drawable;
    }
}
