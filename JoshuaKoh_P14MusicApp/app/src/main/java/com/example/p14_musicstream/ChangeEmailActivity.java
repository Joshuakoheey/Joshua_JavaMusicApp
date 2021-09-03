package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
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

public class ChangeEmailActivity extends AppCompatActivity
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
        setContentView(R.layout.activity_changeemail);

        tOld = findViewById(R.id.oldemail_txt_changeemail);
        tNew = findViewById(R.id.new_txt_changeemail);
        tRetype = findViewById(R.id.retype_txt_changeemail);
        bCancel = findViewById(R.id.cancel_b_changeemail);
        bConfirm = findViewById(R.id.confirm_b_changeemail);
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
                tOld.setText(documentSnapshot.getString("email"));
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
                if(tNew.getText().toString().isEmpty() || tRetype.getText().toString().isEmpty())
                {
                    Toast.makeText(ChangeEmailActivity.this, "Please recheck your entries, one or more fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String email = tNew.getText().toString(); // sets tNew to be email
                User.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        DocumentReference docRef = fStore.collection("users").document(User.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("email",email);
                        docRef.update(edited); // updates doc in firestore
                        Toast.makeText(ChangeEmailActivity.this, "Change of Email Successful", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(ChangeEmailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show(); // Prints error
                    }
                });
            }
        });
    }
}
