package com.menuz.ui.currentorder.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.menuz.R;
import com.menuz.Utils.ConnectionDetector;
import com.menuz.Utils.MyToast;
import com.menuz.Utils.NoConnectionDialog;
import com.menuz.network.HttpResponceListner;
import com.menuz.network.HttpTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PrintNotesActivity extends AppCompatActivity implements View.OnClickListener {
    private String orderId = "";
    private EditText edRemark;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_notes);
        if (getIntent() != null) {
            orderId = getIntent().getStringExtra("orderId");
        }
        initView();
    }

    private void initView() {
        ImageView btnBack = findViewById(R.id.btnBack);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText(R.string.print_noted);
         edRemark = findViewById(R.id.edRemark);
        progressBar = findViewById(R.id.progressBar);
        TextView btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        edRemark.requestFocus();
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;

            case R.id.btnNext:
                if (TextUtils.isEmpty(edRemark.getText().toString().trim())){
                    Toast.makeText(this, "Please Enter Remark", Toast.LENGTH_SHORT).show();
                }else if (edRemark.length()<1){
                    Toast.makeText(this, "Please Enter more than one character", Toast.LENGTH_SHORT).show();
                }else {

                    JSONObject jObjectType = new JSONObject();

                    // put elements into the object as a key-value pair
                    try {
                        jObjectType.put("orderid", orderId);
                        jObjectType.put("remarks", edRemark.getText().toString().trim());
                        submitRemark(jObjectType.toString().trim());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }


    private void submitRemark(String data) {
        //    OkHttpClient client = httpClient.build();

        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(PrintNotesActivity.this, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    submitRemark(data);
                }

            }).show();


        }

        new HttpTask(new HttpTask.Builder(PrintNotesActivity.this, "miscFunction/printNotes/" + data, new HttpResponceListner.Listener() {
            @Override
            public void onResponse(String response, String apiName) {
                Log.e("LogInREsPonce", "onResponse: " + response);
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");

                        if (status.equals("true")) {
                            onBackPressed();


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
                progressBar.setVisibility(View.GONE);
            }
        })
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(false))
                .execute(this.getClass().getName());
    }


    private void showToast(String msg) {
        if (!TextUtils.isEmpty("Select Table"))
            MyToast.getInstance(PrintNotesActivity.this).showDasuAlert(msg);
        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
