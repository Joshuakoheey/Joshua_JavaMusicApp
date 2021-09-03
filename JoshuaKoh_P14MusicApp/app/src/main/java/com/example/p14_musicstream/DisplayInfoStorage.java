package com.example.p14_musicstream;

import java.util.ArrayList;

public class DisplayInfoStorage
{
    // To Display on searchsongactivity

    ArrayList<DisplayInfoConstructor> displaydisplay = new ArrayList<>();

    public DisplayInfoStorage()
    {
        DisplayInfoConstructor thewayyoulooktonight = new DisplayInfoConstructor (
                "0",
                "The Way You Look Tonight",
                "Michael Buble",
                "drawable://" + R.drawable.michael_buble_collection);
        DisplayInfoConstructor photograph = new DisplayInfoConstructor (
                "1",
                "Photograph",
                "Ed Sheeran",
                "drawable://" + R.drawable.photograph);
        DisplayInfoConstructor smoothCriminal = new DisplayInfoConstructor (
                "2",
                "Smooth Criminal",
                "Micheal Jackson",
                "drawable://" + R.drawable.billie_jean);
        DisplayInfoConstructor murmaider = new DisplayInfoConstructor (
                "3",
                "Murmaider",
                "Metalocalypse: Dethklok",
                "drawable://" + R.drawable.cover_murmaider);
        DisplayInfoConstructor thunderhorse = new DisplayInfoConstructor (
                "4",
                "Thunderhorse",
                "Metalocalypse: Dethklok",
                "drawable://" + R.drawable.cover_thunderhorse);
        DisplayInfoConstructor thelostvikings = new DisplayInfoConstructor (
                "5",
                "The Lost Vikings",
                "Metalocalypse: Dethklok",
                "drawable://" + R.drawable.cover_thelostvikings);
        DisplayInfoConstructor hatredcopter = new DisplayInfoConstructor (
                "6",
                "Hatredcopter",
                "Metalocalypse: Dethklok",
                "drawable://" + R.drawable.cover_hatredcopter);
        DisplayInfoConstructor castratikron = new DisplayInfoConstructor (
                "7",
                "Castratikron",
                "Metalocalypse: Dethklok",
                "drawable://" + R.drawable.cover_castratikron);
        DisplayInfoConstructor facefisted = new DisplayInfoConstructor (
                "8",
                "Face Fisted",
                "Metalocalypse: Dethklok",
                "drawable://" + R.drawable.cover_facefisted);
        DisplayInfoConstructor bloodrocuted = new DisplayInfoConstructor (
                "9",
                "Bloodrocuted",
                "Metalocalypse: Dethklok",
                "drawable://" + R.drawable.cover_bloodrocuted);

        displaydisplay.add(thewayyoulooktonight);
        displaydisplay.add(photograph);
        displaydisplay.add(smoothCriminal);
        displaydisplay.add(murmaider);
        displaydisplay.add(thunderhorse);
        displaydisplay.add(thelostvikings);
        displaydisplay.add(hatredcopter);
        displaydisplay.add(castratikron);
        displaydisplay.add(facefisted);
        displaydisplay.add(bloodrocuted);
    }

    public int searchbyid(String id)
    {
        for(int index = 0; index < displaydisplay.size(); index++)
        {
            DisplayInfoConstructor displaytempsong = displaydisplay.get(index);
            int b1 = Integer.parseInt(displaytempsong.getId());
            int b2 = Integer.parseInt(id);
            if(b1 == b2)
            {
                return index;
            }
        }
        return -1;
    }

    // method to retrieve the song based on index num
    public DisplayInfoConstructor getCurrentsong(int index)
    {
        return displaydisplay.get(index);
    }

    //method to retrieve index num of next song
    public int getnextsong (int displaycurrentsongindex)
    {
        if(displaycurrentsongindex >= displaydisplay.size() -1) //if the current song is the last song
        {
            return displaycurrentsongindex;
        }
        else
        {
            return displaycurrentsongindex +1; //go to the index num of the next song
        }
    }

    //method to retrieve the index num of the previous song
    public int getprevsong (int displaycurrentsongindex)
    {
        if(displaycurrentsongindex <= 0) //if the current song is the first song
        {
            return displaycurrentsongindex;
        }
        else
        {
            return displaycurrentsongindex-1; //go to the index num of the prev song
        }
    }
}
