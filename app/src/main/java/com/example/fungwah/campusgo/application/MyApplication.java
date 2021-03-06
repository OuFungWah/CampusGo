package com.example.fungwah.campusgo.application;

import android.app.Application;

import com.example.fungwah.campusgo.common.database.dao.CampusDao;
import com.example.fungwahtools.util.SPUtil;
import com.example.fungwahtools.util.ToastUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by FungWah on 2017/10/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CampusDao.init(this);
        SPUtil.init(this);
        ToastUtil.init(this);
        Fresco.initialize(this);
    }
}
