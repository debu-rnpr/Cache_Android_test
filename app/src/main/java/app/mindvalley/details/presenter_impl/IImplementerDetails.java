package app.mindvalley.details.presenter_impl;

import android.graphics.Bitmap;

import java.util.ArrayList;

import app.mindvalley.home.models.ApiResponse;

/**
 * Created by debu on 14/11/16.
 */
public class IImplementerDetails {
    public interface ICallApi{
        void callApi(String url, int type);
    }

    public interface IApiCallback{
        void onResponseReceived(String url, Bitmap bitmap);
        void onApiError(String msg);
    }
}
