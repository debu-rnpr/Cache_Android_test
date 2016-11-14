package app.mindvalley.home.presenter_Impl;

import java.util.ArrayList;

import app.mindvalley.home.models.ApiResponse;

/**
 * Created by debu on 14/11/16.
 */
public class IImplementerHome {
    public interface ICallApi{
        void callApi(String url, int type);
    }

    public interface IApiCallback{
        void onResponseReceived(ArrayList<ApiResponse> apiResponseArrayList);
        void onApiError(String msg);
    }
}
