package com.example.p14_musicstream;

import android.widget.ArrayAdapter;

import java.util.ArrayList;



public class MusicStorage
{
    public MusicConstructor[] playersongs = new MusicConstructor[10];

    public MusicStorage()
    {
        MusicConstructor thewayyoulooktonight = new MusicConstructor(
                "0",
                "The Way You Look Tonight",
                "Michael Buble",
                "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",
                4.66, R.drawable.michael_buble_collection);
        MusicConstructor photograph = new MusicConstructor(
                "1",
                "Photograph",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/097c7b735ceb410943cbd507a6e1dfda272fd8a8?cid=2afe87a64b0042dabf51f37318616965",
                4.32, R.drawable.photograph);
        MusicConstructor smoothcriminal = new MusicConstructor(
                "2",
                "Smooth Criminal",
                "Micheal Jackson",
                "https://p.scdn.co/mp3-preview/8dcbe2702477ac98c7c711cbafcac43e10063949?cid=2afe87a64b0042dabf51f37318616965",
                4.30, R.drawable.billie_jean);
        MusicConstructor murmaider = new MusicConstructor(
                "3",
                "Murmaider",
                "Metalocalypse: Dethklok",
                "https://p.scdn.co/mp3-preview/b789a7c89b21a6835e1467838bcbab42d28f3df3?cid=2afe87a64b0042dabf51f37318616965",
                3.41, R.drawable.cover_murmaider);
        MusicConstructor thunderhorse = new MusicConstructor(
                "4",
                "Thunderhorse",
                "Metalocalypse: Dethklok",
                "https://p.scdn.co/mp3-preview/5569c832cc14d92bc1dcfb25aef627b753af66d9?cid=2afe87a64b0042dabf51f37318616965",
                2.76, R.drawable.cover_thunderhorse);
        MusicConstructor thelostvikings = new MusicConstructor(
                "5",
                "The Lost Vikings",
                "Metalocalypse: Dethklok",
                "https://p.scdn.co/mp3-preview/c5e2acca5191b55f0f84e77befa5682ff8cee71f?cid=2afe87a64b0042dabf51f37318616965",
                4.44, R.drawable.cover_thelostvikings);
        MusicConstructor hatredcopter = new MusicConstructor(
                "6",
                "Hatredcopter",
                "Metalocalypse: Dethklok",
                "https://p.scdn.co/mp3-preview/dd8b5eb9199f0dbec52c6b6b51a77946865e5675?cid=2afe87a64b0042dabf51f37318616965",
                2.93, R.drawable.cover_hatredcopter);
        MusicConstructor castratikron = new MusicConstructor(
                "7",
                "Castratikron",
                "Metalocalypse: Dethklok",
                "https://p.scdn.co/mp3-preview/e939340f7dffa81b98a989aafd2afd4e62be6c2a?cid=2afe87a64b0042dabf51f37318616965",
                2.96, R.drawable.cover_castratikron);
        MusicConstructor facefisted = new MusicConstructor(
                "8",
                "Face Fisted",
                "Metalocalypse: Dethklok",
                "https://p.scdn.co/mp3-preview/d46975469543fcd8c0d164378f6940ecfd6477ec?cid=2afe87a64b0042dabf51f37318616965",
                4.29, R.drawable.cover_facefisted);
        MusicConstructor bloodrocuted = new MusicConstructor(
                "9",
                "Bloodrocuted",
                "Metalocalypse: Dethklok",
                "https://p.scdn.co/mp3-preview/06b853c1305cfab498b1b6fd444dbc594116ea20?cid=2afe87a64b0042dabf51f37318616965",
                2.31, R.drawable.cover_bloodrocuted);

        playersongs[0] = thewayyoulooktonight;
        playersongs[1] = photograph;
        playersongs[2] = smoothcriminal;
        playersongs[3] = murmaider;
        playersongs[4] = thunderhorse;
        playersongs[5] = thelostvikings;
        playersongs[6] = hatredcopter;
        playersongs[7] = castratikron;
        playersongs[8] = facefisted;
        playersongs[9] = bloodrocuted;
    }
    //method ot retrieve the index num of the selected song
    public int searchbyid(String id)
    {
        for(int index = 0; index < playersongs.length; index++)
        {
            MusicConstructor tempsong = playersongs[index];
            if(tempsong.getID().equals(id))
            {
                return index;
            }
        }
        return -1;
    }
    // method to retrieve teh song based on index num
    public MusicConstructor getCurrentsong(int index)
    {
        return playersongs[index];
    }

    //method to retrieve index num of next song
    public int getnextsong(int currentsongindex)
    {
        if(currentsongindex >= playersongs.length-1) //if the current song is the last song
        {
            return currentsongindex;
        }
        else
        {
            return currentsongindex +1; //go to the index num of the next song
        }
    }

    //method to retrieve the index num of the previous song
    public int getprevsong(int currentsongindex)
    {
        if(currentsongindex <= 0) //if the current song is the first song
        {
            return currentsongindex;
        }
        else
        {
            return currentsongindex -1; //go to the index num of the prev song
        }
    }
}
