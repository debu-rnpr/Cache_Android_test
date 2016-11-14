package com.mylibrary.common;

import java.io.Serializable;

/**
 * Created by debu on 12/11/16.
 */
public class CacheItem implements Serializable{
    private String url;
    private Object data;
    private int size;

    public CacheItem(String url, Object data, int size) {
        this.url = url;
        this.data = data;
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
