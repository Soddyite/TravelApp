package com.example.dllo.zhangxiwei_travelapp.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dllo.zhangxiwei_travelapp.R;
import com.example.dllo.zhangxiwei_travelapp.base.BaseFragment;
import com.example.dllo.zhangxiwei_travelapp.base.MyApplication;
import com.example.dllo.zhangxiwei_travelapp.entity.UserEntity;
import com.example.dllo.zhangxiwei_travelapp.entity.UserEntityDao;
import com.example.dllo.zhangxiwei_travelapp.singleton.UserEntitySingleton;

/**
 * Created by dllo on 16/5/30.
 */
public class RegisterFragment extends BaseFragment implements View.OnClickListener {

    EditText emailText, passwordText;
    Button registerBtn, cancelBtn;


    @Override
    public int getLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView() {
        emailText = bindView(R.id.fragment_register_email_et);
        passwordText = bindView(R.id.fragment_register_password_et);
        registerBtn = bindView(R.id.fragment_register_finishbtn);
        cancelBtn = bindView(R.id.fragment_register_cancelbtn);

    }

    @Override
    public void initData() {
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fragment_register_finishbtn:

                UserEntityDao entityDao = UserEntitySingleton.getInstance().getEntityDao();


                entityDao.insert(new UserEntity(null, emailText.getText().toString(), passwordText.getText().toString()));

                Toast.makeText(MyApplication.getContext(), "注册成功", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fragment_register_cancelbtn:
                emailText.setText("");
                passwordText.setText("");
                break;


        }


    }
}
