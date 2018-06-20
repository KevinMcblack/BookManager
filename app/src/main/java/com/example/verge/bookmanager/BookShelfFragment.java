package com.example.verge.bookmanager;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BookShelfFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View bookShelfLayout = inflater.inflate(R.layout.layout_book_shelf_frame,container,false);
        return  bookShelfLayout;
    }
}
