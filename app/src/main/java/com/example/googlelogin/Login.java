package com.example.googlelogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.michaelrocks.paranoid.Obfuscate;


@Obfuscate
public class Login extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    Button btSignOut;
    TextView tvName, tvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btSignOut = findViewById(R.id.btSignOut);

        btSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signOut();
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
            tvName.setText(personName);
            tvEmail.setText(personEmail);
//            Glide.with(this).load(String.valueOf(personPhoto)).into(pic);
            Toast.makeText(Login.this, "SignOut Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        startActivity(new Intent(Login.this,MainActivity.class));
                        finish();
                    }
                });
    }
}