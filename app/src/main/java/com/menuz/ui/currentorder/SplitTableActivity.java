package com.menuz.ui.currentorder;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.menuz.R;
import com.menuz.Utils.ConnectionDetector;
import com.menuz.Utils.MyToast;
import com.menuz.Utils.NoConnectionDialog;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.network.HttpResponceListner;
import com.menuz.network.HttpTask;
import com.menuz.ui.currentorder.activity.CurrentOrderActivity;
import com.menuz.ui.currentorder.model.SplitItemAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SplitTableActivity extends AppCompatActivity implements View.OnClickListener, SplitItemAdapter.Listener {

    private String tableId = "";
    private String terminalId = "";
    private String orderId = "";
    private String from = "";
    private ArrayList<OrderItemModel> orderItemModels = new ArrayList<>();
    private String destinationTableId = "";
    private ProgressBar progressBar;
    private float totalcost;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_table);
        if (getIntent() != null) {
            tableId = getIntent().getStringExtra("tableId");
            terminalId = getIntent().getStringExtra("terminalId");
            orderId = getIntent().getStringExtra("orderId");
            from = getIntent().getStringExtra("From");
            destinationTableId = getIntent().getStringExtra("destinationTableId");
            orderItemModels = getIntent().getParcelableArrayListExtra("itemList");
        }

        initView();
    }


    void initView() {
        ImageView btnBack = findViewById(R.id.btnBack);
        TextView btnNext = findViewById(R.id.btnNext);
        TextView txtItem = findViewById(R.id.txtItem);
        RelativeLayout ll = findViewById(R.id.ll);
        progressBar = findViewById(R.id.progressBarnew);
        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(SplitTableActivity.this));
        recyclerView.setAdapter(new SplitItemAdapter(this, orderItemModels, this));
        for (int i = 0; i < orderItemModels.size(); i++) {
            totalcost += Float.parseFloat(orderItemModels.get(i).getItemPrice());
        }


        if (from.equals("cartDiscount")) {
            ll.setVisibility(View.VISIBLE);
            txtItem.setText(String.format("$%s/%s", totalcost, tableId));
            tvHeaderTitle.setText(R.string.discount);
        } else {
            ll.setVisibility(View.GONE);
            tvHeaderTitle.setText(R.string.split);

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;

            case R.id.btnNext:
                if (from.equals("cartDiscount")) {
                    dialogDiscount();
                } else {

                    JSONObject jObjectType = new JSONObject();
                    JSONArray jsonArray = null;

                    String[] mStringArray = new String[arrayList.size()];
                    mStringArray = arrayList.toArray(mStringArray);
                    try {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                            jsonArray = new JSONArray(mStringArray);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    try {

                        jObjectType.put("source", tableId);
                        jObjectType.put("orderid", orderId);
                        jObjectType.put("destination", destinationTableId);
                        jObjectType.put("items", jsonArray);

                        splitTable(jObjectType.toString().trim());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                break;

        }
    }

    @Override
    public void getPosition(int position) {
        arrayList.add(orderItemModels.get(position).getItemAutoID());

    }

    private void splitTable(String data) {
        //    OkHttpClient client = httpClient.build();

        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(SplitTableActivity.this, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    splitTable(data);
                }

            }).show();


        }

        new HttpTask(new HttpTask.Builder(SplitTableActivity.this, "miscFunction/splitOrder/" + data, new HttpResponceListner.Listener() {
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
                            lockTablenew(SplitTableActivity.this, terminalId, tableId);

                        } else {
                            String errMes = jsonObject.getString("errMes");

                            showToast(errMes);
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

    private void sendDiscount(String data, Dialog dialogDisc) {
        //    OkHttpClient client = httpClient.build();

        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(SplitTableActivity.this, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    sendDiscount(data, dialogDisc);
                }

            }).show();


        }

        new HttpTask(new HttpTask.Builder(SplitTableActivity.this, "miscFunction/discount/" + data, new HttpResponceListner.Listener() {
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
                            dialogDisc.dismiss();
                            onBackPressed();

                        } else {
                            String errMes = jsonObject.getString("errMes");

                            showToast(errMes);
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
        if (!TextUtils.isEmpty(msg))
            MyToast.getInstance(SplitTableActivity.this).showDasuAlert(msg);
        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /*Call api for lock table*/
    void lockTablenew(Context context, String terminalId, String tableID) {
        progressBar.setVisibility(View.VISIBLE);
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(context, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    lockTablenew(context, terminalId, tableID);
                }

            }).show();
        }

        //String statusTable = "0";
        new HttpTask(new HttpTask.Builder(SplitTableActivity.this, "SetTable/" + terminalId + "/" + tableID + "/" + "0", new HttpResponceListner.Listener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(String response, String apiName) {
                try {
                    //Progress.hide(mContext);
                    progressBar.setVisibility(View.GONE);
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");
                        if (status.equals("true")) {
                            Intent intent = new Intent(SplitTableActivity.this, CurrentOrderActivity.class);
                            startActivity(intent);
                        } else {
                            String errMes = jsonObject.getString("errMes");

                            showToast(errMes);

                        }


                    }


                    // ((OrderListActivity) mContext).addFragment(SelectWaiterFragment.newInstance(), true);

                } catch (Exception e) {
                    progressBar.setVisibility(View.GONE);
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
                .execute("");
    }

    private void dialogDiscount() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_discount);
        Window window = dialog.getWindow();
        assert window != null;
        ImageView ImgClose = dialog.findViewById(R.id.ImgClose);
        EditText edAmount = dialog.findViewById(R.id.edAmount);
        TextView txtPercent = dialog.findViewById(R.id.txtPercent);
        TextView txtNumber = dialog.findViewById(R.id.txtNumber);

        Switch switchButton = dialog.findViewById(R.id.switchButton);
        TextView btnNext = dialog.findViewById(R.id.btnNext);
        switchButton.setChecked(true);

        edAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (switchButton.isChecked()) {
                    String input = edAmount.getText().toString().trim();
                    if (!input.equals("") && Integer.parseInt(input) <= 100) {
                        txtPercent.setTextColor(getResources().getColor(R.color.red));
                        txtNumber.setTextColor(getResources().getColor(R.color.darkgraynew));
                        txtPercent.setText(edAmount.getText().toString() + "%");

                    } else {
                        int maxLength = 3;
                        edAmount.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                        txtPercent.setText("0%");
                    }


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) edAmount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (TextUtils.isEmpty(edAmount.getText())) {
                        txtNumber.setText("0");
                    } else {
                        txtNumber.setText("0");
                        String input = edAmount.getText().toString().trim();
                        if (!input.equals("")) {
                            int inputnew = Integer.parseInt(input);
                            if (inputnew <= 100) {
                                txtPercent.setTextColor(getResources().getColor(R.color.red));
                                txtNumber.setTextColor(getResources().getColor(R.color.darkgraynew));
                                txtPercent.setText(String.format("%s%%", edAmount.getText().toString()));
                            }
                        } else {
                            txtPercent.setText("0%");
                            int maxLength = 3;
                            edAmount.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                            txtPercent.setText("0%");
                        }


                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (TextUtils.isEmpty(edAmount.getText())) {
                        txtPercent.setText("0");
                    }
                }
            });
            else {
                edAmount.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (TextUtils.isEmpty(edAmount.getText())) {
                            txtNumber.setText("0");
                        } else {
                            txtPercent.setText("0%");
                            txtPercent.setTextColor(getResources().getColor(R.color.darkgraynew));
                            txtNumber.setTextColor(getResources().getColor(R.color.red));

                            txtNumber.setText(edAmount.getText().toString());

                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (TextUtils.isEmpty(edAmount.getText())) {
                            txtNumber.setText("0");
                        }
                    }
                });
            }
        });


        btnNext.setOnClickListener(v -> {
            if (TextUtils.isEmpty(edAmount.getText().toString().trim())) {
                Toast.makeText(this, "Enter amount", Toast.LENGTH_SHORT).show();
            } else {
                JSONObject jObjectType = new JSONObject();
                JSONArray jsonArray = null;
                String[] mStringArray = new String[arrayList.size()];
                mStringArray = arrayList.toArray(mStringArray);
                try {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        jsonArray = new JSONArray(mStringArray);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                try {


                    jObjectType.put("orderid", orderId);
                    jObjectType.put("discount", edAmount.getText().toString());
                    jObjectType.put("items", jsonArray);

                    sendDiscount(jObjectType.toString().trim(), dialog);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        dialog.show();
        ImgClose.setOnClickListener(v -> dialog.dismiss());
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        dialog.setOnShowListener(dialogInterface -> {
            View view = dialog.getWindow().getDecorView();
            //for enter from left
            ObjectAnimator.ofFloat(view, "translationX", -view.getWidth(), 0.0f).start();
            //for enter from bottom
            ObjectAnimator.ofFloat(view, "translationY", view.getHeight(), 0.0f).start();
        });
    }


}
