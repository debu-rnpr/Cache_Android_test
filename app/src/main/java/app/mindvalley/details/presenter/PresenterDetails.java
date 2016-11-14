package app.mindvalley.details.presenter;

import android.graphics.Bitmap;

import com.mylibrary.request.Request;

import java.util.ArrayList;

import app.mindvalley.details.presenter_impl.IImplementerDetails;
import app.mindvalley.details.presenter_impl.ImplementerDetails;
import app.mindvalley.home.models.ApiResponse;
import app.mindvalley.home.presenter.IPresenterHome;
import app.mindvalley.home.presenter_Impl.IImplementerHome;
import app.mindvalley.home.presenter_Impl.ImplementerHome;

/**
 * Created by debu on 14/11/16.
 */
public class PresenterDetails implements IImplementerDetails.IApiCallback,IPresenterDetails.IImageRequest {
    private IImplementerDetails.ICallApi callApi;
    private IPresenterDetails.IImageCallbackPresenter callback;

    public PresenterDetails(IPresenterDetails.IImageCallbackPresenter callback) {
        this.callback = callback;
        callApi = new ImplementerDetails(this);
    }

    @Override
    public void onResponseReceived(String url, Bitmap bitmap) {
        callback.onImageReceived(url,bitmap);
    }

    @Override
    public void onApiError(String msg) {
        callback.onImageError(msg);
    }

    @Override
    public void getImage(String url) {
        callApi.callApi(url, Request.TYPE_IMAGE);
    }
}
