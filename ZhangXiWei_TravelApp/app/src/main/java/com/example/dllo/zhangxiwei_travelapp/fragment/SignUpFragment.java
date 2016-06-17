package com.example.dllo.zhangxiwei_travelapp.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.activity.UserActivity;
import com.example.dllo.zhangxiwei_travelapp.base.BaseFragment;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.bean.SinaBean;
import com.example.dllo.zhangxiwei_travelapp.entity.UserEntity;
import com.example.dllo.zhangxiwei_travelapp.entity.UserEntityDao;
import com.example.dllo.zhangxiwei_travelapp.singleton.UserEntitySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/5/30.
 */
public class SignUpFragment extends BaseFragment implements View.OnClickListener {

    EditText emailText, passwordText;
    Button signUpBtn, cancelBtn;
    ImageView sinaIv, qqIv;


    private UMShareAPI mShareAPI = null;

    @Override
    public int getLayout() {
        return R.layout.fragment_signup;
    }

    @Override
    public void initView() {

        emailText = bindView(R.id.fragment_signup_email_et);
        passwordText = bindView(R.id.fragment_signup_password_et);

        signUpBtn = bindView(R.id.fragment_signup_finishbtn);
        cancelBtn = bindView(R.id.fragment_signup_cancelbtn);
        sinaIv = bindView(R.id.fragment_signup_sina_login);
        qqIv = bindView(R.id.fragment_signup_qq_login);
        mShareAPI = UMShareAPI.get(mContext);


    }

    @Override
    public void initData() {

        signUpBtn.setOnClickListener(this);
        sinaIv.setOnClickListener(this);
        qqIv.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


        SHARE_MEDIA platform = null;
        switch (v.getId()) {
            case R.id.fragment_signup_finishbtn:
                String email, password;
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                UserEntityDao entityDao = UserEntitySingleton.getInstance().getEntityDao();
                List<UserEntity> userEntities = entityDao.queryBuilder().list();

                for (UserEntity userEntity : userEntities) {
                    if (userEntity.getUserEmail().equals(email) && userEntity.getUserPassWord().equals(password)) {
                        Intent intent = new Intent(MyApplication.getContext(), UserActivity.class);
                        intent.putExtra("type",1);
                        intent.putExtra("email",email);
                        startActivity(intent);
                    }
                }


                break;

            case R.id.fragment_signup_sina_login:
                //mShareAPI.isInstall(this.getActivity(), platform);这句没用
                platform = SHARE_MEDIA.SINA;
                //mShareAPI.doOauthVerify(this.getActivity(), platform, umAuthListener);这句是又登陆一次返回空,也没用

                break;
            case R.id.fragment_signup_qq_login:
                platform = SHARE_MEDIA.QQ;

                break;
        }

        mShareAPI.getPlatformInfo(getActivity(), platform, umAuthListener);

    }


    /**
     * auth callback interface
     **/
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(mContext, "Authorize succeed", Toast.LENGTH_SHORT).show();
            Log.d("SignUpFragment", "data11:" + data.get("result"));
            Gson gson = new Gson();
            SinaBean sinaBean = gson.fromJson(data.get("result"), new TypeToken<SinaBean>() {
            }.getType());

            Intent intent = new Intent(getContext(), UserActivity.class);
            intent.putExtra("type",2);
            intent.putExtra("sinabean", sinaBean);
            startActivity(intent);


        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(mContext, "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mContext, "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("auth", "on activity re 2");
        mShareAPI.onActivityResult(requestCode, resultCode, data);
        Log.d("auth", "on activity re 3");
    }


}
