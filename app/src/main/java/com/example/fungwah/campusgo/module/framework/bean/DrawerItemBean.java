package com.example.fungwah.campusgo.module.framework.bean;

/**
 * Created by FungWah on 2017/10/17.
 */

public class DrawerItemBean {

    private int iconRes;
    private String name;

    public DrawerItemBean(int iconRes, String name) {
        this.iconRes = iconRes;
        this.name = name;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
