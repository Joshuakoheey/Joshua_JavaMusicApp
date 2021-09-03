package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LibraryDownloadsActivity extends AppCompatActivity
{
    TextView bFav, bCollection;
    ImageButton bHome, bSearch, bLibrary;
    RecyclerView dllist;
    DownloadsAdaptor downloadsAdaptor;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarydownloads);

        bFav = findViewById(R.id.favourites_b_librarydownloads);
        bCollection = findViewById(R.id.collection_b_librarydownloads);
        bHome = findViewById(R.id.home_b_librarydownloads);
        bSearch = findViewById(R.id.search_b_librarydownloads);
        bLibrary = findViewById(R.id.library_b_librarydownloads);

        dllist = findViewById(R.id.recyclerview_downloads);
        downloadsAdaptor = new DownloadsAdaptor(MusicPlayerActivity.dlList);
        dllist.setAdapter(downloadsAdaptor);
        dllist.setLayoutManager(new LinearLayoutManager(this));

        bFav.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), LibraryFavouritesActivity.class));
                overridePendingTransition(0, 0);
            }
        });

        bCollection.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), LibraryCollectionsActivity.class));
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
