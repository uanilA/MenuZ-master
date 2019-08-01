package com.menuz.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;

import com.menuz.ui.authentication.LoginActivity;
import com.menuz.ui.authentication.User;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Session {

    private static final String PREF_NAME = "imLink";
    private static final String PREF_NAME2 = "appSession";
    private static final String PREF_NAME3 = "remember";
    private static final String IS_LOGGEDIN = "isLoggedIn";
    private static final String PORTNUMBER = "portnumber";
    private static final String IPADDRESS = "ipaddress";
    private static final String IS_FIrebaseLogin = "isFirebaseLogin";
    private static final String IS_UPDATE_UID = "isUpdateUid";
    private Context _context;
    private SharedPreferences mypref, mypref2,myprefremember;
    private SharedPreferences.Editor editor, editor2,editor3;

    public Session(Context context) {
        this._context = context;
        mypref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mypref2 = _context.getSharedPreferences(PREF_NAME2, Context.MODE_PRIVATE);
        myprefremember = _context.getSharedPreferences(PREF_NAME3, Context.MODE_PRIVATE);
        editor = mypref.edit();
        editor2 = mypref2.edit();
        editor3 = myprefremember.edit();
        editor3.apply();
        editor.apply();
        editor2.apply();
    }


    public HashMap<String, String> getHeader() {
        HashMap<String, String> mHeaderMap = new HashMap<>();
        return mHeaderMap;
    }


    public boolean isUpdateUid() {
        return mypref.getBoolean(IS_UPDATE_UID, false);
    }

    public void setUpdateUid(boolean isUpdate) {
        editor.putBoolean(IS_UPDATE_UID, isUpdate);
        editor.commit();
    }

    public String[] getStringIp() {
        String arr[] = new String[2];
        String name = myprefremember.getString(PORTNUMBER, "");
        String pwd = myprefremember.getString(IPADDRESS, "");

        arr[0] = name;
        arr[1] = pwd;
        return arr;
    }

    public void setStringIp(String portnumber, String Ipaddress) {
        editor3.putString(PORTNUMBER, portnumber);
        editor3.putString(IPADDRESS, Ipaddress);

        editor3.apply();
    }

  /*  public void createSession(FirebaseUser user) {
        Gson gson = new Gson();
        String json = gson.toJson(user); // myObject - instance of MyObject
        editor.putString("userSession", json);
        editor.putBoolean(IS_LOGGEDIN, true);
        editor.putString("authToken", user.authToken);
        editor.commit();
    }*/

    public void createSession(User user) {
        createSession(user, false);
    }

    private void createSession(User user, boolean isFirebaseLogin) {
        Gson gson = new Gson();
        String json = gson.toJson(user); // myObject - instance of MyObject
        editor.putString("user", json);

        editor.putBoolean(IS_LOGGEDIN, true);
        editor.putBoolean(IS_FIrebaseLogin, isFirebaseLogin);
       // editor.putString("authToken", user.authToken);
        editor.commit();
    }

    public void createsessionLanguage(String language){
        editor2.putString("language",language);
        editor2.commit();

    }
    public void createsessionPassword(String password){
        editor2.putString("password",password);
        editor2.commit();

    }


    public void createsessionGuest(String guest){
        editor2.putString("guest",guest);
        editor2.commit();

    }

    public void createsessionCourse(String course){
        editor2.putString("course",course);
        editor2.commit();

    }

    public void createSessionisUpdatedDb(String updated){
        editor.putString("IsUpdated",updated);
        editor.commit();
    }

    public void setPassword(String pwd) {
        try {
            byte[] data = pwd.getBytes("UTF-8");
            String base64 = Base64.encodeToString(data, Base64.DEFAULT);
            editor.putString("pwd", base64);
            editor.commit();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getPassword(){
        // Receiving side
        try {
            byte[] data = Base64.decode(mypref.getString("pwd", null), Base64.DEFAULT);
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public User getUser() {
        Gson gson = new Gson();
        String string = mypref.getString("user", "");
        if (!string.isEmpty())
            return gson.fromJson(string, User.class);
        else return new User();
    }

    public String getAuthToken() {
        return mypref.getString("authToken", "");
    }

    public String getLanguage(){
        return mypref2.getString("language", "");
    }

    public String getPasswordUser(){
        return mypref2.getString("password", "");
    }

    public String getGuest(){
        return mypref2.getString("guest", "");
    }

    public String getCourse(){
        return mypref2.getString("course", "");
    }

    public String getUpdatedinDb(){
        return mypref.getString("IsUpdated", "");
    }

    public boolean getIsOutCallFilter() {
        return mypref.getBoolean("outcall",false);
    }

    public void setIsOutCallFilter(boolean value){
        editor.putBoolean("outcall", value);
        this.editor.commit();
    }

    public String getUserChangedLocLat() {
        return mypref.getString("lat", "");
    }


    public void setUserChangedLocLat(String lat){
        editor.putString("lat", lat);
        this.editor.commit();
    }

    public String getUserChangedLocLng() {
        return mypref.getString("lng", "");
    }


    public void setUserChangedLocLng(String lng){
        editor.putString("lng", lng);
        this.editor.commit();
    }

    public String getUserChangedLocName() {
        return mypref.getString("locName", "");
    }

    public void setUserChangedLocName(String locName){
        editor.putString("locName", locName);
        this.editor.commit();
    }

    public boolean getIsFirebaseLogin() {
        return mypref.getBoolean(IS_FIrebaseLogin, false);
    }


    public void logout() {
        editor.clear();
        editor.apply();
       /* try {
            FirebaseInstanceId.getInstance().deleteInstanceId();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Intent showLogin = new Intent(_context, LoginActivity.class);
        showLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        showLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(showLogin);
    }

    public boolean isLoggedIn() {
        return mypref.getBoolean(IS_LOGGEDIN, false);
    }

}
