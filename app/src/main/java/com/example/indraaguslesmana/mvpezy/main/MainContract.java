package com.example.indraaguslesmana.mvpezy.main;

import com.example.indraaguslesmana.mvpezy.BasePresenter;
import com.example.indraaguslesmana.mvpezy.BaseView;

/**
 * Created by indraaguslesmana on 5/27/17.
 */

public interface MainContract {

    interface View extends BaseView<Presenter>{

        void setLoadingIndicator(boolean active);

        void showLoadDataError();

        void showEmptyDataError();

        void showMoreCategory();

        void showCardBuy();
    }

    interface Presenter extends BasePresenter{

        void moreCategoryClick();

        void cardBuyClick();

    }
}
