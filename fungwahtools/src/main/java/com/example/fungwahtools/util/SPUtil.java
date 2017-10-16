package com.example.fungwahtools.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fungwahtools.R;

import java.util.Set;

/**
 * Created by 区枫华 on 2017/9/4.
 */

public class SPUtil {

    /**
     * 用于存储使用的全局上下文
     */
    private static Context context = null;
    private static SharedPreferences sharedPreferences = null;
    private static String fileName = "";
    private static SPUtil spUtil = null;

    private SPUtil() {

    }

    /**
     * 初始化SPUtil
     *
     * @param context
     */
    public static void init(Context context) {
        SPUtil.context = context;
        fileName = context.getPackageName();
        if (spUtil == null) {
            synchronized (SPUtil.class) {
                if (spUtil == null) {
                    spUtil = new SPUtil();
                }
            }
        }
    }

    /**
     * 获取SPUtil对象
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static SPUtil getInstance(String name) throws Exception {
        if (spUtil != null) {
            sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            return spUtil;
        } else {
            throw new Exception("请初始化本类");
        }
    }

    /**
     * 存放随便什么类型的对象
     *
     * @param key
     * @param object
     */
    public void putObject(String key, Object object) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor = sharedPreferences.edit();
            if (sharedPreferences != null) {
                if (object instanceof Integer) {
                    editor.putInt(key, (Integer) object);
                } else if (object instanceof Boolean) {
                    editor.putBoolean(key, (Boolean) object);
                } else if (object instanceof Float) {
                    editor.putFloat(key, (Float) object);
                } else if (object instanceof Long) {
                    editor.putLong(key, (Long) object);
                } else if (object instanceof String) {
                    editor.putString(key, (String) object);
                } else if (object instanceof Set) {
                    editor.putStringSet(key, (Set<String>) object);
                }
            }
            editor.commit();
        }
    }

    /**
     * 存放字符串类型
     *
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    /**
     * 存放整型
     *
     * @param key
     * @param value
     */
    public void putInt(String key, int value) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor = sharedPreferences.edit();
            editor.putInt(key, value);
            editor.commit();
        }
    }

    /**
     * 存放布尔值
     *
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    /**
     * 存放浮点型
     *
     * @param key
     * @param value
     */
    public void putFloat(String key, float value) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor = sharedPreferences.edit();
            editor.putFloat(key, value);
            editor.commit();
        }
    }

    /**
     * 存放长整形
     *
     * @param key
     * @param value
     */
    public void putLong(String key, long value) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor = sharedPreferences.edit();
            editor.putLong(key, value);
            editor.commit();
        }
    }

    /**
     * 存放字符数组
     *
     * @param key
     * @param values
     */
    public void putStringSet(String key, Set<String> values) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor = sharedPreferences.edit();
            editor.putStringSet(key, values);
            editor.commit();
        }
    }

    /**
     * @param key
     * @param defBack
     * @return
     */
    public String getString(String key, String defBack) {
        return sharedPreferences.getString(key, defBack);

    }

    /**
     * 获取整型数据
     *
     * @param key
     * @param defBack
     * @return
     */
    public int getInt(String key, int defBack) {
        return sharedPreferences.getInt(key, defBack);

    }

    /**
     * 获取布尔值数据
     *
     * @param key
     * @param defBack
     * @return
     */
    public boolean getBoolean(String key, boolean defBack) {
        return sharedPreferences.getBoolean(key, defBack);

    }

    /**
     * 获取浮点型数据
     *
     * @param key
     * @param defBack
     * @return
     */
    public float getFloat(String key, float defBack) {
        return sharedPreferences.getFloat(key, defBack);

    }

    /**
     * 获取字符串集合数据
     *
     * @param key
     * @param defBack
     * @return
     */
    public Set<String> getStringSet(String key, Set<String> defBack) {
        return sharedPreferences.getStringSet(key, defBack);

    }

    public SPUtil remove(String name) {
        sharedPreferences.edit().remove(name).apply();
        return spUtil;
    }

    public SPUtil clearAll() {
        sharedPreferences.edit().clear().apply();
        return spUtil;
    }

}
