package com.example.songyang.healthmanager;

import android.app.Application;
import android.content.Context;

import com.example.songyang.healthmanager.bean.UserBean;
import com.example.songyang.healthmanager.db.DBManager;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by SongYang on 2016/4/12.
 */
public class HealthManagerApplication extends Application {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Fresco.initialize(this);
        initData();
    }

    public void initData() {
        UserBean userBean = new UserBean();
        userBean.setUserid(1);
        userBean.setUsername("admin");
        userBean.setSex("m");
        userBean.setAge(20);
        userBean.setPassword("123456");
        userBean.setPhone("13800000000");
        userBean.setIdcard("100000000000000000");
        userBean.setNation("汉族");
        DBManager.getInstance().putUser(userBean);
    }
}
