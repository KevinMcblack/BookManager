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
    public static String[] tags = {
            "[三国演义,古典文学,小说,罗贯,古典名著,中国,名著]",
            "[三国演义,小说,罗贯,古典名著,名著]",
            "[三国演义,古典文学,,罗贯,古典名著,中国,名著]",
            "[三国演义,古典文学,小说,中国,名著]",
            "null",
            "['小说','名著','中国古典','四大名著']",
            "null",
            "['']",
            "['小说','名著','中国古典','四大名著']",
            "['运筹帷幄','励志','三国时期','中国经典','三国','故事','文学','四大名著','明清小说','历史小说','呂布','明代','古代','儿童文学','经典推荐','古今地名','天书','曹操','世界名著','滚滚长江罗灌水','三国演义','小时候','未买','六角丛书','Romance','Jins','小说','古代文学','漫畫','文艺','历史地理','中华书局','老羅你真禍害','中国','经典名著','家有藏书','动漫','国学','二年级','少儿','中国文学','回忆','历史','海豚出版社','大陆','中国小说','漫画','中国名著','中国古典文学','谋略','Three','Fiction','李典','童年','罗贯中书','張遼','中国古典','小说闲看','故乡书架','研究','百看不厌','library','整理','剧本','中国画报','图画书','连环画','地图','章回体小说','文学及文艺评论','郑渊洁','通俗小说','明本','張飛','名家改编','罗贯中','本社图书','收藏','三国历史','中国古代文学','很久以前讀的','名著','樂進','文学经典','古典名著','小說','毛宗岗','藏书','陈友琴','古典文学','古典','古']"
    };
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table books(" +
                "_id varchar(50) primary key," +
                "title varchar(50)," +
                "writer varchar(50)," +
                "publishOrg varchar(50)," +
                "coverUrl varchar(100)," +
                "type varchar(50)," +
                "userid integer," +
                "bookUrl varchar(100)," +
                "booktag varchar(100)," +
                "pingjia varchar(255)," +
                "status varchar(255)," +
                "toId vachar(255));";
        db.execSQL(sql);
        Log.i("TAG", "create Database------------->");
        sql = "insert into books values" +
                "('1','dsa','df','dfs','" + eatFoodyImages[0].toString() + "','dsfd','0','" + bookUrl[0] + "','"+tags[0]+"','','已添加','')," +
                "('2','dsa','df','dfs','" + eatFoodyImages[1].toString() + "','dsfd','0','" + bookUrl[1] + "','"+tags[1]+"','','已添加','')," +
                "('3','dsa','df','dfs','" + eatFoodyImages[2].toString() + "','dsfd','0','" + bookUrl[2] + "','"+tags[2]+"','','外借中','admin')," +
                "('4','dsa','df','dfs','" + eatFoodyImages[3].toString() + "','dsfd','0','" + bookUrl[3] + "','"+tags[3]+"','评价内容测试','已添加','')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
