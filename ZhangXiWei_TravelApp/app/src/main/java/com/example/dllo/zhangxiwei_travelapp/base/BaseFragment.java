package com.example.dllo.zhangxiwei_travelapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/5/9.
 */
public abstract class BaseFragment extends Fragment {

    //创建的Context对象
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //三个抽象方法,绑定布局,绑定组件,设置数据
    public abstract int getLayout();

    public abstract void initView();

    public abstract void initData();

    //组件实例化的方法,不需要转型了
    protected <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }


}
