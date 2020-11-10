package com.java.prj.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent i =new Intent(WelcomeActivity.this,IntroActivity.class);
                    startActivity(i);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }
}