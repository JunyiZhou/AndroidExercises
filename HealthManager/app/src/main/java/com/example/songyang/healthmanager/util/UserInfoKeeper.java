package com.example.songyang.healthmanager.util;

import android.content.Context;

import com.example.songyang.healthmanager.HealthManagerApplication;
import com.example.songyang.healthmanager.bean.UserBean;

/**
 * Created by SongYang on 2016/4/28.
 */
public class UserInfoKeeper {
    private final static String SP_USERINFO_DATA = "SP_USERINFO_DATA";

    private static String KEY_USER_INFO = "KEY_USER_INFO";

    private static UserInfoKeeper INSTANCE;

    public static synchronized UserInfoKeeper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserInfoKeeper(HealthManagerApplication.getContext());
        }
        return INSTANCE;
    }

    private SharedPreferencesWrapper mSharedPreferencesWrapper;

    private UserInfoKeeper(Context context) {
        mSharedPreferencesWrapper = new SharedPreferencesWrapper(context, SP_USERINFO_DATA);
        if (getUser() == null) {
            saveUser(new UserBean());
        }
    }

    public void saveUser(UserBean user) {
        mSharedPreferencesWrapper.putValue(KEY_USER_INFO, user);
    }

    /**
     * @desc 获取用户信息
     */
    public UserBean getUser() {
        return mSharedPreferencesWrapper.getValue(KEY_USER_INFO, UserBean.class);
    }

    /**
     * 根据正式token是否存在进行判断
     */
    public boolean checkLogin() {
        if (getUser() != null) {
            return true;
        } else {
            return false;
        }
    }

    public void clearUserInfo() {
        mSharedPreferencesWrapper.clear();
        saveUser(new UserBean());
    }
}
