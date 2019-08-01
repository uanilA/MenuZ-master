package com.menuz.ui.authentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.menuz.R;
import com.menuz.session.Session;

import static com.menuz.application.MenuZ.getDataManager;

public class ChangePortActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtIpServer;
    private EditText edPortNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_port);
        initView();
    }

    void initView() {
        ImageView btnBack = findViewById(R.id.btnBack);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        RelativeLayout rlSubmit = findViewById(R.id.rlSubmit);
        RelativeLayout rlCancel = findViewById(R.id.rlCancelPort);
        edtIpServer=findViewById(R.id.edtIpServer);
        edPortNumber=findViewById(R.id.edPortNumber);
        tvHeaderTitle.setText(R.string.ip_port_setting_new);
        btnBack.setOnClickListener(this);
        rlSubmit.setOnClickListener(this);
        rlCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;

            case R.id.rlSubmit:
                if (checkValidation()){
                    Session session=new Session(ChangePortActivity.this);
                    session.setStringIp(edtIpServer.getText().toString().trim(),edPortNumber.getText().toString().trim());
                    session.logout();
                    new Thread(() -> getDataManager().clearAllTable()).start();

                }

                break;

            case R.id.rlCancelPort:
                onBackPressed();
                break;

        }
    }

    boolean checkValidation(){
        if (TextUtils.isEmpty(edtIpServer.getText().toString().trim())&&TextUtils.isEmpty(edPortNumber.getText().toString().trim())){
            Toast.makeText(this, "Enter Ip number and Port number", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(edtIpServer.getText().toString().trim())){
            Toast.makeText(this, "Enter Ip number", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(edPortNumber.getText().toString().trim())){
            Toast.makeText(this, "Enter Port number", Toast.LENGTH_SHORT).show();
            return false;
        }/*else if (!edtIpServer.getText().toString().equals("82.81.11.210")){
            Toast.makeText(this, "Please Enter correct Ip Address", Toast.LENGTH_SHORT).show();
            return false;

        }else  if (!edPortNumber.getText().toString().equals("12986")){
            Toast.makeText(this, "Please Enter correct Port number", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        /*else if (!edtIpServer.getText().toString().equals("82.81.11.210")&&!edPortNumber.getText().toString().equals("12986")){
            Toast.makeText(this, "Please Enter correct Ip Address and port number", Toast.LENGTH_SHORT).show();
            return false;
        }*/

            return true;




    }
}
