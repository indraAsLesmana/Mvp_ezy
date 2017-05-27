package com.example.indraaguslesmana.mvpezy.intro;

import com.example.indraaguslesmana.mvpezy.BasePresenter;
import com.example.indraaguslesmana.mvpezy.BaseView;
/**
 * Created by indraaguslesmana on 5/27/17.
 */

public interface IntroContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showLoadDataError();
    }

    interface Presenter extends BasePresenter {

        void tryReload();

        void isTokenValid();
    }
}
