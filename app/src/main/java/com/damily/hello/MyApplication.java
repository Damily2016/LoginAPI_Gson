package com.damily.hello;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by Dandan.Cao on 2016/7/28.
 */
public class MyApplication extends Application {
    private  static  RequestQueue mQueue;
    Context context;
    static ImageLoaderConfiguration config;
    @Override
    public void onCreate() {
        super.onCreate();
       mQueue=Volley.newRequestQueue(getApplicationContext());
        DisplayImageOptions defaultOptions;
        defaultOptions = new DisplayImageOptions.Builder()
               // .showImageOnFail(R.drawable.imagerror)
                .displayer(new RoundedBitmapDisplayer(5))
                .cacheInMemory(true).cacheOnDisk(true).build();

         config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480,800)
                .defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config);
    }


    public static ImageLoaderConfiguration getImageLoaderConfiguration() {
        return config;
    }
    public <T> void addToRequestQueue(Request<T> req){
        getQueue().add(req);
        }

    public static  RequestQueue getQueue() {
        return mQueue;
    }
}
