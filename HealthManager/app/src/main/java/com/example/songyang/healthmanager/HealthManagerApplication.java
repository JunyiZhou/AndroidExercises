package com.example.songyang.healthmanager;

import android.app.Application;

import com.example.songyang.healthmanager.bean.UserBean;
import com.example.songyang.healthmanager.db.DBManager;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by JunyiZhou on 2016/4/12.
 */
public class HealthManagerApplication extends Application {
    private static HealthManagerApplication INSTANCE = null;

    public static synchronized HealthManagerApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Fresco.initialize(this);
        initData();
    }

    public void initData() {
        for (int i = 0; i < 6; i++) {
            UserBean userBean = new UserBean(i, "JunyiZhou" + i, "123456");
            DBManager.getInstance().putUser(userBean);
        }
    }
}
