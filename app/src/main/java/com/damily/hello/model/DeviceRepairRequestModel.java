package com.damily.hello.model;

import android.content.Context;

/**
 * Created by Dandan.Cao on 2016/7/28.
 */
public class DeviceRepairRequestModel {
    Context context;

    public DeviceRepairRequestModel(Context context){
this.context=context;

    }
    public interface OnFeedingLogListener
    {
        void onSuccess(String res);
        void onFailed(String errorMsg);
    }
}
