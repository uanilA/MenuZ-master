package com.menuz.ui.Order.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.menuz.R;
import com.menuz.base.BaseActivity;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.ui.Home.activity.HomeActivity;
import com.menuz.ui.Order.adapter.TableAdapter;
import com.menuz.ui.Order.adapter.ZoneAdapter;
import com.menuz.ui.Order.fragment.ZoneFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends BaseActivity implements View.OnClickListener, ZoneAdapter.Listener, TableAdapter.Listener {
    public FrameLayout frameLayout;
    public TextView tvTablenoCount;
    public TextView tvHeaderTitle;
    private String orderId = "";
    private String From = "";
    private String terminalId = "";
    private String tableId = "";
    private List<OrderItemModel>orderItemModels=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        if (getIntent()!=null){
            From=getIntent().getStringExtra("From");
            switch (From) {
                case "cartTransfer":
                    tableId = getIntent().getStringExtra("tableId");
                    terminalId = getIntent().getStringExtra("terminalId");
                    orderId = getIntent().getStringExtra("orderId");

                    break;
                case "cartSplit":
                    tableId = getIntent().getStringExtra("tableId");
                    terminalId = getIntent().getStringExtra("terminalId");
                    orderId = getIntent().getStringExtra("orderId");
                    orderItemModels = getIntent().getParcelableArrayListExtra("itemList");
                    break;
                case "cartSwitch":
                    tableId = getIntent().getStringExtra("tableId");
                    terminalId = getIntent().getStringExtra("terminalId");
                    orderId = getIntent().getStringExtra("orderId");
                    orderItemModels = getIntent().getParcelableArrayListExtra("itemList");
                    break;
            }


        }
        initView();


        switch (From) {
            case "cartSwitch":
                addFragment(ZoneFragment.newInstance(orderId, tableId, terminalId, From, orderItemModels), true);
                break;
            case "cartTransfer":
                addFragment(ZoneFragment.newInstance(orderId, tableId, terminalId, From, orderItemModels), true);

                break;
            case "cartSplit":
                addFragment(ZoneFragment.newInstance(orderId, tableId, terminalId, From, orderItemModels), true);

                break;
            default:
                addFragment(ZoneFragment.newInstance(), true);
                break;
        }

    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        ImageView btnBack = findViewById(R.id.btnBack);
        frameLayout = findViewById(R.id.frame);
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvTablenoCount = findViewById(R.id.tvTablenoCount);
        switch (From) {
            case "cartTransfer":
                tvHeaderTitle.setText("Transfer Table #" + tableId);
                break;
            case "cartSplit":
                tvHeaderTitle.setText("Split item to Table #" + tableId);
                break;

            case "cartSwitch":
                tvHeaderTitle.setText("Switch between Table #" + tableId);
                break;
            default:
                tvHeaderTitle.setText(R.string.new_order);
                break;
        }

        btnBack.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBack) {
            onBackPressed();
        }
    }

    @Override
    public void position(int position) {

    }

    @Override
    public void selected(String selected) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(OrderListActivity.this, HomeActivity.class);
        startActivity(intent);
    }


}


