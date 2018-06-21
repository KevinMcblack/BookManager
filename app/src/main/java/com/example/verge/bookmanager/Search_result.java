package com.example.verge.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class Search_result extends AppCompatActivity {
    String [] pose_title = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_store);
        Bundle b=this.getIntent().getExtras();
        assert b != null;
        pose_title = b.getStringArray("pose_title");
        GridView gridView = findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageListAdapter(Search_result.this,pose_title));
    }
}
