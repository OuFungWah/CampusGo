package com.example.fungwah.campusgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.fungwahtools.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.homepage;
    }

    @Override
    protected void initView() {
        toolbar = findView(R.id.toolbar);
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {

    }
}
