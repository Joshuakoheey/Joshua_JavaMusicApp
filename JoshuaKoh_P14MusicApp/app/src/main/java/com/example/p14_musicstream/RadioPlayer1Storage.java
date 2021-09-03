package com.example.p14_musicstream;

public class RadioPlayer1Storage
{
    public RadioPlayerConstructor[] songs = new RadioPlayerConstructor[3];

    public RadioPlayer1Storage()
    {
        prepareSongs();
    }

    private void prepareSongs()
    {
        RadioPlayerConstructor murmaider = new RadioPlayerConstructor(
                "0",
                "Murmaider",
                "Metalocalypse: Dethklok",
                "https://p.scdn.co/mp3-preview/b789a7c89b21a6835e1467838bcbab42d28f3df3?cid=2afe87a64b0042dabf51f37318616965",
                3.41, R.drawable.cover_murmaider);

        RadioPlayerConstructor thunderhorse = new RadioPlayerConstructor(
                "1",
                "Thunderhorse",
                "Metalocalypse: Dethklok",
                "https://p.scdn.co/mp3-preview/5569c832cc14d92bc1dcfb25aef627b753af66d9?cid=2afe87a64b0042dabf51f37318616965",
                2.76, R.drawable.cover_thunderhorse);

        RadioPlayerConstructor thelostvikings = new RadioPlayerConstructor(
                "2",
                "The Lost Vikings",
                "Metalocalypse: Dethklok",
                "https://p.scdn.co/mp3-preview/c5e2acca5191b55f0f84e77befa5682ff8cee71f?cid=2afe87a64b0042dabf51f37318616965",
                4.44, R.drawable.cover_thelostvikings);

        songs[0] = murmaider;
        songs[1] = thunderhorse;
        songs[2] = thelostvikings;
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

