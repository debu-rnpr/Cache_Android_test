package com.mylibrary.request;

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mylibrary.cache.CacheManager;
import com.mylibrary.common.CacheItem;
import com.mylibrary.interfces.DocumentCallback;
import com.mylibrary.interfces.DownloadCallback;
import com.mylibrary.interfces.ImageCallback;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by debu on 12/11/16.
 */
public class Request<T> implements DocumentCallback,ImageCallback{
    public static final int TYPE_STRING = 1;
    public static final int TYPE_JSON = 2;
    public static final int TYPE_IMAGE = 3;
    private String url;
    private int currentType;
    private DownloadCallback<T> downloadCallback;
    private Call<ResponseBody> currentCall;
    private Class<T> type;
    private TypeToken<List<T>> responseType;
    public Request(String url, int returnType){
        this.url = url;
        this.currentType = returnType;
    }

    /////needed by Gson if the response is of jsonarray
    public void setTypeToken(TypeToken<List<T>> responseType){
        this.responseType = responseType;
    }

    public void setClass(Class<T> type){
        this.type = type;
    }

    public void setDownloadCallback(DownloadCallback<T> callback){
        downloadCallback = callback;
    }

    public String getUrl() {
        return url;
    }

    public int getCurrentType() {
        return currentType;
    }
    public void cancel(){
        currentCall.cancel();
    }

    public void build(){
        switch (currentType){
            case TYPE_STRING :
            case TYPE_JSON :
                if(CacheManager.getInstance().getItemFromCache(url) == null){
                    DocumentRequst documentRequst = new DocumentRequst(url,this);
                    currentCall = documentRequst.makeRequest();
                }else{
                    downloadCallback.onDownloadFinished((T)CacheManager.getInstance().getItemFromCache(url).getData());
                }
                break;
            case TYPE_IMAGE :
                if(CacheManager.getInstance().getItemFromCache(url) == null){
                    ImageRequst imgRequst = new ImageRequst(url,this);
                    currentCall = imgRequst.makeRequest();
                }else{
                    downloadCallback.onDownloadFinished((T)CacheManager.getInstance().getItemFromCache(url).getData());
                }
                break;
        }
    }


    @Override
    public void onDocReceived(String response) {
        switch (currentType) {
            case TYPE_STRING : sendCallbackString(response);
                break;
            case TYPE_JSON : sendCallbackJson(response);
                break;
        }
    }

    private void sendCallbackJson(String response){
        try {
            if(response.charAt(0) == '['){
                CacheManager.getInstance().addDocumentToCache(url,new CacheItem(url,new Gson().fromJson(response,responseType.getType()), response.getBytes().length));
            }else{
                CacheManager.getInstance().addDocumentToCache(url,new CacheItem(url,new Gson().fromJson(response,type), response.getBytes().length));
            }
            downloadCallback.onDownloadFinished((T)CacheManager.getInstance().getItemFromCache(url).getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendCallbackString(String response){
        try {
            CacheManager.getInstance().addDocumentToCache(url,new CacheItem(url,response, response.getBytes().length));
            downloadCallback.onDownloadFinished((T)CacheManager.getInstance().getItemFromCache(url).getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDocError(String errMsg) {
        downloadCallback.onDownloadError(errMsg);
    }

    @Override
    public void onImageReceived(Bitmap image) {
        try {
            CacheManager.getInstance().addDocumentToCache(url,new CacheItem(url,image, image.getByteCount()));
            downloadCallback.onDownloadFinished((T)CacheManager.getInstance().getItemFromCache(url).getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onImageError(String errMsg) {
        downloadCallback.onDownloadError(errMsg);
    }
}
