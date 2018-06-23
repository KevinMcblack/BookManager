package com.example.verge.bookmanager;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.verge.DAO.BookDAO;
import com.example.verge.DAO.BookDBHelper;

public class BookShelfFragment extends Fragment{
    SwipeRefreshLayout swipeRefreshLayout;
    GridView gridView;
    BookDAO bookDAO;
    BookDBHelper dbHelper;
    SimpleCursorAdapter simpleCursorAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View bookShelfLayout = inflater.inflate(R.layout.activity_book_store ,container,false);
        gridView=  bookShelfLayout.findViewById(R.id.grid_view);
        /*swipeRefreshLayout = bookShelfLayout.findViewById(R.id.fresh);*/
        bookDAO = new BookDAO(getActivity());
        Cursor cursor = bookDAO.queryBook1();
        String[] from={"coverUrl","_id"};
        int[] to={R.id.bookImage,R.id.bookid};
        simpleCursorAdapter = new SimpleCursorAdapter(getContext(),R.layout.layout_bookshelf,cursor,from,to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        SimpleCursorAdapter.ViewBinder viewBinder = new SimpleCursorAdapter.ViewBinder() {
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                Log.i("BookShelf","columnIndex"+columnIndex);
                // 如果目标视图类型为ImageView
                if (view instanceof ImageView) {
                    ImageView imageView = (ImageView) view;
                    String value = cursor.getString(columnIndex);
                    Log.i("MainActivity", "设置图片----" + value);
                    //Uri uri=Uri.parse(value); imageView.setImageURI(uri);
                    Glide.with(getContext()).load(value).into(imageView);
                    return true;
                }
                // 默认返回false;
                return false;
            }
        };
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView bookid = view.findViewById(R.id.bookid);
                String bId=bookid.getText().toString();
                Intent intent = new Intent(getActivity(),BookDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",bId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        simpleCursorAdapter.setViewBinder(viewBinder);
        gridView.setAdapter(simpleCursorAdapter);
        return  bookShelfLayout;
    }
}
