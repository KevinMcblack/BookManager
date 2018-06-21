package com.example.verge.bookmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class BookStore extends AppCompatActivity {
    //定义图标数组
    int[] imageRes = {
            R.drawable.bookshelf,
            R.drawable.my,
            R.drawable.store,
            R.drawable.bookshelf,
            R.drawable.my,
            R.drawable.store,
            R.drawable.bookshelf,
            R.drawable.my,
            R.drawable.store,
    };
    private String[] name = {
            "O2O收款",
            "订单查询",
            "转账",
            "手机充值",
            "信用卡还款",
            "水电煤",
            "违章代缴",
            "快递查询",
            "更多"
    };

    public static String[] eatFoodyImages = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
    };

    String image_url;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_store);
        GridView gridView=findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageListAdapter(BookStore.this, eatFoodyImages));
       /* imageView=findViewById(R.id.iv_item_gridview);
        image_url="http://img.my.csdn.net/uploads/201407/26/1406383243_5120.jpg";
        *//*Glide.with(BookStore.this).load(image_url).into(imageView);*//*

        int length = imageRes.length;
        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("ItemImage",imageRes[i]);//添加图像资源的ID
            map.put("ItemText", name[i]);//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem 与动态数组的元素相对应
        SimpleAdapter saImageItems = new SimpleAdapter(this,
                lstImageItem,//数据来源
                R.layout.item,//item的XML实现

                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.iv_item_gridview, R.id.tv_item_gridview});
        //添加并且显示
        gridView.setAdapter(saImageItems);*/
        //添加消息处理
        /*gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,name[position], Toast.LENGTH_LONG).show();
            }
        });*/


    }
}
