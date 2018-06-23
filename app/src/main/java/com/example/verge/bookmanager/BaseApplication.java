package com.example.verge.bookmanager;

import android.app.Application;

public class BaseApplication extends Application {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
