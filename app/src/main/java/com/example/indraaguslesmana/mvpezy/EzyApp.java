package com.example.indraaguslesmana.mvpezy;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Build;

import com.example.indraaguslesmana.mvpezy.util.Constant;
import com.example.indraaguslesmana.mvpezy.util.Helper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by indraaguslesmana on 5/27/17.
 */

public class EzyApp extends Application {

    private static final String TAG = "EzyApp";
    private static EzytopupAPI sAPIService;
    private static SharedPreferences sPreferences;
    private static Boolean isSunmiDevice;
    private final String HEADER_KEY1 = "application_id";
    private final String HEADER_KEY2 = "Authorize";

    @Override
    public void onCreate() {
        super.onCreate();
        sPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        okhttpClientBuilder
                .addInterceptor(logging)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader(HEADER_KEY1, "Ezy_Apps_WGS")
                                .build();
                        return chain.proceed(request);
                    }
                });

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constant.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okhttpClientBuilder.build());

        Retrofit retrofit = builder.build();
        sAPIService = retrofit.create(EzytopupAPI.class);

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

    public static EzytopupAPI getsAPIService() {
        return sAPIService;
    }
}
