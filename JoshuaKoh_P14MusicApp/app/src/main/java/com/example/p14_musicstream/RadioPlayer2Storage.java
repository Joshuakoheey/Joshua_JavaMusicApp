package com.example.p14_musicstream;

public class RadioPlayer2Storage
{
    public RadioPlayerConstructor[] songs = new RadioPlayerConstructor[3];

    public RadioPlayer2Storage()
    {
        prepareSongs();
    }

    private void prepareSongs()
    {
        RadioPlayerConstructor tkn = new RadioPlayerConstructor(
                "0",
                "TKN",
                "Travis Scott",
                "https://p.scdn.co/mp3-preview/3b011ea2f3a0b6faf549e21faf6d5ca5aa6f74fc?cid=2afe87a64b0042dabf51f37318616965",
                2.16,
                R.drawable.tkn);

        RadioPlayerConstructor theends = new RadioPlayerConstructor(
                "1",
                "The Ends",
                "Travis Scott",
                "https://p.scdn.co/mp3-preview/50575091bd6075ce1f6f21a3149d79b32c53251a?cid=2afe87a64b0042dabf51f37318616965",
                3.87,
                R.drawable.theends);

        RadioPlayerConstructor californialove = new RadioPlayerConstructor(
                "2",
                "California Love",
                "2Pac",
                "https://p.scdn.co/mp3-preview/93e456ef0b73f23f50eeadaeaad852d79d4f4610?cid=2afe87a64b0042dabf51f37318616965",
                5.8,
                R.drawable.calilove);

        songs[0] = tkn;
        songs[1] = theends;
        songs[2] = californialove;
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
