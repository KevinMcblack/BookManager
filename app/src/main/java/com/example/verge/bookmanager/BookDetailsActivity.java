package com.example.verge.bookmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.verge.DAO.BookDAO;
import com.example.verge.model.Book;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BookDetailsActivity extends AppCompatActivity {
    ImageView bookImage;
    TextView bookName;
    TextView author;
    TextView bookType;
    TextView publishOrgName;
    Button removeBook;
    ArrayList<Book> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details2);
        bookImage = findViewById(R.id.bookImage);
        bookName = findViewById(R.id.bookname);
        author = findViewById(R.id.author);
        bookType = findViewById(R.id.bookType);
        publishOrgName = findViewById(R.id.publishOrgName);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        String id = bundle.getString("id");
        BookDAO dao = new BookDAO(this);
        arrayList = dao.queryBook("select * from books where _id = '"+id+"'");
        ShowNetPicThread readImage = new ShowNetPicThread();
        readImage.run();
        bookName.setText(String.format("标题：%s", arrayList.get(0).getTitle()));
        author.setText(String.format("作者：%s", arrayList.get(0).getWriter()));
        bookType.setText(String.format("类型：%s", arrayList.get(0).getType()));
        publishOrgName.setText(String.format("出版社：%s", arrayList.get(0).getPublishOrg()));
    }
    /**
     * 显示背景图片的内部类子线程
     *
     * @author Cheny
     *
     */
    private class ShowNetPicThread implements Runnable {

        @Override
        public void run() {
            try {
                // 图片地址,这里是百度获得链接，如果不能显示图片（链接失效）请再百度个链接
                String urlStr = arrayList.get(0).getUrl();
                // 将图片地址转化为URL对象
                URL picUrl = new URL(urlStr);
                // 获取连接网络的对象（HTTP协议）
                HttpURLConnection connection = (HttpURLConnection) picUrl.openConnection();
                // 设置连接超时时间，5秒
                connection.setConnectTimeout(5000);
                // 设置连接获取输入流
                connection.setDoInput(true);
                // 设置连接使用缓存
                connection.setUseCaches(true);
                // 连接网络，貌似可写可不写
                connection.connect();
                // 获取连接后的输入流
                InputStream is = connection.getInputStream();
                // 将该输入流转化为Bitmap对象
                final Bitmap bitmap = BitmapFactory.decodeStream(is);
                // 这里是子线程，需要回归主线程更新UI
                runOnUiThread(new Runnable() {
                    public void run() {
                        // 将Bitmap对象转换为Drawable对象
                        Drawable pic;
                        pic = new BitmapDrawable(getResources(),bitmap);
                        // 设置rl_content背景
                        bookImage.setBackground(pic);
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}