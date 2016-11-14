package app.mindvalley.home.presenter_Impl;

import com.google.gson.reflect.TypeToken;
import com.mylibrary.interfces.DownloadCallback;
import com.mylibrary.request.Request;

import java.util.ArrayList;
import java.util.List;

import app.mindvalley.home.models.ApiResponse;

/**
 * Created by debu on 14/11/16.
 */
public class ImplementerHome implements IImplementerHome.ICallApi{
    private IImplementerHome.IApiCallback callback;

    public ImplementerHome(IImplementerHome.IApiCallback callback) {
        this.callback = callback;
    }

    @Override
    public void callApi(String url, int type) {
        Request request = new Request(url,Request.TYPE_JSON);
        request.setTypeToken(new TypeToken<List<ApiResponse>>(){});
        request.setDownloadCallback(new DownloadCallback<ArrayList<ApiResponse>>() {
            @Override
            public void onDownloadFinished(ArrayList<ApiResponse> response) {
                callback.onResponseReceived(response);
            }

            @Override
            public void onDownloadError(String errMsg) {
                callback.onApiError(errMsg);
            }
        });
        request.build();
    }
}
