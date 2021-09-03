package com.example.p14_musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;

//import io.opencensus.tags.Tag;


public class SignupActivity extends AppCompatActivity
{
    public static final String TAG = "TAG";
    EditText tFullname, tEmail, tPassword, tRetype;
    ImageButton bback, bsignup;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // calls the parent class constructor
        super.onCreate(savedInstanceState);
        // loads the layout
        setContentView(R.layout.activity_signuppage);

        // associates with id
        tFullname = findViewById(R.id.name_txt_signup);
        tEmail = findViewById(R.id.email_txt_signup);
        tPassword = findViewById(R.id.pass_txt_signup);
        tRetype = findViewById(R.id.retype_txt_signup);
        bback = findViewById(R.id.back_b_signup);
        bsignup = findViewById(R.id.signup_b_signup);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // If user is already logged in or is already a user
        if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
            finish();
        }

        // Check if any data is missing or badly formatted
        bsignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // trim() removes all white space at the front and back
                String email = tEmail.getText().toString().trim();
                String password = tPassword.getText().toString().trim();
                String retype = tRetype.getText().toString().trim();
                String fullname = tFullname.getText().toString();


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
                if(TextUtils.isEmpty(retype))
                {
                    tRetype.setError(("Please retype your password"));
                    return;
                }
                // if password length is under 6, error
                if(password.length() < 6)
                {
                    tPassword.setError(("Your password must be more than 6 characters long"));
                    return;
                }
                // if retype is not the same as the password
                if(!retype.equals(password))
                {
                    tRetype.setError(("Your password does not match"));
                    return;
                }

                // User Creation
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            // register the user in firebase, addOnCompleteListener method allows us to know if it's successful
                            Toast.makeText(SignupActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            UserID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(UserID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fullname",fullname);
                            user.put("email",email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void aVoid)
                                {
                                    Log.d(TAG, "onSuccess: User Profile is created for " + UserID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
                        }
                        else
                        {
                            // Throws the user an error if they made any
                            Toast.makeText(SignupActivity.this, "Error, check your entries" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        bback.setOnClickListener(new View.OnClickListener()
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
