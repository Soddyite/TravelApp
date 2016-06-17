package com.example.dllo.zhangxiwei_travelapp.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.RegisterAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.fragment.RegisterFragment;
import com.example.dllo.zhangxiwei_travelapp.fragment.SignUpFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/30.
 */
public class RegisterActivity extends BaseActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    RegisterAdapter adapter;
    TextView titleText;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.activity_register_tablayout);
        viewPager = bindView(R.id.activity_register_viewpager);
        titleText = bindView(R.id.register_title_tv);


    }

    @Override
    protected void initData() {
        titleText.setTextColor(Color.WHITE);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SignUpFragment());
        fragments.add(new RegisterFragment());

        adapter = new RegisterAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.rgb(0, 0, 0), Color.rgb(00, 153, 255));
        tabLayout.setSelectedTabIndicatorColor(Color.rgb(00, 153, 255));

    }
}
