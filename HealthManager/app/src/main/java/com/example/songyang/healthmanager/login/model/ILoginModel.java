package com.example.songyang.healthmanager.login.model;

/**
 * Created by SongYang on 2016/4/13.
 */
public interface ILoginModel {
    boolean checkUserAuthority(String userName, String password);
}
