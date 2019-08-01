package com.menuz.ui.Payment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.media.AudioManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;
import com.acs.audiojack.AesTrackData;
import com.acs.audiojack.AudioJackReader;
import com.acs.audiojack.DukptReceiver;
import com.acs.audiojack.DukptTrackData;
import com.acs.audiojack.Result;
import com.acs.audiojack.Status;
import com.acs.audiojack.Track1Data;
import com.acs.audiojack.Track2Data;
import com.acs.audiojack.TrackData;
import com.menuz.R;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Acr31 {
    public static final String DEFAULT_AES_KEY_STRING = "4E 61 74 68 61 6E 2E 4C 69 20 54 65 64 64 79 20";
    public static final String DEFAULT_IKSN_STRING = "FF FF 98 76 54 32 10 E0 00 00";
    public static final String DEFAULT_IPEK_STRING = "6A C2 92 FA A1 31 5B 4D 85 8A B3 A3 D7 D5 93 3A";
    public static final String DEFAULT_MASTER_KEY_STRING = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
    protected Activity _activity;
    protected AudioJackReader _audioJackReader;
    protected AudioManager _audioManager;
    protected byte[] _customId;
    protected boolean _customIdReady;
    protected byte[] _deviceId;
    protected boolean _deviceIdReady;
    protected String _firmwareVersion;
    protected boolean _firmwareVersionReady;
    protected boolean _isConnected;
    protected int _iswipeCount;
    protected byte[] _masterKey = new byte[16];
    protected ProgressDialog _progressDialog;
    protected String _rawData;
    protected Acr31 _reader;
    protected Object _responseEvent = new Object();
    protected Result _result;
    protected boolean _resultReady;
    protected Status _status;
    protected boolean _statusReady;
    protected String _t1CardNumber;
    protected String _t1DiscretionaryData;
    protected String _t1ExpirationDate;
    protected String _t1Jis2Data;
    protected String _t1Name;
    protected String _t2CardNumber;
    protected String _t2DiscretionaryData;
    protected String _t2ExpirationDate;
    protected String _t2ServiceCode;
    protected OnReaderDataAvailableListener onReaderDataAvailableListener;
    protected OnReaderErrorListener onReaderErrorListener;

    public enum AvailableData {
        RAW_DATA,
        TRACT_DATA,
        FIRMWARE,
        BATTERY_LEVEL,
        CUSTOM_ID,
        SET_CUSTOM_ID,
        READER_ID,
        RESET_DONE
    }

    private class OnAuthCompleteListener implements AudioJackReader.OnAuthCompleteListener {
        private OnAuthCompleteListener() {
        }

        public void onAuthComplete(AudioJackReader audioJackReader, int i) {
            Log.i("Settings", "Authentication Complete");
            if (i == 0) {
                Log.i("Settings", "Success");
                Acr31.this._resultReady = false;
                if (!audioJackReader.setCustomId(Acr31.this._customId)) {
                    Acr31.this.onReaderErrorListener.UpdateUI("Error", "The request cannot be queued.");
                } else {
                    boolean access$900 = Acr31.this.showResult();
                }
            } else if (i == 2) {
                Log.i("Settings", "Timeout");
                Acr31.this._activity.runOnUiThread(new Runnable() {
                    @SuppressLint("WrongConstant")
                    public void run() {
                        Toast.makeText(Acr31.this._activity, "The authentication timed out.", 1).show();
                    }
                });
            } else {
                Log.i("Settings", "Authentication Fail");
                Acr31.this._activity.runOnUiThread(new Runnable() {
                    @SuppressLint("WrongConstant")
                    public void run() {
                        Toast.makeText(Acr31.this._activity, "The authentication failed.", 1).show();
                    }
                });
            }
        }
    }

    protected class OnCustomIdAvailableListener implements AudioJackReader.OnCustomIdAvailableListener {
        protected OnCustomIdAvailableListener() {
        }

        public void onCustomIdAvailable(AudioJackReader audioJackReader, byte[] bArr) {
            synchronized (Acr31.this._responseEvent) {
                Log.i("CustomId", "Available");
                Acr31.this._customId = new byte[bArr.length];
                System.arraycopy(bArr, 0, Acr31.this._customId, 0, bArr.length);
                Acr31.this._customIdReady = true;
                Acr31.this._responseEvent.notifyAll();
            }
        }
    }

    protected class OnDeviceIdAvailableListener implements AudioJackReader.OnDeviceIdAvailableListener {
        protected OnDeviceIdAvailableListener() {
        }

        public void onDeviceIdAvailable(AudioJackReader audioJackReader, byte[] bArr) {
            synchronized (Acr31.this._responseEvent) {
                Log.i("DeviceId", "Available");
                Acr31.this._deviceId = new byte[bArr.length];
                System.arraycopy(bArr, 0, Acr31.this._deviceId, 0, bArr.length);
                Acr31.this._deviceIdReady = true;
                Acr31.this._responseEvent.notifyAll();
            }
        }
    }

    private class OnFirmwareVersionAvailableListener implements AudioJackReader.OnFirmwareVersionAvailableListener {
        private OnFirmwareVersionAvailableListener() {
        }

        public void onFirmwareVersionAvailable(AudioJackReader audioJackReader, String str) {
            synchronized (Acr31.this._responseEvent) {
                Log.i("Firmware", "Available");
                Acr31.this._firmwareVersion = str;
                Acr31.this._firmwareVersionReady = true;
                Acr31.this._responseEvent.notifyAll();
            }
        }
    }

    protected class OnRawDataAvailableListener implements AudioJackReader.OnRawDataAvailableListener {
        private String hexString;

        protected OnRawDataAvailableListener() {
        }

        public void onRawDataAvailable(AudioJackReader audioJackReader, byte[] bArr) {
            Log.i("Explore Magnetic Card", "Raw Data Available");
            StringBuilder sb = new StringBuilder();
            sb.append(Acr31.this.toHexString(bArr));
            sb.append(audioJackReader.verifyData(bArr) ? " (Checksum OK)" : " (Checksum ERROR)");
            this.hexString = sb.toString();
            Acr31.this._rawData = this.hexString;
            if (bArr.length > 7) {
                Acr31.this._activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Acr31.this.onReaderDataAvailableListener.UpdateUI(Acr31.this._reader, AvailableData.RAW_DATA);
                    }
                });
            }
        }
    }

    public interface OnReaderDataAvailableListener {
        void UpdateUI(Acr31 acr31, AvailableData availableData);
    }

    public interface OnReaderErrorListener {
        void UpdateUI(String str, String str2);
    }

    protected class OnResetCompleteListener implements AudioJackReader.OnResetCompleteListener {
        protected ResetType resetType = ResetType.NOTHING;

        public OnResetCompleteListener() {
            Log.i("Explore Smart Card", "Reset was completed");
        }

        public OnResetCompleteListener(ResetType resetType2) {
            this.resetType = resetType2;
        }

        public void onResetComplete(AudioJackReader audioJackReader) {
            boolean z;
            Log.i("ResetComplete", "Was called");
            StringBuilder sb = new StringBuilder();
            sb.append("Type: ");
            sb.append(this.resetType.toString());
            Log.i("ResetComplete", sb.toString());
            boolean z2 = false;
            Acr31.this._firmwareVersionReady = false;
            Acr31.this._statusReady = false;
            Acr31.this._customIdReady = false;
            Acr31.this._deviceIdReady = false;
            Acr31.this._resultReady = false;
            switch (this.resetType) {
                case START_UP:
                    try {
                        if (!Acr31.this._audioJackReader.getFirmwareVersion()) {
                            Acr31.this.showRequestQueueError();
                            return;
                        } else {
                            Acr31.this.showFirmwareVersion();
                            return;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                case EXPLORE_MAGNETIC_CARD:
                    Log.i("Explore Magnetic Card", "Reset Complete Was called");
                    Acr31.this._audioJackReader.setOnTrackDataAvailableListener(new OnTrackDataAvailableListener());
                    Acr31.this._audioJackReader.setOnRawDataAvailableListener(new OnRawDataAvailableListener());
                    Acr31.this._activity.runOnUiThread(new Runnable() {
                        public void run() {
                            Acr31.this.onReaderDataAvailableListener.UpdateUI(Acr31.this._reader, AvailableData.RESET_DONE);
                        }
                    });
                    return;
                case EXPLORE_DEVICE:
                    if (!Acr31.this._audioJackReader.getFirmwareVersion()) {
                        Acr31.this.onReaderErrorListener.UpdateUI("Error", "The request cannot be queued.");
                        z = false;
                    } else {
                        z = Acr31.this.showFirmwareVersion();
                    }
                    if (z) {
                        if (!Acr31.this._audioJackReader.getStatus()) {
                            Acr31.this.onReaderErrorListener.UpdateUI("Error", "The request cannot be queued.");
                            z = false;
                        } else {
                            z = Acr31.this.showStatus();
                        }
                    }
                    if (!z) {
                        z2 = z;
                    } else if (!Acr31.this._audioJackReader.getDeviceId()) {
                        Acr31.this.onReaderErrorListener.UpdateUI("Error", "The request cannot be queued.");
                    } else {
                        z2 = Acr31.this.showDeviceId();
                    }
                    if (!z2) {
                        return;
                    }
                    if (!Acr31.this._audioJackReader.getCustomId()) {
                        Acr31.this.onReaderErrorListener.UpdateUI("Error", "The request cannot be queued.");
                        return;
                    } else {
                        Acr31.this.showCustomId();
                        return;
                    }
                case SETTINGS:
                    audioJackReader.authenticate(Acr31.this._masterKey, new OnAuthCompleteListener());
                    return;
                default:
                    return;
            }
        }
    }

    private class OnResultAvailableListener implements AudioJackReader.OnResultAvailableListener {
        private OnResultAvailableListener() {
        }

        public void onResultAvailable(AudioJackReader audioJackReader, Result result) {
            Log.i("Settings", "Result is Available");
            synchronized (Acr31.this._responseEvent) {
                Acr31.this._result = result;
                Acr31.this._resultReady = true;
                Acr31.this._responseEvent.notifyAll();
            }
        }
    }

    protected class OnStatusAvailableListener implements AudioJackReader.OnStatusAvailableListener {
        protected OnStatusAvailableListener() {
        }

        public void onStatusAvailable(AudioJackReader audioJackReader, Status status) {
            synchronized (Acr31.this._responseEvent) {
                Log.i("Status", "Available");
                Acr31.this._status = status;
                Acr31.this._statusReady = true;
                Acr31.this._responseEvent.notifyAll();
            }
        }
    }

    protected class OnTrackDataAvailableListener implements AudioJackReader.OnTrackDataAvailableListener {
        private byte[] aesKey = new byte[16];
        private String batteryStatus;
        private DukptReceiver dukptReceiver = new DukptReceiver();
        private byte[] iksn = new byte[10];
        private byte[] ipek = new byte[16];
        private String keySerialNumber;
        /* access modifiers changed from: private */
        public Track1Data track1Data;
        private String track1Mac;
        /* access modifiers changed from: private */
        public Track1Data track1MaskedData;
        /* access modifiers changed from: private */
        public Track2Data track2Data;
        private String track2Mac;
        /* access modifiers changed from: private */
        public Track2Data track2MaskedData;

        protected OnTrackDataAvailableListener() {
        }

        public void onTrackDataAvailable(AudioJackReader audioJackReader, TrackData trackData) {
            Log.i("Explore Magnetic Card", "Track Data Available");
            this.track1Data = new Track1Data();
            this.track2Data = new Track2Data();
            this.track1MaskedData = new Track1Data();
            this.track2MaskedData = new Track2Data();
            this.track1Mac = "";
            this.track2Mac = "";
            this.batteryStatus = Acr31.this.toBatteryStatusString(trackData.getBatteryStatus());
            this.keySerialNumber = "";
            Acr31.this.toByteArray(Acr31.DEFAULT_IKSN_STRING, this.iksn);
            Acr31.this.toByteArray(Acr31.DEFAULT_IPEK_STRING, this.ipek);
            Acr31.this.toByteArray(Acr31.DEFAULT_AES_KEY_STRING, this.aesKey);
            if (trackData.getTrack1ErrorCode() == 0 && trackData.getTrack2ErrorCode() == 0) {
                if (trackData instanceof AesTrackData) {
                    Log.i("Explore Magnetic Card", "AES TRACK DATA");
                    showAesTrackData((AesTrackData) trackData);
                } else if (trackData instanceof DukptTrackData) {
                    Log.i("Explore Magnetic Card", "DUKP TRACK DATA");
                    showDukptTrackData((DukptTrackData) trackData);
                }
                return;
            }
            Acr31.this._activity.runOnUiThread(new Runnable() {
                public void run() {
                   // Acr31.this.onReaderErrorListener.UpdateUI("Error", String.format(Acr31.this._activity.getResources().getString(R.string.message_track_data_error_corrupted), new Object[0]));
                }
            });
            Log.i("Explore Magnetic Card", "ERROR");
            showTrackData();
        }

        private void showAesTrackData(AesTrackData aesTrackData) {
            try {
                byte[] aesDecrypt = Acr31.this.aesDecrypt(this.aesKey, aesTrackData.getTrackData());
                if (!Acr31.this._audioJackReader.verifyData(aesDecrypt)) {
                    Acr31.this._activity.runOnUiThread(new Runnable() {
                        public void run() {
                            Acr31.this.onReaderErrorListener.UpdateUI("Error", String.format(Acr31.this._activity.getResources().getString(R.string.message_track_data_error_checksum), new Object[0]));
                        }
                    });
                    Log.i("Explore Magnetic Card", "Check Sum ERROR");
                    showTrackData();
                    return;
                }
                this.track1Data.fromByteArray(aesDecrypt, 0, aesTrackData.getTrack1Length());
                this.track2Data.fromByteArray(aesDecrypt, 79, aesTrackData.getTrack2Length());
                showTrackData();
            } catch (GeneralSecurityException unused) {
                Acr31.this._activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Acr31.this.onReaderErrorListener.UpdateUI("Error", String.format(Acr31.this._activity.getResources().getString(R.string.message_track_data_error_decrypted), new Object[0]));
                    }
                });
                Log.i("Explore Magnetic Card", "ERROR");
                showTrackData();
            }
        }

        private void showDukptTrackData(DukptTrackData dukptTrackData) {
            this.keySerialNumber = Acr31.this.toHexString(dukptTrackData.getKeySerialNumber());
            this.track1Mac = Acr31.this.toHexString(dukptTrackData.getTrack1Mac());
            this.track2Mac = Acr31.this.toHexString(dukptTrackData.getTrack2Mac());
            this.track1MaskedData.fromString(dukptTrackData.getTrack1MaskedData());
            this.track2MaskedData.fromString(dukptTrackData.getTrack2MaskedData());
            if (!DukptReceiver.compareKeySerialNumber(this.iksn, dukptTrackData.getKeySerialNumber())) {
                Acr31.this._activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Acr31.this.onReaderErrorListener.UpdateUI("Error", String.format(Acr31.this._activity.getResources().getString(R.string.message_track_data_error_ksn), new Object[0]));
                    }
                });
                showTrackData();
                return;
            }
            int encryptionCounter = DukptReceiver.getEncryptionCounter(dukptTrackData.getKeySerialNumber());
            int encryptionCounter2 = this.dukptReceiver.getEncryptionCounter();
            if (encryptionCounter < encryptionCounter2) {
                this.dukptReceiver.loadInitialKey(this.ipek);
                encryptionCounter2 = this.dukptReceiver.getEncryptionCounter();
            }
            while (encryptionCounter > encryptionCounter2) {
                this.dukptReceiver.getKey();
                encryptionCounter2 = this.dukptReceiver.getEncryptionCounter();
            }
            if (encryptionCounter != encryptionCounter2) {
                Acr31.this._activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Acr31.this.onReaderErrorListener.UpdateUI("Error", String.format(Acr31.this._activity.getResources().getString(R.string.message_track_data_error_ec), new Object[0]));
                    }
                });
                showTrackData();
                return;
            }
            byte[] key = this.dukptReceiver.getKey();
            if (key == null) {
                Acr31.this._activity.runOnUiThread(new Runnable() {
                    @SuppressLint("WrongConstant")
                    public void run() {
                        Toast.makeText(Acr31.this._activity, "The maximum encryption count had been reached.", 1).show();
                    }
                });
                showTrackData();
                return;
            }
            byte[] generateDataEncryptionRequestKey = DukptReceiver.generateDataEncryptionRequestKey(key);
            byte[] generateMacRequestKey = DukptReceiver.generateMacRequestKey(key);
            byte[] bArr = new byte[24];
            System.arraycopy(generateDataEncryptionRequestKey, 0, bArr, 0, generateDataEncryptionRequestKey.length);
            System.arraycopy(generateDataEncryptionRequestKey, 0, bArr, 16, 8);
            try {
                if (dukptTrackData.getTrack1Data() != null) {
                    byte[] tripleDesDecrypt = Acr31.this.tripleDesDecrypt(bArr, dukptTrackData.getTrack1Data());
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.track1Mac);
                    sb.append(" (");
                    sb.append(Acr31.this.toHexString(DukptReceiver.generateMac(generateMacRequestKey, tripleDesDecrypt)));
                    sb.append(")");
                    this.track1Mac = sb.toString();
                    this.track1Data.fromString(new String(tripleDesDecrypt, 1, dukptTrackData.getTrack1Length(), "US-ASCII"));
                }
                if (dukptTrackData.getTrack2Data() != null) {
                    byte[] tripleDesDecrypt2 = Acr31.this.tripleDesDecrypt(bArr, dukptTrackData.getTrack2Data());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(this.track2Mac);
                    sb2.append(" (");
                    sb2.append(Acr31.this.toHexString(DukptReceiver.generateMac(generateMacRequestKey, tripleDesDecrypt2)));
                    sb2.append(")");
                    this.track2Mac = sb2.toString();
                    this.track2Data.fromString(new String(tripleDesDecrypt2, 1, dukptTrackData.getTrack2Length(), "US-ASCII"));
                }
            } catch (GeneralSecurityException unused) {
                Acr31.this._activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Acr31.this.onReaderErrorListener.UpdateUI("Error", String.format(Acr31.this._activity.getResources().getString(R.string.message_track_data_error_decrypted), new Object[0]));
                    }
                });
            } catch (UnsupportedEncodingException unused2) {
            }
            showTrackData();
        }

        private void showTrackData() {
            Acr31.this._activity.runOnUiThread(new Runnable() {
                public void run() {
                    Acr31.this._iswipeCount++;
                    Acr31.this._t1Jis2Data = OnTrackDataAvailableListener.this.track1Data.getJis2Data();
                    Acr31.this._t1CardNumber = OnTrackDataAvailableListener.this.concatString(OnTrackDataAvailableListener.this.track1Data.getPrimaryAccountNumber(), OnTrackDataAvailableListener.this.track1MaskedData.getPrimaryAccountNumber());
                    Acr31.this._t1Name = OnTrackDataAvailableListener.this.concatString(OnTrackDataAvailableListener.this.track1Data.getName(), OnTrackDataAvailableListener.this.track1MaskedData.getName());
                    Acr31.this._t1ExpirationDate = OnTrackDataAvailableListener.this.concatString(OnTrackDataAvailableListener.this.track1Data.getExpirationDate(), OnTrackDataAvailableListener.this.track1MaskedData.getExpirationDate());
                    Acr31.this._t1DiscretionaryData = OnTrackDataAvailableListener.this.track1Data.getDiscretionaryData();
                    Acr31.this._t2CardNumber = OnTrackDataAvailableListener.this.concatString(OnTrackDataAvailableListener.this.track2Data.getPrimaryAccountNumber(), OnTrackDataAvailableListener.this.track2MaskedData.getPrimaryAccountNumber());
                    Acr31.this._t2ExpirationDate = OnTrackDataAvailableListener.this.concatString(OnTrackDataAvailableListener.this.track2Data.getExpirationDate(), OnTrackDataAvailableListener.this.track2MaskedData.getExpirationDate());
                    Acr31.this._t2ServiceCode = OnTrackDataAvailableListener.this.concatString(OnTrackDataAvailableListener.this.track2Data.getServiceCode(), OnTrackDataAvailableListener.this.track2MaskedData.getServiceCode());
                    Acr31.this._t2DiscretionaryData = OnTrackDataAvailableListener.this.concatString(OnTrackDataAvailableListener.this.track2Data.getDiscretionaryData(), OnTrackDataAvailableListener.this.track2MaskedData.getDiscretionaryData());
                    Acr31.this.onReaderDataAvailableListener.UpdateUI(Acr31.this._reader, AvailableData.TRACT_DATA);
                    Log.i("Testing", "Done Updating UI");
                }
            });
        }

        /* access modifiers changed from: private */
        public String concatString(String str, String str2) {
            if (str.length() > 0 && str2.length() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("\n");
                str = sb.toString();
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(str2);
            return sb2.toString();
        }
    }

    public enum ResetType {
        NOTHING,
        START_UP,
        EXPLORE_DEVICE,
        EXPLORE_MAGNETIC_CARD,
        SETTINGS
    }

    /* access modifiers changed from: protected */
    public String toBatteryStatusString(int i) {
        switch (i) {
            case 0:
                return "Low";
            case 1:
                return "Full";
            default:
                return "Unknown";
        }
    }

    /* access modifiers changed from: protected */
    public String toErrorCodeString(int i) {
        if (i == 0) {
            return "The operation completed successfully.";
        }
        switch (i) {
            case Result.ERROR_VERIFICATION_FAILED /*247*/:
                return "The verification is failed.";
            case Result.ERROR_FLASH_DATA_CORRUPTED /*248*/:
                return "The flash data is corrupted.";
            case Result.ERROR_DUKPT_DATA_CORRUPTED /*249*/:
                return "The DUKPT data is corrupted.";
            case Result.ERROR_DUKPT_OPERATION_CEASED /*250*/:
                return "The DUKPT operation is ceased.";
            case Result.ERROR_UNKNOWN /*251*/:
                return "The error is unknown.";
            case Result.ERROR_INVALID_START_BYTE /*252*/:
                return "The start byte is invalid.";
            case Result.ERROR_INVALID_CHECKSUM /*253*/:
                return "The checksum is invalid.";
            case Result.ERROR_INVALID_PARAMETER /*254*/:
                return "The parameter is invalid.";
            case 255:
                return "The command is invalid.";
            default:
                return "ERROR communicating with reader.";
        }
    }

    public String getFirmwareVersion() {
        return this._firmwareVersion;
    }

    public String getCustomId() {
        return new String(this._customId, Charset.forName("UTF-8")).trim();
    }

    public String getDeviceId() {
        return toHexString(this._deviceId);
    }

    public String getBatteryLevel() {
        return toBatteryLevelString(this._status.getBatteryLevel());
    }

    public int getIswipeCount() {
        return this._iswipeCount;
    }

    public String getRawData() {
        return this._rawData;
    }

    public String getT1Jis2Data() {
        return this._t1Jis2Data;
    }

    public String getT1CardNumber() {
        return this._t1CardNumber;
    }

    public String getT1Name() {
        return this._t1Name;
    }

    public String getT1ExpirationDate() {
        return this._t1ExpirationDate;
    }

    public String getT1DiscretionaryData() {
        return this._t1DiscretionaryData;
    }

    public String getT2CardNumber() {
        return this._t2CardNumber;
    }

    public String getT2ExpirationDate() {
        return this._t2ExpirationDate;
    }

    public String getT2ServiceCode() {
        return this._t2ServiceCode;
    }

    public String getT2DiscretionaryData() {
        return this._t2DiscretionaryData;
    }

    public void setActivity(Activity activity) {
        this._activity = activity;
    }

    public void setOnReaderDataAvailableListener(OnReaderDataAvailableListener onReaderDataAvailableListener2) {
        this.onReaderDataAvailableListener = onReaderDataAvailableListener2;
    }

    public void setOnReaderErrorListener(OnReaderErrorListener onReaderErrorListener2) {
        this.onReaderErrorListener = onReaderErrorListener2;
    }

    @SuppressLint("WrongConstant")
    public Acr31(Activity activity) {
        this._activity = activity;
        Activity activity2 = this._activity;
        Activity activity3 = this._activity;
        this._audioManager = (AudioManager) activity2.getSystemService("audio");
        this._audioJackReader = new AudioJackReader(this._audioManager);
        toByteArray(DEFAULT_MASTER_KEY_STRING, this._masterKey);
        this._reader = this;
    }

    public void reset() {
        this._audioJackReader.reset();
    }

    public void reset(ResetType resetType) {
        start();
        switch (resetType) {
            case START_UP:
                this._audioJackReader.setOnFirmwareVersionAvailableListener(new OnFirmwareVersionAvailableListener());
                break;
            case EXPLORE_MAGNETIC_CARD:
                Log.i("Explore Magnetic Card", "OnTrackDataAvailableListener was set");
                this._audioJackReader.setOnTrackDataAvailableListener(new OnTrackDataAvailableListener());
                this._audioJackReader.setOnRawDataAvailableListener(new OnRawDataAvailableListener());
                this._audioJackReader.setOnStatusAvailableListener(new OnStatusAvailableListener());
                break;
            case EXPLORE_DEVICE:
                this._audioJackReader.setOnFirmwareVersionAvailableListener(new OnFirmwareVersionAvailableListener());
                this._audioJackReader.setOnStatusAvailableListener(new OnStatusAvailableListener());
                this._audioJackReader.setOnCustomIdAvailableListener(new OnCustomIdAvailableListener());
                this._audioJackReader.setOnDeviceIdAvailableListener(new OnDeviceIdAvailableListener());
                break;
            case SETTINGS:
                this._audioJackReader.setOnResultAvailableListener(new OnResultAvailableListener());
                break;
        }
        this._audioJackReader.reset(new OnResetCompleteListener(resetType));
    }

    public void maxVolume() {
        this._audioManager.setStreamMute(3, false);
        this._audioManager.setStreamVolume(3, this._audioManager.getStreamMaxVolume(3), 0);
    }

    public void minVolume() {
        this._audioManager.setStreamVolume(3, 0, 0);
    }

    public void start() {
        if (isRecordAudioEnabled()) {
            if (this._progressDialog == null) {
                this._progressDialog = new ProgressDialog(this._activity);
                this._progressDialog.setCancelable(false);
                this._progressDialog.setIndeterminate(true);
            }
            maxVolume();
            this._audioJackReader.start();
            this._isConnected = true;
        }
    }

    public void stop() {
        minVolume();
        if (this._progressDialog != null) {
            this._progressDialog.dismiss();
        }
        this._progressDialog = null;
        this._audioJackReader.stop();
        this._isConnected = false;
    }

    public void sleep() {
        this._audioJackReader.sleep();
        if (this._progressDialog != null) {
            this._progressDialog.dismiss();
        }
    }

    public void setCustomId(String str) {
        byte[] bArr;
        try {
            bArr = String.format("%1$-10s", new Object[]{str}).getBytes("US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            bArr = null;
        }
        this._customId = bArr;
        reset(ResetType.SETTINGS);
    }

    /* access modifiers changed from: protected */
    public byte[] aesDecrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
        instance.init(2, secretKeySpec, new IvParameterSpec(new byte[16]));
        return instance.doFinal(bArr2);
    }

    /* access modifiers changed from: protected */
    public String toHexString(byte[] bArr) {
        String str = "";
        if (bArr != null) {
            for (byte b : bArr) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("0");
                    sb.append(hexString);
                    hexString = sb.toString();
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(hexString.toUpperCase(Locale.US));
                sb2.append(" ");
                str = sb2.toString();
            }
        }
        return str;
    }

    /* access modifiers changed from: protected */
    public byte[] tripleDesDecrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "DESede");
        Cipher instance = Cipher.getInstance("DESede/CBC/NoPadding");
        instance.init(2, secretKeySpec, new IvParameterSpec(new byte[8]));
        return instance.doFinal(bArr2);
    }

    /* access modifiers changed from: protected */
    public int toByteArray(String str, byte[] bArr) {
        boolean z = true;
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            int i3 = (charAt < '0' || charAt > '9') ? (charAt < 'A' || charAt > 'F') ? (charAt < 'a' || charAt > 'f') ? -1 : (charAt - 'a') + 10 : (charAt - 'A') + 10 : charAt - '0';
            if (i3 >= 0) {
                if (z) {
                    bArr[i] = (byte) (i3 << 4);
                } else {
                    bArr[i] = (byte) (i3 | bArr[i]);
                    i++;
                }
                z = !z;
            }
            if (i >= bArr.length) {
                break;
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void showRequestQueueError() {
        Log.i("ResetComplete", "ERROR");
        this._activity.runOnUiThread(new Runnable() {
            @SuppressLint("WrongConstant")
            public void run() {
                Toast.makeText(Acr31.this._activity, "The request cannot be queued.", 1).show();
            }
        });
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:2|3|(2:7|8)|9|10|(1:12)(3:13|(1:15)(1:16)|17)|18|19) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0017  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean showStatus() {
        /*
            r4 = this;
            java.lang.Object r0 = r4._responseEvent
            monitor-enter(r0)
            boolean r1 = r4._statusReady     // Catch:{ all -> 0x0043 }
            if (r1 != 0) goto L_0x0012
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x0043 }
            if (r1 != 0) goto L_0x0012
            java.lang.Object r1 = r4._responseEvent     // Catch:{ InterruptedException -> 0x0012 }
            r2 = 1000(0x3e8, double:4.94E-321)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x0012 }
        L_0x0012:
            boolean r1 = r4._statusReady     // Catch:{ all -> 0x0043 }
            r2 = 0
            if (r1 == 0) goto L_0x0023
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0043 }
            com.acs.mobilemate.Readers.Acr31$2 r3 = new com.acs.mobilemate.Readers.Acr31$2     // Catch:{ all -> 0x0043 }
            r3.<init>()     // Catch:{ all -> 0x0043 }
            r1.runOnUiThread(r3)     // Catch:{ all -> 0x0043 }
            r1 = 1
            goto L_0x003d
        L_0x0023:
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x0032
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0043 }
            com.acs.mobilemate.Readers.Acr31$3 r3 = new com.acs.mobilemate.Readers.Acr31$3     // Catch:{ all -> 0x0043 }
            r3.<init>()     // Catch:{ all -> 0x0043 }
            r1.runOnUiThread(r3)     // Catch:{ all -> 0x0043 }
            goto L_0x003c
        L_0x0032:
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0043 }
            com.acs.mobilemate.Readers.Acr31$4 r3 = new com.acs.mobilemate.Readers.Acr31$4     // Catch:{ all -> 0x0043 }
            r3.<init>()     // Catch:{ all -> 0x0043 }
            r1.runOnUiThread(r3)     // Catch:{ all -> 0x0043 }
        L_0x003c:
            r1 = r2
        L_0x003d:
            r4._statusReady = r2     // Catch:{ all -> 0x0043 }
            r4._resultReady = r2     // Catch:{ all -> 0x0043 }
            monitor-exit(r0)     // Catch:{ all -> 0x0043 }
            return r1
        L_0x0043:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0043 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.acs.mobilemate.Readers.Acr31.showStatus():boolean");
    }

    private String toBatteryLevelString(int i) {
        String str;
        switch (i) {
            case 0:
                str = "100%";
                break;
            case 1:
                str = "90%";
                break;
            case 2:
                str = "80%";
                break;
            case 3:
                str = "70%";
                break;
            case 4:
                str = "60%";
                break;
            case 5:
                str = "50%";
                break;
            case 6:
                str = "40%";
                break;
            case 7:
                str = "30%";
                break;
            case 8:
                str = "15%";
                break;
            default:
                str = "Unknown";
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Level: ");
        sb.append(i);
        Log.i("Battery Level", sb.toString());
        return str;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:2|3|(2:7|8)|9|10|(1:12)(3:13|(1:15)(1:16)|17)|18|19) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0017  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean showFirmwareVersion() {
        /*
            r4 = this;
            java.lang.Object r0 = r4._responseEvent
            monitor-enter(r0)
            boolean r1 = r4._firmwareVersionReady     // Catch:{ all -> 0x0043 }
            if (r1 != 0) goto L_0x0012
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x0043 }
            if (r1 != 0) goto L_0x0012
            java.lang.Object r1 = r4._responseEvent     // Catch:{ InterruptedException -> 0x0012 }
            r2 = 1000(0x3e8, double:4.94E-321)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x0012 }
        L_0x0012:
            boolean r1 = r4._firmwareVersionReady     // Catch:{ all -> 0x0043 }
            r2 = 0
            if (r1 == 0) goto L_0x0023
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0043 }
            com.acs.mobilemate.Readers.Acr31$5 r3 = new com.acs.mobilemate.Readers.Acr31$5     // Catch:{ all -> 0x0043 }
            r3.<init>()     // Catch:{ all -> 0x0043 }
            r1.runOnUiThread(r3)     // Catch:{ all -> 0x0043 }
            r1 = 1
            goto L_0x003d
        L_0x0023:
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x0032
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0043 }
            com.acs.mobilemate.Readers.Acr31$6 r3 = new com.acs.mobilemate.Readers.Acr31$6     // Catch:{ all -> 0x0043 }
            r3.<init>()     // Catch:{ all -> 0x0043 }
            r1.runOnUiThread(r3)     // Catch:{ all -> 0x0043 }
            goto L_0x003c
        L_0x0032:
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0043 }
            com.acs.mobilemate.Readers.Acr31$7 r3 = new com.acs.mobilemate.Readers.Acr31$7     // Catch:{ all -> 0x0043 }
            r3.<init>()     // Catch:{ all -> 0x0043 }
            r1.runOnUiThread(r3)     // Catch:{ all -> 0x0043 }
        L_0x003c:
            r1 = r2
        L_0x003d:
            r4._firmwareVersionReady = r2     // Catch:{ all -> 0x0043 }
            r4._resultReady = r2     // Catch:{ all -> 0x0043 }
            monitor-exit(r0)     // Catch:{ all -> 0x0043 }
            return r1
        L_0x0043:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0043 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.acs.mobilemate.Readers.Acr31.showFirmwareVersion():boolean");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:2|3|(2:7|8)|9|10|(1:12)(2:13|(1:15)(1:16))|17|18) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0016  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showCustomId() {
        /*
            r4 = this;
            java.lang.Object r0 = r4._responseEvent
            monitor-enter(r0)
            boolean r1 = r4._customIdReady     // Catch:{ all -> 0x0041 }
            if (r1 != 0) goto L_0x0012
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x0041 }
            if (r1 != 0) goto L_0x0012
            java.lang.Object r1 = r4._responseEvent     // Catch:{ InterruptedException -> 0x0012 }
            r2 = 1000(0x3e8, double:4.94E-321)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x0012 }
        L_0x0012:
            boolean r1 = r4._customIdReady     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x0021
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0041 }
            com.acs.mobilemate.Readers.Acr31$8 r2 = new com.acs.mobilemate.Readers.Acr31$8     // Catch:{ all -> 0x0041 }
            r2.<init>()     // Catch:{ all -> 0x0041 }
            r1.runOnUiThread(r2)     // Catch:{ all -> 0x0041 }
            goto L_0x003a
        L_0x0021:
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x0030
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0041 }
            com.acs.mobilemate.Readers.Acr31$9 r2 = new com.acs.mobilemate.Readers.Acr31$9     // Catch:{ all -> 0x0041 }
            r2.<init>()     // Catch:{ all -> 0x0041 }
            r1.runOnUiThread(r2)     // Catch:{ all -> 0x0041 }
            goto L_0x003a
        L_0x0030:
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0041 }
            com.acs.mobilemate.Readers.Acr31$10 r2 = new com.acs.mobilemate.Readers.Acr31$10     // Catch:{ all -> 0x0041 }
            r2.<init>()     // Catch:{ all -> 0x0041 }
            r1.runOnUiThread(r2)     // Catch:{ all -> 0x0041 }
        L_0x003a:
            r1 = 0
            r4._customIdReady = r1     // Catch:{ all -> 0x0041 }
            r4._resultReady = r1     // Catch:{ all -> 0x0041 }
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            return
        L_0x0041:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.acs.mobilemate.Readers.Acr31.showCustomId():void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:2|3|(2:7|8)|9|10|(1:12)(3:13|(1:15)(1:16)|17)|18|19) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0017  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean showDeviceId() {
        /*
            r4 = this;
            java.lang.Object r0 = r4._responseEvent
            monitor-enter(r0)
            boolean r1 = r4._deviceIdReady     // Catch:{ all -> 0x0043 }
            if (r1 != 0) goto L_0x0012
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x0043 }
            if (r1 != 0) goto L_0x0012
            java.lang.Object r1 = r4._responseEvent     // Catch:{ InterruptedException -> 0x0012 }
            r2 = 1000(0x3e8, double:4.94E-321)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x0012 }
        L_0x0012:
            boolean r1 = r4._deviceIdReady     // Catch:{ all -> 0x0043 }
            r2 = 0
            if (r1 == 0) goto L_0x0023
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0043 }
            com.acs.mobilemate.Readers.Acr31$11 r3 = new com.acs.mobilemate.Readers.Acr31$11     // Catch:{ all -> 0x0043 }
            r3.<init>()     // Catch:{ all -> 0x0043 }
            r1.runOnUiThread(r3)     // Catch:{ all -> 0x0043 }
            r1 = 1
            goto L_0x003d
        L_0x0023:
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x0032
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0043 }
            com.acs.mobilemate.Readers.Acr31$12 r3 = new com.acs.mobilemate.Readers.Acr31$12     // Catch:{ all -> 0x0043 }
            r3.<init>()     // Catch:{ all -> 0x0043 }
            r1.runOnUiThread(r3)     // Catch:{ all -> 0x0043 }
            goto L_0x003c
        L_0x0032:
            android.app.Activity r1 = r4._activity     // Catch:{ all -> 0x0043 }
            com.acs.mobilemate.Readers.Acr31$13 r3 = new com.acs.mobilemate.Readers.Acr31$13     // Catch:{ all -> 0x0043 }
            r3.<init>()     // Catch:{ all -> 0x0043 }
            r1.runOnUiThread(r3)     // Catch:{ all -> 0x0043 }
        L_0x003c:
            r1 = r2
        L_0x003d:
            r4._deviceIdReady = r2     // Catch:{ all -> 0x0043 }
            r4._resultReady = r2     // Catch:{ all -> 0x0043 }
            monitor-exit(r0)     // Catch:{ all -> 0x0043 }
            return r1
        L_0x0043:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0043 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.acs.mobilemate.Readers.Acr31.showDeviceId():boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:2|3|(2:5|6)|7|8|(1:10)(1:11)|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x000e */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0014  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean showResult() {
        /*
            r4 = this;
            java.lang.Object r0 = r4._responseEvent
            monitor-enter(r0)
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x002e }
            if (r1 != 0) goto L_0x000e
            java.lang.Object r1 = r4._responseEvent     // Catch:{ InterruptedException -> 0x000e }
            r2 = 1000(0x3e8, double:4.94E-321)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x000e }
        L_0x000e:
            boolean r1 = r4._resultReady     // Catch:{ all -> 0x002e }
            boolean r2 = r4._resultReady     // Catch:{ all -> 0x002e }
            if (r2 == 0) goto L_0x001f
            android.app.Activity r2 = r4._activity     // Catch:{ all -> 0x002e }
            com.acs.mobilemate.Readers.Acr31$14 r3 = new com.acs.mobilemate.Readers.Acr31$14     // Catch:{ all -> 0x002e }
            r3.<init>()     // Catch:{ all -> 0x002e }
            r2.runOnUiThread(r3)     // Catch:{ all -> 0x002e }
            goto L_0x0029
        L_0x001f:
            android.app.Activity r2 = r4._activity     // Catch:{ all -> 0x002e }
            com.acs.mobilemate.Readers.Acr31$15 r3 = new com.acs.mobilemate.Readers.Acr31$15     // Catch:{ all -> 0x002e }
            r3.<init>()     // Catch:{ all -> 0x002e }
            r2.runOnUiThread(r3)     // Catch:{ all -> 0x002e }
        L_0x0029:
            r2 = 0
            r4._resultReady = r2     // Catch:{ all -> 0x002e }
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return r1
        L_0x002e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.acs.mobilemate.Readers.Acr31.showResult():boolean");
    }

    public boolean isRecordAudioEnabled() {
        if (ActivityCompat.checkSelfPermission(this._activity, "android.permission.RECORD_AUDIO") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this._activity, new String[]{"android.permission.RECORD_AUDIO"}, SwipePaymentActivity.REQUEST_CODE_ASK_PERMISSIONS);
        return false;
    }
}
