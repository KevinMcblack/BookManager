package com.example.verge.bookmanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.verge.DAO.BookDAO;

public class Book_details extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    Button addToShelf;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        textView1=findViewById(R.id.textview1);
        textView2=findViewById(R.id.textview2);
        textView3=findViewById(R.id.textview3);
        textView4=findViewById(R.id.textview4);
        textView5=findViewById(R.id.textview5);
        textView6=findViewById(R.id.textview6);
        addToShelf=findViewById(R.id.addToShelf);
        Intent intent=getIntent();
        String details=intent.getStringExtra("details");
        String title=intent.getStringExtra("title");
        String writers=intent.getStringExtra("writers");
        String book_url=intent.getStringExtra("book_url");
        String price=intent.getStringExtra("price");
        String tags=intent.getStringExtra("tags");
        Log.i(title, "onCreate: ---------------------------------");
        textView1.setText("书名："+title);
        textView2.setText("作者："+writers);
        textView3.setText("标签："+tags);
        textView4.setText("价格："+price);
        textView5.setText("简介："+details);
        textView6.setText("试看链接："+book_url);
        addToShelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDAO dao = new BookDAO(Book_details.this);
                dao.addBook();
            }
        });

    }
}
