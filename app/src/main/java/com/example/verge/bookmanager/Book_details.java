package com.example.verge.bookmanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verge.DAO.BookDAO;
import com.example.verge.model.Book;

public class Book_details extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    Button readOnline;
    Button addToShelf;
    @SuppressLint({"SetTextI18n", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        textView1=findViewById(R.id.textview1);
        textView2=findViewById(R.id.textview2);
        textView3=findViewById(R.id.textview3);
        textView4=findViewById(R.id.textview4);
        textView5=findViewById(R.id.textview5);
        readOnline =findViewById(R.id.textview6);
        addToShelf=findViewById(R.id.addToShelf);
        Intent intent=getIntent();
        String details=intent.getStringExtra("details");
        final String title=intent.getStringExtra("title");
        final String writers=intent.getStringExtra("writers");
        final String book_url=intent.getStringExtra("book_url");
        String price=intent.getStringExtra("price");
        String tags=intent.getStringExtra("tags");
        final String id=intent.getStringExtra("id");
        final String publishOrg=intent.getStringExtra("publishOrg");
        final String photo = intent.getStringExtra("photo");
        Log.i(title, "onCreate: ---------------------------------");
        textView1.setText("书名："+title);
        textView2.setText("作者："+writers);
        textView3.setText("标签："+tags);
        textView4.setText("价格："+price);
        textView5.setText("简介："+details);
        readOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = book_url;
                Intent intent = new Intent(Book_details.this,BookToWebActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        addToShelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDAO dao = new BookDAO(Book_details.this);
                Book book = new Book();
                book.setId(id);
                book.setWriter(writers);
                book.setUrl(photo);
                book.setType("book");
                book.setPublishOrg(publishOrg);
                book.setTitle(title);
                book.setUserId(((BaseApplication)getApplication()).getUserId());
                book.setBookUrl(book_url);
                dao.addBook(book);
                Toast.makeText(Book_details.this,"添加完成",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
