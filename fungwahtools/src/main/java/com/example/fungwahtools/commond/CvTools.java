package com.example.fungwahtools.commond;

import android.content.ContentValues;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by FungWah on 2017/11/1.
 */

public class CvTools {

    /**
     * 用于将Bean的数据转换成ContentValues
     * @param obj
     * @return
     */
    public static ContentValues getCvFromObject(Object obj) {
        ContentValues contentValues = null;
        Class objClass = obj.getClass();

        try {
            contentValues = new ContentValues();
            //获取当前类中的所有参数
            Field[] fields = objClass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                //临时存放变量名
                String tempName = fields[i].getName();
                //获取变量名首字母
                String firstChar = String.valueOf(tempName.charAt(0));
                //首字母转换大写
                String tempFirstChar = firstChar.toUpperCase();
                //切去原有首字母，然后和大写首字母连接
                tempName = tempName.substring(1);
                tempName = tempFirstChar + tempName;
                //合并方法名
                String tempMethodName = "get" + tempName;
                Method tempMethod = objClass.getMethod(tempMethodName);
                //如果没有参数，则new一个空的Object数组里面不放东西，而不是用null
                Object tempValue = tempMethod.invoke(obj,new Object[]{});

                //按照对象类型插入键值对
                if(tempValue instanceof String){
                    contentValues.put(tempName,(String)tempValue);
                }else if(tempValue instanceof Integer){
                    contentValues.put(tempName,(int)tempValue);
                }else if (tempValue instanceof Boolean){
                    contentValues.put(tempName,(boolean)tempValue);
                }else if (tempValue instanceof Float){
                    contentValues.put(tempName,(float)tempValue);
                }else if (tempValue instanceof Long){
                    contentValues.put(tempName,(long)tempValue);
                }else if (tempValue instanceof Double){
                    contentValues.put(tempName,(double)tempValue);
                }
            }
        } catch (Exception e) {

        }

        return contentValues;
    }

}
