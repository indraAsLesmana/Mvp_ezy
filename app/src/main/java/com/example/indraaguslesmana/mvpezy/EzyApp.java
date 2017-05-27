package com.example.indraaguslesmana.mvpezy;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by indraaguslesmana on 5/27/17.
 */

public class EzyApp extends Application {

    private static final String TAG = "EzyApp";
    private static SharedPreferences sPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

    }

    public static SharedPreferences getsPreferences() {
        return sPreferences;
    }
}
