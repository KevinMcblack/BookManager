package com.example.verge.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class Search_result extends AppCompatActivity {
    String [] photo = null;
    String [] details = null;
    String [] writers = null;
    String [] book_url = null;
    String [] title = null;
    String [] price = null;
    String [] tags = null;

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

        GridView gridView = findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageListAdapter(Search_result.this,photo));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(String.valueOf(id), "onItemClick:----------------");
                Intent intent = new Intent(Search_result.this, Book_details.class);
                intent.putExtra("details",details[(int) id]);
                intent.putExtra("title",title[(int) id]);
                intent.putExtra("writers",writers[(int) id]);
                intent.putExtra("book_url",book_url[(int) id]);
                intent.putExtra("price",price[(int) id]);
                intent.putExtra("tags",tags[(int) id]);
                startActivity(intent);//启动Activity
            }
        });
    }
}
