package com.example.verge.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import DAO.Test1;

public class Search extends AppCompatActivity {
    SearchView searchView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView=findViewById(R.id.searchView);
        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(query, "onQueryTextSubmit: ----------------------------");
                //打开另一个Activity,一个Intent对象代表一个意图
                Intent intent = new Intent(Search.this,Book_Api.class);
                //给Activity传值方式一：创建一个Bundle对象封装数据
                intent.putExtra("data",query);
                startActivity(intent);//启动Activity
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

    }
}