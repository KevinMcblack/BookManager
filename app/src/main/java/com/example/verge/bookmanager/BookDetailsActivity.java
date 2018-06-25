package com.example.verge.bookmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView tagText;
    TextView pingjia;
    Bundle bundle;
    Toolbar toolbar;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details2);
        bookImage = findViewById(R.id.bookImage);
        bookName = findViewById(R.id.bookname);
        author = findViewById(R.id.author);
        bookType = findViewById(R.id.bookType);
        publishOrgName = findViewById(R.id.publishOrgName);
        pingjia = findViewById(R.id.pingjia);
        tagText = findViewById(R.id.tagName);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Intent intent = getIntent();
        bundle = intent.getExtras();
        assert bundle != null;
        final BookDAO dao = new BookDAO(this);
        ShowNetPicThread readImage = new ShowNetPicThread();
        readImage.run();
        status=bundle.getString("status");
        bookName.setText(String.format("标题：%s", bundle.getString("title")));
        author.setText(String.format("作者：%s", bundle.getString("writer")));
        bookType.setText(String.format("类型：%s", bundle.getString("type")));
        tagText.setText(String.format("标签：%s", bundle.getString("tag").replace("[","").replace("]","")));
        publishOrgName.setText(String.format("出版社：%s", bundle.getString("publishOrg")));
        if (bundle.getString("pingjia")!=null){
            pingjia.setVisibility(View.VISIBLE);
            pingjia.setText(String.format("您的评价：%s", bundle.getString("pingjia")));
        }
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.readOnline:
                        String url = bundle.getString("bookurl");
                        Intent intent = new Intent(BookDetailsActivity.this,BookToWebActivity.class);
                        intent.putExtra("url",url);
                        startActivity(intent);
                        break;
                    case R.id.remove :
                        if(status.equals("已外借")){
                            DialogDemo.builder(BookDetailsActivity.this,"错误信息","图书已外借不能删除");
                        } else{
                            final AlertDialog.Builder builder = new AlertDialog.Builder(BookDetailsActivity.this);
                            builder.setTitle("确认信息");
                            builder.setMessage("确认删除这本书吗");
                            builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //TODO: 2018/6/19
                                    int res = dao.deleteBook(bundle.getString("id"));
                                    Toast.makeText(BookDetailsActivity.this,"删除完成",Toast.LENGTH_SHORT).show();
                                    setResult(0);
                                    finish();
                                }
                            });
                            builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setResult(1);
                                }
                            });
                            builder.show();
                        }
                        break;
                    case R.id.pingjia:
                        View view = getLayoutInflater().inflate(R.layout.activity_edit_evaluation, null);
                        final EditText editText = view.findViewById(R.id.editText);
                        AlertDialog dialog = new AlertDialog.Builder(BookDetailsActivity.this)
                                .setTitle("请输入评价内容")
                                .setView(view)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String content = editText.getText().toString();
                                        String id = getIntent().getStringExtra("id");
                                        BookDAO dao = new BookDAO(BookDetailsActivity.this);
                                        dao.editBook(id,content);
                                        pingjia.setVisibility(View.VISIBLE);
                                        pingjia.setText(String.format("您的评价：%s",content));
                                        Toast.makeText(BookDetailsActivity.this,"评价成功",Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create();
                        dialog.show();
                        break;
                    case R.id.bringBack:
                        if(status.equals("已出借")){
                            dao.updateBook(bundle.getString("id"));
                            Toast.makeText(BookDetailsActivity.this,"收回完成",Toast.LENGTH_SHORT).show();
                        } else {
                            DialogDemo.builder(BookDetailsActivity.this,"错误信息","此书未出借，无序收回");
                        }
                        break;
                    default:
                        View view1 = getLayoutInflater().inflate(R.layout.activity_edit_evaluation, null);
                        final EditText borrowOutText = view1.findViewById(R.id.editText);
                        borrowOutText.setHint("用户名");
                        AlertDialog alertDialog = new AlertDialog.Builder(BookDetailsActivity.this)
                                .setTitle("请输入出借对象")
                                .setView(view1)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String content = borrowOutText.getText().toString();
                                        String id = getIntent().getStringExtra("id");
                                        BookDAO dao = new BookDAO(BookDetailsActivity.this);
                                        dao.updateBook(id,content);
                                        Toast.makeText(BookDetailsActivity.this,"出借完成",Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create();
                        alertDialog.show();
                        status="已外借";
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
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
                String urlStr = bundle.getString("url");
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
