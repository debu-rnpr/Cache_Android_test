package com.mylibrary.cache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.mylibrary.common.CacheItem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by debu on 12/11/16.
 */
public class CacheManager {
    private LruCache<String, CacheItem> mMemoryCache;
    private static CacheManager ourInstance = new CacheManager();

    public static CacheManager getInstance() {
        return ourInstance;
    }

    private CacheManager() {
        init();
    }

    private void init(){
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory());
        final int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, CacheItem>(cacheSize) {
            @Override
            protected int sizeOf(String key, CacheItem item) {
                return item.getSize()/1024;
            }
        };
    }

    public CacheItem getItemFromCache(String key) {
        return mMemoryCache.get(key);
    }

    public CacheItem addDocumentToCache(String key, CacheItem doc) throws Exception{
        if (getItemFromCache(key) == null) {
            return mMemoryCache.put(key, doc);
        }else
            return getItemFromCache(key);
    }
}
