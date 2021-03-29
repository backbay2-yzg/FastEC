package com.yzg.koala.core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.ContentFrameLayout;

import com.yzg.koala.core.R;
import com.yzg.koala.core.delegates.KoalaDelegate;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity {

    public abstract KoalaDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActionBar actionBar =getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.hide();
//        }
        Log.e("TAG", "onCreate: 111" );
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        //@SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);
        FrameLayout container = new FrameLayout(this);//为本Activity创建一个布局对象
        container.setId(R.id.delegate_container);

        setContentView(container);
        if (savedInstanceState==null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
