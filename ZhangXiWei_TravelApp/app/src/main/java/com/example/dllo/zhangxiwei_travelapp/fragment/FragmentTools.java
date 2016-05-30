package com.example.dllo.zhangxiwei_travelapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.activity.ToolSelectPlaceActivity;
import com.example.dllo.zhangxiwei_travelapp.base.BaseFragment;
import com.example.dllo.zhangxiwei_travelapp.bean.ToolSinglePlaceBean;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/5/9.
 * 工具页的fragment
 */
public class FragmentTools extends BaseFragment implements View.OnClickListener {

    TextView placeName, minTem, minTem2, maxTem, maxTem2;
    TextView time;
    String name = null;

    private ToolSinglePlaceBean toolSinglePlaceBean;


    @Override
    public int getLayout() {
        return R.layout.fragment_tools;
    }

    @Override
    public void initView() {

        placeName = bindView(R.id.fragment_tool_place_name);
        minTem = bindView(R.id.fragment_tool_min_tem);
        minTem2 = bindView(R.id.fragment_tool_min_tem_2);
        maxTem = bindView(R.id.fragment_tool_max_tem);
        maxTem2 = bindView(R.id.fragment_tool_max_tem_2);
        time = bindView(R.id.fragment_tool_time);

    }

    @Override
    public void initData() {

        minTem.setTextColor(Color.rgb(0, 153, 255));

        minTem2.setTextColor(Color.rgb(0, 153, 255));
        maxTem.setTextColor(Color.rgb(223, 5, 19));
        maxTem2.setTextColor(Color.rgb(223, 5, 19));
        placeName.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_tool_place_name:

                Intent intent = new Intent(getContext(), ToolSelectPlaceActivity.class);
                startActivityForResult(intent, 1);

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int placeId = 0;
        if (requestCode == 1 && resultCode == 2) {
            name = data.getStringExtra("placeName");
            placeId = data.getIntExtra("placeId", 0);
            Log.d("FragmentTools", "placeId:" + placeId);
        }

        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getContext());
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/wiki/destinations/infos/" + placeId + ".json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                toolSinglePlaceBean = gson.fromJson(response, ToolSinglePlaceBean.class);
                initBackData();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);

    }

    private void initBackData() {

        placeName.setText(name);
        minTem.setText(toolSinglePlaceBean.getTemp_min() + "");
        maxTem.setText(toolSinglePlaceBean.getTemp_max() + "");
        time.setText(toolSinglePlaceBean.getCurrent_time());


    }
}
