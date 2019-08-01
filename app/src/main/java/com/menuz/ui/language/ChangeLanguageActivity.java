package com.menuz.ui.language;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.menuz.ui.Home.activity.HomeActivity;
import com.menuz.R;
import com.menuz.session.Session;

public class ChangeLanguageActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton rbEnglish;
    private RadioButton rbHebrew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        init();
    }


    private void init() {
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        Button btnContinue = findViewById(R.id.btnContinue);
        rbEnglish = findViewById(R.id.rbEnglish);
        rbHebrew = findViewById(R.id.rbHebrew);
        Session session = new Session(ChangeLanguageActivity.this);
        String userselectedlanguage = session.getLanguage();
        switch (userselectedlanguage) {
            case "en":
                Language.SetLanguage(ChangeLanguageActivity.this, "en");
                rbEnglish.setChecked(true);
                break;
            case "iw":
                Language.SetLanguage(ChangeLanguageActivity.this, "iw");
                rbHebrew.setChecked(true);
                break;
            case "":
                Language.SetLanguage(ChangeLanguageActivity.this, "en");
                rbEnglish.setChecked(true);
                break;
        }

        if (rbEnglish.isChecked()) {
            rbHebrew.setChecked(false);
        } else if (rbHebrew.isChecked()) {
            rbEnglish.setChecked(false);
        }

        rbEnglish.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rbHebrew.setChecked(false);
            }
        });

        rbHebrew.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rbEnglish.setChecked(false);
            }
        });
        tvHeaderTitle.setText(R.string.change_language);
        ImageView btnBack = findViewById(R.id.btnBack);
        btnContinue.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;

            case R.id.btnContinue:
                if (rbHebrew.isChecked()) {
                    Session session1 = new Session(ChangeLanguageActivity.this);
                    session1.createsessionLanguage("iw");
                    Language.SetLanguage(ChangeLanguageActivity.this, "iw");
                    Intent intent = new Intent(ChangeLanguageActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (rbEnglish.isChecked()) {
                    Session session1 = new Session(ChangeLanguageActivity.this);
                    session1.createsessionLanguage("en");
                    Language.SetLanguage(ChangeLanguageActivity.this, "en");
                    Intent intent = new Intent(ChangeLanguageActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();


                }
                break;
        }

    }

}

