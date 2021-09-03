package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

public class ChangeUsernameActivity extends AppCompatActivity
{
    public static final String TAG = "TAG";
    EditText tNew, tRetype;
    TextView tOld;
    ImageButton bCancel, bConfirm, bHome;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser User;
    String UserID;

    protected void onCreate(Bundle savedInstanceState)
    {
        // calls the parent class constructor
        super.onCreate(savedInstanceState);
        // loads the layout
        setContentView(R.layout.activity_changeusername);

        tOld = findViewById(R.id.oldname_txt_changeusername);
        tNew = findViewById(R.id.new_txt_changeusername);
        tRetype = findViewById(R.id.retype_txt_changeusername);
        bCancel = findViewById(R.id.cancel_b_changeusername);
        bConfirm = findViewById(R.id.confirm_b_changeusername);
        bHome = findViewById(R.id.home_b_changeemail);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        User = fAuth.getCurrentUser();
        UserID = fAuth.getCurrentUser().getUid();

        // Display current Email
        DocumentReference documentReference = fStore.collection("users").document(UserID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>()
        {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error)
            {
                tOld.setText(documentSnapshot.getString("fullname"));
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
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

        // Check the entries
        bConfirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //if there are any empty tbs
                if(tNew.getText().toString().isEmpty() || tRetype.getText().toString().isEmpty())
                {
                    Toast.makeText(ChangeUsernameActivity.this, "Please recheck your entries, one or more fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Updates user doc in Firestore
                final String newname = tNew.getText().toString();

                // When successfully or unsuccessfully requested
                documentReference.update("fullname", newname).addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        Toast.makeText(ChangeUsernameActivity.this, "Username Change Successful", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(ChangeUsernameActivity.this, "Username Change Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
