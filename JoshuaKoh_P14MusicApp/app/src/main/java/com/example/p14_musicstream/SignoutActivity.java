package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignoutActivity extends AppCompatActivity
{
    ImageButton bConfirm, bCancel, bHome, bChangeEmail;
    FirebaseAuth fAuth;

    protected void onCreate(Bundle savedInstanceState)
    {
        // calls the parent class constructor
        super.onCreate(savedInstanceState);
        // loads the layout
        setContentView(R.layout.activity_signout);

        bConfirm = findViewById(R.id.confirm_b_signout);
        bCancel = findViewById(R.id.cancel_b_signout);
        bHome = findViewById(R.id.home_b_signout);
        bChangeEmail = findViewById(R.id.changemail_b_signout);
        fAuth = FirebaseAuth.getInstance();

        bConfirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SignoutActivity.this, WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // if the activity being launched is already running in the current task,
                // then instead of launching a new instance of that activity,
                // all of the other activities on top of it will be closed and this
                // Intent will be delivered to the (now on top) old activity as a new Intent
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(0, 0);
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