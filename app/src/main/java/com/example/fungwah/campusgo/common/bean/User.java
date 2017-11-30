package com.example.fungwah.campusgo.common.bean;

/**
 * Created by FungWah on 2017/11/25.
 */

public class User {

    private String name;
    private String sno;
    private String sex;
    private String grade;
    private String password;
    private String passwordConfirm;
    private String tellphone;
    private String college;
    private String maiorClass;

    public User() {
    }

    public User(String name, String sno, String sexy, String grade, String password, String passwordConfirm, String tellphone, String college, String maiorClass) {
        this.name = name;
        this.sno = sno;
        this.sex = sexy;
        this.grade = grade;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.tellphone = tellphone;
        this.college = college;
        this.maiorClass = maiorClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
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

    public String getTellphone() {
        return tellphone;
    }

    public void setTellphone(String tellphone) {
        this.tellphone = tellphone;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMaiorClass() {
        return maiorClass;
    }

    public void setMaiorClass(String maiorClass) {
        this.maiorClass = maiorClass;
    }
}
