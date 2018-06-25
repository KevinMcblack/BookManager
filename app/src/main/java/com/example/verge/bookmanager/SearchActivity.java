package com.example.verge.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class SearchActivity extends AppCompatActivity {
    SearchView searchView = null;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;
    Button button13;
    Button button14;
    Button button15;
    Button button16;
    String url;
    String[] photo = new String[20];
    String[] details = new String[20];
    String[] writers = new String[20];
    String[] publishOrg = new String[20];
    String[] id = new String[20];
    String[] book_url = new String[20];
    String[] title = new String[20];
    String[] price = new String[20];
    String[] tags = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(true);
        searchView.onActionViewExpanded();
        searchView.setBackgroundColor(0x22ff00ff);
        searchView.setIconifiedByDefault(true);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("7_11004");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_154");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_226");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_144");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_189");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_235");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_140");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_219");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("7_11002");
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_121");
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_218");
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_132");
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_229");
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_117");
            }
        });
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("2_134");
            }
        });
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classify("7_12005");
            }
        });
        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(query, "classify:这是搜索查找——————————————————————————————————————");
                Log.i(query, "onQueryTextSubmit: ----------------------------");
                //书名模糊查询
                /*url += "&pageToken=1";//第几页*/
                /*url += "&catid=7_11014";//查询书籍或者查询分类*/
                for (int j = 0; j < 2; j++) {
                    JSONObject json = null;
                    url = "http://api01.bitspaceman.com:8000/book/baiduread";
                    url += "?apikey=eVQVoieFyQYmFgy2EErjfz3Hj5zs7cV5huRh9IF32cmNgMcy68xEsymLeGeIj41y";
                    url += "&kw=" + query;
                    url += "&pageToken=" + j + 1;
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
                            JSONArray jsonArray2 = JSONArray.fromObject(jUser.getString("publishOrgs"));
                            JSONObject username2 = jsonArray2.getJSONObject(0);
                            photo[i + j * 10] = jUser.getString("coverUrl");
                            details[i + j * 10] = jUser.getString("abstract");
                            writers[i + j * 10] = username.getString("name");
                            publishOrg[i + j * 10] = username2.getString("name");
                            book_url[i + j * 10] = jUser.getString("url");
                            title[i + j * 10] = jUser.getString("title");
                            price[i + j * 10] = jUser.getString("price");
                            tags[i + j * 10] = jUser.getString("tags");
                            id[i + j * 10] = jUser.getString("id");
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
                b.putStringArray("id", id);
                b.putStringArray("publishOrg", publishOrg);
                intent.putExtras(b);
                intent.setClass(SearchActivity.this, Search_resultActivity.class);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

    }

    public void classify(String name) {
        for (int j = 0; j < 2; j++) {
            JSONObject json = null;
            url = "http://api01.bitspaceman.com:8000/book/baiduread";
            url += "?apikey=eVQVoieFyQYmFgy2EErjfz3Hj5zs7cV5huRh9IF32cmNgMcy68xEsymLeGeIj41y";
            url += "&catid=" + name;//查询书籍或者查询分类
            url += "&pageToken=" + j + 1;
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
                    JSONArray jsonArray2 = JSONArray.fromObject(jUser.getString("publishOrgs"));
                    JSONObject username2 = jsonArray2.getJSONObject(0);
                    photo[i + j * 10] = jUser.getString("coverUrl");
                    details[i + j * 10] = jUser.getString("abstract");
                    writers[i + j * 10] = username.getString("name");
                    publishOrg[i + j * 10] = username2.getString("name");
                    book_url[i + j * 10] = jUser.getString("url");
                    title[i + j * 10] = jUser.getString("title");
                    price[i + j * 10] = jUser.getString("price");
                    tags[i + j * 10] = jUser.getString("tags");
                    id[i + j * 10] = jUser.getString("id");
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
        b.putStringArray("id", id);
        b.putStringArray("publishOrg", publishOrg);
        intent.putExtras(b);
        intent.setClass(SearchActivity.this, Search_resultActivity.class);
        startActivity(intent);
        Log.i(name, "classify:这是分类查找——————————————————————————————————————");
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