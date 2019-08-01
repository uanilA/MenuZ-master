package com.menuz.ui.Payment;

import android.app.Activity;
import android.util.Log;
import com.acs.audiojack.AudioJackReader;
import com.acs.audiojack.Result;

public class Acr35 extends Acr31 {
    /* access modifiers changed from: private */
    public Acr35 _acr35;
    protected byte[] _apdu;
    protected boolean _atrForDisplay = false;
    protected byte[] _piccAtr;
    protected boolean _piccAtrReady;
    protected int _piccCardType = 143;
    protected byte[] _piccResponseApdu;
    protected boolean _piccResponseApduReady;
    protected int _piccTimeout = 1;
    protected boolean _transmitCommand;
    /* access modifiers changed from: private */
    public OnPICCDataAvailableListener onPICCDataAvailableListener;

    public interface OnPICCDataAvailableListener {
        void UpdateUI(Acr35 acr35, ResponseType responseType);

        void UpdateUI(String str, String str2);
    }

    private class OnPiccAtrAvailableListener implements AudioJackReader.OnPiccAtrAvailableListener {
        private OnPiccAtrAvailableListener() {
        }

        public void onPiccAtrAvailable(AudioJackReader audioJackReader, byte[] bArr) {
            Log.i("Explore Smart Card", "PICC Atr Available");
            synchronized (Acr35.this._responseEvent) {
                Acr35.this._piccAtr = new byte[bArr.length];
                System.arraycopy(bArr, 0, Acr35.this._piccAtr, 0, bArr.length);
                Acr35.this._piccAtrReady = true;
                Acr35.this._responseEvent.notifyAll();
            }
        }
    }

    private class OnPiccResponseApduAvailableListener implements AudioJackReader.OnPiccResponseApduAvailableListener {
        private OnPiccResponseApduAvailableListener() {
        }

        public void onPiccResponseApduAvailable(AudioJackReader audioJackReader, byte[] bArr) {
            synchronized (Acr35.this._responseEvent) {
                Acr35.this._piccResponseApdu = new byte[bArr.length];
                System.arraycopy(bArr, 0, Acr35.this._piccResponseApdu, 0, bArr.length);
                Acr35.this._piccResponseApduReady = true;
                Acr35.this._responseEvent.notifyAll();
            }
        }
    }

    protected class OnResetCompleteListener implements AudioJackReader.OnResetCompleteListener {
        protected OnResetCompleteListener() {
        }

        public void onResetComplete(AudioJackReader audioJackReader) {
            Log.i("Explore Smart Card", "reset completed");
            Acr35.this._piccResponseApduReady = false;
            Acr35.this._piccAtrReady = false;
            Acr35.this._resultReady = false;
            Acr35.this.powerOn();
        }
    }

    protected class OnResultAvailableListener implements AudioJackReader.OnResultAvailableListener {
        protected OnResultAvailableListener() {
        }

        public void onResultAvailable(AudioJackReader audioJackReader, Result result) {
            synchronized (Acr35.this._responseEvent) {
                Acr35.this._result = result;
                Acr35.this._resultReady = true;
                Acr35.this._responseEvent.notifyAll();
            }
        }
    }

    public enum ResponseType {
        ATR,
        RESPONSE,
        ERROR
    }

    public Acr35(Activity activity, OnPICCDataAvailableListener onPICCDataAvailableListener2) {
        super(activity);
        Log.i("Explore Smart Card", "Custom Listener Set");
        this.onPICCDataAvailableListener = onPICCDataAvailableListener2;
        this._audioJackReader.setOnStatusAvailableListener(new OnStatusAvailableListener());
        this._audioJackReader.setOnPiccAtrAvailableListener(new OnPiccAtrAvailableListener());
        this._audioJackReader.setOnPiccResponseApduAvailableListener(new OnPiccResponseApduAvailableListener());
        this._audioJackReader.setOnResultAvailableListener(new OnResultAvailableListener());
        this._acr35 = this;
    }

    public String getAtrValue() {
        return toHexString(this._piccAtr);
    }

    public String getResponseValue() {
        return toHexString(this._piccResponseApdu);
    }

    public void transmitAPDU(String str) {
        Log.i("Explore Smart Card", "Transmit APDU Initialize");
        start();
        this._transmitCommand = true;
        this._apdu = toByteArray(str);
        this._audioJackReader.reset(new OnResetCompleteListener());
    }

    /* access modifiers changed from: private */
    public void transmit() {
        this._piccResponseApduReady = false;
        this._resultReady = false;
        this._transmitCommand = false;
        if (!this._audioJackReader.piccTransmit(this._piccTimeout, this._apdu)) {
            this.onPICCDataAvailableListener.UpdateUI("Error", "The request cannot be queued.");
        } else {
            showPiccResponseApdu();
        }
    }

    public void getAtr() {
        start();
        Log.i("Explore Smart Card", "Get Atr Fired up");
        this._atrForDisplay = true;
        this._audioJackReader.reset(new OnResetCompleteListener());
    }

    public void powerOn() {
        Log.i("Explore Smart Card", "Power On Fired up");
        new Thread(new Runnable() {
            public void run() {
                Acr35.this._piccAtrReady = false;
                Acr35.this._resultReady = false;
                if (!Acr35.this._audioJackReader.piccPowerOn(Acr35.this._piccTimeout, Acr35.this._piccCardType)) {
                    Log.i("Explore Smart Card", "Power On Error!");
                    Acr35.this.onPICCDataAvailableListener.UpdateUI("Error", "The request cannot be queued.");
                    return;
                }
                Acr35.this.showPiccAtr();
            }
        }).start();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:2|3|(2:7|8)|9|10|(1:12)(2:13|(1:15)(1:16))|17|18) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0016  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showPiccAtr() {
        /*
            r4 = this;
            java.lang.Object r0 = r4._responseEvent
            monitor-enter(r0)
            boolean r1 = r4._piccAtrReady     // Catch:{ all -> 0x0041 }
            if (r1 != 0) goto L_0x0012
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x0041 }
            if (r1 != 0) goto L_0x0012
            java.lang.Object r1 = r4._responseEvent     // Catch:{ InterruptedException -> 0x0012 }
            r2 = 10000(0x2710, double:4.9407E-320)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x0012 }
        L_0x0012:
            boolean r1 = r4._piccAtrReady     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x0021
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0041 }
            com.acs.mobilemate.Readers.Acr35$1 r2 = new com.acs.mobilemate.Readers.Acr35$1     // Catch:{ all -> 0x0041 }
            r2.<init>()     // Catch:{ all -> 0x0041 }
            r1.runOnUiThread(r2)     // Catch:{ all -> 0x0041 }
            goto L_0x003a
        L_0x0021:
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x0030
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0041 }
            com.acs.mobilemate.Readers.Acr35$2 r2 = new com.acs.mobilemate.Readers.Acr35$2     // Catch:{ all -> 0x0041 }
            r2.<init>()     // Catch:{ all -> 0x0041 }
            r1.runOnUiThread(r2)     // Catch:{ all -> 0x0041 }
            goto L_0x003a
        L_0x0030:
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0041 }
            com.acs.mobilemate.Readers.Acr35$3 r2 = new com.acs.mobilemate.Readers.Acr35$3     // Catch:{ all -> 0x0041 }
            r2.<init>()     // Catch:{ all -> 0x0041 }
            r1.runOnUiThread(r2)     // Catch:{ all -> 0x0041 }
        L_0x003a:
            r1 = 0
            r4._piccAtrReady = r1     // Catch:{ all -> 0x0041 }
            r4._resultReady = r1     // Catch:{ all -> 0x0041 }
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            return
        L_0x0041:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.acs.mobilemate.Readers.Acr35.showPiccAtr():void");
    }

    private byte[] toByteArray(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if ((charAt >= '0' && charAt <= '9') || ((charAt >= 'A' && charAt <= 'F') || (charAt >= 'a' && charAt <= 'f'))) {
                i++;
            }
        }
        boolean z = true;
        byte[] bArr = new byte[((i + 1) / 2)];
        int i3 = 0;
        for (int i4 = 0; i4 < str.length(); i4++) {
            char charAt2 = str.charAt(i4);
            int i5 = (charAt2 < '0' || charAt2 > '9') ? (charAt2 < 'A' || charAt2 > 'F') ? (charAt2 < 'a' || charAt2 > 'f') ? -1 : (charAt2 - 'a') + 10 : (charAt2 - 'A') + 10 : charAt2 - '0';
            if (i5 >= 0) {
                if (z) {
                    bArr[i3] = (byte) (i5 << 4);
                } else {
                    bArr[i3] = (byte) (i5 | bArr[i3]);
                    i3++;
                }
                z = !z;
            }
        }
        return bArr;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:2|3|(2:7|8)|9|10|(1:12)(2:13|(1:15)(1:16))|17|18) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0016  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void showPiccResponseApdu() {
        /*
            r4 = this;
            java.lang.Object r0 = r4._responseEvent
            monitor-enter(r0)
            boolean r1 = r4._piccResponseApduReady     // Catch:{ all -> 0x0041 }
            if (r1 != 0) goto L_0x0012
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x0041 }
            if (r1 != 0) goto L_0x0012
            java.lang.Object r1 = r4._responseEvent     // Catch:{ InterruptedException -> 0x0012 }
            r2 = 10000(0x2710, double:4.9407E-320)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x0012 }
        L_0x0012:
            boolean r1 = r4._piccResponseApduReady     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x0021
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0041 }
            com.acs.mobilemate.Readers.Acr35$4 r2 = new com.acs.mobilemate.Readers.Acr35$4     // Catch:{ all -> 0x0041 }
            r2.<init>()     // Catch:{ all -> 0x0041 }
            r1.runOnUiThread(r2)     // Catch:{ all -> 0x0041 }
            goto L_0x003a
        L_0x0021:
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x0030
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0041 }
            com.acs.mobilemate.Readers.Acr35$5 r2 = new com.acs.mobilemate.Readers.Acr35$5     // Catch:{ all -> 0x0041 }
            r2.<init>()     // Catch:{ all -> 0x0041 }
            r1.runOnUiThread(r2)     // Catch:{ all -> 0x0041 }
            goto L_0x003a
        L_0x0030:
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0041 }
            com.acs.mobilemate.Readers.Acr35$6 r2 = new com.acs.mobilemate.Readers.Acr35$6     // Catch:{ all -> 0x0041 }
            r2.<init>()     // Catch:{ all -> 0x0041 }
            r1.runOnUiThread(r2)     // Catch:{ all -> 0x0041 }
        L_0x003a:
            r1 = 0
            r4._piccResponseApduReady = r1     // Catch:{ all -> 0x0041 }
            r4._resultReady = r1     // Catch:{ all -> 0x0041 }
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            return
        L_0x0041:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.acs.mobilemate.Readers.Acr35.showPiccResponseApdu():void");
    }
}
