package com.example.indraaguslesmana.mvpezy.intro;

import android.app.Activity;
import android.widget.TextView;

import com.example.indraaguslesmana.mvpezy.BasePresenter;
import com.example.indraaguslesmana.mvpezy.BaseView;
/**
 * Created by indraaguslesmana on 5/27/17.
 */

public interface IntroContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showLoadDataError(boolean isShow);

        void showBuildVersion();
    }

    interface Presenter extends BasePresenter {

        void tryReload(String token);

        void isTokenValid();

        void setBuildVersion(TextView textView, Activity activity);
    }
}
