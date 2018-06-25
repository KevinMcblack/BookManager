package com.example.verge.DAO;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.verge.bookmanager.DialogDemo;
import com.example.verge.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookDAO{
    private BookDBHelper dbHelper;
    public BookDAO(Context context) {
        this.dbHelper = new BookDBHelper(context);
    }
    public BookDAO(BookDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    public BookDAO(){

    }

    public int addBook(Book book) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql="insert into books values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        db.execSQL(sql,new String[]{book.getId(),book.getTitle(),book.getWriter(),
                book.getPublishOrg(),book.getUrl(),String.valueOf(book.getType()),String.valueOf(book.getUserId()),book.getBookUrl(),book.getTag(),book.getPingjia(),book.getStatus(),book.getToid()});
        return 0;
    }
    public Cursor queryBook2(String sql){
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        Cursor cursor = sdb.rawQuery(sql,null);
        return cursor;
    }
    public ArrayList<Book> queryBook(String sql) {
        // TODO: 2018/6/19
        //String sql = bundle.getString("sql");
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        Cursor cursor = sdb.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            ArrayList<Book> data = new ArrayList<>();
            do {
                Book book = new Book();
                book.setId(cursor.getString(0));
                book.setTitle(cursor.getString(1));
                book.setWriter(cursor.getString(2));
                book.setPublishOrg(cursor.getString(3));
                book.setUrl(cursor.getString(4));
                book.setType(cursor.getString(5));
                book.setBookUrl(cursor.getString(7));
                book.setTag(cursor.getString(8));
                book.setPingjia(cursor.getString(9));
                book.setStatus(cursor.getString(10));
                book.setToid(cursor.getString(11));
                data.add(book);
            } while (cursor.moveToNext());
            cursor.close();
            return data;
        } else{
            return null;
        }
    }
    public Cursor queryBook1(int userId){
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("select * from books where userid='"+userId+"'", null);
        return cursor;
    }
    public void updateBook(String id,String content) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql="update books set toId=?,status='外借中' where _id=?";
        db.execSQL(sql,new String[]{content,id});
    }
    public void updateBook(String id) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql="update books set status='已添加' where _id=?";
        db.execSQL(sql,new String[]{id});
    }
    public void editBook(String id,String content){
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql="update books set pingjia=? where _id=?";
        db.execSQL(sql,new String[]{content,id});
    }
    public int deleteBook(String bookid) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            String sql = "delete from books where _id=?";
            db.execSQL(sql,new String[]{bookid});
            return 1;
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
