package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class RequestsActivity extends AppCompatActivity
{
    public static final String TAG = "TAG";
    EditText tSong, tArtist;
    ImageButton bCancel, bConfirm, bHome;
    FirebaseFirestore fStore;

    protected void onCreate(Bundle savedInstanceState)
    {
        // calls the parent class constructor
        super.onCreate(savedInstanceState);
        // loads the layout
        setContentView(R.layout.activity_requests);

        tSong = findViewById(R.id.songname_txt_requests);
        tArtist = findViewById(R.id.artist_txt_requests);
        bCancel = findViewById(R.id.cancel_b_requests);
        bConfirm = findViewById(R.id.confirm_b_requests);
        bHome = findViewById(R.id.home_b_requests);

        fStore = FirebaseFirestore.getInstance();

        bCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        bHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
            }
        });

        bConfirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Checks if there is any missing text
                if(tSong.getText().toString().isEmpty() || tArtist.getText().toString().isEmpty())
                {
                    Toast.makeText(RequestsActivity.this, "Please recheck your entries, one or more fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Updates in Firestore
                final String reqsong = tSong.getText().toString();
                final String reqartist = tArtist.getText().toString();

                Map<String, Object> data = new HashMap<>();
                data.put("songname", reqsong); // adds request to firestore library for admin to add song
                data.put("artistname", reqartist); // adds request to firestore for admin to add song

                // When successfully or unsuccessfully requested
                fStore.collection("requests").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                {
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        Toast.makeText(RequestsActivity.this, "Song Request Successful, it'll be added shortly", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(RequestsActivity.this, "Song Request Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
