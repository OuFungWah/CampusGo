package com.example.fungwah.campusgo.module.timeline.bean;

/**
 * Created by FungWah on 2017/11/21.
 */

public class TagDate {

    private int year;
    private int month;
    private int day;
    private int dayOfWeek;
    private String title;
    private boolean selected;

    public TagDate() {

    }

    public TagDate(String title) {
        this.title = title;
    }

    public TagDate(int year, int month, int day, boolean selected) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.selected = selected;
    }

    public TagDate(int year, int month, int day, int dayOfWeek, boolean selected) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.selected = selected;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
