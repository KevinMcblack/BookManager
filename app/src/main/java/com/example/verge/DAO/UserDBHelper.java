package com.example.verge.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper {
	static String name = "Preferences.db";
	static int version = 1;
	public static String TABLE_NAME = "user";
	public UserDBHelper(Context context) {
		super(context, name, null, version);
	}

	// 只在创建的时候用一次
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table user(id integer primary key autoincrement,username varchar(20),password varchar(20),sex varchar(10),phone varchar(20),area varchar(20))";
		db.execSQL(sql);

		// 插入默认用户
		String sql_use = "insert into user(id,username,password,sex,phone,area) values('0','admin','admin','男','13958824166','浙江温州')";
		db.execSQL(sql_use);

	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
