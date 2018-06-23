package com.example.verge.bookmanager;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;
//精选
public class StoreFragment extends Fragment {
    public static String[] eatFoodyImages = {
            "http://hiphotos.baidu.com/doc/pic/item/8d5494eef01f3a29de01404d9a25bc315d607c6c.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/4ec2d5628535e5ddd960d8a674c6a7efce1b627e.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/4ec2d5628535e5ddcc19c19f7ac6a7efce1b62bc.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/eac4b74543a982263c711c518082b9014a90eb10.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/5fdf8db1cb134954cd260fea5c4e9258d1094a00.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/3812b31bb051f819a9c6e058dcb44aed2f73e7c5.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/8b82b9014a90f603f6cadfbd3312b31bb151edd6.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/a044ad345982b2b7c8bf479e3dadcbef76099b43.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/1c950a7b02087bf4072e6d34f5d3572c10dfcf28.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/574e9258d109b3dee04af78bc8bf6c81810a4c5f.jpg",
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View storeLayout = inflater.inflate(R.layout.activity_book_store, container, false);
        final SwipeRefreshLayout swipeRefreshLayout = storeLayout.findViewById(R.id.fresh);
        GridView gridView = storeLayout.findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageListAdapter(getContext(), eatFoodyImages));
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
        return storeLayout;
    }
}
