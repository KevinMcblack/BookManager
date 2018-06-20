package com.example.verge.bookmanager;

import android.app.Fragment;
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
