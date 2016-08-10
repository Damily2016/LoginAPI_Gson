package com.damily.hello.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.damily.hello.R;
import com.damily.hello.model.entity.DeviceFixInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dandan.Cao on 2016/7/27.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    String tag = "HomeAdapter";
    Context context;
    DeviceFixInfo.DeviceFixMessage.DeviceFix deviceFix;
    ArrayList<DeviceFixInfo.DeviceFixMessage.DeviceFix> title;

    public HomeAdapter(List<DeviceFixInfo.DeviceFixMessage.DeviceFix> records,Context context) {
        this.title = (ArrayList<DeviceFixInfo.DeviceFixMessage.DeviceFix>) records;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.device_adapter, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        deviceFix = title.get(position);
        holder.datetime.setText(deviceFix.getSubmitTime());
        holder.fixpeople.setText(deviceFix.getSubmitUser());
        holder.equipment_type.setText(deviceFix.getTypeName());
        holder.appointedname.setText(deviceFix.getDeviceRegId());
        Log.e(tag, deviceFix.getSubmitTime());
        holder.itemView.setTag(position);

        String ip = deviceFix.getDirectoryIP();
        String after = deviceFix.getDirectory();
        String img_name = deviceFix.getImgAlias();
//     "directoryIP": "172.22.35.164:8000/ams"+     "directory": "\\maintain\\201607"   +    "imgAlias": "201607250219061469427546835.jpg"
        if (ip != null && after != null && img_name != null) {
            String subAftert = after.substring(10, after.length()).replace(
                    "\\", "/");

            String[] imageName = img_name.split(",");
            for (int i = 0; i < imageName.length; i++) {
                String url = "http://"
                        + title.get(position).getDirectoryIP()
                        + after.replace("\\", "/") + "/" + imageName[i];
//
//                ImageLoader.getInstance().displayImage(url,
//                        holder.deviceimage);

            }

        }
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView datetime;
        TextView fixpeople;
        TextView equipment_type;
        TextView appointedname;
        ImageView deviceimage;

        public MyViewHolder(View view) {
            super(view);
            datetime = (TextView) view.findViewById(R.id.datetime);
            fixpeople = (TextView) view.findViewById(R.id.fixpeople);
            equipment_type = (TextView) view
                    .findViewById(R.id.equipment_type);
            appointedname = (TextView) view
                    .findViewById(R.id.appointedname);
            deviceimage = (ImageView) view.findViewById(R.id.deviceimage);
        }


    }
}
