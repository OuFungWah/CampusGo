package com.example.fungwah.campusgo.common.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by FungWah on 2017/11/25.
 */

public class User extends BmobObject{

    private String name;
    private String num;
    private String sex;
    private String grade;
    private String password;
    private String passwordConfirm;
    private String telephone;
    private String college;
    private String majorClass;

    public User() {
    }

    public User(String name, String sno, String sexy, String grade, String password, String passwordConfirm, String tellphone, String college, String maiorClass) {
        this.name = name;
        this.num = sno;
        this.sex = sexy;
        this.grade = grade;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.telephone = tellphone;
        this.college = college;
        this.majorClass = maiorClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajorClass() {
        return majorClass;
    }

    public void setMajorClass(String majorClass) {
        this.majorClass = majorClass;
    }
}
