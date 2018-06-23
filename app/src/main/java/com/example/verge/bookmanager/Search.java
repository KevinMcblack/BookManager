package com.example.verge.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import DAO.Test1;

public class Search extends AppCompatActivity {
    SearchView searchView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.searchView);
        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(query, "onQueryTextSubmit: ----------------------------");
                //书名模糊查询
                /*url += "&pageToken=1";//第几页*/
                /*url += "&catid=7_11014";//查询书籍或者查询分类*/
                String url;
                String[] photo = new String[100];
                String[] details = new String[100];
                String[] writers = new String[100];
/*
                String[] publishOrg = new String[100];
*/
                String[] book_url = new String[100];
                String[] title = new String[100];
                String[] price = new String[100];
                String[] tags = new String[100];
                for (int j = 0; j < 3; j++) {
                    JSONObject json = null;
                    url = "http://api01.bitspaceman.com:8000/book/baiduread";
                    url += "?apikey=eVQVoieFyQYmFgy2EErjfz3Hj5zs7cV5huRh9IF32cmNgMcy68xEsymLeGeIj41y";
                    url += "&kw=" + query;
                    url += "&pageToken="+j+1;
                    Log.i(url, "onQueryTextSubmit: -----------------------");
                    try {
                        json = getRequestFromUrl(url);
                        //将jsonArray字符串转化为JSONArray
                        JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
                        for (int i = 0; i < 10; i++) {
                            Log.i(String.valueOf(i), "onQueryTextSubmit: .........................................");
                            JSONObject jUser = jsonArray.getJSONObject(i);
                            JSONArray jsonArray1 = JSONArray.fromObject(jUser.getString("writers"));
                            JSONObject username = jsonArray1.getJSONObject(0);
                            photo[i+j*10] = jUser.getString("coverUrl");
                            details[i+j*10] = jUser.getString("abstract");
                            writers[i+j*10] = username.getString("name");
                    /*publishOrg[i]=jUser.getString("publishOrg");*/
                            book_url[i+j*10] = jUser.getString("url");
                            title[i+j*10] = jUser.getString("title");
                            price[i+j*10] = jUser.getString("price");
                            tags[i+j*10] = jUser.getString("tags");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                //打开另一个Activity,一个Intent对象代表一个意图
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putStringArray("photo", photo);
                b.putStringArray("details", details);
                b.putStringArray("writers", writers);
                b.putStringArray("book_url", book_url);
                b.putStringArray("title", title);
                b.putStringArray("price", price);
                b.putStringArray("tags", tags);
                intent.putExtras(b);

                intent.setClass(Search.this, Search_result.class);
                Search.this.startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

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

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}