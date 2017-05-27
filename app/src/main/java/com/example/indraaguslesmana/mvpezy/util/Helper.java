package com.example.indraaguslesmana.mvpezy.util;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import com.example.indraaguslesmana.mvpezy.R;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by indraaguslesmana on 4/1/17.
 */

public class Helper {

    private static ProgressDialog sProgressDialog;
    private static final String TAG = "Helper";
    /**
     * this method require to complete API parameter, from last developer
     * */
    public static String deviceId(){
        //register device_id
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddhhmmss");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String getDeviceDate = dateFormatter.format(today);

        Random r = new Random();
        int getDeviceNum = r.nextInt(99999999 - 10000000) + 10000000;
        return getDeviceDate + String.valueOf(getDeviceNum);
    }

    /**
     * Show progress dialog, can only be called once per tier (show-hide)
     */
    public static void showProgressDialog(Context ctx, int bodyStringId) {
        if(sProgressDialog == null) {
            sProgressDialog = ProgressDialog.show(ctx,
                    ctx.getString(R.string.progress_title_default),
                    ctx.getString(bodyStringId), true, false, null);
        }
    }

    /**
     * Hide current progress dialog and set to NULL
     */
    public static void hideProgressDialog() {
        if(sProgressDialog != null && sProgressDialog.isShowing()) {
            sProgressDialog.dismiss();
            sProgressDialog = null;     // so it can be called in the next showProgressDialog
        }
    }

    public static String printTextCenter(String src) {
        StringBuilder stringBuilder = new StringBuilder();
        String finalString = null;
        if (src.length() < 30) {
            int n = 30 - src.length();
            int space = n / 2;

            for (int i = 0; i <= space; i++) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(src);
            for (int i = 0; i <= space; i++) {
                stringBuilder.append(" ");
            }
            finalString = stringBuilder.toString();
        }
        return finalString;
    }

    public static void log(String TAG, String message, Throwable throwable) {
        if(Constant.ENABLE_LOG) {
            Log.v(TAG, message, throwable);
        }
    }

    public static void apiSnacbarError(Context context, Throwable t, View view){
        Helper.log(TAG, "onFailure: " + t.getMessage(), null);
        String message = t.getMessage();
        if (t.getMessage().contains("Use JsonReader.setLenient")) {
            message = context.getResources().getString(R.string.response_error);
        }
        if (t.getMessage().contains("Expected BEGIN")) {
            message = context.getResources().getString(R.string.response_error);
        }
        if (t.getMessage().contains("Unable to resolve host")) {
            message = context.getResources().getString(R.string.cantreachserver);
        }

        final Snackbar snackbar = Snackbar.make(view, message,
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    public static void snacbarError(int message, View view){
        Helper.log(TAG, "onFailure: " + message, null);
        final Snackbar snackbar = Snackbar.make(view, message,
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    public static void snacbarError(String message, View view){
        Helper.log(TAG, "onFailure: " + message, null);
        final Snackbar snackbar = Snackbar.make(view, message,
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    public static void downloadFile(Context context, String uRl) {
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/Ezytopup");

        if (!direct.exists()) {
            direct.mkdirs();
        }

        @SuppressLint("SdCardPath")
        File file = new File("/mnt/sdcard/Ezytopup/print_logo.jpg");
        if (file.exists()){
            return;
        }else {
            DownloadManager mgr = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

            Uri downloadUri = Uri.parse(uRl);
            DownloadManager.Request request = new DownloadManager.Request(
                    downloadUri);

            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI
                            | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false).setTitle("Ezytopup")
                    .setDescription("Ezytopup resouce folder")
                    .setDestinationInExternalPublicDir("/Ezytopup", "print_logo.jpg");

            mgr.enqueue(request);
        }
    }

    public static boolean dateCheck(String tglCetak, String reprintTime, String serverTime){
        SimpleDateFormat df = new SimpleDateFormat(getDefaultDisplayDateTimeFormat());
        Date startDate = null, currentTime = null, endDate = null, tempDate;

        try {
            startDate = df.parse(tglCetak);
            tempDate = startDate;
            Calendar cal = Calendar.getInstance();
            cal.setTime(tempDate);
            cal.add(Calendar.MINUTE, Integer.parseInt(reprintTime));
            String tempDateupdate = df.format(cal.getTime());
            endDate = df.parse(tempDateupdate);
            currentTime = df.parse(serverTime);
            Helper.log(TAG, "original   : " + tglCetak, null);
            Helper.log(TAG, "validate   : " + tempDateupdate, null);
            Helper.log(TAG, "now   : " + serverTime, null);
            Helper.log(TAG, "isWithRage   : " + Helper.isWithinRange(currentTime,
                    startDate, endDate), null);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isWithinRange(currentTime, startDate, endDate);
    }
    private static boolean isWithinRange(Date currenTime, Date startDate, Date endDate) {
        return !(currenTime.before(startDate) || currenTime.after(endDate));
    }

    /**
     * Show soft keyboard for given view
     */
    public static void hideSoftKeyboard(View view) {
        if(view == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void enableImmersiveMode(View view) {
        int systemUiVisibility = view.getSystemUiVisibility();
        int flags = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        systemUiVisibility |= flags;
        view.setSystemUiVisibility(systemUiVisibility);
    }

    /**
     * this tricky to get is keyboard hide or not.
     * and if keybord is hide, rerun again immersive mode
     * */
    public static void setImmersivebyKeyboard(final View rootView){
        rootView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();

                        if (heightDiff > 100) {
                            Helper.log(TAG, "keyboard opened", null);
                        } else {
                            Helper.log(TAG, "keyboard closed", null);
                            Helper.enableImmersiveMode(rootView);
                        }
                    }
                });
    }

    private static String getDefaultDisplayDateTimeFormat() {
        return "yyyy-MM-dd HH:mm:ss";
    }

}
