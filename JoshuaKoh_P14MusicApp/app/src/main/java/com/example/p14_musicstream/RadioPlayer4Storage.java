package com.example.p14_musicstream;

public class RadioPlayer4Storage
{
    public RadioPlayerConstructor[] songs = new RadioPlayerConstructor[3];

    public RadioPlayer4Storage()
    {
        prepareSongs();
    }

    private void prepareSongs()
    {
        RadioPlayerConstructor castleonthehill = new RadioPlayerConstructor(
                "0",
                "Castle on the Hill",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/beb4ed48cca5d2a792e877c7efe92d54046eac67?cid=2afe87a64b0042dabf51f37318616965",
                4.35,
                R.drawable.castleonthehillgalwaygirlnancymulligan);

        RadioPlayerConstructor galwaygirl = new RadioPlayerConstructor(
                "1",
                "Galway Girl",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/cec1fc40a0220f20d3b91dd28d8e1141ad5e7e25?cid=2afe87a64b0042dabf51f37318616965",
                2.85,
                R.drawable.castleonthehillgalwaygirlnancymulligan);

        RadioPlayerConstructor nancymulligan = new RadioPlayerConstructor(
                "2",
                "Nancy Mulligan",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/96644ed062a08f54434611cf88114de8c6d57177?cid=2afe87a64b0042dabf51f37318616965",
                3,
                R.drawable.castleonthehillgalwaygirlnancymulligan);

        songs[0] = castleonthehill;
        songs[1] = galwaygirl;
        songs[2] = nancymulligan;
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
