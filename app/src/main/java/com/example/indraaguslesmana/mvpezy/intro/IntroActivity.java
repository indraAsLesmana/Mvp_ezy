package com.example.indraaguslesmana.mvpezy.intro;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.indraaguslesmana.mvpezy.BaseActivity;
import com.example.indraaguslesmana.mvpezy.R;
import com.example.indraaguslesmana.mvpezy.util.ActivityUtils;
import com.example.indraaguslesmana.mvpezy.util.Constant;
import com.example.indraaguslesmana.mvpezy.util.PreferenceUtils;

/**
 * Created by indraaguslesmana on 5/27/17.
 */

public class IntroActivity extends BaseActivity implements View.OnClickListener {

    private IntroPresenter mIntroPresenter;
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntroFragment tasksFragment =
                (IntroFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (tasksFragment == null) {
            // Create the fragment
            tasksFragment = IntroFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
        }
         token = PreferenceUtils
                .getSinglePrefrenceString(this, R.string.settings_def_storeaccess_token_key);

        tokenCheck(token);

        mIntroPresenter = new IntroPresenter(tasksFragment);
    }

    private void tokenCheck(String lastToken){
        if (!lastToken.equals(Constant.PREF_NULL)){
            mIntroPresenter.tryReload(lastToken);
        }else {
            PreferenceUtils.destroyUserSession(IntroActivity.this);
        }
    }

    @Override
    public int getContentView() {
        return R.layout.activity_intro;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reload:
                if (!token.equals(Constant.PREF_NULL)) mIntroPresenter.tryReload(token);
                break;
        }
    }
}
