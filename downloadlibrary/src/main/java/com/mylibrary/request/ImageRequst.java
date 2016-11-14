package com.mylibrary.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mylibrary.common.IApiMethods;
import com.mylibrary.interfces.ImageCallback;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Struct;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by debu on 12/11/16.
 */
public class ImageRequst {
    private String url;
    private ImageCallback callback;
    public ImageRequst(String url, ImageCallback callback) {
        this.url = url;
        this.callback = callback;
    }

    public Call<ResponseBody> makeRequest(){
        String baseUrl = getBaseUrl(url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();
        IApiMethods service = retrofit.create(IApiMethods.class);
        Call<ResponseBody> allLeadsResponseCall = service.fetchResponse(url.substring(baseUrl.length(),url.length() - 1));
        allLeadsResponseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.body() != null){
                        try {
                            Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                            if(callback != null)
                                callback.onImageReceived(bm);
                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.onImageError(e.getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(callback != null)
                    callback.onImageError(t.getMessage());
            }
        });

        return allLeadsResponseCall;
    }

    private String getBaseUrl(String u){
        try {
            URL url = new URL(u);
            return url.getProtocol() + "://" + url.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return u;
    }
}
