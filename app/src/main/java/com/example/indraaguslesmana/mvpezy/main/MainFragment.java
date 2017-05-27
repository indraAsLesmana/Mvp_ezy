package com.example.indraaguslesmana.mvpezy.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.indraaguslesmana.mvpezy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements MainContract.View{


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showLoadDataError() {

    }

    @Override
    public void showEmptyDataError() {

    }

    @Override
    public void showMoreCategory() {

    }

    @Override
    public void showCardBuy() {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }
}
