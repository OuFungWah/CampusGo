package com.example.fungwah.campusgo.module.guide.activity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.application.Config;
import com.example.fungwah.campusgo.common.database.DataTools;
import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.util.ToastUtil;

/**
 * Created by FungWah on 2017/11/25.
 */

public class GuideMajorClassActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "GuideMajorClassActivity";

    private RadioGroup classRg;
    private static final String MAJOR_CLASSES[][] = {
            {"汽车服务工程 1 班", "汽车服务工程 2 班", "汽车服务工程 3 班", "汽车服务工程 4 班", "汽车服务工程 5 班", "汽车服务工程 6 班", "车辆工程 1 班", "车辆工程 2 班", "车辆工程 3 班", "车辆工程 4 班", "车辆工程 5 班", "车辆工程 6 班"},
            {"机械工程 1 班", "机械工程 2 班", "机械工程 3 班", "机械工程 4 班", "机械工程 5 班", "机械工程 6 班", "机械电子工程 1 班", "机械电子工程 2 班", "机械电子工程 3 班", "机械电子工程 4 班", "机械电子工程 5 班", "机械电子工程 6 班", "工业设计 1 班", "工业设计 2 班", "工业设计 3 班", "工业设计 4 班", "工业设计 5 班", "工业设计 6 班", "机械人工程 1 班", "机械人工程 2 班", "机械人工程 3 班", "机械人工程 4 班", "机械人工程 5 班", "机械人工程 6 班"},
            {"电子信息工程 1 班", "电子信息工程 2 班", "电子信息工程 3 班", "电子信息工程 4 班", "电子信息工程 5 班", "电子信息工程 6 班", "自动化 1 班", "自动化 2 班", "自动化 3 班", "自动化 4 班", "自动化 5 班", "自动化 6 班", "通信工程 1 班", "通信工程 2 班", "通信工程 3 班", "通信工程 4 班", "通信工程 5 班", "通信工程 6 班"},
            {"电气工程及其自动化 1 班", "电气工程及其自动化 2 班", "电气工程及其自动化 3 班", "电气工程及其自动化 4 班", "电气工程及其自动化 5 班", "电气工程及其自动化 6 班", "新能源科学与工程 1 班", "新能源科学与工程 2 班", "新能源科学与工程 3 班", "新能源科学与工程 4 班", "新能源科学与工程 5 班", "新能源科学与工程 6 班"},
            {
                    "计算机科学与技术 1 班",
                    "计算机科学与技术 2 班",
                    "计算机科学与技术 3 班",
                    "计算机科学与技术 4 班",
                    "计算机科学与技术 5 班",
                    "计算机科学与技术 6 班",
                    "软件工程 1 班",
                    "软件工程 2 班",
                    "软件工程 3 班",
                    "软件工程 4 班",
                    "软件工程 5 班",
                    "软件工程 6 班",
                    "网络工程 1 班",
                    "网络工程 2 班",
                    "网络工程 3 班",
                    "网络工程 4 班",
                    "网络工程 5 班",
                    "网络工程 6 班",
                    "信息与计算科学 1 班",
                    "信息与计算科学 2 班",
                    "信息与计算科学 3 班",
                    "信息与计算科学 4 班",
                    "信息与计算科学 5 班",
                    "信息与计算科学 6 班"
            },
            {
                    "国际经济与贸易 1 班",
                    "国际经济与贸易 2 班",
                    "国际经济与贸易 3 班",
                    "国际经济与贸易 4 班",
                    "国际经济与贸易 5 班",
                    "国际经济与贸易 6 班",
                    "国际经济与贸易 7 班",
                    "国际经济与贸易 8 班",
                    "国际经济与贸易 9 班",
                    "国际经济与贸易 10 班",
                    "国际经济与贸易 11 班",
                    "国际经济与贸易 12 班",
                    "金融工程 1 班",
                    "金融工程 2 班",
                    "金融工程 3 班",
                    "金融工程 4 班",
                    "金融工程 5 班",
                    "金融工程 6 班",
                    "金融工程 7 班",
                    "金融工程 8 班",
                    "金融工程 9 班",
                    "金融工程 10 班",
                    "金融工程 11 班",
                    "金融工程 12 班",
                    "经济统计 1 班",
                    "经济统计 2 班",
                    "经济统计 3 班",
                    "经济统计 4 班",
                    "经济统计 5 班",
                    "经济统计 6 班",
                    "经济统计 7 班",
                    "经济统计 8 班",
                    "经济统计 9 班",
                    "经济统计 10 班",
                    "经济统计 11 班",
                    "经济统计 12 班",
                    "税收学 1 班",
                    "税收学 2 班",
                    "税收学 3 班",
                    "税收学 4 班",
                    "税收学 5 班",
                    "税收学 6 班",
                    "税收学 7 班",
                    "税收学 8 班",
                    "税收学 9 班",
                    "税收学 10 班",
                    "税收学 11 班",
                    "税收学 12 班"
            },
            {
                    "工商管理 1 班",
                    "工商管理 2 班",
                    "工商管理 3 班",
                    "工商管理 4 班",
                    "工商管理 5 班",
                    "工商管理 6 班",
                    "工商管理 7 班",
                    "工商管理 8 班",
                    "工商管理 9 班",
                    "工商管理 10 班",
                    "工商管理 11 班",
                    "工商管理 12 班",
                    "人力资源管理 1 班",
                    "人力资源管理 2 班",
                    "人力资源管理 3 班",
                    "人力资源管理 4 班",
                    "人力资源管理 5 班",
                    "人力资源管理 6 班",
                    "人力资源管理 7 班",
                    "人力资源管理 8 班",
                    "人力资源管理 9 班",
                    "人力资源管理 10 班",
                    "人力资源管理 11 班",
                    "人力资源管理 12 班",
                    "会计学 1 班",
                    "会计学 2 班",
                    "会计学 3 班",
                    "会计学 4 班",
                    "会计学 5 班",
                    "会计学 6 班",
                    "会计学 7 班",
                    "会计学 8 班",
                    "会计学 9 班",
                    "会计学 10 班",
                    "会计学 11 班",
                    "会计学 12 班",
                    "财务管理 1 班",
                    "财务管理 2 班",
                    "财务管理 3 班",
                    "财务管理 4 班",
                    "财务管理 5 班",
                    "财务管理 6 班",
                    "财务管理 7 班",
                    "财务管理 8 班",
                    "财务管理 9 班",
                    "财务管理 10 班",
                    "财务管理 11 班",
                    "财务管理 12 班",
                    "市场营销 1 班",
                    "市场营销 2 班",
                    "市场营销 3 班",
                    "市场营销 4 班",
                    "市场营销 5 班",
                    "市场营销 6 班",
                    "市场营销 7 班",
                    "市场营销 8 班",
                    "市场营销 9 班",
                    "市场营销 10 班",
                    "市场营销 11 班",
                    "市场营销 12 班",
                    "电子商务 1 班",
                    "电子商务 2 班",
                    "电子商务 3 班",
                    "电子商务 4 班",
                    "电子商务 5 班",
                    "电子商务 6 班",
                    "电子商务 7 班",
                    "电子商务 8 班",
                    "电子商务 9 班",
                    "电子商务 10 班",
                    "电子商务 11 班",
                    "电子商务 12 班"
            },
            {
                    "英语 1 班",
                    "英语 2 班",
                    "英语 3 班",
                    "英语 4 班",
                    "英语 5 班",
                    "英语 6 班",
                    "英语 7 班",
                    "英语 8 班",
                    "英语 9 班",
                    "英语 10 班",
                    "英语 11 班",
                    "英语 12 班",
                    "日语 1 班",
                    "日语 2 班",
                    "日语 3 班",
                    "日语 4 班",
                    "日语 5 班",
                    "日语 6 班",
                    "日语 7 班",
                    "日语 8 班",
                    "日语 9 班",
                    "日语 10 班",
                    "日语 11 班",
                    "日语 12 班"
            },
            {
                    "服装与服饰设计 1 班",
                    "服装与服饰设计 2 班",
                    "服装与服饰设计 3 班",
                    "服装与服饰设计 4 班",
                    "服装与服饰设计 5 班",
                    "产品设计 1 班",
                    "产品设计 2 班",
                    "产品设计 3 班",
                    "产品设计 4 班",
                    "产品设计 5 班",
                    "宝石及材料工艺 1 班",
                    "宝石及材料工艺 2 班",
                    "宝石及材料工艺 3 班",
                    "宝石及材料工艺 4 班",
                    "宝石及材料工艺 5 班"
            },
            {
                    "建筑 1 班",
                    "建筑 2 班",
                    "建筑 3 班",
                    "建筑 4 班",
                    "建筑 5 班",
                    "建筑 6 班",
                    "建筑 7 班",
                    "建筑 8 班",
                    "建筑 9 班",
                    "建筑 10 班"
            },
            {
                    "土木工程 1 班",
                    "土木工程 2 班",
                    "土木工程 3 班",
                    "土木工程 4 班",
                    "土木工程 5 班",
                    "土木工程 6 班",
                    "土木工程 7 班",
                    "土木工程 8 班",
                    "土木工程 9 班",
                    "土木工程 10 班",
                    "交通工程 1 班",
                    "交通工程 2 班",
                    "交通工程 3 班",
                    "交通工程 4 班",
                    "交通工程 5 班",
                    "交通工程 6 班",
                    "交通工程 7 班",
                    "交通工程 8 班",
                    "交通工程 9 班",
                    "交通工程 10 班"
            },
            {
                    "投资学 1 班",
                    "投资学 2 班",
                    "投资学 3 班",
                    "国际经济与贸易（国际班） 1 班",
                    "国际经济与贸易（国际班） 2 班",
                    "国际经济与贸易（国际班） 3 班",
                    "国际经济与贸易（双语班） 1 班",
                    "国际经济与贸易（双语班） 2 班",
                    "国际经济与贸易（双语班） 3 班",
                    "会计学（双语班） 1 班",
                    "会计学（双语班） 2 班",
                    "会计学（双语班） 3 班",
                    "会计学（双语注会班） 1 班",
                    "会计学（双语注会班） 2 班",
                    "会计学（双语注会班） 3 班",
            },
            {
                    "通信工程 1 班",
                    "通信工程 2 班",
                    "通信工程 3 班",
                    "通信工程 4 班"
            }
    };

    private int collegeNum = 0;

    private LinearLayout lastStepLl;
    private LinearLayout finishLl;

    private Bundle bundle;

    private String majorClass;

    @Override
    protected int getLayoutId() {
        return R.layout.guide_major_class_activity;
    }

    @Override
    protected void initView() {
        bundle = getIntent().getExtras();
        collegeNum = bundle.getInt("collegeNum");
        Log.d(TAG, "initView: collegeNum = "+collegeNum);
        lastStepLl = findView(R.id.major_class_last_step_ll);
        finishLl = findView(R.id.major_class_next_step_ll);
        classRg = findView(R.id.guide_class_rg);
        initRbList();
    }

    private void initRbList() {
        classRg.removeAllViews();
        for (int i = 0; i < MAJOR_CLASSES[collegeNum].length; i++) {
            RadioButton tempRb = new RadioButton(this);
            tempRb.setText(MAJOR_CLASSES[collegeNum][i]);
            classRg.addView(tempRb, new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3 * 56));
        }
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {
        classRg.setOnCheckedChangeListener(this);
        lastStepLl.setOnClickListener(this);
        finishLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.major_class_last_step_ll:
                onBackPressed();
                break;
            case R.id.major_class_next_step_ll:
                if (classRg.getCheckedRadioButtonId() == -1) {
                    ToastUtil.showShort("请选择班级");
                } else {
                    boolean flag = DataTools.insertUser(Config.user);
                    if(flag){
                        startActivity(LoginActivity.class);
                        finish();
                    }else{
                        ToastUtil.showShort("创建用户失败");
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CollegeChooseActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        majorClass = ((RadioButton) (group.findViewById(checkedId))).getText().toString();
        Config.user.setMaiorClass(majorClass);
        Log.d(TAG, "onCheckedChanged: majorClass = " + ((RadioButton) (group.findViewById(checkedId))).getText().toString());
    }
}
