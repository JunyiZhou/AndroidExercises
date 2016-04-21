package com.example.songyang.healthmanager.main.presenter;

import com.example.songyang.healthmanager.bean.UserBean;
import com.example.songyang.healthmanager.main.model.IMainModel;
import com.example.songyang.healthmanager.main.model.MainModel;
import com.example.songyang.healthmanager.main.view.IMainView;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class MainPresenter implements IMainPresenter{
    private IMainModel mUserModel;
    private IMainView mMainView;

    public MainPresenter(IMainView view) {
        mMainView = view;
        mUserModel = new MainModel();
    }

    @Override
    public void loadUser(int id) {
        UserBean user = mUserModel.get(id);
        mMainView.setUserName(user.getUsername());
    }
}
