package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LibraryCollectionsActivity extends AppCompatActivity
{
    RecyclerView collelist;
    CollectionsAdaptor collectionAdaptor;
    TextView bFav, bCollections, bDownloads;
    ImageButton bHome, bSearch, bLibrary;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarycollections);

        bFav = findViewById(R.id.favourites_b_librarycollection);
        bCollections = findViewById(R.id.playlists_b_librarycollection);
        bDownloads = findViewById(R.id.downloads_b_librarycollection);
        bHome = findViewById(R.id.home_b_librarycollection);
        bSearch = findViewById(R.id.search_b_librarycollection);
        bLibrary = findViewById(R.id.library_b_librarycollection);
        collelist = findViewById(R.id.recyclerview_collection);

        collectionAdaptor = new CollectionsAdaptor(MusicPlayerActivity.colleList);
        collelist.setAdapter(collectionAdaptor);
        collelist.setLayoutManager(new LinearLayoutManager(this));

        bFav.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), LibraryFavouritesActivity.class));
                overridePendingTransition(0, 0);
            }
        });

        bDownloads.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), LibraryDownloadsActivity.class));
                overridePendingTransition(0, 0);
            }
        });

        bHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        bSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), SearchSongActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        bLibrary.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), LibraryCollectionsActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}


