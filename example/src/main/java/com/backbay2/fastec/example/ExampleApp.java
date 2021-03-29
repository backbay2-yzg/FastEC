package com.backbay2.fastec.example;

import android.app.Application;

import com.backbay2.fastec.R;
import com.yzg.koala.core.app.Koala;
import com.yzg.koala.core.net.interceptors.DebugInterceptor;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Koala.init(this)
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("create", R.raw.test))
                .configure();
    }
}
