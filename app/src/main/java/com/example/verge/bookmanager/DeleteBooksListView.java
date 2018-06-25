package com.example.verge.bookmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verge.DAO.BookDAO;
import com.example.verge.DAO.UserDAO;
import com.example.verge.DAO.UserDBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteBooksListView extends Activity {

	private UserDBHelper dbHelper;
	private TextView setting_about_back;
	String na[];
	String au[];
	String pr[];
	int i = 0;
	String id[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.querybooks_listview);
		setting_about_back = findViewById(R.id.setting_about_back);
		setting_about_back
				.setOnClickListener(new android.view.View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						finish();
					}

				});
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		dbHelper = new UserDBHelper(this);
		final SQLiteDatabase db = dbHelper.getWritableDatabase();

		Intent intent = getIntent();
		//String data = intent.getStringExtra("extra_data");
		final UserDAO dao = new UserDAO(DeleteBooksListView.this);
		// 查询Book表中所有的数据
		Cursor cursor = dao.queryAllUser("select * from user");

		if (cursor.moveToFirst()) {
			do {
				List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();

				int count = cursor.getCount();
				id = new String[count];
				pr = new String[count];
				au = new String[count];
				na = new String[count];
				do {
					try {
						id[i] = cursor.getString(0);
						na[i] = cursor.getString(1);
						au[i] = cursor.getString(2);
						pr[i] = cursor.getString(3);
						i++;

					} catch (Exception e) {
						// TODO: handle exception

					}

				} while (cursor.moveToNext());

				for (int i = 0; i < na.length; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", id[i]);
					map.put("bookname", na[i]);
					map.put("author", au[i]);
					map.put("price", pr[i]);
					data1.add(map);
				}

				SimpleAdapter simple = new SimpleAdapter(this, data1,
						R.layout.listview, new String[] { "id", "bookname",
								"author", "price" }, new int[] { R.id.t_id,
								R.id.t_name, R.id.t_author, R.id.t_price });
				ListView listView = findViewById(R.id.t_listview);
				listView.setAdapter(simple);

				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int arg2, long arg3) {
						// TODO Auto-generated method stub
						for (int i = 0; i < na.length; i++) {
							if (arg2 == i) {
								builder.setTitle("确认消息");
								builder.setMessage("确定要删除该用户吗？");
								final int j = i;
								builder.setPositiveButton(
										"确定",
										new android.content.DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												dao.deleteUser("delete from user where id='"+id[j]+"'");
												Toast.makeText(DeleteBooksListView.this,"删除成功",Toast.LENGTH_SHORT).show();
												finish();
											}
										});
								builder.setNegativeButton(
										"取消",
										new android.content.DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										});
								builder.create().show();
							}
						}
					}
				});
			} while (cursor.moveToNext());
		}
		cursor.close();

	}

}
