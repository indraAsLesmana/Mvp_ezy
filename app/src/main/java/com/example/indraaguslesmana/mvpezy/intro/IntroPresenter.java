package com.example.indraaguslesmana.mvpezy.intro;

/**
 * Created by indraaguslesmana on 5/27/17.
 */

import android.app.Activity;
import android.widget.TextView;

import com.example.indraaguslesmana.mvpezy.BuildConfig;
import com.example.indraaguslesmana.mvpezy.EzyApp;
import com.example.indraaguslesmana.mvpezy.R;
import com.example.indraaguslesmana.mvpezy.model.TokencheckResponse;
import com.example.indraaguslesmana.mvpezy.util.Constant;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Listens to user actions from the UI ({@link IntroActivity}), retrieves the data and updates the
 * UI as required.
 */
public class IntroPresenter implements IntroContract.Presenter{

    private final IntroContract.View mIntroView;

    IntroPresenter(IntroContract.View introView) {
        mIntroView = introView;
        mIntroView.setPresenter(this);
    }

    @Override
    public void tryReload(String token) {
        mIntroView.setLoadingIndicator(true);

        if (token.equals(Constant.PREF_NULL)) {
            throw new RuntimeException("Token is null");
        }

        Call<TokencheckResponse> tokenResponse = EzyApp.getsAPIService().checkToken(token);
        tokenResponse.enqueue(new Callback<TokencheckResponse>() {
            @Override
            public void onResponse(Call<TokencheckResponse> call,
                                   Response<TokencheckResponse> response) {
                if (response.isSuccessful()
                        && response.body().status.getCode()
                        .equals(String.valueOf(HttpURLConnection.HTTP_OK))
                        && response.body().tokenValidity == Boolean.TRUE) {

                } else {

                }

                mIntroView.setLoadingIndicator(false);
            }

            @Override
            public void onFailure(Call<TokencheckResponse> call, Throwable t) {
                mIntroView.setLoadingIndicator(false);
                mIntroView.showLoadDataError(true);
            }
        });
    }

    @Override
    public void isTokenValid() {

    }

    @Override
    public void setBuildVersion(TextView textView, Activity activity) {

        String versionStr = BuildConfig.VERSION_NAME;
        versionStr += "-" + BuildConfig.BUILD_TYPE;
        textView.setText(String.format("%s: %s",
                activity.getString(R.string.code_version), versionStr));
    }

    @Override
    public void start() {

    }
}
