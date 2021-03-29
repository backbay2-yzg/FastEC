package com.backbay2.fastec.example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.backbay2.fastec.R;
import com.yzg.koala.core.activities.ProxyActivity;
import com.yzg.koala.core.delegates.KoalaDelegate;

public class ExampleActivity extends ProxyActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//       // setContentView(R.layout.delegate_example);
//       // initContainer(savedInstanceState);
//    }

    @Override
    public KoalaDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
