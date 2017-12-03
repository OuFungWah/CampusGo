package com.example.fungwah.campusgo.module.guide.activity;

import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.application.Config;
import com.example.fungwah.campusgo.common.bean.User;
import com.example.fungwah.campusgo.common.database.DataTools;
import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.util.ToastUtil;

/**
 * Created by FungWah on 2017/11/24.
 */

public class GuideBaseInfoActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout nextStepLL;
    private TextView loginTv;

    private EditText nameEt;
    private EditText snoEt;
    private RadioGroup sexyRg;
    private EditText gradeEt;
    private EditText passwordEt;
    private EditText passwordConfirmEt;
    private EditText phoneEt;

    private User user = new User();


    @Override
    protected int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.guide_baseinfo_activity;
    }

    @Override
    protected void initView() {
        nextStepLL = findView(R.id.next_step_ll);
        loginTv = findView(R.id.login_tv);
        nameEt = findView(R.id.guide_info_name_et);
        snoEt = findView(R.id.guide_info_sno_et);
        sexyRg = findView(R.id.guide_info_sexy_rg);
        gradeEt = findView(R.id.guide_info_grade_et);
        passwordEt = findView(R.id.guide_info_password_et);
        passwordConfirmEt = findView(R.id.guide_info_confirm_password_et);
        phoneEt = findView(R.id.guide_info_phone_et);
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {
        nextStepLL.setOnClickListener(this);
        loginTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_step_ll:
//                startActivity(CollegeChooseActivity.class);
                checkInfo();
                break;
            case R.id.login_tv:
                onBackPressed();
                break;
        }
    }

    private void checkInfo() {
        String name = nameEt.getText().toString();
        String sno = snoEt.getText().toString();
        String sexy = ((RadioButton) (sexyRg.findViewById(sexyRg.getCheckedRadioButtonId()))).getText().toString();
        String grade = gradeEt.getText().toString();
        String password = passwordEt.getText().toString();
        String passwordConfirm = passwordConfirmEt.getText().toString();
        String phone = phoneEt.getText().toString();
        if (name == null || name.equals("")) {
            ToastUtil.showShort("请填写姓名");
        } else if (sno == null || sno.equals("")) {
            ToastUtil.showShort("请填写学号");
        } else if(DataTools.selectUserByNum(sno)!=null){
            ToastUtil.showShort("该学生已注册");
        }else if (grade == null || grade.equals("")) {
            ToastUtil.showShort("请填写年级");
        } else if (password == null || password.equals("")) {
            ToastUtil.showShort("请填写密码");
        } else if (passwordConfirm == null || passwordConfirm.equals("")) {
            ToastUtil.showShort("请确认密码");
        } else if (phone == null || phone.equals("")) {
            ToastUtil.showShort("请填写手机号码");
        } else if (!password.equals(passwordConfirm)) {
            ToastUtil.showShort("前后密码不一致");
        } else {
            user.setName(name);
            user.setNum(sno);
            user.setSex(sexy);
            user.setGrade(grade);
            user.setPassword(password);
            user.setPasswordConfirm(passwordConfirm);
            user.setTelephone(phone);
            Config.user = user;
            startActivity(CollegeChooseActivity.class);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(LoginActivity.class);
        finish();
    }
}
