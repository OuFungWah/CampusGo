package com.example.fungwah.campusgo.module.guide.activity;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.WindowManager;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.application.Config;
import com.example.fungwah.campusgo.common.database.DataTools;
import com.example.fungwah.campusgo.module.framework.activity.FrameWorkActivity;
import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.util.SPUtil;

/**
 * Created by FungWah on 2017/11/24.
 */

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    private FloatingActionButton fab;

    @Override
    protected int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            if (SPUtil.getInstance("loginConfig").getString("num", null) != null) {
                Config.user = DataTools.selectUserByNum(SPUtil.getInstance("loginConfig").getString("num", null));
                startActivity(FrameWorkActivity.class);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        switch (v.getId()) {
            case R.id.guide_forward_fab:
                startActivity(GuideBaseInfoActivity.class);
                break;
        }
    }
}
