package com.example.songyang.healthmanager.login.model;

import com.example.songyang.healthmanager.bean.UserBean;
import com.example.songyang.healthmanager.db.DBManager;

import java.util.List;

/**
 * Created by SongYang on 2016/4/13.
 */
public class LoginModel implements ILoginModel {
    private List<UserBean> users;

    public LoginModel() {
        users = DBManager.getInstance().getUsers();
    }

    @Override
    public boolean checkUserAuthority(String userName, String password) {
        for (UserBean user : users) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
