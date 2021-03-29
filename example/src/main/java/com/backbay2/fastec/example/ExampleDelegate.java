package com.backbay2.fastec.example;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.backbay2.fastec.R;
import com.yzg.koala.core.delegates.KoalaDelegate;
import com.yzg.koala.core.net.RestClient;
import com.yzg.koala.core.net.callback.IError;
import com.yzg.koala.core.net.callback.IFailure;
import com.yzg.koala.core.net.callback.ISuccess;

public class ExampleDelegate extends KoalaDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private  void testRestClient(){
        RestClient.builder()
                .url("https://www.mxnzp.com/api/barcode/create")
                //.url("https://www.baidu.com/")
//                .params("","")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        Log.e("RestClient", "onSuccess: "+response );
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("TAG", "onFailure: ",throwable );
                        Toast.makeText(getContext(), "onFailure: "+throwable, Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "onError", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();

    }
}
