package com.example.fungwah.campusgo.module.guide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.application.Config;
import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FungWah on 2017/11/25.
 */

public class CollegeChooseActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "CollegeChooseActivity";

    private static final String COLLEGE_NAMES[] = {
            "汽车与交通工程学院",
            "机械工程学院",
            "电子信息工程学院",
            "电气工程学院",
            "计算机工程学院",
            "经济学院",
            "管理学院",
            "外国语学院",
            "珠宝学院",
            "建筑学院",
            "土木工程学院",
            "国际商学院",
            "中兴通信工程学院"
    };

    private String college;
    private int collegeNum;

    private RadioGroup collegeRG;
    private LinearLayout nextStepLl;
    private LinearLayout lastStepLl;
    private List<RadioButton> radioButtonList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.guide_collegeinfo_activity;
    }

    @Override
    protected void initView() {
        collegeRG = findView(R.id.guide_college_rg);
        lastStepLl = findView(R.id.college_last_step_ll);
        nextStepLl = findView(R.id.college_next_step_ll);
        initRbList();
    }

    private void initRbList() {
        collegeRG.removeAllViews();
        for (int i = 0; i < COLLEGE_NAMES.length; i++) {
            RadioButton tempRb = new RadioButton(this);
            tempRb.setText(COLLEGE_NAMES[i]);
            collegeRG.addView(tempRb, new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 56 * 3));
            radioButtonList.add(tempRb);
        }
        for (int i = 0; i < radioButtonList.size(); i++) {
            Log.d(TAG, "initRbList: 第" + i + "个 id = " + radioButtonList.get(i).getId());
        }
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {
        collegeRG.setOnCheckedChangeListener(this);
        lastStepLl.setOnClickListener(this);
        nextStepLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.college_next_step_ll:
                if (collegeRG.getCheckedRadioButtonId() == -1) {
                    ToastUtil.showShort("请先选择学院");
                } else {
                    Intent intent = new Intent(this, GuideMajorClassActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("college", college);
                    for(int i = 0;i<COLLEGE_NAMES.length;i++){
                        if(COLLEGE_NAMES[i].equals(college)){
                            collegeNum = i;
                            break;
                        }
                    }
                    bundle.putInt("collegeNum", collegeNum);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.college_last_step_ll:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(GuideBaseInfoActivity.class);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        college = ((RadioButton) (collegeRG.findViewById(checkedId))).getText().toString();
        Config.user.setCollege(college);
        Log.d(TAG, "onCheckedChanged: college = " + ((RadioButton) (collegeRG.findViewById(checkedId))).getText().toString());
    }
}
