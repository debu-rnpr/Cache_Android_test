package app.mindvalley.details.presenter_impl;

import android.graphics.Bitmap;

import com.google.gson.reflect.TypeToken;
import com.mylibrary.interfces.DownloadCallback;
import com.mylibrary.request.Request;

import java.util.ArrayList;
import java.util.List;

import app.mindvalley.home.models.ApiResponse;
import app.mindvalley.home.presenter_Impl.IImplementerHome;

/**
 * Created by debu on 14/11/16.
 */
public class ImplementerDetails implements IImplementerDetails.ICallApi{
    private IImplementerDetails.IApiCallback callback;

    public ImplementerDetails(IImplementerDetails.IApiCallback callback) {
        this.callback = callback;
    }

    @Override
    public void callApi(final String url, int type) {
        Request request = new Request(url,type);
        request.setDownloadCallback(new DownloadCallback<Bitmap>() {
            @Override
            public void onDownloadFinished(Bitmap bitmap) {
                callback.onResponseReceived(url,bitmap);
            }

            @Override
            public void onDownloadError(String errMsg) {
                callback.onApiError(errMsg);
            }
        });
        request.build();
    }
}
