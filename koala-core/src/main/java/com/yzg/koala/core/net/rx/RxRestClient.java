package com.yzg.koala.core.net.rx;

import android.content.Context;

import com.yzg.koala.core.net.HttpMethod;
import com.yzg.koala.core.net.RestClientBuilder;
import com.yzg.koala.core.net.RestCreator;
import com.yzg.koala.core.net.RestService;
import com.yzg.koala.core.net.callback.IError;
import com.yzg.koala.core.net.callback.IFailure;
import com.yzg.koala.core.net.callback.IRequest;
import com.yzg.koala.core.net.callback.ISuccess;
import com.yzg.koala.core.net.callback.RequestCallbacks;
import com.yzg.koala.core.net.download.DownloadHandler;
import com.yzg.koala.core.ui.KoalaLoader;
import com.yzg.koala.core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RxRestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;

    public RxRestClient(String url,
                        Map<String, Object> params,
                        RequestBody body,
                        File file,
                        Context context,
                        LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;

        this.LOADER_STYLE = loaderStyle;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;
        if (LOADER_STYLE != null) {
            KoalaLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = RestCreator.getRxRestService().upload(URL, body);

                break;
            default:
                break;
        }
      return observable;

    }




    public final Observable<String>  get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String>  post() {
        if (BODY == null) {
            return   request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public final Observable<String>  put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String>  delete() {
        return  request(HttpMethod.DELETE);
    }

    public final Observable<String>  upload() {
        return  request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody>  download() {

        final Observable<ResponseBody> responseBodyObservable = RestCreator.getRxRestService().download(URL,PARAMS);
        return responseBodyObservable;
    }
}
