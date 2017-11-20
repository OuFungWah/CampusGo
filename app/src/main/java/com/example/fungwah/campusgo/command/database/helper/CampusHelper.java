package com.example.fungwah.campusgo.command.database.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FungWah on 2017/10/31.
 */

public class CampusHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Campus";
    public static final String TABLE_USERS = "Users";
    public static final String TABLE_EVENTS = "Events";

    //注意SQLite的大小写
    private String createUserTable = "CREATE TABLE " + TABLE_USERS + "(" +
            " num CHAR(64) PRIMARY KEY NOT NULL," +
            " name CHAR(20) NOT NULL," +
            " password CHAR(20) NOT NULL," +
            " avatar CHAR(20)," +
            " sex CHAR(20) NOT NULL," +
            " birthday CHAR(20) NOT NULL," +
            " collage CHAR(20) NOT NULL," +
            " subject CHAR(20) NOT NULL," +
            " grade INT," +
            " class INT)";

    private String createEventTable = "CREATE TABLE " + TABLE_EVENTS + "(" +
            " num CHAR(64) PRIMARY KEY NOT NULL," +
            " name CHAR(64) NOT NULL," +
            " type CHAR(20) NOT NULL," +
            " year INT," +
            " month INT," +
            " day INT," +
            " hour INT," +
            " minute INT," +
            " location char(20) NOT NULL," +
            " dayOfWeek INT," +
            " startWeek CHAR(20)," +
            " endWeek CHAR(20)," +
            " startTime CHAR(20)," +
            " endTime CHAR(20)," +
            " content CHAR(400))";

    public CampusHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public CampusHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUserTable);
        db.execSQL(createEventTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_EVENTS + "");
        db.execSQL("DROP TABLE " + TABLE_USERS + "");
        onCreate(db);
//        db.execSQL("ALTER TABLE "+TABLE_EVENTS+" ADD COLUMN year INT NOT NULL");
//        db.execSQL("ALTER TABLE "+TABLE_EVENTS+" ADD COLUMN month INT NOT NULL");
//        db.execSQL("ALTER TABLE "+TABLE_EVENTS+" ADD COLUMN day INT NOT NULL");
//        db.execSQL("ALTER TABLE "+TABLE_EVENTS+" ADD COLUMN hour INT NOT NULL");
//        db.execSQL("ALTER TABLE "+TABLE_EVENTS+" ADD COLUMN day INT NOT NULL");
    }
}
