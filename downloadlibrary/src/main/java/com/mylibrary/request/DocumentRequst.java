package com.mylibrary.request;

import com.mylibrary.common.IApiMethods;
import com.mylibrary.interfces.DocumentCallback;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by debu on 12/11/16.
 */
public class DocumentRequst {
    private String url;
    private DocumentCallback callback;
    public DocumentRequst(String url,DocumentCallback callback) {
        this.url = url;
        this.callback = callback;
    }

    public Call<ResponseBody> makeRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
        IApiMethods service = retrofit.create(IApiMethods.class);
        Call<ResponseBody> responseCall = service.fetchResponse("");
        responseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(callback != null){
                        try {
                            callback.onDocReceived(response.body().string());
                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.onDocError(e.getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(callback != null)
                    callback.onDocError(t.getMessage());
            }
        });

        return responseCall;
    }

    ////////for xml, using SimpleXmlConverterFactory to convert xml to POJO
    /*public Call<ResponseBody> makeRequestXML(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        IApiMethods service = retrofit.create(IApiMethods.class);
        Call<ResponseBody> responseCall = service.fetchResponse("");
        responseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(callback != null){
                    try {
                        callback.onDocReceived(response.body().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                        callback.onDocError(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(callback != null)
                    callback.onDocError(t.getMessage());
            }
        });

        return responseCall;
    }*/

}
