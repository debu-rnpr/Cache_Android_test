package app.mindvalley.home.presenter;

import java.util.ArrayList;

import app.mindvalley.home.models.ApiResponse;
import app.mindvalley.home.presenter_Impl.IImplementerHome;
import app.mindvalley.home.presenter_Impl.ImplementerHome;

/**
 * Created by debu on 14/11/16.
 */
public class PresenterHome implements IImplementerHome.IApiCallback,IPresenterHome.IApiRequest {
    private IImplementerHome.ICallApi callApi;
    private IPresenterHome.IApiCallbackPresenter callback;

    public PresenterHome(IPresenterHome.IApiCallbackPresenter callback) {
        this.callback = callback;
        callApi = new ImplementerHome(this);
    }

    @Override
    public void onResponseReceived(ArrayList<ApiResponse> responseArrayList) {
        callback.onResponseReceived(responseArrayList);
    }

    @Override
    public void onApiError(String msg) {
        callback.onApiError(msg);
    }

    @Override
    public void getHomeItems(String url, int type) {
        callApi.callApi(url,type);
    }
}
