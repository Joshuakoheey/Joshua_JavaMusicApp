package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchSongActivity extends AppCompatActivity
{
    ImageButton bSearch, bHome, bLibrary, bCover;
    EditText tSearch;

    DisplayInfoStorage displaystorage = new DisplayInfoStorage();
    private static final String TAG = "SearchSongActivity";
    private ArrayAdapter adapter;

    public void onCreate(Bundle savedInstanceState)
    {
        // calls the parent class constructor
        super.onCreate(savedInstanceState);
        // loads the layout
        setContentView(R.layout.activity_searchsong);

        bLibrary = findViewById(R.id.library_b_search);
        bHome = findViewById(R.id.home_b_search);
        bSearch = findViewById(R.id.search_b_search);
        tSearch = findViewById(R.id.search_txt_search);
        bCover = findViewById(R.id.bCover);
        EditText search = (EditText) findViewById(R.id.search_txt_search);
        ListView list = (ListView) findViewById(R.id.listview_search);
        Log.d(TAG, "onCreate: Started");

        adapter = new MusicPlayerAdapter(this, R.layout.music_list_layout, displaystorage.displaydisplay);
        list.setAdapter(adapter);

        search.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                SearchSongActivity.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable charSequence)
            {

            }
        });

        bHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        bSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), SearchSongActivity.class));
            }
        });

        bLibrary.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), LibraryCollectionsActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    public void handleselection(View myView)
    {
    }

    public int getArrayIndex(int index)
    {
        return index;
    }
}
