package com.example.fungwahtools.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 用于继承的Fragment
 * Created by 区枫华 on 2017/9/6.
 */

public abstract class BaseFragment extends Fragment {

    protected View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setLayoutId(), container, false);
        initView(view);
        setView();
        initListener();
        return view;
    }

    /**
     * 设置LayoutId
     *
     * @return
     */
    protected abstract int setLayoutId();

    /**
     * 初始化视图
     *
     * @param parent
     */
    protected abstract void initView(View parent);

    /**
     * 设置视图各个相关属性等
     */
    protected abstract void setView();

    /**
     * 初始化监听器
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
        return (T) view.findViewById(id);

    }

    /**
     * 携带bundle转跳Activity
     *
     * @param bundle 参数
     * @param c      目标Activity类
     */
    protected void startActivity(Bundle bundle, Class c) {
        Intent intent = new Intent(this.getContext(), c);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 转跳Activity
     *
     * @param c 目标Activity类
     */
    protected void startActivity(Class c) {
        startActivity(new Intent(this.getContext(), c));
    }

}
