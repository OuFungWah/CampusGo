package com.example.fungwah.campusgo.module.guide.activity;

import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.common.bean.User;
import com.example.fungwah.campusgo.common.database.DataTools;
import com.example.fungwah.campusgo.module.framework.activity.FrameWorkActivity;
import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.util.ToastUtil;

/**
 * Created by FungWah on 2017/11/25.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout loginLl;
    private TextView registerTv;

    private EditText snoEt;
    private EditText passwordEt;

    private String inputSno;
    private String inputPassword;

    @Override
    protected int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.login_activity;
    }

    @Override
    protected void initView() {
        loginLl = findView(R.id.login_finish_ll);
        registerTv = findView(R.id.register_tv);
        snoEt = findView(R.id.login_sno_et);
        passwordEt = findView(R.id.login_password_et);
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {
        loginLl.setOnClickListener(this);
        registerTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_finish_ll:
                inputSno = snoEt.getText().toString();
                inputPassword = passwordEt.getText().toString();
                User user = DataTools.selectUserByNum(inputSno);
                if (user == null) {
                    ToastUtil.showShort("不存在该用户");
                } else if (!inputPassword.equals(user.getPassword())) {
                    ToastUtil.showShort("密码不正确");
                } else {
                    startActivity(FrameWorkActivity.class);
                    finish();
                }
                break;
            case R.id.register_tv:
                startActivity(GuideBaseInfoActivity.class);
                finish();
                break;
        }
    }
}
