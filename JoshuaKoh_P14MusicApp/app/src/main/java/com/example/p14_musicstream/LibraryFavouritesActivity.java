package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LibraryFavouritesActivity extends AppCompatActivity
{
    RecyclerView favlist;
    FavouritesAdaptor favouritesAdaptor;
    TextView bFav, bCollection, bDownloads;
    ImageButton bHome, bSearch, bLibrary;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libraryfavourites);

        bFav = findViewById(R.id.favourites_b_libraryfavourites);
        bCollection = findViewById(R.id.collection_b_libraryfavourites);
        bDownloads = findViewById(R.id.downloads_b_libraryfavourites);
        bHome = findViewById(R.id.home_b_libraryfavourites);
        bSearch = findViewById(R.id.search_b_libraryfavourites);
        bLibrary = findViewById(R.id.library_b_libraryfavourites);
        favlist = findViewById(R.id.recyclerview_favourites);

        favouritesAdaptor = new FavouritesAdaptor(MusicPlayerActivity.favList);
        favlist.setAdapter(favouritesAdaptor);
        favlist.setLayoutManager(new LinearLayoutManager(this));

        bCollection.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), LibraryCollectionsActivity.class));
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