package com.example.fungwahtools.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by FungWah on 2017/9/21.
 */

public class ToastUtil {

    private static String oldStr;
    private static Context context;
    private static Toast toast;
    private static long startTime = 0;

    /**
     * 请在 Application 初始化本类
     *
     * @param context
     */
    public static void init(Context context) {
        ToastUtil.context = context;
    }

    public static void showShort(String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            startTime = System.currentTimeMillis();
            toast.show();
            oldStr = msg;
        } else {
            if (System.currentTimeMillis() - startTime > 2000) {
                toast.setText(msg);
                startTime = System.currentTimeMillis();
                toast.show();
            } else {
                if(oldStr.equals(msg)){
                    toast.setText(msg);
                }else{
                    toast.cancel();
                    toast.setText(msg);
                    toast.show();
                }

            }
        }
    }

    public static void showLong(String msg) {
        if (System.currentTimeMillis() - startTime > Toast.LENGTH_LONG) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
            startTime = System.currentTimeMillis();
            toast.show();
        } else {
            toast.setText(msg);
        }
    }

}
