package com.example.sqlnest.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    Context context = null;
    SharedPreferences pref;
    String PREF_NAME = "app_pref";
    String TEST_NAME = "testName";


    public SessionManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

    }

    public void setTest(String testName){
        pref.edit().putString(TEST_NAME , testName).commit();
    }

    public String getTest(){
        return pref.getString(TEST_NAME , "");
    }

    public void clearSessionManager(){
        setTest("");
    }
}
