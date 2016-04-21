package com.example.songyang.healthmanager.info.model;

import com.example.songyang.healthmanager.bean.UserBean;
import com.example.songyang.healthmanager.db.DBManager;

import java.util.List;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class UserModel implements IUserModel {
    private List<UserBean> users;

    public UserModel() {
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
