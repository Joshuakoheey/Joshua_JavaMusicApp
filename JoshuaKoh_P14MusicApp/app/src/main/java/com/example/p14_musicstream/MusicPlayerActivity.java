package com.example.p14_musicstream;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MusicPlayerActivity extends AppCompatActivity
{
    ImageButton bExit, bShuffle, bLoop, bOptions, bAddtoFavourites, bAddtoQueue, bAddtoCollection, bAddtoDownloads;
    SeekBar volctrl, timectrl;
    TextView tStart, tEnd, txtQueue;
    ImageView vCover, vCoverOptions;

    private String title = "";
    private String artist = "";
    private String filelink = "";
    private int drawable;
    private int currentindex;
    private int queueindex;
    private MediaPlayer player = new MediaPlayer();
    private ImageButton playpause = null;
    private AudioManager mAudioManager;

    Handler handler = new Handler();
    Boolean loopFlag = false;
    Boolean shuffleFlag = false;
    boolean pressed = false;

    private MusicStorage musicolle = new MusicStorage();

    //static allows for this line to be accessed by any class with out creating another instance
    static ArrayList<MusicConstructor> favList = new ArrayList<MusicConstructor>();
    static ArrayList<MusicConstructor> dlList = new ArrayList<MusicConstructor>();
    static ArrayList<MusicConstructor> colleList = new ArrayList<MusicConstructor>();
    static ArrayList<MusicConstructor> recentList = new ArrayList<MusicConstructor>();
    List<MusicConstructor> shufflelist = Arrays.asList(musicolle.playersongs);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayer);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        Bundle songdata = this.getIntent().getExtras();
        currentindex = songdata.getInt("Index");
        playpause = (ImageButton)findViewById(R.id.playpause_b_radioplayer);
        volctrl = findViewById(R.id.volctrl_f_radioplayer);
        timectrl = findViewById(R.id.timectrl_f_radioplayer);
        bExit = findViewById(R.id.exit_b_radioplayer);
        tEnd = findViewById(R.id.durationend_tv_radioplayer);
        tStart = findViewById(R.id.durationstart_tv_radioplayer);
        bLoop = findViewById(R.id.loop_b_radioplayer);
        bShuffle = findViewById(R.id.shuffle_b_radioplayer);
        bOptions = findViewById(R.id.options_b_radioplayer);
        vCover = findViewById(R.id.cover_v_radioplayer);
        vCoverOptions = findViewById(R.id.optionscover_v_musicplayer);
        bAddtoCollection = findViewById(R.id.addtocollection_b_radioplayer);
        bAddtoDownloads = findViewById(R.id.addtodownloads_b_radioplayer);
        bAddtoQueue = findViewById(R.id.addtoqueue_b_radioplayer);
        bAddtoFavourites = findViewById(R.id.addtofavourites_b_radioplayer);
        txtQueue = findViewById(R.id.nxtinqueue_v_musicplayer);
        TextView txtTitlequeue = findViewById(R.id.queuetitle_txt_musicplayer);
        TextView txtArtistqueue = findViewById(R.id.queueartist_txt_musicplayer);
        ImageView coverQueue = findViewById(R.id.queuecover_v_musicplayer);

        displaysongbasedonindex(currentindex); // Don't move, code breaks
        playsong(filelink); // Don't move, code breaks

        // The options screen
        vCoverOptions.setVisibility(View.GONE);
        bAddtoFavourites.setVisibility(View.GONE);
        bAddtoQueue.setVisibility(View.GONE);
        bAddtoDownloads.setVisibility(View.GONE);
        bAddtoCollection.setVisibility(View.GONE);
        txtTitlequeue.setVisibility(View.GONE);
        txtArtistqueue.setVisibility(View.GONE);
        coverQueue.setVisibility(View.GONE);
        txtQueue.setVisibility(View.GONE);

        // Scrubbable Seekbar
        timectrl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                if(player != null && player.isPlaying())                    
                {                                                           
                    player.seekTo(timectrl.getProgress());
                }
            }
        });

        // Hide or show options
        bOptions.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!pressed)
                {
                    vCover.setVisibility(View.GONE);
                    vCoverOptions.setVisibility(View.VISIBLE);
                    vCoverOptions.setVisibility(View.VISIBLE);
                    bAddtoFavourites.setVisibility(View.VISIBLE);
                    bAddtoQueue.setVisibility(View.VISIBLE);
                    bAddtoDownloads.setVisibility(View.VISIBLE);
                    bAddtoCollection.setVisibility(View.VISIBLE);
                    pressed = true;
                }
                else
                {
                    vCover.setVisibility(View.VISIBLE);
                    vCoverOptions.setVisibility(View.GONE);
                    vCoverOptions.setVisibility(View.GONE);
                    bAddtoFavourites.setVisibility(View.GONE);
                    bAddtoQueue.setVisibility(View.GONE);
                    bAddtoDownloads.setVisibility(View.GONE);
                    bAddtoCollection.setVisibility(View.GONE);
                    txtTitlequeue.setVisibility(View.GONE);
                    txtArtistqueue.setVisibility(View.GONE);
                    coverQueue.setVisibility(View.GONE);
                    txtQueue.setVisibility(View.GONE);
                    pressed = false;
                }
            }
        });

        // Show and hide add to queue + loop so long as added
        bAddtoQueue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int queueindex = currentindex;
                MusicConstructor queuesong = musicolle.getCurrentsong(queueindex);
                title = queuesong.getTitle();
                artist = queuesong.getArtist();
                filelink = queuesong.getLink();
                drawable = queuesong.getDrawable();

                TextView txtTitlequeue = findViewById(R.id.queuetitle_txt_musicplayer);
                txtTitlequeue.setText(title);

                TextView txtArtistqueue = findViewById(R.id.queueartist_txt_musicplayer);
                txtArtistqueue.setText(artist);

                ImageView coverQueue = findViewById(R.id.queuecover_v_musicplayer);
                coverQueue.setImageResource(drawable);

                if (!pressed)
                {
                    txtTitlequeue.setVisibility(View.VISIBLE);
                    txtArtistqueue.setVisibility(View.VISIBLE);
                    coverQueue.setVisibility(View.VISIBLE);
                    txtQueue.setVisibility(View.VISIBLE);
                    pressed = true;
                    player.setLooping(true);
                }
                else
                {
                    txtTitlequeue.setVisibility(View.GONE);
                    txtArtistqueue.setVisibility(View.GONE);
                    coverQueue.setVisibility(View.GONE);
                    txtQueue.setVisibility(View.GONE);
                    pressed = false;
                    player.setLooping(false);
                }
            }
        });

        // volume bar control
        int maxV = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curV = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volctrl.setMax(maxV);
        volctrl.setProgress(curV);

        volctrl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    // time control
    Runnable p_bar = new Runnable()
    {
        @Override
        public void run()
        {
            if(player != null && player.isPlaying())
            {
                timectrl.setProgress(player.getCurrentPosition());
            }
            handler.postDelayed(this, 1000);    
        }
    };

    //method to display song info
    public void displaysongbasedonindex(int selectedindex)
    {
        MusicConstructor selectedsong = musicolle.getCurrentsong(selectedindex);
        title = selectedsong.getTitle();
        artist = selectedsong.getArtist();
        filelink = selectedsong.getLink();
        drawable = selectedsong.getDrawable();

        TextView txtTitle = findViewById(R.id.title_txt_radioplayer);
        txtTitle.setText(title);

        TextView txtArtist = findViewById(R.id.artist_txt_radioplayer);
        txtArtist.setText(artist);

        ImageView iCoverart = findViewById(R.id.cover_v_radioplayer);
        iCoverart.setImageResource(drawable);
    }

    //create method to play song
    public void playsong(String songURL)
    {
        //try to play music if fails display error message
        try
        {
            player.reset();
            player.setDataSource(songURL);
            player.prepare();
            player.start();
            timectrl.setMax(player.getDuration());
            handler.removeCallbacks(p_bar);
            handler.postDelayed(p_bar, 1000);
            stopWhenMusicEnds();
            playpause.setImageResource(R.drawable.mplayerpause_removebg);
            setTitle(title); //set the title of the page to display the song title
        }
        catch(IOException e)
        {
            e.printStackTrace(); //print error message
        }
    }

    //create a method that will be triggered when the play button is pressed
    public void playOrPauseMusic(View view)
    {
        if(player.isPlaying()) //checking if the player is playing the song
        {
            player.pause();
            playpause.setImageResource(R.drawable.mplayerplay_removebg);
        }
        else
        {
            player.start();
            playpause.setImageResource(R.drawable.mplayerpause_removebg);
        }
    }

    // Ensures that the song doesn't continue to play in the background
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        if (player != null)
        {
            handler.removeCallbacks(p_bar);
            player.stop();
            player.release();
            player = null;
        }
    }

    // Detects when the song finishes playing
    private void stopWhenMusicEnds()
    {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                if(loopFlag)
                {
                    playOrPauseMusic(null);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "The song has ended", Toast.LENGTH_LONG).show();
                    playpause.setImageResource(R.drawable.mplayerpause_removebg);
                }
            }
        });
    }

    //create method for next button
    public void playnext(View myView)
    {
        currentindex = musicolle.getnextsong(currentindex);
        displaysongbasedonindex(currentindex);
        playsong(filelink);
    }

    //create method for prev button
    public void playprev(View myView)
    {
        currentindex = musicolle.getprevsong(currentindex);
        displaysongbasedonindex(currentindex);
        playsong(filelink);
    }

    // Loop
    public void loopsong(View view)
    {
        if(loopFlag)
        {
            bLoop.setImageResource(R.drawable.unlooped);
            Toast.makeText(this, "Un-Looped!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            bLoop.setImageResource(R.drawable.looped);
            Toast.makeText(this, "Looped!", Toast.LENGTH_SHORT).show();
        }
        loopFlag = !loopFlag;
    }

    // Shuffle
    public void shufflesong(View view)
    {
          if(shuffleFlag)
          {                                                                                                                  
              bShuffle.setImageResource(R.drawable.unshuffled);
              Toast.makeText(this, "Un-Shuffled!", Toast.LENGTH_SHORT).show();
              musicolle = new MusicStorage();
          }                                                                                                                  
          else                                                                                                               
          {
              bShuffle.setImageResource(R.drawable.shuffled);
              Toast.makeText(this, "Shuffled!", Toast.LENGTH_SHORT).show();
              Collections.shuffle(shufflelist);
              shufflelist.toArray(musicolle.playersongs);
          }                                                                                                                  
          shuffleFlag = !shuffleFlag;
    }

    public void addToFavourites(View view) 
    {
        Integer songID = currentindex;
        MusicConstructor favlistsong = musicolle.getCurrentsong(songID);
        favList.add(favlistsong);

        Toast.makeText(this, "Added to Favourites!", Toast.LENGTH_SHORT).show();
    }

    public void addToDownloads(View view)
    {
        Integer songID = currentindex;
        MusicConstructor dllistsong = musicolle.getCurrentsong(songID);
        dlList.add(dllistsong);

        Toast.makeText(this, "Added to Downloads!", Toast.LENGTH_SHORT).show();
    }

    public void addtocollection(View view)
    {
        Integer songID = currentindex;
        MusicConstructor collelistsong = musicolle.getCurrentsong(songID);
        colleList.add(collelistsong);

        Toast.makeText(this, "Added to Collection!", Toast.LENGTH_SHORT).show();
    }

    public void exitandadd(View view)
    {
        Integer songID = currentindex;
        MusicConstructor recentlistsong = musicolle.getCurrentsong(songID);
        recentList.add(recentlistsong);

        player.pause();
        finish();
    }
}

