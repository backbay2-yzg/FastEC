package com.yzg.koala.core.net.download;

import com.yzg.koala.core.net.RestCreator;
import com.yzg.koala.core.net.callback.IError;
import com.yzg.koala.core.net.callback.IFailure;
import com.yzg.koala.core.net.callback.IRequest;
import com.yzg.koala.core.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

public class DownloadHandler {

    private String mUrl = null;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;

    public DownloadHandler(String url,
                           IRequest request,
                           String downloadDir,
                           String extension,
                           String name,
                           ISuccess success,
                           IFailure failure,
                           IError error) {
        this.mUrl = url;
        this.mIRequest = request;
        this.mDownloadDir = downloadDir;
        this.mExtension = extension;
        this.mName = name;
        this.mISuccess = success;
        this.mIFailure = failure;
        this.mIError = error;
    }
}
