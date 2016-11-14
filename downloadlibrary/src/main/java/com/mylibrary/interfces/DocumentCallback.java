package com.mylibrary.interfces;

/**
 * Created by debu on 11/11/16.
 */
public interface DocumentCallback {
        void onDocReceived(String response);
        void onDocError(String errMsg);
}
