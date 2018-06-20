package com.example.verge.bookmanager;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class StoreFragment extends Fragment {
    public static String[] eatFoodyImages = {
            "http://hiphotos.baidu.com/doc/pic/item/fcfaaf51f3deb48f11fa7986f81f3a292cf578d3.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/f3d3572c11dfa9ec0d3ff5b96ed0f703918fc10f.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/e61190ef76c6a7efccbce232f6faaf51f3de660f.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/b7fd5266d016092481043d23de0735fae6cd34e0.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/902397dda144ad34cd927db7daa20cf431ad85d5.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/9d82d158ccbf6c812fc8ece0ba3eb13533fa403f.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/43a7d933c895d143a51b067a7ff082025aaf077d.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/77c6a7efce1b9d16fd772de4ffdeb48f8d546486.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/6609c93d70cf3bc746fc27e8d900baa1cd112a32.jpg",
            "http://hiphotos.baidu.com/doc/pic/item/4a36acaf2edda3cc5729ce070be93901203f92c3.jpg",
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View storeLayout = inflater.inflate(R.layout.activity_book_store,container,false);
        GridView gridView=  storeLayout.findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageListAdapter(getContext(), eatFoodyImages));
        /*Log.i("test","111");*/
        return  storeLayout;
    }
}
