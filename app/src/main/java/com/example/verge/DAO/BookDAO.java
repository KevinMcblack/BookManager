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

public class BookDAO extends AppCompatActivity {
    private BookDBHelper dbHelper;
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new BookDBHelper(this);
        intent = this.getIntent();
        bundle = intent.getExtras();
        int code = bundle.getInt("code");
        onSwich(code);
    }
    public BookDAO(Context context) {
        this.dbHelper = new BookDBHelper(context);
    }
    public BookDAO(BookDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void onSwich(int requestCode) {
        // TODO: 2018/6/19
        switch (requestCode) {
            case 1:
                addBook();
                break;
            case 2:
                deleteBook();
                break;
            case 3:
                updateBook();
                break;
            default:
//                queryBook();
                break;
        }
    }

    public int addBook() {
        return 0;
        // TODO: 2018/6/19
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
                data.add(book);
            } while (cursor.moveToNext());
            cursor.close();
            return data;
        } else{
            return null;
        }
    }
    public Cursor queryBook1(){
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        Cursor cursor = sdb.rawQuery("select * from books", null);
        return cursor;
    }
    public void updateBook() {
        // TODO: 2018/6/19  
    }

    public void deleteBook() {
        final String bookid = bundle.getString("bookid");
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select * from books where id=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{bookid});
        if (bookid.equals("")) {
            DialogDemo.builder(this, "错误信息", "书名不能为空！");
            finish();
        } else if (cursor.moveToFirst()) {
            cursor.close();
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final SQLiteDatabase db = dbHelper.getWritableDatabase();
            builder.setTitle("确认信息");
            builder.setMessage("确认删除这本书吗");
            builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //TODO: 2018/6/19
                    db.execSQL("delete from books where id='"+bookid+"'");
                    Toast.makeText(BookDAO.this,"删除完成",Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        } else {
            DialogDemo.builder(this, "错误信息", "您要删除的图书不存在！");
            finish();
        }
    }
}
