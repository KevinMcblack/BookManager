package com.example.verge.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.verge.bookmanager.BaseApplication;
import com.example.verge.model.User;

public class UserDAO {
	private UserDBHelper dbHelper;

	public UserDAO(Context context) {
		dbHelper = new UserDBHelper(context);
	}

	// 登录用
	public int login(String username, String password) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select * from user where username=? and password=?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { username, password });
		if (cursor.moveToFirst()) {
			int userid=cursor.getInt(0);
			cursor.close();
			return userid;
		}
		return -1;
	}

	// 注册用
	public boolean register(User user) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "insert into user(username,password,sex,phone,area) values(?,?,?,?,?)";
		Object obj[] = { user.getUsername(), user.getPassword() ,user.getSex(), user.getPhone(),user.getArea()};
		sdb.execSQL(sql, obj);
		return true;
	}
	public void deleteUser(int id){
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql="delete user where id='"+id+"'";
		sdb.execSQL(sql);
	}
	// 添加用
	public boolean add(String bookname, String author, String price) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select * from bookstore where bookname=? and author=? and price=?";
		Cursor cursor = sdb.rawQuery(sql, new String[] { bookname, author,
				price });
		if (cursor.moveToFirst()) {
			cursor.close();
			return true;
		}
		return false;
	}
	public void update(User user){
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "update user set username=?,password=?,sex=?,phone=?,area=? where id='"+String.valueOf(user.getId())+"'";
		Object obj[] = { user.getUsername(), user.getPassword() ,user.getSex(), user.getPhone(),user.getArea()};
		sdb.execSQL(sql, obj);
	}
	public User queryUser(int userid){
		SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
		User user = new User();
		String sql="select * from user where id=?";
		Cursor cursor = sqLiteDatabase.rawQuery(sql,new String[]{String.valueOf(userid)});
		if (cursor.moveToNext()){
			user.setId(cursor.getInt(0));
			user.setUsername(cursor.getString(1));
			user.setSex(cursor.getString(3));
			user.setPhone(cursor.getString(4));
			user.setArea(cursor.getString(5));
			cursor.close();
			return user;
		} else {
			return null;
		}
	}
}
