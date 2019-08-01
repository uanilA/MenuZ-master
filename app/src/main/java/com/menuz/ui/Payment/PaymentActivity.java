package com.menuz.ui.Payment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.menuz.R;
import com.menuz.Utils.ConnectionDetector;
import com.menuz.Utils.MyToast;
import com.menuz.Utils.NoConnectionDialog;
import com.menuz.application.MenuZ;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderJsonModel;
import com.menuz.data.model.db.OrderNewjsonModel;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.data.model.db.PayMethodsModel;
import com.menuz.data.model.db.PaymentModel;
import com.menuz.network.HttpResponceListner;
import com.menuz.network.HttpTask;
import com.menuz.ui.Payment.adapter.PaymentMethodAdapter;
import com.menuz.ui.Payment.adapter.PaymentTypeAdapter;
import com.mypos.slavesdk.ConnectionListener;
import com.mypos.slavesdk.ConnectionType;
import com.mypos.slavesdk.Currency;
import com.mypos.slavesdk.Language;
import com.mypos.slavesdk.POSHandler;
import com.mypos.slavesdk.POSInfoListener;
import com.mypos.slavesdk.TransactionData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.menuz.application.MenuZ.getDataManager;


public class PaymentActivity extends AppCompatActivity implements PaymentMethodAdapter.OnCarDClick, View.OnClickListener {

    List<PayMethodsModel> arrayList = new ArrayList<>();
    List<PaymentModel> paymentModelArrayList = new ArrayList<>();
    private Handler handler = new Handler(Looper.getMainLooper());
    private RecyclerView recyclerPayment;
    private PaymentTypeAdapter paymentTypeAdapter;
    EditText edCardno, edSecurity, edId, edPhone, edNoofPayment, edFristPay, edFrirstP, edRestPay, edRest, edMonth;
    EditText edYear;
    private String paymentamomt = "";
    private OrderNewjsonModel orderJsonModel1 = new OrderNewjsonModel();
    private List<OrderNewjsonModel.OrdersBean.ItemsBean.ItemprepsBean> itemsBeans = new ArrayList<>();
    private List<OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean> addonprepsBeanArrayList = new ArrayList<>();
    private String cash = "";
    private String stredAmount_cash = "";
    Spinner edSp;
    private TextView txtTotalCount;
    private String orderId = "";
    private String tableId = "";
    private TextView mTerminalType;
    private TextView tvCardRead;
    private Acr31 _reader;
    private NewOrderModel newOrderModel;
    private static final int REQUEST_CODE_MAKE_PAYMENT = 1;
    private static final int REQUEST_CODE_MAKE_REFUND = 2;
    private static final int PERMISSION_COARSE_LOCATION = 1;
    private POSHandler mPOSHandler;
    public  AudioJackPluginReceiver _audioJackPluginChecker;
    private boolean isConnected = true;


    private class OnDataAvailableListener implements Acr31.OnReaderDataAvailableListener {
        private OnDataAvailableListener() {
        }

        public void UpdateUI(Acr31 acr31, Acr31.AvailableData availableData) {
           // String firmwareVersion = acr31.getFirmwareVersion();
           // StringBuilder sb = new StringBuilder();
          //  sb.append("Firmware version: ");
          //  sb.append(firmwareVersion);
          //  Log.i("StartUp", sb.toString());
           // if (firmwareVersion.contains("31") || firmwareVersion.contains("32") || firmwareVersion.contains("35")) {
                isConnected = false;
          //  }
            _reader.stop();
        }
    }

    private class OnErrorListener implements Acr31.OnReaderErrorListener {
        private OnErrorListener() {
        }

        public void UpdateUI(String str, String str2) {
            isConnected = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
            builder.setMessage(str2).setTitle(str).setPositiveButton(R.string.ok, null);
            try {
                builder.show();
            } catch (Exception unused) {
            }
            _reader.stop();
        }
    }

    private class OnReaderPlugIn implements AudioJackPluginReceiver.OnReaderPlugin {
        private OnReaderPlugIn() {
        }

        public void PostUI(boolean z) {
            if (z) {
                Log.i("Reader", "get Firmware triggered");
                _reader.reset(Acr31.ResetType.START_UP);
                isConnected = false;
                return;
            }
            isConnected = true;
            Toast.makeText(PaymentActivity.this, "Device not connect", Toast.LENGTH_SHORT).show();
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        _reader = new Acr31(this);
        _audioJackPluginChecker = new AudioJackPluginReceiver(this, _reader, new OnReaderPlugIn());
        _audioJackPluginChecker.setReader(_reader);
        _reader.setOnReaderDataAvailableListener(new OnDataAvailableListener());
        _reader.setOnReaderErrorListener(new OnErrorListener());
        _reader.setActivity(this);
        registerReceiver(_audioJackPluginChecker, new IntentFilter("android.intent.action.HEADSET_PLUG"));

        Intent intent = getIntent();
        if (intent != null) {
            paymentamomt = intent.getStringExtra("paymentamomt");
            orderId = intent.getStringExtra("orderId");
            tableId = intent.getStringExtra("tableId");
        }
        initView();
    }

    @SuppressLint("SetTextI18n")
    public void initView() {
        recyclerPayment = findViewById(R.id.recyclerPayment);
        ImageView btnBack = findViewById(R.id.btnBack);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        LinearLayout llPay = findViewById(R.id.llPay);
        LinearLayout llHeader = findViewById(R.id.llHeaderN);
        tvCardRead = findViewById(R.id.tvCardRead);
        tvCardRead.setVisibility(View.GONE);
        tvCardRead.setOnClickListener(this);
        txtTotalCount = findViewById(R.id.txtTotalCount);
        txtTotalCount.setText(paymentamomt);
        tvHeaderTitle.setText(R.string.payment_n);
        btnBack.setOnClickListener(this);
        llPay.setOnClickListener(this);
        RecyclerView recyclerPaymentMethod = findViewById(R.id.recyclerPaymentMethod);

        new Thread(() -> {
            arrayList = getDataManager().loadAllPaymentType(orderId);
            handler.post(() -> {
                if (paymentModelArrayList.size() == 0) {
                    llHeader.setVisibility(View.GONE);
                } else {
                    llHeader.setVisibility(View.VISIBLE);
                }
            /*  recyclerPayment.setVisibility(View.VISIBLE);
                recyclerPayment.setLayoutManager(new LinearLayoutManager(PaymentActivity.this));
                paymentTypeAdapter = new PaymentTypeAdapter(PaymentActivity.this, paymentModelArrayList);
                recyclerPayment.setAdapter(paymentTypeAdapter);*/
                // recyclerPayment.setVisibility(View.VISIBLE);
                PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(PaymentActivity.this, arrayList, this);
                recyclerPaymentMethod.setLayoutManager(new GridLayoutManager(PaymentActivity.this, 3));
                recyclerPaymentMethod.setAdapter(paymentMethodAdapter);

            });
        }).start();

        new Thread(() -> {
            PayMethodsModel payMethodsModels = getDataManager().loadSinglePayment(orderId);
            if (payMethodsModels != null)
                handler.post(() -> {
                    if (!payMethodsModels.getPaidAmt().isEmpty()) {
                        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + payMethodsModels.getPaidAmt());
                        String valueAmt = txtTotalCount.getText().toString().trim();
                        float val = Float.parseFloat(valueAmt);
                        float newval = Float.parseFloat(payMethodsModels.getPaidAmt());
                        //  float Totalval = val - newval;
                        txtTotalCount.setText(String.valueOf(val));
                    }
                });

        }).start();

        POSHandler.setConnectionType(ConnectionType.BLUETOOTH);
        POSHandler.setCurrency(Currency.EUR);
        POSHandler.setLanguage(Language.ENGLISH);
        POSHandler.setApplicationContext(this);
        POSHandler.setDefaultReceiptConfig(POSHandler.RECEIPT_PRINT_AUTOMATICALLY);
        mPOSHandler = POSHandler.getInstance();
        checkPermissions();
        registerReceiver(mReceiver, new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED));
        setPosInfoListener();
    }


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(intent.getAction())) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (POSHandler.getInstance().getConnectedDevice() != null && device != null &&
                        POSHandler.getInstance().getConnectedDevice().getAddress().equalsIgnoreCase(device.getAddress())) {
                    //  mStatus.setText("");
                    Toast.makeText(context, "Not connected", Toast.LENGTH_SHORT).show();
                    mTerminalType.setText("");
                    mTerminalType.setVisibility(View.INVISIBLE);
                    // setEnabled(false);
                }
            }
        }
    };


    private void setPosInfoListener() {
        mPOSHandler.setPOSInfoListener(new POSInfoListener() {
            @Override
            public void onPOSInfoReceived(final int command, final int status, final String description) {
                // handle pos info events here
            }

            @Override
            public void onTransactionComplete(final TransactionData transactionData) {
                // handle purchase and refund transactions complete here
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        setConnectionListener();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
            _reader.stop();
    }

    @Override
    public void onDestroy() {
        if (mReceiver != null)
            unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_COARSE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_COARSE_LOCATION:
                if (grantResults.length < 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_MAKE_PAYMENT && resultCode == RESULT_OK) {
            TransactionData transactionData = data.getParcelableExtra(POSHandler.INTENT_EXTRA_TRANSACTION_DATA);
            // showTransactionDataAlert(transactionData);
            // handle transaction data, result of payment via activity
        } else if (requestCode == REQUEST_CODE_MAKE_REFUND && resultCode == RESULT_OK) {
            TransactionData transactionData = data.getParcelableExtra(POSHandler.INTENT_EXTRA_TRANSACTION_DATA);
            // handle transaction data, result of refund via activity
        }
    }


    private void setConnectionListener() {
        mPOSHandler.setConnectionListener(new ConnectionListener() {
            @Override
            public void onConnected(final BluetoothDevice device) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PaymentActivity.this, "Connected", Toast.LENGTH_SHORT).show();
                        mTerminalType.setVisibility(View.VISIBLE);
                        if (device == null)
                            mTerminalType.setText("E500");
                        else
                            mTerminalType.setText(device.getName().equalsIgnoreCase("") ? device.getAddress() : device.getName());
                        //  setEnabled(true);
                    }
                });
            }
        });
    }


    @Override
    public void getPos(int position) {
        PayMethodsModel payMethodsModel = arrayList.get(position);

        System.out.println("_________________________"+payMethodsModel.getPayMethodId());

        if (payMethodsModel.getPayMethodName().equals("אשראי ידני")) {
            showManualcreditDialog(payMethodsModel);
        } else if (payMethodsModel.getPayMethodName().equals("מזומן")) {
            showCashDialog(payMethodsModel);
        } else if (payMethodsModel.getPayMethodName().equals("סיבוס")) {
            if (!isConnected){
                paymethodId = payMethodsModel.getPayMethodId();
                startActivity(new Intent(PaymentActivity.this, SwipePaymentActivity.class)
                        .putExtra("total_pmt_amt", txtTotalCount.getText().toString())
                        .putExtra("orderId",orderId)
                        .putExtra("paymentMethodId",paymethodId)
                        .putExtra("tableId",tableId));
            }else {
                Toast.makeText(this, "Please connect cibus device", Toast.LENGTH_SHORT).show();
            }
            // showPopupPayment();
        } else if (payMethodsModel.getPayMethodName().equals("")){

          //  showPopupPayment();
        }
    }

   /* public void showPopupPayment() {
        final Dialog dialog = new Dialog(PaymentActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.swap_cibus_dialog_card);
        ImageView imgClose = dialog.findViewById(R.id.imgClose);
        TextView tvHeader = dialog.findViewById(R.id.tvHeader);
        EditText edAmount = dialog.findViewById(R.id.edAmount);
        EditText edCibusCardno = dialog.findViewById(R.id.edCibusCardno);
        mTerminalType = dialog.findViewById(R.id.terminal_connection_type);
        TextView btnDone = dialog.findViewById(R.id.btnDone);
        POSHandler.getInstance().connectDevice(this);
        btnDone.setOnClickListener(v -> {
            if (TextUtils.isEmpty(edAmount.getText())) {
                showToast("Enter amount");
            } else {

                paymentModel.setAmount(edAmount.getText().toString());
                paymentModel.setPayMethod(arrayList.get(position).itemName);
                paymentModel.setInvoice("0");
                paymentModel.setOrderId(MenuZ.getInstance().getOrderId());
                paymentModel.setReference("123466");
                new Thread(() -> {

                    handler.post(() -> {
                        recyclerPayment.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    });

                }).start();


            }
        });
        //tvHeader.setText(arrayList.get(position).getPayMethodName());
        imgClose.setOnClickListener((View v) -> dialog.dismiss());
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }*/


    private void showToast(String msg) {
        if (!TextUtils.isEmpty(msg))
            MyToast.getInstance(PaymentActivity.this).showAlert(msg);
        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.llPay:
                parseData(paymentamomt);
                break;
            case R.id.tvCardRead:
                startActivity(new Intent(PaymentActivity.this, SwipePaymentActivity.class));
                break;


        }
    }

    //int mYear, mMonth, mDay, mHour, mMinute;
    String cardno = "", month = "", year = "", security = "", ID = "", phone = "", paymethodId = "";

    void showManualcreditDialog(PayMethodsModel payMethodsModel) {

        final Dialog dialog = new Dialog(PaymentActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_manual_credit);
        Button btnDone = dialog.findViewById(R.id.btnDone);
        Switch switchSplit = dialog.findViewById(R.id.switchSplit);
        switchSplit.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                edNoofPayment.setEnabled(true);
                edFrirstP.setEnabled(true);
                edRestPay.setEnabled(true);
            } else {
                edNoofPayment.setEnabled(false);
                edFrirstP.setEnabled(false);
                edRestPay.setEnabled(false);
            }
        });
        edCardno = dialog.findViewById(R.id.edCardno);
        edMonth = dialog.findViewById(R.id.edMonth);
        edYear = dialog.findViewById(R.id.edYear);
        edSecurity = dialog.findViewById(R.id.edSecurity);
        edId = dialog.findViewById(R.id.edId);
        edPhone = dialog.findViewById(R.id.edPhone);
        edNoofPayment = dialog.findViewById(R.id.edNoofPayment);
        edSp = dialog.findViewById(R.id.edSp);
        edFristPay = dialog.findViewById(R.id.edFristPay);
        edFrirstP = dialog.findViewById(R.id.edFrirstP);
        edRestPay = dialog.findViewById(R.id.edRestPay);
        edRest = dialog.findViewById(R.id.edRest);
        btnDone.setOnClickListener(v -> {
            if (checkValidation()) {
                cardno = edCardno.getText().toString().trim();
                month = edMonth.getText().toString().trim();
                year = edYear.getText().toString().trim();
                security = edSecurity.getText().toString();
                ID = edId.getText().toString().trim();
                phone = edPhone.getText().toString().trim();
                new Thread(() -> {
                    getDataManager().updatePaymentmethods(orderId
                            , payMethodsModel.getPayMethodId()
                            , payMethodsModel.getPayMethodFixValue(), payMethodsModel.getPayMethodName()
                            , payMethodsModel.getPayMethodType(), cardno, month, year, security, ID, phone);
                    paymethodId = payMethodsModel.getPayMethodId();
                    paymentModelArrayList = getDataManager().loadPaymentRef(orderId);

                    handler.post(() -> {
                        recyclerPayment.setVisibility(View.VISIBLE);
                        recyclerPayment.setLayoutManager(new LinearLayoutManager(PaymentActivity.this));
                        paymentTypeAdapter = new PaymentTypeAdapter(PaymentActivity.this, paymentModelArrayList);
                        recyclerPayment.setAdapter(paymentTypeAdapter);
                        dialog.dismiss();
                    });
                }).start();

            }
        });
        valuedate = edMonth.getText().toString().trim();
        valueYear = edYear.getText().toString().trim();
        edYear.setOnClickListener(v -> showNuPickerYear());
        dialog.findViewById(R.id.imgClose).setOnClickListener((View v) -> dialog.dismiss());
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    void showCashDialog(PayMethodsModel payMethodsModel) {
        final Dialog dialog = new Dialog(PaymentActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.cash_payment_view);
        EditText edAmount_cash = dialog.findViewById(R.id.edAmount_cash);
        TextView btnDone_cash = dialog.findViewById(R.id.btnDone_cash);

        btnDone_cash.setOnClickListener(v -> {
            cash = "CashPrice";
            stredAmount_cash = edAmount_cash.getText().toString().trim();
            paymentamomt = stredAmount_cash;
            dialog.dismiss();
          /*  if (!stredAmount_cash.equals("")) {
                if (Float.parseFloat(stredAmount_cash) > Float.parseFloat(paymentamomt)) {

                    Toast.makeText(this, "Please enter valid amount. this amount is not real.", Toast.LENGTH_SHORT).show();
                }else {

                }

            }*/
        });
        dialog.findViewById(R.id.img__cash_cancel).setOnClickListener((View v) -> dialog.dismiss());
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }


    public boolean checkValidation() {
        if (TextUtils.isEmpty(edCardno.getText())) {
            showToast("Enter Card no");
            return false;

        } else if (TextUtils.isEmpty(edMonth.getText())) {
            showToast("Enter Date");
            return false;

        } else if (TextUtils.isEmpty(edYear.getText())) {
            showToast("Enter Year");
            return false;

        } else if (TextUtils.isEmpty(edSecurity.getText())) {
            showToast("Enter Security");
            return false;

        } else if (TextUtils.isEmpty(edId.getText())) {
            showToast("Enter Id");
            return false;

        } else if (TextUtils.isEmpty(edPhone.getText())) {
            showToast("Enter Mobile no");
            return false;

        } else if (edPhone.length() > 10) {
            showToast("Enter Valid nu");
            return false;
        }
        return true;
    }

    String valuedate = "";
    String valueYear = "";

    private void showNuPickerDate() {
        final Dialog dialog = new Dialog(PaymentActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.datepicker);
        NumberPicker numberPicker = dialog.findViewById(R.id.nuPicker);
        for (int i = 1; i < 30; i++) {
            numberPicker.setMinValue(1);
            numberPicker.setMaxValue(i + 1);
        }

        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> valuedate = String.valueOf(newVal));

        TextView txtOk = dialog.findViewById(R.id.txtOk);
        TextView txtCancel = dialog.findViewById(R.id.txtCancel);
        txtOk.setOnClickListener(v -> {
            edMonth.setText(valuedate);
            dialog.dismiss();
        });

        txtCancel.setOnClickListener(v -> {
            edMonth.setText("");
            dialog.dismiss();
        });
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    private void showNuPickerYear() {
        final Dialog dialog = new Dialog(PaymentActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.datepicker);
        NumberPicker numberPicker = dialog.findViewById(R.id.nuPicker);
        TextView txtPickdate = dialog.findViewById(R.id.txtPickdate);
        txtPickdate.setText(R.string.pick_year);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int yearafter = year + 30;
        for (int i = year; i < yearafter; i++) {
            numberPicker.setMinValue(year);
            numberPicker.setMaxValue(i + 1);
        }

        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> valueYear = String.valueOf(newVal));

        TextView txtOk = dialog.findViewById(R.id.txtOk);
        TextView txtCancel = dialog.findViewById(R.id.txtCancel);
        txtOk.setOnClickListener(v -> {
            String removeCurrency = String.valueOf(valueYear).substring(2);

            edYear.setText(removeCurrency);
            dialog.dismiss();
        });

        txtCancel.setOnClickListener(v -> {
            edYear.setText("");
            dialog.dismiss();
        });
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    @SuppressLint("StaticFieldLeak")
    public void parseData(String txtGrandtotal) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<OrderItemModel> selectedItemList = getDataManager().getAllorderItem(MenuZ.getInstance().getOrderId());
                Log.d("selec", "" + selectedItemList.size());
                // @SuppressLint("HardwareIds") String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                OrderNewjsonModel.OrdersBean orderJsonModel = new OrderNewjsonModel.OrdersBean();
                orderJsonModel.setOrderID(orderId);
                orderJsonModel.setOrderEmployeeID(MenuZ.getInstance().getEmployeeId());
                orderJsonModel.setOrderStart("");
                orderJsonModel.setOrderTableID(tableId);
                orderJsonModel.setOrderDiscountPCT("");
                orderJsonModel.setOrderDiners("");
                orderJsonModel.setOrderRemark("");
                orderJsonModel.setOrderTerminalID(MenuZ.getInstance().getTerminalId());

                List<OrderNewjsonModel.OrdersBean.ItemsBean> itemsBeanList = new ArrayList<>();
                for (int i = 0; i < selectedItemList.size(); i++) {
                    OrderItemModel orderItemModel = selectedItemList.get(i);
                    OrderNewjsonModel.OrdersBean.ItemsBean itemsBean = new OrderNewjsonModel.OrdersBean.ItemsBean();
                    itemsBean.setItemChoiceID("");
                    itemsBean.setItemEmployeeID(MenuZ.getInstance().getEmployeeId());
                    itemsBean.setItemID(orderItemModel.getItemId());
                    itemsBean.setItemDiner("");
                    itemsBean.setItemCourse("");
                    itemsBean.setItemAutoID(orderItemModel.getItemAutoID());

                    itemsBean.setItemPrice(orderItemModel.getItemPrice());
                    itemsBean.setItemPriceID("");
                    itemsBean.setItemOrderID("");
                    itemsBean.setItemTerminalID(MenuZ.getInstance().getTerminalId());
                    itemsBean.setItemRemark("");
                    itemsBean.setItemQuantity(orderItemModel.getCountPrice());


                    List<OrderPreparationModel> orderPreparationModels = getDataManager().loadAllSelectedPreprations(MenuZ.getInstance().getOrderId(), orderItemModel.getItemPrimaryKey());
                    Log.d("orderPreparationModels", "" + orderPreparationModels.size());
                    OrderNewjsonModel.OrdersBean.ItemsBean.ItemprepsBean itemprepsBean = new OrderNewjsonModel.OrdersBean.ItemsBean.ItemprepsBean();

                    for (int j = 0; j < orderPreparationModels.size(); j++) {
                        OrderPreparationModel orderPreparationModel = orderPreparationModels.get(j);
                        itemprepsBean.setIpreparationPrefixID(orderPreparationModel.getPrefixId());
                        itemprepsBean.setIpreparationID(orderPreparationModel.getPreparationId());
                        itemsBeans.add(itemprepsBean);
                    }
                    itemsBean.setItempreps(itemsBeans);
                    itemsBeanList.add(itemsBean);

                    // itemsBean1.setItempreps(itemsBeans);

                    List<OrderAddOnChild> cartSeltedAddonList = getDataManager().loadAllSelectedAddons(orderItemModel.getItemPrimaryKey(), MenuZ.getInstance().getOrderId());
                    List<OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean> addonsBeans = new ArrayList<>();
                    for (int k = 0; k < cartSeltedAddonList.size(); k++) {
                        OrderAddOnChild orderAddOnChild = cartSeltedAddonList.get(k);
                        OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean addonsBean = new OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean();
                        addonsBean.setAddonChoiceID("");
                        addonsBean.setAddonCourse("");
                        addonsBean.setAddonAutoID(orderAddOnChild.getAddonAutoID());
                        addonsBean.setAddonDiner("");
                        addonsBean.setAddonEmployeeID(MenuZ.getInstance().getEmployeeId());
                        addonsBean.setAddonID(orderAddOnChild.getAddonId());
                        addonsBean.setAddonOrderID("");
                        addonsBean.setAddonQuantity("");
                        addonsBean.setAddonRemark("");
                        addonsBean.setAddonTerminalID(MenuZ.getInstance().getTerminalId());
                        addonsBean.setAddonPrice("");

                        List<OrderPreparationAddonModel> orderPreparationAddonModels = getDataManager().loadallAddongroup(orderAddOnChild.getItemIdAddon(), orderAddOnChild.getAddonId(), MenuZ.getInstance().getOrderId(), orderItemModel.getItemPrimaryKey());
                        for (int l = 0; l < orderPreparationAddonModels.size(); l++) {
                            OrderPreparationAddonModel orderPreparationAddonModel = orderPreparationAddonModels.get(l);
                            OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean addonprepsBean = new OrderNewjsonModel.OrdersBean.ItemsBean.AddonsBean.AddonprepsBean();
                            addonprepsBean.setApreparationID(orderPreparationAddonModel.getPreparationId());
                            addonprepsBean.setApreparationPrefixID(orderPreparationAddonModel.getPreparationIsPrefixed());
                            addonprepsBeanArrayList.add(addonprepsBean);
                        }
                        addonsBean.setAddonpreps(addonprepsBeanArrayList);


                        addonsBeans.add(addonsBean);
                    }
                    itemsBean.setAddons(addonsBeans);
                    orderJsonModel.setItems(itemsBeanList);
                }
                JSONObject jObjectType = new JSONObject();
                // put elements into the object as a key-value pair
                try {
                    jObjectType.put("cardid", cardno);
                    jObjectType.put("expdate", "0" + month + year);
                    jObjectType.put("ident", "");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("noPayments", "");
                    jsonObject.put("firstAmount", "");
                    jsonObject.put("noFirstPayment", "");
                    jObjectType.put("splitter", jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // 2nd array for user information
                // Create Json Object using Facebook Data
                List<OrderJsonModel.OrdersBean.PaymentsBean> paymentsBeans = new ArrayList<>();
                OrderJsonModel.OrdersBean.PaymentsBean paymentsBean = new OrderJsonModel.OrdersBean.PaymentsBean();

                String ttlPrice = txtGrandtotal.concat("$");
                if (cash.equals("")) {
                    paymentsBean.setPaymentApproved("");
                    paymentsBean.setPaymentID(paymethodId);
                    //paymentsBean.setPaymentIdentification(jObjectType.toString());
                    paymentsBean.setPaymentIdentification("");
                    if (ttlPrice.contains("$")) {
                        String da = ttlPrice.replace("$", "");
                        paymentsBean.setPaymentPrice(da);
                        paymentsBean.setPaymentTerminalID(MenuZ.getInstance().getTerminalId());
                        paymentsBeans.add(paymentsBean);
                        orderJsonModel.setPayments(paymentsBeans);
                    }
                } else {
                    paymentsBean.setPaymentApproved("");
                    paymentsBean.setPaymentID("");
                    //paymentsBean.setPaymentIdentification(jObjectType.toString());
                    paymentsBean.setPaymentIdentification("");
                    if (ttlPrice.contains("$")) {
                        String da = ttlPrice.replace("$", "");
                        paymentsBean.setPaymentPrice(da);
                        paymentsBean.setPaymentTerminalID("");
                        paymentsBeans.add(paymentsBean);
                        orderJsonModel.setPayments(paymentsBeans);
                    }
                }

                List<OrderNewjsonModel.OrdersBean> ordersBeans = new ArrayList<>();
                ordersBeans.add(orderJsonModel);
                orderJsonModel1.setOrders(ordersBeans);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Gson gson = new Gson();
                String jsonString = gson.toJson(orderJsonModel1);
                try {
                    JSONObject request = new JSONObject(jsonString);
                    Log.d("json", "" + request);
                    String json = request.toString().trim();
                    sendJson(json, txtGrandtotal);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    private void sendJson(String jsonObject, String txtGrandtotal) {
        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(PaymentActivity.this, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    sendJson(jsonObject, txtGrandtotal);
                }
            }).show();
        }
        new HttpTask(new HttpTask.Builder(PaymentActivity.this, "syncclient/0/admin/admin/" + jsonObject, new HttpResponceListner.Listener() {
            @Override
            public void onResponse(String response, String apiName) {
                Log.e("SyncClient ResPonce", "onResponse: " + response);
                try {
                    JSONObject js = new JSONObject(response);
                    JSONArray jsonArray = js.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String status = jsonObject.getString("success");
                        if (status.equals("true")) {
                            JSONObject jsonObjectOrder = jsonObject.getJSONObject("orders");
                            String orderID = jsonObjectOrder.getString("orderID");
                            MenuZ.getInstance().setOrderId(orderID);
                            Toast.makeText(PaymentActivity.this, "Order Submit Successfully.", Toast.LENGTH_SHORT).show();
                            new Thread(() -> {
                                getDataManager().updateOrderAmt(txtGrandtotal, orderID);
                                PayMethodsModel payMethodsModels = getDataManager().loadSinglePayment(orderID);
                                newOrderModel = getDataManager().loadDataOnOrderId(orderID);
                                if (payMethodsModels != null)
                                    handler.post(() -> {
                                        if (!payMethodsModels.getPaidAmt().isEmpty()) {
                                            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + payMethodsModels.getPaidAmt());
                                            String valueAmt = txtTotalCount.getText().toString().trim();
                                            float val = Float.parseFloat(valueAmt);
                                            float newval = Float.parseFloat(payMethodsModels.getPaidAmt());
                                            float Totalval = val - newval;
                                            txtTotalCount.setText(String.valueOf(Totalval));
                                        }
                                    });
                            }).start();
                            onBackPressed();
                        } else {
                            String errorMessage = jsonObject.getString("errorMessage");
                            Toast.makeText(PaymentActivity.this, "" + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void ErrorListener(VolleyError error) {
            }
        }).setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(true))
                .execute("");
    }


}