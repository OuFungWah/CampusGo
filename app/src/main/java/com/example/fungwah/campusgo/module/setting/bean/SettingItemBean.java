package com.example.fungwah.campusgo.module.setting.bean;

/**
 * Created by FungWah on 2017/10/22.
 */

public class SettingItemBean {

    public static final int TITLE = 0;
    public static final int CONTENT = 1;

    private int type;
    private String text;

    public SettingItemBean(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
