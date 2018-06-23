package com.example.verge.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class Book_Api extends AppCompatActivity {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject postRequestFromUrl(String url, String body) throws IOException, JSONException {
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        PrintWriter out = new PrintWriter(conn.getOutputStream());
        out.print(body);
        out.flush();

        InputStream instream = conn.getInputStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            instream.close();
        }
    }

    public static JSONObject getRequestFromUrl(String url) throws IOException, JSONException {
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection();
        InputStream instream = conn.getInputStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            instream.close();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        // 请求示例 url 默认请求参数已经做URL编码
        String url = "http://api01.bitspaceman.com:8000/book/baiduread";
        url += "?apikey=eVQVoieFyQYmFgy2EErjfz3Hj5zs7cV5huRh9IF32cmNgMcy68xEsymLeGeIj41y";
        url += "&kw=" + data;//书名模糊查询
        /*url += "&pageToken=1";//第几页*/
        /*url += "&catid=7_11014";//查询书籍或者查询分类*/
        JSONObject json = null;
        try {
            json = getRequestFromUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] photo = new String[100];
        String[] details = new String[100];
        String[] writers = new String[100];
        String[] publishOrg=new String[100];
        String[] book_url=new String[100];
        String[] title=new String[100];
        String[] price=new String[100];
        String[] tags=new String[100];
        //将jsonArray字符串转化为JSONArray
        JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
        for (int i = 0; i < 10; i++) {
            JSONObject jUser = jsonArray.getJSONObject(i);
            JSONArray jsonArray1 = JSONArray.fromObject(jUser.getString("writers"));
            JSONObject username = jsonArray1.getJSONObject(0);
            photo[i] = jUser.getString("coverUrl");
            details[i] = jUser.getString("abstract");
            writers[i] = username.getString("name");
            /*publishOrg[i]=jUser.getString("publishOrg");*/
            book_url[i]=jUser.getString("url");
            title[i]=jUser.getString("title");
            price[i]=jUser.getString("price");
            tags[i]=jUser.getString("tags");
        }
        //打开另一个Activity,一个Intent对象代表一个意图
        intent = new Intent();
        Bundle b = new Bundle();
        b.putStringArray("photo", photo);
        b.putStringArray("details", details);
        b.putStringArray("writers", writers);
        b.putStringArray("book_url", book_url);
        b.putStringArray("title", title);
        b.putStringArray("price", price);
        b.putStringArray("tags", tags);
        intent.putExtras(b);

        intent.setClass(Book_Api.this, Search_result.class);
        Book_Api.this.startActivity(intent);
    }
}
