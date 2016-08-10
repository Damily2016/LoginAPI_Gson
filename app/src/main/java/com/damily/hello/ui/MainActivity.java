package com.damily.hello.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.damily.hello.R;
import com.damily.hello.adapter.HomeAdapter;
import com.damily.hello.common.URL;
import com.damily.hello.model.DeviceRepairRequestModel;
import com.damily.hello.model.entity.DeviceFixInfo;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    Context context;
    String tag="ManActivity";
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private DeviceFixInfo deviceFixInfo;
    private List<DeviceFixInfo.DeviceFixMessage.DeviceFix> records;
    private SwipeRefreshLayout flash_layout;
    public DeviceRepairRequestModel.OnFeedingLogListener mOnFeedingLogListener;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        initView();
        initData();
    }

    private void initView() {
        flash_layout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        flash_layout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.devicefix_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onRefresh() {
        initData();
    }

    private void initData() {
        String url = URL.DEVICEFIX;
        final RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (response!=null) {
                    Gson gson = new Gson();
                    deviceFixInfo = gson.fromJson(response, DeviceFixInfo.class);
                    records = deviceFixInfo.getMessage().getRecords();
//                    Log.i(TAG, "onResponse: " + response);
                    mAdapter = new HomeAdapter(records,getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    flash_layout.setRefreshing(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mRequestQueue.add(request);
    }

}
