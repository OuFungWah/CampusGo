package com.example.fungwah.campusgo.module.guide.activity;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.WindowManager;

import com.example.fungwah.campusgo.R;
import com.example.fungwahtools.activity.BaseActivity;

/**
 * Created by FungWah on 2017/11/24.
 */

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    private FloatingActionButton fab;

    @Override
    protected int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.guide_activity;
    }

    @Override
    protected void initView() {
        fab = findView(R.id.guide_forward_fab);
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guide_forward_fab:
                startActivity(GuideBaseInfoActivity.class);
                break;
        }
    }
}
