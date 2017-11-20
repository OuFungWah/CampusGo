package com.example.fungwahtools.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基本数据库操作类
 * Created by FungWah on 2017/10/31.
 */

public class BaseDao {

    private static final String TAG = "BaseDao";
    private SQLiteOpenHelper helper;

    public BaseDao(SQLiteOpenHelper helper) {
        this.helper = helper;
    }

    /**
     * 插入方法
     *
     * @param table         表名
     * @param contentValues 插入字段键值对
     * @return 受影响数据行数
     */
    protected long insert(String table, ContentValues contentValues) {
        long index = -1;
        SQLiteDatabase database = null;
        try {
            database = helper.getWritableDatabase();
            index = database.insert(table, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "insert: " + e.getMessage());
        } finally {
            if (database != null)
                database.close();
        }
        return index;
    }

    /**
     * 修改方法
     *
     * @param table         表名
     * @param contentValues 修改字段键值对
     * @param where         约束条件字段
     * @param whereArgs     约束条件字段值
     * @return 返回受影响行数
     */
    protected long update(String table, ContentValues contentValues, String where, String[] whereArgs) {
        long index = -1;
        SQLiteDatabase database = null;
        try {
            database = helper.getWritableDatabase();
            index = database.update(table, contentValues, where, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "update: " + e.getMessage());
        } finally {
            if (database != null)
                database.close();
        }
        return index;
    }

    /**
     * 删除方法
     *
     * @param table     表名
     * @param where     约束条件字段
     * @param whereArgs 约束条件字段值
     * @return 返回受影响的行数
     */
    protected long delete(String table, String where, String[] whereArgs) {
        long index = -1;
        SQLiteDatabase database = null;
        try {
            database = helper.getWritableDatabase();
            index = database.delete(table, where, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "delete: " + e.getMessage());
        } finally {
            if (database != null)
                database.close();
        }
        return index;
    }

    /**
     * 查询方法
     *
     * @param table         表名
     * @param selection     约束条件字段
     * @param selectionArgs 约束条件字段值
     * @param groupBy       数据分组
     * @param having        选取分组
     * @param orderBy       排序
     * @param limit         限制查询结果行数
     * @return 装有查询结果的列表
     */
    protected List<Map> select(String table, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        List<Map> mapList = new ArrayList<>();
        Cursor cursor = null;
        SQLiteDatabase database = null;
        try {
            database = helper.getReadableDatabase();
            cursor = database.query(table, null, selection, selectionArgs, groupBy, having, orderBy, limit);
            while (cursor.moveToNext()) {
                Map map = new HashMap();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    String name = cursor.getColumnName(i);
                    switch (cursor.getType(i)) {
                        case Cursor.FIELD_TYPE_NULL:
                            map.put(name, null);
                            break;
                        case Cursor.FIELD_TYPE_BLOB:
                            map.put(name, cursor.getBlob(i));
                            break;
                        case Cursor.FIELD_TYPE_FLOAT:
                            map.put(name, cursor.getFloat(i));
                            break;
                        case Cursor.FIELD_TYPE_INTEGER:
                            map.put(name, cursor.getInt(i));
                            break;
                        case Cursor.FIELD_TYPE_STRING:
                            map.put(name, cursor.getString(i));
                            break;
                    }
                }
                mapList.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "select: " + e.getMessage());
            return null;
        } finally {
            if (database != null)
                database.close();
        }
        return mapList;
    }

}
