package com.example.p14_musicstream;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RadioPlayer2Activity extends AppCompatActivity
{
    ImageButton bExit, bShuffle, bLoop;
    SeekBar volctrl, timectrl;
    TextView tStart, tEnd;
    ImageView vCover;

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

    // For Loop and Shuffle
    Boolean loopFlag = false;
    Boolean shuffleFlag = false;
    boolean pressed = false;

    private RadioPlayer2Storage musicolle = new RadioPlayer2Storage();

    List<RadioPlayerConstructor> shufflelist = Arrays.asList(musicolle.songs);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radioplayer);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        Bundle songdata = this.getIntent().getExtras();
        playpause = (ImageButton)findViewById(R.id.playpause_b_radioplayer);
        volctrl = findViewById(R.id.volctrl_f_radioplayer);
        timectrl = findViewById(R.id.timectrl_f_radioplayer);
        bExit = findViewById(R.id.exit_b_radioplayer);
        tEnd = findViewById(R.id.durationend_tv_radioplayer);
        tStart = findViewById(R.id.durationstart_tv_radioplayer);
        bLoop = findViewById(R.id.loop_b_radioplayer);
        bShuffle = findViewById(R.id.shuffle_b_radioplayer);
        vCover = findViewById(R.id.cover_v_radioplayer);

        displaysongbasedonindex(currentindex); // Don't move this
        playsong(filelink); // Don't move this

        // Time bar control
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
        // Time bar control

        // Volume bar control
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
        // Volume bar control

        bExit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                player.pause();
                startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
                finish();
            }
        });
    }

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
        RadioPlayerConstructor selectedsong = musicolle.getCurrentsong(selectedindex);
        title = selectedsong.getTitle();
        artist = selectedsong.getArtist();
        filelink = selectedsong.getFileLink();
        drawable = selectedsong.getCoverArt();

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

    //create method to detect when the song finish playing
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

    public void shufflesong(View view)
    {
        if(shuffleFlag)
        {
            bShuffle.setImageResource(R.drawable.unshuffled);
            Toast.makeText(this, "Un-Shuffled!", Toast.LENGTH_SHORT).show();
            musicolle = new RadioPlayer2Storage();
        }
        else
        {
            bShuffle.setImageResource(R.drawable.shuffled);
            Toast.makeText(this, "Shuffled!", Toast.LENGTH_SHORT).show();
            Collections.shuffle(shufflelist);
            shufflelist.toArray(musicolle.songs);
        }
        shuffleFlag = !shuffleFlag;
    }
}
