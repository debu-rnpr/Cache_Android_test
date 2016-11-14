package com.mylibrary.interfces;

import android.graphics.Bitmap;

/**
 * Created by debu on 11/11/16.
 */
public interface DownloadCallback<T> {
        void onDownloadFinished(T response);
        void onDownloadError(String errMsg);
}
