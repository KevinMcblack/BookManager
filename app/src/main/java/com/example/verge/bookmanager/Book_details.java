package com.example.verge.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Book_details extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
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
        Intent intent=getIntent();
        String details=intent.getStringExtra("details");
        String title=intent.getStringExtra("title");
        String writers=intent.getStringExtra("writers");
        String book_url=intent.getStringExtra("book_url");
        String price=intent.getStringExtra("price");
        String tags=intent.getStringExtra("tags");
        Log.i(title, "onCreate: ---------------------------------");
        textView1.setText(title);
        textView2.setText(writers);
        textView3.setText(tags);
        textView4.setText(price);
        textView5.setText(details);
        textView6.setText(book_url);

    }
}
