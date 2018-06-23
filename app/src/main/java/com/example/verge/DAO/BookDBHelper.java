package com.example.verge.DAO;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.net.URL;

public class BookDBHelper extends SQLiteOpenHelper {
    private static String name = "Preferences.book";
    private static int version = 1;
    public static String TABLE_NAME = "books";

    BookDBHelper(Context context) {
        super(context, name, null, version);
    }

    private static Uri[] eatFoodyImages = new Uri[]{Uri.parse("http://hiphotos.baidu.com/doc/pic/item/fcfaaf51f3deb48f11fa7986f81f3a292cf578d3.jpg"),
            Uri.parse("http://hiphotos.baidu.com/doc/pic/item/f3d3572c11dfa9ec0d3ff5b96ed0f703918fc10f.jpg"),
            Uri.parse("http://hiphotos.baidu.com/doc/pic/item/e61190ef76c6a7efccbce232f6faaf51f3de660f.jpg"),
            Uri.parse("http://hiphotos.baidu.com/doc/pic/item/b7fd5266d016092481043d23de0735fae6cd34e0.jpg"),
    };
    private String[] bookUrl = new String[]{
            "http://yuedu.baidu.com/ebook/2f4e1f01a26925c52dc5bf6b?fr=search&type=book",
            "http://yuedu.baidu.com/ebook/c810021d647d27284b7351cb?fr=search&type=book",
            "http://yuedu.baidu.com/ebook/80f7afcb900ef12d2af90242a8956bec0975a5fa?fr=search&type=book",
            "http://yuedu.baidu.com/ebook/2ab2ea14f4335a8102d276a20029bd64793e6241?fr=search&type=book"
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table books(_id varchar(50) primary key,title varchar(50),writer varchar(50),publishOrg varchar(50)," +
                "coverUrl varchar(100),type varchar(50),userid integer,bookUrl varchar(100));";
        db.execSQL(sql);
        Log.i("TAG", "create Database------------->");
        sql = "insert into books values('1','dsa','df','dfs','" + eatFoodyImages[0].toString() + "','dsfd','0','" + bookUrl[0] + "'),('2','dsa','df','dfs','" + eatFoodyImages[1].toString() + "','dsfd','0','" + bookUrl[1] + "')," +
                "('3','dsa','df','dfs','" + eatFoodyImages[2].toString() + "','dsfd','0','" + bookUrl[2] + "'),('4','dsa','df','dfs','" + eatFoodyImages[3].toString() + "','dsfd','0','" + bookUrl[3] + "')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
