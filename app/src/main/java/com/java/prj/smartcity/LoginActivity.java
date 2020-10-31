package com.java.prj.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInClient googleSignInClient;
    private FirebaseAuth auth;
    SignInButton  button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        System.out.println(LoginActivity.this);

        FirebaseApp.initializeApp(LoginActivity.this);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        button = (SignInButton) findViewById(R.id.login_google_sign_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               createRequest();
            }
        });
        //ImageView imageView = findViewById(R.id.imageback);
        //Picasso.get().load("https://trello-attachments.s3.amazonaws.com/5f68733f83a521251ed3d9c0/5f72b4ea52454d2ab4af58c7/3713319394798943cd260a7de90ceb6d/animated_bg.gif").into(imageView);

    }

    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        signIn();
    }

    public void SignIn(View view) {
//       loadingDialog.load();
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    public void googleSignIn(View view) {
        createRequest();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {

                e.printStackTrace();
            }
        }
    }

    private void signIn() {
        //loadingDialog.load();
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 2);
    }



    private void firebaseAuthWithGoogle(String idToken) {
        auth = FirebaseAuth.getInstance();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            finishAffinity();

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                            Toast.makeText(LoginActivity.this, "Sign in Successful", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(getApplicationContext(), "Authentication Failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

}