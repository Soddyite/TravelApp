package com.example.dllo.zhangxiwei_travelapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.activity.ToolSelectPlaceActivity;
import com.example.dllo.zhangxiwei_travelapp.base.BaseFragment;

/**
 * Created by dllo on 16/5/9.
 */
public class FragmentTools extends BaseFragment implements View.OnClickListener {

    TextView placeName, minTem, minTem2, maxTem, maxTem2;


    @Override
    public int getLayout() {
        return R.layout.fragment_tools;
    }

    @Override
    public void initView() {
        placeName = bindView(R.id.fragment_tool_place_name);
        minTem = bindView(R.id.fragment_tool_min_tem);
        minTem.setTextColor(Color.rgb(0, 153, 255));
        minTem2 = bindView(R.id.fragment_tool_min_tem_2);
        minTem2.setTextColor(Color.rgb(0, 153, 255));
        maxTem = bindView(R.id.fragment_tool_max_tem);
        maxTem.setTextColor(Color.rgb(223, 5, 19));
        maxTem2 = bindView(R.id.fragment_tool_max_tem_2);
        maxTem2.setTextColor(Color.rgb(223, 5, 19));

    }

    @Override
    public void initData() {

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

        if (requestCode == 1 && resultCode == 2) {
            int placeId = data.getIntExtra("placeId", 0);
            Log.d("FragmentTools", "placeId:" + placeId);
        }

    }
}
