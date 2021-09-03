package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity
{
    ImageButton bLogin, bSignup;

    protected void onCreate(Bundle savedInstanceState)
    {
        // calls the parent class constructor
        super.onCreate(savedInstanceState);
        // loads the layout
        setContentView(R.layout.activity_welcomepage);

        bLogin = findViewById(R.id.login_b_welcome);
        bSignup = findViewById(R.id.signup_b_welcome);

        bLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        bSignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}
