package com.example.dllo.zhangxiwei_travelapp.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.activity.NoteContentActivity;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.NoteListViewAdapter;
import com.example.dllo.zhangxiwei_travelapp.adapter.NoteViewPagerAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseFragment;
import com.example.dllo.zhangxiwei_travelapp.bean.NoteBean;
import com.example.dllo.zhangxiwei_travelapp.bean.NoteRotateBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/5/9.
 * 游记页fragment
 */
public class FragmentNote extends BaseFragment implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    private static final String TAG = "xxxx";
    private int id = 2;
    ViewPager noteViewPager;
    ListView noteListView;

    NoteViewPagerAdapter viewPagerAdapter;
    NoteListViewAdapter listViewAdapter;

    private int num = 1;
    private List<View> views;
    private static Handler handler;
    private static boolean isRunning = true;
    public static NoteRotateRunning noteRotateRunning = new NoteRotateRunning();
    private List<NoteBean> noteBeans;
    private View myViewPager;

    private List<NoteRotateBean> noteRotateBeans;
    private RequestQueue requestQueue;

    @Override
    public int getLayout() {
        return R.layout.fragment_note;
    }

    @Override
    public void initView() {

        noteListView = bindView(R.id.fragment_note_listview);

    }

    @Override
    public void initData() {

        initRotateView();
        initListView();

        noteListView.setOnScrollListener(this);
        noteListView.setOnItemClickListener(this);

    }


    //加载listview的数据
    private void initListView() {

        requestQueue = Volley.newRequestQueue(MyApplication.getContext());

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/trips/featured.json?page",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        /**
                         * 解析一个集合需要用new TypeToken<List<NoteBean>>(){}.getType()做参数
                         */
                        noteBeans = gson.fromJson(response, new TypeToken<List<NoteBean>>() {
                        }.getType());

                        listViewAdapter = new NoteListViewAdapter(MyApplication.getContext());
                        listViewAdapter.setNoteBeans(noteBeans);

                        noteListView.setAdapter(listViewAdapter);
                        //把ViewPager加入到listview的头布局中
                        noteListView.addHeaderView(myViewPager, null, false);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: ");

            }
        });
        requestQueue.add(stringRequest);


    }


    //加载ViewPager轮播图的数据
    private void initRotateView() {

        requestQueue = Volley.newRequestQueue(MyApplication.getContext());
        noteRotateBeans = new ArrayList<>();
        views = new ArrayList<>();
        StringRequest stringRequest1 = new StringRequest("http://chanyouji.com/api/adverts.json?name=app_featured_banner_android",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        /**
                         * 解析数组数据Url
                         */
                        noteRotateBeans = gson.fromJson(response, new TypeToken<List<NoteRotateBean>>() {
                        }.getType());

                        Log.d(TAG, "noteRotateBeans.size():" + noteRotateBeans.size());


                        //设置好view集合的数据
                        //把数据加到views中,准备放入ViewPager里的方法
                        initViewPager();

                        //myViewPager是View,要放入头布局的
                        myViewPager = LayoutInflater.from(getContext()).inflate(R.layout.fragment_note_viewpager, null);
                        TabLayout noteTabLayout = (TabLayout) myViewPager.findViewById(R.id.fragment_note_rotate_point);

                        noteViewPager = (ViewPager) myViewPager.findViewById(R.id.fragment_note_rotate_view);
                        noteViewPager.setLayoutParams(new RelativeLayout.LayoutParams(ListView.LayoutParams.MATCH_PARENT, 280));

                        viewPagerAdapter = new NoteViewPagerAdapter();
                        viewPagerAdapter.setViews(views);
                        noteViewPager.setAdapter(viewPagerAdapter);
                        noteTabLayout.setupWithViewPager(noteViewPager);

                        noteTabLayout.getTabAt(0).setIcon(R.drawable.selector_point);
                        noteTabLayout.getTabAt(1).setIcon(R.drawable.selector_point);
                        noteTabLayout.setSelectedTabIndicatorHeight(0);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest1);


        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (noteViewPager != null) {
                    noteViewPager.setCurrentItem(num % views.size());
                    num++;
                }
                return false;
            }
        });

    }

    //把数据加到view中,准备放入ViewPager里
    private void initViewPager() {

        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.fragment_note_viewpager_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.fragment_note_viewpager_item_iv);
        Picasso.with(MyApplication.getContext()).load(noteRotateBeans.get(0).getImage_url()).into(imageView);

        views.add(view);

        View view1 = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.fragment_note_viewpager_item, null);
        ImageView imageView1 = (ImageView) view1.findViewById(R.id.fragment_note_viewpager_item_iv);
        Picasso.with(MyApplication.getContext()).load(noteRotateBeans.get(1).getImage_url()).into(imageView1);

        views.add(view1);
    }

    //滑动ListView的方法
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Log.d(TAG, "scroll");
        Log.d(TAG, "view.getLastVisiblePosition():" + view.getLastVisiblePosition());
        Log.d(TAG, "(noteBeans.size() - 1):" + (noteBeans.size()));

        if (view.getLastVisiblePosition() == noteBeans.size()) {
            StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/trips/featured.json?page=" + id++,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Gson gson = new Gson();
                            /**
                             * 解析一个集合需要用new TypeToken<List<NoteBean>>(){}.getType()做参数
                             */
                            List<NoteBean> anotherNoteBeans = new ArrayList<>();
                            anotherNoteBeans = gson.fromJson(response, new TypeToken<List<NoteBean>>() {
                            }.getType());
//                            listViewAdapter.addNoteBeans(anotherNoteBeans);
                            for (NoteBean anotherNoteBean : anotherNoteBeans) {
                                noteBeans.add(anotherNoteBean);
                            }
                            listViewAdapter.setNoteBeans(noteBeans);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse: ");

                }
            });

            requestQueue.add(stringRequest);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    //点击ListView的方法
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NoteBean noteBean = noteBeans.get(position - 1);
        Intent intent = new Intent(MyApplication.getContext(), NoteContentActivity.class);
        intent.putExtra("noteId", noteBean.getId());
        Log.d(TAG, "noteBean.getId():" + noteBean.getId());
        startActivity(intent);

    }

    //轮播图转动的子线程Runnable
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
