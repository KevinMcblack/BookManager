package com.example.verge.bookmanager;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class LoaclSearchResultActivity extends AppCompatActivity {
    String [] photo = null;
    String [] details = null;
    String [] writers = null;
    String [] book_url = null;
    String [] title = null;
    String [] price = null;
    String [] tags = null;
    String [] idd = null;
    String [] publishOrg = null;
    String[] pingjia=null;
    String[] status=null;
    String[] toid=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_store);
        Bundle b=this.getIntent().getExtras();
        assert b != null;
        photo = b.getStringArray("photo");
        details=b.getStringArray("details");
        writers=b.getStringArray("writers");
        book_url=b.getStringArray("book_url");
        title=b.getStringArray("title");
        price=b.getStringArray("price");
        tags=b.getStringArray("tags");
        idd=b.getStringArray("id");
        pingjia=b.getStringArray("pingjia");
        status=b.getStringArray("status");
        toid=b.getStringArray("toid");
        publishOrg=b.getStringArray("publishOrg");
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.fresh);
        GridView gridView = findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageListAdapter(LoaclSearchResultActivity.this,photo));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(String.valueOf(id), "onItemClick:----------------");
                Intent intent = new Intent(LoaclSearchResultActivity.this, BookDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",idd[(int) id]);
                bundle.putString("title",title[(int) id]);
                bundle.putString("writer",writers[(int) id]);
                bundle.putString("type","book");
                bundle.putString("publishOrg",publishOrg[(int) id]);
                bundle.putString("url",photo[(int) id]);
                bundle.putString("bookurl",book_url[(int) id]);
                if (!pingjia[(int) id].equals("")){
                    bundle.putString("pingjia",pingjia[(int) id]);
                }
                bundle.putString("status",status[(int) id]);
                bundle.putString("toid",toid[(int) id]);
                bundle.putString("tag",tags[(int) id]);
                intent.putExtras(bundle);
                startActivity(intent);//启动Activity
            }
        });
    }
}
