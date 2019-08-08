package com.menuz.ui.authentication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.menuz.R;
import com.menuz.Utils.ConnectionDetector;
import com.menuz.Utils.MyToast;
import com.menuz.Utils.NoConnectionDialog;
import com.menuz.base.BaseActivity;
import com.menuz.network.HttpResponceListner;
import com.menuz.network.HttpTask;
import com.menuz.session.Session;
import com.menuz.ui.Home.activity.HomeActivity;
import com.menuz.ui.language.Language;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText edEmail;
    private EditText edPassword;
    private Session session;
    private ProgressBar progress;
    private String userselectedlanguage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLanguage();
        setContentView(R.layout.activity_login);
        Session session = new Session(LoginActivity.this);
        String[] a = session.getStringIp();
        String PORTNUMBER = a[0];
        String IP = a[1];
        if (PORTNUMBER.equals("")&& IP.equals("")){
            portPopUp();
        }
        init();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }


    public void init() {
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        TextView btnSignIn = findViewById(R.id.btnSignIn);
        progress = findViewById(R.id.progress);
        session = new Session(LoginActivity.this);
        btnSignIn.setOnClickListener(this);

    }

    public boolean checkValidation() {
        if (TextUtils.isEmpty(edEmail.getText())) {
            showToast(getString(R.string.enter_email));
            return false;

        } else if (TextUtils.isEmpty(edPassword.getText())) {
            showToast(getString(R.string.enter_password));
            return false;

        }
        return true;

    }


    private void showToast(String msg) {
        if (!TextUtils.isEmpty(msg))
            MyToast.getInstance(this).showDasuAlert(msg);
        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSignIn) {
            if (checkValidation()) {
                loginProcess();
            }
        }
    }

    private void loginProcess() {
        //    OkHttpClient client = httpClient.build();

        progress.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(LoginActivity.this, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    loginProcess();
                }

            }).show();


        }
        String email = edEmail.getText().toString().trim();
        String password = edPassword.getText().toString().trim();


        new HttpTask(new HttpTask.Builder(this, "Login/" + email + "/" + password + "/", new HttpResponceListner.Listener() {
            @Override
            public void onResponse(String response, String apiName) {
                Log.e("LogInREsPonce", "onResponse: " + response);
                try {
                    progress.setVisibility(View.GONE);
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");

                        if (status.equals("true")) {
                            Gson gson = new Gson();
                            User user = gson.fromJson(String.valueOf(jsonObject), User.class);
                            session.createSession(user);
                            session.createsessionLanguage(userselectedlanguage);
                            session.createsessionPassword(edPassword.getText().toString().trim());
                            session.createsessionGuest("0");
                            session.createsessionCourse("0");
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            String errMes = jsonObject.getString("errMes");
                            if (errMes.equals("invalid username or password or user out of service"))
                                showToast(getString(R.string.invalid_username));
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void ErrorListener(VolleyError error) {
                portPopUp();
            }
        })
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(false))
                .execute(this.getClass().getName());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    public void checkLanguage() {
        Session session = new Session(LoginActivity.this);
        userselectedlanguage = session.getLanguage();
        switch (userselectedlanguage) {
            case "en":
                Language.SetLanguage(LoginActivity.this, "en");
                break;
            case "iw":
                Language.SetLanguage(LoginActivity.this, "iw");
                break;
            case "":
                Language.SetLanguage(LoginActivity.this, "en");
                break;
        }

    }


    private void portPopUp(){
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_port_change);
        EditText edIp=dialog.findViewById(R.id.edIp);
        EditText edPortNu=dialog.findViewById(R.id.edPortNu);
        TextView txtIp=dialog.findViewById(R.id.txtIp);
        TextView txtPort=dialog.findViewById(R.id.txtPort);
        txtIp.setText(R.string.Ip_server);
        txtPort.setText(R.string.port_number);

        //edIp.addTextChangedListener(new NumberTextWatcherForThousand(edIp));

        //edIp.setInputType(EditorInfo.TYPE_NUMBER_FLAG_SIGNED|EditorInfo.TYPE_CLASS_NUMBER);

        dialog.setCancelable(false);
        TextView btnCancel=dialog.findViewById(R.id.btnCancel);
        TextView btnOk=dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(v -> {
            if (TextUtils.isEmpty(edIp.getText().toString().trim())&&TextUtils.isEmpty(edPortNu.getText().toString().trim())){
                Toast.makeText(this, "Enter Ip number and Port number", Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(edIp.getText().toString().trim())){
                Toast.makeText(this, "Enter Ip number", Toast.LENGTH_SHORT).show();

            }else if (TextUtils.isEmpty(edPortNu.getText().toString().trim())){
                Toast.makeText(this, "Enter Port number", Toast.LENGTH_SHORT).show();

            }/*else if (!edIp.getText().toString().equals("82.81.11.210")){
                Toast.makeText(this, "Please Enter correct Ip Address", Toast.LENGTH_SHORT).show();

            }*//*else if (!edPortNu.getText().toString().equals("12986")){
                Toast.makeText(this, "Please Enter correct Port number", Toast.LENGTH_SHORT).show();
            }*/
           /* else if (!edIp.getText().toString().equals("82.81.11.210")&&!edPortNu.getText().toString().equals("12986")){
                Toast.makeText(this, "Please Enter correct Ip Address and port number", Toast.LENGTH_SHORT).show();
            }*/
            else {
                Session session=new Session(LoginActivity.this);
                session.setStringIp(edIp.getText().toString().trim(),edPortNu.getText().toString().trim());
                dialog.dismiss();

            }
        });
        btnCancel.setOnClickListener(v -> Toast.makeText(this, "You need to enter Ip address and port number", Toast.LENGTH_SHORT).show());


        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }


}
