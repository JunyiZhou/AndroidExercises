package com.example.songyang.healthmanager.login.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.login.event.UserLoginEvent;
import com.example.songyang.healthmanager.login.presenter.ILoginPresenter;
import com.example.songyang.healthmanager.login.presenter.LoginPresenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by SongYang on 2016/4/13.
 */
public class LoginFragment extends Fragment  implements ILoginView, View.OnClickListener{

    public LoginFragment() {
        mInfoPresenter = new LoginPresenter(this);
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    private EditText etUsername, etPassword;
    private TextView submit;

    private ILoginPresenter mInfoPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUsername = (EditText) view.findViewById(R.id.username_login);
        etPassword = (EditText) view.findViewById(R.id.password_login);
        submit = (TextView) view.findViewById(R.id.submit_login);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_login:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                mInfoPresenter.login(username, password);
                break;
        }
    }

    @Override
    public void showLoginResult(boolean result) {
        EventBus.getDefault().post(new UserLoginEvent(result));
        getActivity().finish();
    }
}
