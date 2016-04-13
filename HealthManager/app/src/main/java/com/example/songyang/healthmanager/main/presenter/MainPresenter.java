package com.example.songyang.healthmanager.main.presenter;

import com.example.songyang.healthmanager.bean.UserBean;
import com.example.songyang.healthmanager.main.model.IUserModel;
import com.example.songyang.healthmanager.main.model.UserModel;
import com.example.songyang.healthmanager.main.view.IMainView;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class MainPresenter implements IMainPresenter{
    private IUserModel mUserModel;
    private IMainView mMainView;

    public MainPresenter(IMainView view) {
        mMainView = view;
        mUserModel = new UserModel();
    }

    @Override
    public void getUser(int id) {
        UserBean user = mUserModel.get(id);
        mMainView.setUserName(user.getName());
    }
}
