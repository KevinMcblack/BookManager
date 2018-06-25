package com.example.verge.bookmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verge.DAO.UserDBHelper;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private UserDBHelper dbHelper;
    private TextView setting_about_back;
//	private String[] data = { "创建书库", "添加图书", "更新图书", "查询图书", "删除图书", "浏览图书" };

    private List<Icons> IconsList = new ArrayList<Icons>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_admin);
        setting_about_back = (TextView) findViewById(R.id.setting_about_back);

        initIcon();
        IconsAdapter adapter = new IconsAdapter(AdminActivity.this, R.layout.icons_item, IconsList);

//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main.this,
//				android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.listview_main);
        listView.setAdapter(adapter);
        dbHelper = new UserDBHelper(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                if (arg2 == 0) {
                    Intent intent = new Intent(AdminActivity.this,DeleteBooksListView.class);
                    startActivity(intent);
                }
                if (arg2 == 1) {
                    Intent intent = new Intent(AdminActivity.this,DeletePingjiaActivity.class);
                    startActivity(intent);
                }

            }
        });
        setting_about_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initIcon() {
        // TODO Auto-generated method stub
        Icons upbook = new Icons("浏览和删除用户", R.drawable.c);
        IconsList.add(upbook);
        Icons debook = new Icons("浏览和删除评价", R.drawable.e);
        IconsList.add(debook);
    }
}
