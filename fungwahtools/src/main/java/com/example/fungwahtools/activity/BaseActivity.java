package com.example.fungwahtools.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 区枫华 on 2017/8/31.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        setView();
        initListener();
    }

    /**
     * 设置 Activity 的 Layout Id
     *
     * @return 返回 xml Id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置控件相关配置
     */
    protected abstract void setView();

    /**
     * 初始化各种监听器
     */
    protected abstract void initListener();

    /**
     * 简化关联控件
     *
     * @param id  view 的 id
     * @param <T> 泛型，当前 View 对应的 View 下面的子类
     * @return 返回已经转型的类型
     */
    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);

    }

    /**
     * 携带bundle转跳Activity
     *
     * @param bundle 参数
     * @param c      目标Activity类
     */
    protected void startActivity(Bundle bundle, Class c) {
        Intent intent = new Intent(this, c);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 转跳Activity
     *
     * @param c 目标Activity类
     */
    protected void startActivity(Class c) {
        startActivity(new Intent(this, c));
    }

}
