package com.example.dllo.zhangxiwei_travelapp.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.UserActivityAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.bean.SinaBean;
import com.example.dllo.zhangxiwei_travelapp.entity.CollectEntity;
import com.example.dllo.zhangxiwei_travelapp.entity.CollectEntityDao;
import com.example.dllo.zhangxiwei_travelapp.singleton.CollectEntitySingleton;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/5/31.
 */
public class UserActivity extends BaseActivity {

    ListView listView;
    UserActivityAdapter adapter;
    List<CollectEntity> collectEntities;
    ImageView headIv;
    TextView userName;
    Button btn;


    @Override
    protected int getLayout() {
        return R.layout.activity_user;
    }

    @Override
    protected void initView() {
        headIv = bindView(R.id.activity_user_headimage);
        userName = bindView(R.id.activity_user_username);
        btn = bindView(R.id.activity_user_exitbtn);
        listView = bindView(R.id.activity_user_listview);

    }

    @Override
    protected void initData() {
        CollectEntityDao collectEntityDao = CollectEntitySingleton.getInstance().getEntityDao();
        collectEntities = collectEntityDao.queryBuilder().list();

        adapter = new UserActivityAdapter(this);
        adapter.setCollectEntities(collectEntities);
        listView.setAdapter(adapter);

        Intent intent = getIntent();
        switch (intent.getIntExtra("type", 0)) {
            case 1:
                headIv.setImageResource(R.mipmap.ic_launcher);
                userName.setText(intent.getStringExtra("email"));

                break;

            case 2:
                SinaBean sinaBean = (SinaBean) intent.getSerializableExtra("sinabean");
                Picasso.with(MyApplication.getContext()).load(sinaBean.getProfile_image_url()).into(headIv);
                userName.setText(sinaBean.getName());

                break;

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserActivity.this.finish();

            }
        });
    }
}
