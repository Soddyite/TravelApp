package com.example.dllo.zhangxiwei_travelapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.NoteContentAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.bean.NoteBeanContent;
import com.example.dllo.zhangxiwei_travelapp.utils.MyListView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/5/11.
 */
public class NoteContentActivity extends BaseActivity {

    private NoteBeanContent beanContent;
    ImageView titleBackground, titleHeadImage;
    TextView titleName, titleDate, titleDays, titlePhotoCount;
    TextView whichDay, date;
    MyListView noteListView;
    NoteContentAdapter noteContentAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FloatingActionButton fab;


    @Override
    protected int getLayout() {
        return R.layout.activity_note_content;
    }

    @Override
    protected void initView() {

        titleBackground = bindView(R.id.note_content_title_background);
        titleHeadImage = bindView(R.id.note_content_user_head_image);
        titleName = bindView(R.id.note_content_title_name);
        titleName.setTextColor(Color.WHITE);
        titleDate = bindView(R.id.note_content_title_date);
        titleDate.setTextColor(Color.WHITE);
        titleDays = bindView(R.id.note_content_title_days);
        titleDays.setTextColor(Color.WHITE);
        titlePhotoCount = bindView(R.id.note_content_photo_title_count);
        titlePhotoCount.setTextColor(Color.WHITE);
        noteListView = bindView(R.id.note_content_listview);
        whichDay = bindView(R.id.note_content_title_which_day);
        date = bindView(R.id.note_content_title_another_date);
        drawerLayout = bindView(R.id.note_content_drawer_layout);
        navigationView = bindView(R.id.note_content_nav_view);
        fab = bindView(R.id.note_content_nav_fab);

    }

    private void initDataToView() {

        Picasso.with(this).load(beanContent.getFront_cover_photo_url()).into(titleBackground);
        Picasso.with(this).load(beanContent.getUser().getImage()).into(titleHeadImage);
        titleName.setText(beanContent.getName());
        titleDate.setText(beanContent.getStart_date() + " | ");
        titleDays.setText(beanContent.getTrip_days().size() + "天 , ");
        titlePhotoCount.setText(beanContent.getPhotos_count() + "图");

        noteContentAdapter = new NoteContentAdapter(this);
        noteContentAdapter.setNoteBeanContent(beanContent.getTrip_days());
        noteListView.setAdapter(noteContentAdapter);

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        int id = (int) intent.getIntExtra("noteId", 0);

        beanContent = new NoteBeanContent();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/trips/" + id + ".json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                /**
                 * 解析Object数据Url
                 */
                beanContent = gson.fromJson(response, NoteBeanContent.class);

                initDataToView();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

//        //打开抽屉的方法
//        drawerLayout.openDrawer(GravityCompat.START);
//
//        //关闭抽屉的方法
//        drawerLayout.closeDrawer(GravityCompat.START);


    }

}
