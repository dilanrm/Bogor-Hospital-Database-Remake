package com.app.dilan.rumahsakitbogor;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.content.Intent;


public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent;
                intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, 1000);
    }
}
