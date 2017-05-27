package com.example.indraaguslesmana.mvpezy;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Build;

import com.example.indraaguslesmana.mvpezy.util.Helper;

/**
 * Created by indraaguslesmana on 5/27/17.
 */

public class EzyApp extends Application {

    private static final String TAG = "EzyApp";
    private static SharedPreferences sPreferences;
    private static Boolean isSunmiDevice;

    @Override
    public void onCreate() {
        super.onCreate();
        sPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        if (Build.BRAND.equals("SUNMI")
                && Build.DEVICE.equals("V1")) {

            isSunmiDevice = Boolean.TRUE;
            Helper.log(TAG, "isSunmi device= " + Boolean.TRUE, null);
        } else {

            isSunmiDevice = Boolean.FALSE;
            Helper.log(TAG, "isSunmi device= " + Boolean.FALSE, null);
        }

    }

    public static SharedPreferences getsPreferences() {
        return sPreferences;
    }

    public static void setsPreferences(SharedPreferences sPreferences) {
        EzyApp.sPreferences = sPreferences;
    }

    public static Boolean getIsSunmiDevice() {
        return isSunmiDevice;
    }

    public static void setIsSunmiDevice(Boolean isSunmiDevice) {
        EzyApp.isSunmiDevice = isSunmiDevice;
    }


}
