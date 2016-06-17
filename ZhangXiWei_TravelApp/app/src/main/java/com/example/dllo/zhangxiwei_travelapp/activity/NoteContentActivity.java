package com.example.dllo.zhangxiwei_travelapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.adapter.NoteContentAdapter;
import com.example.dllo.zhangxiwei_travelapp.base.BaseActivity;
import com.example.dllo.zhangxiwei_travelapp.bean.NoteBeanContent;
import com.example.dllo.zhangxiwei_travelapp.entity.CollectEntity;
import com.example.dllo.zhangxiwei_travelapp.entity.CollectEntityDao;
import com.example.dllo.zhangxiwei_travelapp.singleton.CollectEntitySingleton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by dllo on 16/5/11.
 * 游记详细内容页
 */
public class NoteContentActivity extends BaseActivity implements ExpandableListView.OnGroupClickListener, View.OnClickListener {

    private NoteBeanContent beanContent;
    ImageView titleBackground, titleHeadImage, titleCollect, titleShare;
    TextView titleName, titleDate, titleDays, titlePhotoCount;
    RecyclerView recyclerView;
    NoteContentAdapter noteContentAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FloatingActionButton fab;


    //绑定布局的方法
    @Override
    protected int getLayout() {
        return R.layout.activity_note_content;
    }

    //绑定组件的方法
    @Override
    protected void initView() {
        titleCollect = bindView(R.id.activity_note_content_title_collect);
        titleShare = bindView(R.id.activity_note_content_title_share);

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
        recyclerView = bindView(R.id.note_content_recyclerview);


        drawerLayout = bindView(R.id.note_content_drawer_layout);
        navigationView = bindView(R.id.note_content_nav_view);
        fab = bindView(R.id.note_content_nav_fab);


    }

    //导入数据的方法
    private void initDataToView() {


        Picasso.with(this).load(beanContent.getFront_cover_photo_url()).into(titleBackground);
        Log.d("NoteContentActivity", beanContent.getUser().getImage());
        Picasso.with(this).load(beanContent.getUser().getImage()).into(titleHeadImage);
        titleName.setText(beanContent.getName());
        titleDate.setText(beanContent.getStart_date() + " | ");
        titleDays.setText(beanContent.getTrip_days().size() + "天 , ");
        titlePhotoCount.setText(beanContent.getPhotos_count() + "图");
        noteContentAdapter = new NoteContentAdapter(this);
        noteContentAdapter.setNoteBeanContent(beanContent.getTrip_days());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteContentAdapter);


        StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(noteContentAdapter); //绑定之前的adapter
        recyclerView.addItemDecoration(headersDecor);
        headersDecor.invalidateHeaders();


    }

    //复写的导入数据方法
    @Override
    protected void initData() {

        Intent intent = getIntent();
        int id = intent.getIntExtra("noteId", 0);

        beanContent = new NoteBeanContent();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/trips/" + id + ".json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("NoteContentActivity", response);
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


        titleCollect.setOnClickListener(this);
        titleShare.setOnClickListener(this);

//        //打开抽屉的方法
//        drawerLayout.openDrawer(GravityCompat.START);
//
//        //关闭抽屉的方法
//        drawerLayout.closeDrawer(GravityCompat.START);

    }


    //设置父布局不能点击
    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_note_content_title_collect:
                CollectEntityDao collectEntityDao = CollectEntitySingleton.getInstance().getEntityDao();
                List<CollectEntity> collectEntities = collectEntityDao.queryBuilder().list();

                boolean beHave = false;
                for (CollectEntity collectEntity : collectEntities) {
                    if (collectEntity.getCollectTitle().equals(beanContent.getName())) {
                        beHave = true;
                    }
                }

                if (!beHave) {
                    collectEntityDao.insert(new CollectEntity(null, "游记", beanContent.getName(), beanContent.getFront_cover_photo_url()));
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                } else {

                    QueryBuilder qb = collectEntityDao.queryBuilder();
                    qb.where(CollectEntityDao.Properties.CollectTitle.eq(beanContent.getName()));

                    List<CollectEntity> entities = qb.list();
                    collectEntityDao.delete(entities.get(0));
                    Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.activity_note_content_title_share:

                UMImage image = new UMImage(NoteContentActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");
                /**shareboard  need the platform all you want and callbacklistener,then open it**/
                new ShareAction(this).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.WEIXIN_FAVORITE)
                        .withText("来自友盟分享面板")
                        .withMedia(image)
                        .setCallback(umShareListener)
                        .open();

                break;
        }

    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(NoteContentActivity.this,platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(NoteContentActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(NoteContentActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(NoteContentActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.d("result","onActivityResult");
    }



}
