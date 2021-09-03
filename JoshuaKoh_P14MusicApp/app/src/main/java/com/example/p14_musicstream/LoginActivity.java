package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity
{
    EditText tEmail, tPassword;
    ImageButton bLogin, bBack;
    FirebaseAuth fAuth;


    protected void onCreate(Bundle savedInstanceState)
    {
        // calls the parent class constructor
        super.onCreate(savedInstanceState);
        // loads the layout
        setContentView(R.layout.activity_loginpage);

        tEmail = findViewById(R.id.email_txt_login);
        tPassword = findViewById(R.id.pass_txt_login);
        bLogin = findViewById(R.id.login_b_login);
        bBack = findViewById(R.id.back_b_login);
        fAuth = FirebaseAuth.getInstance();

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // trim() removes all white space at the front and back
                String email = tEmail.getText().toString().trim();
                String password = tPassword.getText().toString().trim();

                // validates if the value is null or not
                if(TextUtils.isEmpty(email))
                {
                    tEmail.setError(("An Email is required"));
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    tPassword.setError(("An Password is required"));
                    return;
                }
                // if password length is under 6, error
                if(password.length() < 6)
                {
                    tPassword.setError(("Your password must be more than 6 characters long"));
                    return;
                }

                //authenticate user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            // checks the firebase user regs and if successful, sends to main
                            Toast.makeText(LoginActivity.this, "Login Successful, Welcome Back", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Error, account not found" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        bBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}
