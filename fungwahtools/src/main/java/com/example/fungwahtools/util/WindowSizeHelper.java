package com.example.fungwahtools.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * 获取屏幕的宽高像素和相应比例
 * <p>
 * Created by 区枫华 on 2017/7/20.
 */

public class WindowSizeHelper {

    private static final String TAG = "WindowSizeHelper";
    public static final int DESIGN_WIDTH = 750;
    public static final int DESIGN_HEIGHT = 1334;

    private int windowWidth;
    private int windowHeight;
    private int screenWidth = 0;
    private int screenHeight = 0;
    private float proporationX;
    private float proporationY;

    private static WindowSizeHelper helper = null;

    private Context context = null;

    private WindowSizeHelper(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //获取显示窗口高度
        windowHeight = windowManager.getDefaultDisplay().getHeight();
        //获取显示窗口宽度
        windowWidth = windowManager.getDefaultDisplay().getWidth();
//        //获取手机屏幕高度
//        screenHeight = ((Activity)context).getWindow().getDecorView().getHeight();
//        //获取手机屏幕宽度
//        screenHeight = ((Activity)context).getWindow().getDecorView().getWidth();
        //横向比例
        proporationX = (float) windowWidth / (float) DESIGN_WIDTH;
        //纵向比例
        proporationY = (float) windowHeight / (float) DESIGN_HEIGHT;
        Log.d(TAG, "获取窗口高度:" + windowHeight + "\n" + "获取窗口宽度:" + windowWidth + "\n" + "横向比例:" + proporationX + "\n" + "纵向比例:" + proporationY + "\n" + "获取屏幕高度:" + windowHeight + "\n" + "获取屏幕宽度:" + windowWidth + "\n");
    }

    /**
     * 初始化工具类
     *
     * @param context
     */
    public static void init(Context context) {
        if (helper == null) {
            helper = new WindowSizeHelper(context);
        }
    }

    /**
     * 获取Helper
     *
     * @return
     * @throws Exception 如果没有初始化就获取则会抛出异常
     */
    public static WindowSizeHelper getHelper() {
        if (helper == null) {
            Log.d(TAG, "Fatal error: request before init");
            return null;
        } else {
            return helper;
        }
    }

    public int getDpi() {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取 虚拟按键的高度
     *
     * @return
     */
    public int getBottomStatusHeight() {

        int totalHeight = getDpi();

        return totalHeight - windowHeight;
    }

    public int getScreenWidth(Context context) {
        if (screenWidth == 0) {
            screenWidth = ((Activity) context).getWindow().getDecorView().getWidth();
        }
        return screenWidth;
    }

    public int getScreenHeight(Context context) {
        if (screenHeight == 0) {
            screenHeight = ((Activity) context).getWindow().getDecorView().getHeight();
        }
        return screenHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public float getProporationX() {
        return proporationX;
    }

    public float getProporationY() {
        return proporationY;
    }
}
