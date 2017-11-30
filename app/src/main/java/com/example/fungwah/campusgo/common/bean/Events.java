package com.example.fungwah.campusgo.common.bean;

/**
 * Created by FungWah on 2017/11/18.
 */

public class Events {

    private String num;
    private String name;
    private String type;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int dayOfWeek;
    private String location;
    private String startWeek;
    private String endWeek;
    private String content;
    private String startTime;
    private String endTime;

    public Events() {
    }

    public Events(String num, String name, String type, int year, int month, int day, int hour, int minute, String location, String content) {
        this.num = num;
        this.name = name;
        this.type = type;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.location = location;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.content = content;
    }

    public Events(String num, String name, String type, int hour, int minute, int dayOfWeek, String location, String startWeek, String endWeek, String startTime, String endTime) {
        this.num = num;
        this.name = name;
        this.type = type;
        this.hour = hour;
        this.minute = minute;
        this.dayOfWeek = dayOfWeek;
        this.location = location;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(String startWeek) {
        this.startWeek = startWeek;
    }

    public String getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(String endWeek) {
        this.endWeek = endWeek;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
