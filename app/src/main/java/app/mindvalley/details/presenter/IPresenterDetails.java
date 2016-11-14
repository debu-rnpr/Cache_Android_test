package app.mindvalley.details.presenter;

import android.graphics.Bitmap;

import java.util.ArrayList;

import app.mindvalley.home.models.ApiResponse;

/**
 * Created by debu on 14/11/16.
 */
public class IPresenterDetails {
    public interface IImageRequest{
        void getImage(String url);
    }

   public interface IImageCallbackPresenter{
        void onImageReceived(String url, Bitmap bitmap);
        void onImageError(String msg);
    }
}
