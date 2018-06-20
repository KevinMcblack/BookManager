package com.example.verge.DAO;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDBHelper extends SQLiteOpenHelper {
    static String name = "Preferences.db";
    static int version = 1;
    public static String TABLE_NAME = "books";
    public BookDBHelper(Context context) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table books(id varchar(50) primary key,title varchar(50),writer varchar(50),publishOrg varchar(50)," +
                "coverUrl varchar(50),type varchar(50));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
