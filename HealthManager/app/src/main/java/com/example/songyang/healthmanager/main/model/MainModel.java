package com.example.songyang.healthmanager.main.model;

import com.example.songyang.healthmanager.bean.UserBean;
import com.example.songyang.healthmanager.db.DBManager;

import java.util.List;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class MainModel implements IMainModel {
    private List<UserBean> users;

    public MainModel() {
        users = DBManager.getInstance().getUsers();
    }

    @Override
    public UserBean get(int id) {
        if (users != null) {
            for (UserBean user : users) {
                if (user.getUserid() == id) {
                    return user;
                }
            }
        }
        return null;
    }
}
