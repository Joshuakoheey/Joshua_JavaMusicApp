package com.example.p14_musicstream;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class DeleteAccountActivity extends AppCompatActivity
{
    ImageButton bCancel, bConfirm, bHome;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser User;

    protected void onCreate(Bundle savedInstanceState)
    {
        // calls the parent class constructor
        super.onCreate(savedInstanceState);
        // loads the layout
        setContentView(R.layout.activity_deleteaccount);

        bCancel = findViewById(R.id.cancel_b_deleteacc);
        bConfirm = findViewById(R.id.confirm_b_deleteacc);
        bHome = findViewById(R.id.home_b_deleteacc);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        User = fAuth.getCurrentUser();

        bConfirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                User.delete().addOnCompleteListener(new OnCompleteListener<Void>() // inbuilt firebase delete feature
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            // Deletes fAuth Acc
                            Toast.makeText(DeleteAccountActivity.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DeleteAccountActivity.this, WelcomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            // if the activity being launched is already running in the current task,
                            // then instead of launching a new instance of that activity,
                            // all of the other activities on top of it will be closed and this
                            // Intent will be delivered to the (now on top) old activity as a new Intent
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                        else
                        {
                            Toast.makeText(DeleteAccountActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                overridePendingTransition(0, 0);
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
    }
}
