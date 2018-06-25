package com.example.verge.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.example.verge.DAO.BookDAO;
import com.example.verge.model.Book;

import java.util.ArrayList;

public class LocalSearch extends AppCompatActivity {
    SearchView searchView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_search);
        searchView = findViewById(R.id.searchView);
        /**
         * 默认情况下, search widget是"iconified“的，只是用一个图标 来表示它(一个放大镜),
         * 当用户按下它的时候才显示search box . 你可以调用setIconifiedByDefault(false)让search
         * box默认都被显示。 你也可以调用setIconified()让它以iconified“的形式显示。
         */
        searchView.setIconifiedByDefault(true);
        /**
         * 默认情况下是没提交搜索的按钮，所以用户必须在键盘上按下"enter"键来提交搜索.你可以同过setSubmitButtonEnabled(
         * true)来添加一个提交按钮（"submit" button)
         * 设置true后，右边会出现一个箭头按钮。如果用户没有输入，就不会触发提交（submit）事件
         */
        searchView.setSubmitButtonEnabled(true);
        /**
         * 初始是否已经是展开的状态
         * 写上此句后searchView初始展开的，也就是是可以点击输入的状态，如果不写，那么就需要点击下放大镜，才能展开出现输入框
         */
        searchView.onActionViewExpanded();

        /**
         * 默认情况下, search widget是"iconified“的，只是用一个图标 来表示它(一个放大镜),
         * 当用户按下它的时候才显示search box . 你可以调用setIconifiedByDefault(false)让search
         * box默认都被显示。 你也可以调用setIconified()让它以iconified“的形式显示。
         */
        searchView.setIconifiedByDefault(true);
        // 设置搜索文本监听
        final String[] photo = new String[20];
        String[] details = new String[20];
        final String[] writers = new String[20];
        final String[] publishOrg = new String[20];
        final String[] id = new String[20];
        final String[] book_url = new String[20];
        final String[] title = new String[20];
        String[] price = new String[20];
        final String[] tags = new String[20];
        final String[] pingjia= new String[20];
        final String[] status = new String[20];
        final String[] toid = new String[20];
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                BookDAO dao = new BookDAO(LocalSearch.this);
                int uid=((BaseApplication)getApplication()).getUserId();
                String sql="select * from books where (title like '%"+query+"%' or booktag like '%"+query+"%' )and userid='"+uid+"'";
                Log.i("userId",uid+"--------------------");
                ArrayList<Book> arrayList = dao.queryBook(sql);
                Log.i("arryList Size",arrayList.size()+"-----------------");
                Intent intent = new Intent(LocalSearch.this,LoaclSearchResultActivity.class);
                for(int i=0;i<arrayList.size();i++){
                    photo[i]=arrayList.get(i).getUrl();
                    title[i]=arrayList.get(i).getTitle();
                    writers[i]=arrayList.get(i).getWriter();
                    publishOrg[i]=arrayList.get(i).getPublishOrg();
                    id[i]=arrayList.get(i).getId();
                    book_url[i]=arrayList.get(i).getBookUrl();
                    tags[i]=arrayList.get(i).getTag();
                    pingjia[i]=arrayList.get(i).getPingjia();
                    status[i]=arrayList.get(i).getStatus();
                    toid[i]=arrayList.get(i).getToid();
                }
                Bundle b = new Bundle();
                b.putStringArray("photo", photo);
                b.putStringArray("writers", writers);
                b.putStringArray("book_url", book_url);
                b.putStringArray("title", title);
                b.putStringArray("tags", tags);
                b.putStringArray("id",id);
                b.putStringArray("publishOrg",publishOrg);
                b.putStringArray("pingjia",pingjia);
                b.putStringArray("status",status);
                b.putStringArray("toid",toid);
                intent.putExtras(b);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
