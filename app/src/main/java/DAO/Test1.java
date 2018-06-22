package DAO;


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

/**
 * Created by KevinWang on 2018/6/19.
 */

public class Test1 {
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

    public static void main(String[] args) throws IOException, JSONException {
        // 请求示例 url 默认请求参数已经做URL编码
        String url = "http://api01.bitspaceman.com:8000/book/baiduread";
        url += "?apikey=eVQVoieFyQYmFgy2EErjfz3Hj5zs7cV5huRh9IF32cmNgMcy68xEsymLeGeIj41y";
        url += "&kw=红楼梦";//书名模糊查询
        url += "&pageToken=1";//第几页
        /*url += "&catid=7_11014";//查询书籍或者查询分类*/
        JSONObject json = getRequestFromUrl(url);
        //System.out.println(url);
        //System.out.println(json.toString());
        //System.out.print(json.getString("appCode"));
        //System.out.println(json.getInt("pageToken"));
        //System.out.println(json.getString("data"));
        //将jsonArray字符串转化为JSONArray
        JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
        for (int i = 0; i < 10; i++) {
            JSONObject jUser = jsonArray.getJSONObject(i);
            JSONArray jsonArray1 = JSONArray.fromObject(jUser.getString("writers"));
            JSONObject username = jsonArray1.getJSONObject(0);
            String username2 = username.getString("name");
            System.out.println(username2);
                /*System.out.println(jUser.getString("url"));
                System.out.println(jUser.getString("abstract"));

	            System.out.println(jUser.getString("price"));*/
            /*System.out.println(jUser.getString("coverUrl"));
            System.out.println(jUser.getString("title"));
            System.out.println(jUser.getString("writers"));*/
                /*System.out.println(jUser.getString("tags"));*/
        }

    }
}