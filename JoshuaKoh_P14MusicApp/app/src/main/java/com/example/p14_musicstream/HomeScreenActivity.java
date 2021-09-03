package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class HomeScreenActivity extends AppCompatActivity
{
    TextView tName;
    ImageButton bSettings, bRadMetal, bRadPop, bRadHipHop, bRadRock, bRequests, bLibrary, bSearch;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UserID;
    RecyclerView recentlist;
    RecentSongsAdaptor recentsongsAdaptor;

    protected void onCreate(Bundle savedInstanceState)
    {
        // calls the parent class constructor
        super.onCreate(savedInstanceState);
        // loads the layout
        setContentView(R.layout.activity_homepage);

        tName = findViewById(R.id.name_txtview_home);
        bSettings = findViewById(R.id.settings_b_home);
        bRadMetal = findViewById(R.id.r_metalmayham_b_home);
        bRadPop = findViewById(R.id.r_poppinpop_b_home);
        bRadHipHop = findViewById(R.id.r_hiphot_b_home);
        bRadRock = findViewById(R.id.r_rockotheages_b_home);
        bRequests = findViewById(R.id.requests_b_home);
        bLibrary = findViewById(R.id.library_b_home);
        bSearch = findViewById(R.id.search_b_home);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        UserID = fAuth.getCurrentUser().getUid();

        recentlist = findViewById(R.id.recyclerview_home);
        recentsongsAdaptor = new RecentSongsAdaptor(MusicPlayerActivity.recentList);
        recentlist.setAdapter(recentsongsAdaptor);
        recentlist.setLayoutManager(new LinearLayoutManager(this));

        //displays username at top of screen
        DocumentReference documentReference = fStore.collection("users").document(UserID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>()
        {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error)
            {
                tName.setText(documentSnapshot.getString("fullname"));
            }
        });

        bSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });

        bRequests.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), RequestsActivity.class));
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

        bSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), SearchSongActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        bRadMetal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), RadioPlayer1Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        bRadHipHop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), RadioPlayer2Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        bRadRock.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), RadioPlayer3Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        bRadPop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), RadioPlayer4Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}
