package com.menuz.ui.setting;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.menuz.R;
import com.menuz.session.Session;
import com.menuz.ui.authentication.ChangePortActivity;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        session = new Session(SettingActivity.this);
        initView();
    }


    public void initView() {
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        Switch switchGuest = findViewById(R.id.switchGuest);
        LinearLayout llPortSetting = findViewById(R.id.llPortSetting);
        Switch switchCourse = findViewById(R.id.switchCourse);
        ImageView btnBack = findViewById(R.id.btnBack);
        tvHeaderTitle.setText(getString(R.string.action_settings));
        btnBack.setOnClickListener(this);
        llPortSetting.setOnClickListener(this);
        String guest = session.getGuest();
        String course = session.getCourse();
        if (guest.equals("1")) {
            switchGuest.setChecked(true);
        } else {
            switchGuest.setChecked(false);
        }

        if (course.equals("1")) {
            switchCourse.setChecked(true);
        } else {
            switchCourse.setChecked(false);
        }
        switchGuest.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                session.createsessionGuest("1");
            } else {
                session.createsessionGuest("0");
            }
        });


        switchCourse.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                session.createsessionCourse("1");
            } else {
                session.createsessionCourse("0");
            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;

            case R.id.llPortSetting:
               passwordPopup();
                break;
        }
    }



    void passwordPopup(){
        final Dialog dialog = new Dialog(SettingActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_password);
        ImageView imgClose=dialog.findViewById(R.id.imgClose);
        TextView txtTitle=dialog.findViewById(R.id.txtTitle);
        txtTitle.setText(R.string.enter_password);
        TextView btnDone=dialog.findViewById(R.id.btnDone);
        EditText edpw=dialog.findViewById(R.id.edpw);
        btnDone.setOnClickListener(v -> {
            Session session=new Session(SettingActivity.this);
            String password=session.getPasswordUser();
            if (TextUtils.isEmpty(edpw.getText().toString().trim()))    {
                Toast.makeText(SettingActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            }else if (!password.equals(edpw.getText().toString().trim())){
                Toast.makeText(SettingActivity.this, "Please Enter Correct  Password", Toast.LENGTH_SHORT).show();

            }else {
                dialog.dismiss();
                Intent intent=new Intent(SettingActivity.this,ChangePortActivity.class);
                startActivity(intent);

                //portPopUp();
            }
        });
        imgClose.setOnClickListener(v -> dialog.dismiss());

        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }


  /*  private void portPopUp(){
        final Dialog dialog = new Dialog(SettingActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_port_change);
        EditText edIp=dialog.findViewById(R.id.edIp);
        EditText edPortNu=dialog.findViewById(R.id.edPortNu);
        TextView btnCancel=dialog.findViewById(R.id.btnCancel);
        TextView btnOk=dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(v -> {
            if (TextUtils.isEmpty(edIp.getText().toString().trim())&&TextUtils.isEmpty(edPortNu.getText().toString().trim())){
                Toast.makeText(this, "Enter Ip number and Port number", Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(edIp.getText().toString().trim())){
                Toast.makeText(this, "Enter Ip number", Toast.LENGTH_SHORT).show();

            }else if (TextUtils.isEmpty(edPortNu.getText().toString().trim())){
                Toast.makeText(this, "Enter Port number", Toast.LENGTH_SHORT).show();

            }else if (!edIp.getText().toString().equals("82.81.11.210")&&!edPortNu.getText().toString().equals("12986")){
                Toast.makeText(this, "Please Enter correct Ip Address and port number", Toast.LENGTH_SHORT).show();
            }else {
                Session session=new Session(SettingActivity.this);
                session.setStringIp(edIp.getText().toString().trim(),edPortNu.getText().toString().trim());
                session.logout();

            }
        });
        btnCancel.setOnClickListener(v -> dialog.dismiss());


        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }*/

}
