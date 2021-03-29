package com.backbay2.fastec.example;

import android.app.Application;

import com.yzg.koala.core.app.Koala;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Koala.init(this)
                .withApiHost("http://127.0.0.1/")
                .configure();
    }
}
