package com.mylibrary.interfces;

import android.graphics.Bitmap;

/**
 * Created by debu on 11/11/16.
 */
public interface ImageCallback {
        void onImageReceived(Bitmap image);
        void onImageError(String errMsg);
}
