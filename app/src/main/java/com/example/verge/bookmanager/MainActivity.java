package com.example.verge.bookmanager;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TitleActivity {
    private static final String TAG = "MainActivity";
    BookShelfFragment bookShelfFragment;
    StoreFragment storeFragment;
    MyFragment myFragment;
    View bookShelfLayout, storeLayout, myLayout;
    ImageView bookShelfIcon, storeIcon, myIcon;
    TextView bookShelfText, storeTest, myTest;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setTitle("图书管理系统");
       /* showBackwardView(true);*/
        RelativeLayout biaoti = findViewById(R.id.biaoti);
        biaoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: 22222222222222");
            }
        });
        showForwardView(R.string.text_forward,true);
        bookShelfLayout = findViewById(R.id.bookShelfLayout);
        storeLayout = findViewById(R.id.storeLayout);
        myLayout = findViewById(R.id.myLayout);
        bookShelfIcon = findViewById(R.id.bookShelfIcon);
        storeIcon = findViewById(R.id.storeIcon);
        myIcon = findViewById(R.id.myIcon);
        bookShelfText = findViewById(R.id.bookShelfText);
        storeTest = findViewById(R.id.storeText);
        myTest = findViewById(R.id.myText);
        bookShelfLayout.setOnClickListener(this);
        storeLayout.setOnClickListener(this);
        myLayout.setOnClickListener(this);
        fragmentManager = getFragmentManager();
        showForwardView(R.string.text_forward,true);
        setTabSelection(1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.action_bar_color));
        }
    }
    @Override
    protected void setupViews() {
        mTitleTextView = findViewById(R.id.text_title);
        mContentLayout = findViewById(R.id.layout_content);
        /*mBackwardbButton = (Button) findViewById(R.id.button_backward);*/
        mForwardButton = findViewById(R.id.button_forward);
        mForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = ((BaseApplication)getApplication()).getFlag();
                Intent intent;
                if(flag==0){
                    intent = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(MainActivity.this, LocalSearch.class);
                    startActivityForResult(intent,1);
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bookShelfLayout:
                setTabSelection(0);
                break;
            case R.id.storeLayout:
                /*Intent intent = new Intent(MainActivity.this, BookStore.class);
                startActivity(intent);*/
                setTabSelection(1);
                break;
            case R.id.myLayout:
                setTabSelection(2);
                break;
        }
    }

    private void setTabSelection(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (bookShelfFragment != null)
            transaction.hide(bookShelfFragment);
        if (storeFragment != null)
            transaction.hide(storeFragment);
        if (myFragment != null)
            transaction.hide(myFragment);
        switch (index) {
            case 0:
                bookShelfIcon.setImageResource(R.drawable.bookshelf1);
                storeIcon.setImageResource(R.drawable.store);
                myIcon.setImageResource(R.drawable.my);
                setmForwardButtonText(R.string.localSearch);
                ((BaseApplication)getApplication()).setFlag(1);
                if (bookShelfFragment == null) {
                    bookShelfFragment = new BookShelfFragment();
                    transaction.add(R.id.content, bookShelfFragment);
                } else {
                    transaction.show(bookShelfFragment);
                }
                setTitle(R.string.bookshelf);
                break;
            case 1:
                storeIcon.setImageResource(R.drawable.store1);
                bookShelfIcon.setImageResource(R.drawable.bookshelf);
                myIcon.setImageResource(R.drawable.my);
                setmForwardButtonText(R.string.onlineSearch);
                ((BaseApplication)getApplication()).setFlag(0);
                Log.i("单击书城", "更改图片");
                if (storeFragment == null) {
                    storeFragment = new StoreFragment();
                    transaction.add(R.id.content, storeFragment);
                } else {
                    transaction.show(storeFragment);
                }
                setTitle("精选");
                break;
            case 2:
                myIcon.setImageResource(R.drawable.my1);
                bookShelfIcon.setImageResource(R.drawable.bookshelf);
                storeIcon.setImageResource(R.drawable.store);
                setmForwardButtonText(R.string.onlineSearch);
                ((BaseApplication)getApplication()).setFlag(0);
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.content, myFragment);
                } else {
                    transaction.show(myFragment);
                }
                setTitle(R.string.my);
                break;
        }
        transaction.commit();
    }
}
