package com.example.indraaguslesmana.mvpezy.intro;

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

        void tryReload();

        void isTokenValid();

        void setBuildVersion();
    }
}
