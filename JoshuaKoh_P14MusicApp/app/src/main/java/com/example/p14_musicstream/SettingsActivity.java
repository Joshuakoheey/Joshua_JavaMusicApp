package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity
{

    ImageButton bChangeemail, bSignout, bChangeusername, bHome, bDelete;
    FirebaseAuth fAuth;

    protected void onCreate(Bundle savedInstanceState)
    {
        // calls the parent class constructor
        super.onCreate(savedInstanceState);
        // loads the layout
        setContentView(R.layout.activity_settingspage);

        bChangeemail = findViewById(R.id.changemail_b_settings);
        bChangeusername = findViewById(R.id.changeusername_b_settings);
        bSignout = findViewById(R.id.signout_b_settings);
        bHome = findViewById(R.id.home_b_settings);
        bDelete = findViewById(R.id.delete_b_settings);
        fAuth = FirebaseAuth.getInstance();

        bChangeemail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), ChangeEmailActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        bChangeusername.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), ChangeUsernameActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        bSignout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), SignoutActivity.class));
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

        bDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), DeleteAccountActivity.class));
                overridePendingTransition(0, 0);
            }
        });

    }
}
