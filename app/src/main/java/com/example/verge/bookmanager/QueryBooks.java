package com.example.verge.bookmanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.verge.DAO.BookDBHelper;

public class QueryBooks extends Activity implements OnClickListener {

	private EditText edit_querybook;
	private TextView setting_about_back;
	private BookDBHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.querybooks);
		Button btn_search = findViewById(R.id.btn_search);
		setting_about_back = findViewById(R.id.setting_about_back);
		btn_search.setOnClickListener(this);
		setting_about_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_search:
			edit_querybook = findViewById(R.id.querybook_edit);
			String querybook = edit_querybook.getText().toString();
			dbHelper = new BookDBHelper(this);
			SQLiteDatabase sdb = dbHelper.getReadableDatabase();
			String sql = "select * from bookstore";
			Cursor cursor = sdb.rawQuery(sql, new String[] { querybook });

			if (querybook.equals("")) {
				DialogDemo.builder(QueryBooks.this, "������Ϣ", "��������Ϊ�գ�");
			} else if (cursor.moveToFirst() == true) {
				cursor.close();
				String data = querybook;
				Intent intent = new Intent(QueryBooks.this, QueryBooksListView.class);
				intent.putExtra("extra_data", data);
				startActivity(intent);
			} else {
				DialogDemo.builder(QueryBooks.this, "������Ϣ", "��Ҫ��ѯ��ͼ�鲻���ڣ�");
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