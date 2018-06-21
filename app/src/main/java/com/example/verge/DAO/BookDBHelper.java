package com.example.verge.DAO;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.net.URL;

public class BookDBHelper extends SQLiteOpenHelper {
    static String name = "Preferences.book";
    static int version = 1;
    public static String TABLE_NAME = "books";
    public BookDBHelper(Context context) {
        super(context, name, null, version);
    }
    public static Uri[] eatFoodyImages = new Uri[]{Uri.parse("http://hiphotos.baidu.com/doc/pic/item/fcfaaf51f3deb48f11fa7986f81f3a292cf578d3.jpg"),
            Uri.parse("http://hiphotos.baidu.com/doc/pic/item/f3d3572c11dfa9ec0d3ff5b96ed0f703918fc10f.jpg"),
            Uri.parse("http://hiphotos.baidu.com/doc/pic/item/e61190ef76c6a7efccbce232f6faaf51f3de660f.jpg"),
            Uri.parse( "http://hiphotos.baidu.com/doc/pic/item/b7fd5266d016092481043d23de0735fae6cd34e0.jpg"),
    };
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table books(_id varchar(50) primary key,title varchar(50),writer varchar(50),publishOrg varchar(50)," +
                "coverUrl varchar(100),type varchar(50));";
        db.execSQL(sql);
        Log.i("TAG", "create Database------------->");
        sql="insert into books values('dss','dsa','df','dfs','"+eatFoodyImages[0].toString()+"','dsfd'),('ds1s','dsa','df','dfs','"+eatFoodyImages[1].toString()+"','dsfd')," +
                "('ds2s','dsa','df','dfs','"+eatFoodyImages[2].toString()+"','dsfd'),('ds3s','dsa','df','dfs','"+eatFoodyImages[3].toString()+"','dsfd')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
