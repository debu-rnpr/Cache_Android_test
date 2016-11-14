package app.mindvalley.home.presenter;

import java.util.ArrayList;

import app.mindvalley.home.models.ApiResponse;

/**
 * Created by debu on 14/11/16.
 */
public class IPresenterHome {
    public interface IApiRequest{
        void getHomeItems(String url, int type);
    }

   public interface IApiCallbackPresenter{
        void onResponseReceived(ArrayList<ApiResponse> responseArrayList);
        void onApiError(String msg);
    }
}
