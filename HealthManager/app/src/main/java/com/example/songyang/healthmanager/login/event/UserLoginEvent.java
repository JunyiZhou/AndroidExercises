package com.example.songyang.healthmanager.login.event;

/**
 * Created by JunyiZhou on 2016/6/11.
 */
public class UserLoginEvent {
    public final boolean isLogin;

    public UserLoginEvent(boolean isLogin) {
        this.isLogin = isLogin;
    }
}
