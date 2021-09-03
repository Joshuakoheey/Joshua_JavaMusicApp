package com.example.p14_musicstream;

public class RadioPlayer3Storage
{
    public RadioPlayerConstructor[] songs = new RadioPlayerConstructor[3];

    public RadioPlayer3Storage()
    {
        prepareSongs();
    }

    private void prepareSongs()
    {
        RadioPlayerConstructor tomorrowsdream = new RadioPlayerConstructor(
                "0",
                "Tomorrow's Dream",
                "Black Sabbath",
                "https://p.scdn.co/mp3-preview/4d8b87c4b59c3652367e5070f9d84919b48f618a?cid=2afe87a64b0042dabf51f37318616965",
                3.07,
                R.drawable.ironamanhandofdoomtmrsdream);

        RadioPlayerConstructor handofdoom = new RadioPlayerConstructor(
                "1",
                "Hand of Doom",
                "Black Sabbath",
                "https://p.scdn.co/mp3-preview/cbb8745252dba0891fad6c1ca288d8c21e25ce0d?cid=2afe87a64b0042dabf51f37318616965",
                8.43,
                R.drawable.ironamanhandofdoomtmrsdream);

        RadioPlayerConstructor ironman = new RadioPlayerConstructor(
                "2",
                "Iron Man",
                "Black Sabbath",
                "https://p.scdn.co/mp3-preview/8ecd823997d786cbacf25ed4e86e8f825c1e3ce6?cid=2afe87a64b0042dabf51f37318616965",
                6.43,
                R.drawable.ironamanhandofdoomtmrsdream);

        songs[0] = tomorrowsdream;
        songs[1] = handofdoom;
        songs[2] = ironman;
    }

    public RadioPlayerConstructor searchById(String id)
    {
        RadioPlayerConstructor tempSong = null;
        for (int i = 0; i < songs.length; i++)
        {
            tempSong = songs[i];
            String tempId = tempSong.getId();
            if (tempId.equals(id))
            {
                return tempSong;
            }
        }
        return tempSong;
    }

    public RadioPlayerConstructor getCurrentsong(int index)
    {
        return songs[index];
    }

    //method to retrieve index num of next song
    public int getnextsong(int currentsongindex)
    {
        if(currentsongindex >= songs.length-1) //if the current song is the last song
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
