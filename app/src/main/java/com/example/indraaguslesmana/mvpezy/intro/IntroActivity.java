package com.example.indraaguslesmana.mvpezy.intro;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.indraaguslesmana.mvpezy.BaseActivity;
import com.example.indraaguslesmana.mvpezy.BuildConfig;
import com.example.indraaguslesmana.mvpezy.R;

/**
 * Created by indraaguslesmana on 5/27/17.
 */

public class IntroActivity extends BaseActivity implements IntroContract.View{

    private IntroContract.Presenter mPresenter;
    private TextView version;
    private ProgressBar progressBar;
    private ConstraintLayout view_introerror;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        version = (TextView) findViewById(R.id.version_code);
        progressBar = (ProgressBar) findViewById(R.id.intro_progress_bar);
        view_introerror = (ConstraintLayout) findViewById(R.id.container_introerror);

        setLoadingIndicator(false);
        showBuildVersion();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_intro;
    }

    @Override
    public void setLoadingIndicator(boolean loadingActive) {
        if (loadingActive) {
            progressBar.setVisibility(View.VISIBLE);
            showLoadDataError(false);
        } else {
            progressBar.setVisibility(View.GONE);
            showLoadDataError(true);
        }
    }

    @Override
    public void showLoadDataError(boolean isLoadError) {
        if (isLoadError){
            view_introerror.setVisibility(View.VISIBLE);
        }else {
            view_introerror.setVisibility(View.GONE);
        }
    }

    @Override
    public void showBuildVersion() {
        if(version != null) {
            String versionStr = BuildConfig.VERSION_NAME;
            versionStr += "-" + BuildConfig.BUILD_TYPE;
            version.setText(String.format("%s: %s",
                    getString(R.string.code_version), versionStr));
        }
    }

    @Override
    public void setPresenter(IntroContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
