package com.example.indraaguslesmana.mvpezy.intro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.indraaguslesmana.mvpezy.R;
import com.example.indraaguslesmana.mvpezy.util.PreferenceUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends Fragment implements IntroContract.View{

    private ProgressBar progressBar;
    private LinearLayout viewError;
    private TextView txBuildversion;
    private Button btnReload;
    private IntroContract.Presenter mPresenter;

    public IntroFragment() {
        // Required empty public constructor
    }

    public static IntroFragment newInstance() {
        return new IntroFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_intro, container, false);

        progressBar = (ProgressBar) root.findViewById(R.id.intro_progress_bar);
        viewError = (LinearLayout) root.findViewById(R.id.view_errorIntro);
        txBuildversion = (TextView) root.findViewById(R.id.txBuildversion);
        btnReload = (Button) root.findViewById(R.id.btn_reload);

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.tryReload(PreferenceUtils.getSinglePrefrenceString(getContext(),
                        R.string.settings_def_storeaccess_token_key));
            }
        });

        return root;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoadDataError(boolean isShow) {
        if (isShow){
            viewError.setVisibility(View.VISIBLE);
        }else {
            viewError.setVisibility(View.GONE);
        }
    }

    @Override
    public void showBuildVersion() {
        mPresenter.setBuildVersion(txBuildversion, getActivity());
    }

    @Override
    public void setPresenter(IntroContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }
}
