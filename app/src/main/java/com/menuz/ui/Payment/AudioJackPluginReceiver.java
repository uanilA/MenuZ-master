package com.menuz.ui.Payment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

import com.menuz.R;

public class AudioJackPluginReceiver extends BroadcastReceiver {
    private Activity _activity = null;
    private Boolean _isCreated = Boolean.valueOf(true);
    private Activity _mainActivity;
    private boolean _priorState;
    private Acr31 _reader;
    private boolean _state;
    private AlertDialog dialog;
    private OnReaderPlugin onReaderPluginListener;

    public interface OnReaderPlugin {
        void PostUI(boolean z);
    }

    public AudioJackPluginReceiver(Activity activity, Acr31 acr31, OnReaderPlugin onReaderPlugin) {
        this._reader = acr31;
        this.onReaderPluginListener = onReaderPlugin;
        this._mainActivity = activity;
        Builder builder = new Builder(activity);
        builder.setMessage(R.string.message_reader_removed).setTitle(R.string.warning).setPositiveButton("OK", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        this.dialog = builder.create();
    }

    public void setActivity(Activity activity) {
        this._activity = activity;
    }

    public void onReceive(Context context, Intent intent) {
        boolean z = true;
        if (intent.getExtras().getInt("state") != 1) {
            z = false;
        }
        this._state = z;
        if (this._state) {
            this._isCreated = Boolean.valueOf(false);
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
        } else if (!this._isCreated.booleanValue()) {
            this.dialog.show();
            this._reader.stop();
        }
        this.onReaderPluginListener.PostUI(this._state);
    }

    public void setReader(Acr31 acr31) {
        this._reader = acr31;
    }
}
