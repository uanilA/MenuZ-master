package com.menuz.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.menuz.R;
import com.menuz.application.MenuZ;
import com.menuz.session.Session;
import com.menuz.ui.Home.activity.HomeActivity;
import com.menuz.ui.authentication.LoginActivity;
import com.menuz.ui.language.Language;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(
                () -> {

                    Session session = MenuZ.getInstance().getSessionManager();

                    if(session.isLoggedIn()){
                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    }else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }finish();

                    Session session1=new Session(SplashActivity.this);
                    String userselectedlanguage = session1.getLanguage();

                    switch (userselectedlanguage) {
                        case "":
                            Language.SetLanguage(SplashActivity.this, "en");

                            break;
                        case "en":
                            Language.SetLanguage(SplashActivity.this, "en");
                            break;
                        case "iw":
                            Language.SetLanguage(SplashActivity.this, "iw");
                            break;
                    }


                }, 1000);

    }
}

