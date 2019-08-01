package com.menuz.ui.Payment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SubActivityAudioJackPlugin extends BroadcastReceiver {
    private Activity _activity = null;
    private boolean _priorState;
    private Acr31 _reader;
    private boolean _state;
    private AlertDialog dialog;

    public SubActivityAudioJackPlugin(Activity activity, Acr31 acr31) {
        this._reader = acr31;
        this._activity = activity;
    }

    public void setActivity(Activity activity) {
        this._activity = activity;
    }

    public void onReceive(Context context, Intent intent) {
        int i = intent.getExtras().getInt("state");
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        this._state = z;
        if (this._state) {
            this._reader.start();
            this._reader.sleep();
            return;
        }
        this._reader.stop();
        this._activity.finish();
    }
}
