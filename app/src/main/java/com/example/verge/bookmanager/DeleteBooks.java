package com.example.verge.bookmanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amitshekhar.utils.DatabaseHelper;
import com.example.verge.DAO.UserDBHelper;

public class DeleteBooks extends Activity implements View.OnClickListener {

    private TextView setting_about_back;
    private Button btndelete;
    private UserDBHelper dbHelper;
    private EditText deletebooks_edit;

    //public DeleteBooks() {
       // dbHelper = new UserDBHelper(this);
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_delete_user);

        setting_about_back = findViewById(R.id.setting_about_back);
        btndelete = findViewById(R.id.btn_search);
        btndelete.setOnClickListener(this);
        setting_about_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_search:
                deletebooks_edit = findViewById(R.id.deletebooks_edit);
                String deletebook = deletebooks_edit.getText().toString();

                SQLiteDatabase sdb = dbHelper.getReadableDatabase();
                String sql = "select * from bookstore where bookname=?";
                Cursor cursor = sdb.rawQuery(sql, new String[] { deletebook });

                if (deletebook.equals("")) {
                    DialogDemo.builder(DeleteBooks.this, "错误信息", "书名不能为空！");
                } else if (cursor.moveToFirst() == true) {
                    cursor.close();
                    String data = deletebook;
                    Intent intent = new Intent(DeleteBooks.this, DeleteBooksListView.class);
                    intent.putExtra("extra_data", data);
                    startActivity(intent);
                } else {
                    DialogDemo.builder(DeleteBooks.this, "错误信息", "您要删除的图书不存在！");
                }
                break;
            case R.id.setting_about_back:
                finish();
                break;
            default:
                break;
        }
    }
}