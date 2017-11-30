package com.example.fungwah.campusgo.common.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by FungWah on 2017/10/31.
 */

public class CampusDao extends BaseDao {

    private static final String TAG = "CampusDao";
    private static String table = null;
    private static CampusDao instance = null;

    public CampusDao(Context context) {
        super(context);
    }

    /**
     * 初始化Dao类
     *
     * @param context
     * @return
     */
    public static CampusDao init(Context context) {
        if (instance == null) {
            synchronized (CampusDao.class) {
                if (instance == null) {
                    instance = new CampusDao(context);
                }
            }
        }
        return instance;
    }

    /**
     * 获取dao类对象
     *
     * @return
     */
    public static CampusDao getInstance() {
        if (instance != null) {
            return instance;
        } else {
            Log.e(TAG, "getInstance: 请先初始化本类再进行使用！！！！");
        }
        return null;
    }

    /**
     * 设置选用表
     *
     * @param table 表名
     */
    public static void useTable(String table) {
        CampusDao.table = table;
    }

    /**
     * 插入方法
     *
     * @param contentValues 键值对
     * @return 是否操作成功
     */
    public boolean insert(ContentValues contentValues) {
        boolean flag = false;
        if (table != null) {
            if (insert(table, contentValues) != -1) {
                flag = true;
            }
        } else {
            Log.e(TAG, "insert: 请先设置选用表！！");
        }
        return flag;
    }

    /**
     * 修改方法
     *
     * @param contentValues 键值对
     * @param where         约束条件
     * @param whereArgs     约束条件值
     * @return 操作是否成功
     */
    public boolean update(ContentValues contentValues, String where, String[] whereArgs) {
        boolean flag = false;
        if (table != null) {
            if (update(table, contentValues, where, whereArgs) != -1) {
                flag = true;
            }
        } else {
            Log.e(TAG, "update: 请先设置选用表！！");
        }
        return flag;
    }

    /**
     * 删除方法
     *
     * @param where     约束条件
     * @param whereArgs 约束条件值
     * @return 操作是否成功
     */
    public boolean delete(String where, String[] whereArgs) {
        boolean flag = false;
        if (table != null) {
            if (delete(table, where, whereArgs) != -1) {
                flag = true;
            }
        } else {
            Log.e(TAG, "delete: 请先设置选用表！！");
        }
        return flag;
    }

    /**
     * 查询方法
     *
     * @param where     约束条件
     * @param whereArgs 约束条件值
     * @return 查询结果List\<Map\>
     */
    public List<Map> select(String where, String[] whereArgs) {
        if (table != null) {
            return select(table, where, whereArgs, null, null, null, null);
        } else {
            Log.e(TAG, "select: 请先设置选用表");
        }
        return null;
    }

    public static ContentValues CvAssembler(Class classname){
        ContentValues values = null;
        Method[] methods = classname.getMethods();
        Field[] fields =classname.getClass().getFields();
        String methodName = methods[0].getName();
        String fieldName = fields[0].getName();
        System.out.println(methodName);
        System.out.println(fieldName);
        return values;
    }

}
