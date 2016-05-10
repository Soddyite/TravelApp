package com.example.dllo.zhangxiwei_travelapp.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.NoteRecyclerViewAdapter;
import com.example.dllo.zhangxiwei_travelapp.adapter.NoteViewPagerAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseFragment;
import com.example.dllo.zhangxiwei_travelapp.bean.NoteBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/5/9.
 */
public class FragmentNote extends BaseFragment {


    private static final String TAG = "kkkkkkk";
    ViewPager noteViewPager;
    RecyclerView noteRecyclerView;
    RecyclerViewHeader noteRecyclerViewHeader;

    NoteViewPagerAdapter viewPagerAdapter;
    NoteRecyclerViewAdapter recyclerViewAdapter;

    private int num = 1;
    private List<View> views;
    private static Handler handler;
    private static boolean isRunning = true;
    public static NoteRotateRunning noteRotateRunning = new NoteRotateRunning();
    private List<NoteBean> noteBeans;

    @Override
    public int getLayout() {
        return R.layout.fragment_note;
    }

    @Override
    public void initView() {
        noteViewPager = bindView(R.id.fragment_note_rotate_view);
        noteRecyclerView = bindView(R.id.fragment_note_recyclerview);
        noteRecyclerViewHeader = (RecyclerViewHeader) getView().findViewById(R.id.fragment_note_recycler_header);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        noteRecyclerViewHeader.attachTo(noteRecyclerView, true);

    }

    @Override
    public void initData() {

        initRotateView();
        initRecyclerView();

    }

    private void initRecyclerView() {


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/trips/featured.json?page",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        /**
                         * 解析一个集合需要用new TypeToken<List<NoteBean>>(){}.getType()做参数
                         */
                        noteBeans = gson.fromJson(response, new TypeToken<List<NoteBean>>(){}.getType());

                        recyclerViewAdapter = new NoteRecyclerViewAdapter(getContext());
                        recyclerViewAdapter.setNoteBeans(noteBeans);
                        noteRecyclerView.setAdapter(recyclerViewAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: ");

            }
        });
        requestQueue.add(stringRequest);




    }


    private void initRotateView() {
        views = new ArrayList<>();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_note_viewpager_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.fragment_note_viewpager_item_iv);
        imageView.setImageResource(R.mipmap.ic_launcher);
        views.add(view);

        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.fragment_note_viewpager_item, null);
        ImageView imageView1 = (ImageView) view1.findViewById(R.id.fragment_note_viewpager_item_iv);
        imageView1.setImageResource(R.mipmap.ic_launcher);
        views.add(view1);

        viewPagerAdapter = new NoteViewPagerAdapter();
        viewPagerAdapter.setViews(views);
        noteViewPager.setAdapter(viewPagerAdapter);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                noteViewPager.setCurrentItem(num % views.size());
                num++;
                return false;
            }
        });
    }

    static class NoteRotateRunning implements Runnable {

        @Override
        public void run() {
            while (isRunning) {
                try {
                    Thread.sleep(3000);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onDestroy() {
        isRunning = false;
        super.onDestroy();

    }
}
