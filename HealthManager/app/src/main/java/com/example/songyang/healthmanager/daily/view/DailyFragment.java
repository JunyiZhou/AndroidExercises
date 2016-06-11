package com.example.songyang.healthmanager.daily.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.component.LineDiagramView;
import com.example.songyang.healthmanager.daily.presenter.DailyPresenter;
import com.example.songyang.healthmanager.daily.presenter.IDailyPresenter;
import com.example.songyang.healthmanager.login.event.UserLoginEvent;
import com.example.songyang.healthmanager.login.view.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class DailyFragment extends Fragment implements IDailyView, View.OnClickListener {

    private LineDiagramView mLineDiagramView;

    private IDailyPresenter mDailyPresenter;

    private LinearLayout content;
    private TextView login;

    public DailyFragment() {
        mDailyPresenter = new DailyPresenter(this);
    }

    public static DailyFragment newInstance() {
        DailyFragment fragment = new DailyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void login(UserLoginEvent event) {
        if (event.isLogin) {
            content.setVisibility(View.VISIBLE);
            login.setVisibility(View.GONE);
            mDailyPresenter.loadLineDiagramData();
        } else {
            Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        content = (LinearLayout) view.findViewById(R.id.content_daily);

        login = (TextView) view.findViewById(R.id.login_daily);
        login.setOnClickListener(this);

        mLineDiagramView = (LineDiagramView) view.findViewById(R.id.line_diagram_view);
    }

    @Override
    public void setLineDiagramData(int[] yParams, float[] values, String[] xParams, String unite) {
        mLineDiagramView.setParams(yParams, values, xParams, unite);
        mLineDiagramView.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_daily:
                LoginActivity.startInfoActivity(getActivity());
                break;
        }
    }
}
