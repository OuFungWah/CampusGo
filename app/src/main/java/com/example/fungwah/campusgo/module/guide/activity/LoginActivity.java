package com.example.fungwah.campusgo.module.guide.activity;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.application.Config;
import com.example.fungwah.campusgo.common.bean.User;
import com.example.fungwah.campusgo.common.database.DataTools;
import com.example.fungwah.campusgo.module.framework.activity.FrameWorkActivity;
import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.util.SPUtil;
import com.example.fungwahtools.util.ToastUtil;

import org.json.JSONArray;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by FungWah on 2017/11/25.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

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
        Bmob.initialize(this, Config.APP_KEY);
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

                BmobQuery<User> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("num", inputSno).findObjects(new FindListener<User>() {
                    @Override
                    public void done(List<User> list, BmobException e) {
                        if (e == null) {
                            Log.d(TAG, "done: " + list.get(0).toString());
                            if (inputPassword.equals(list.get(0).getPassword())) {
                                Config.user = list.get(0);
                                DataTools.getEventFromNet();
                                //存储登录成功的用户
                                try {
                                    SPUtil.getInstance("loginConfig").putString("num", Config.user.getNum());
                                    SPUtil.getInstance("loginConfig").putString("password", Config.user.getPassword());
                                    DataTools.insertUser(Config.user);
                                    startActivity(FrameWorkActivity.class);
                                    finish();
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            } else {
                                ToastUtil.showShort("密码不正确");
                            }
                        } else {
                            ToastUtil.showShort("服务器不通，尝试本地登录");
                            User user = DataTools.selectUserByNum(inputSno);
                            if (user == null) {
                                //若本地数据库没有该用户
                                ToastUtil.showShort("不存在该学生，请注册");
                            } else if (inputPassword.equals(user.getPassword())) {
                                ToastUtil.showShort("密码不正确");
                            } else {
                                Config.user = user;
                                try {
                                    SPUtil.getInstance("loginConfig").putString("num", Config.user.getNum());
                                    SPUtil.getInstance("loginConfig").putString("password", Config.user.getPassword());
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                                startActivity(FrameWorkActivity.class);
                                finish();
                            }
                        }
                    }
                });
                break;
            case R.id.register_tv:
                startActivity(GuideBaseInfoActivity.class);
                finish();
                break;
        }
    }
}
