package com.example.songyang.healthmanager.treat.view;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.treat.presenter.TreatPresenter;
import com.example.songyang.healthmanager.treat.presenter.ITreatPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class TreatFragment extends Fragment implements ITreatView {
    private ITreatPresenter mHospitalPresenter;
    private SimpleDraweeView simpleDraweeView;

    public TreatFragment() {
        mHospitalPresenter = new TreatPresenter(this);
    }

    public static TreatFragment newInstance() {
        TreatFragment fragment = new TreatFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_treat, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.item_adv);
        mHospitalPresenter.load();
    }

    @Override
    public void setImage(Uri uri) {
        simpleDraweeView.setImageURI(uri);
        simpleDraweeView.setAspectRatio(1);
        //创建DraweeController
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //加载的图片URI地址
                .setUri(uri)
                        //设置点击重试是否开启
                .setTapToRetryEnabled(true)
                        //设置旧的Controller
                .setOldController(simpleDraweeView.getController())
                        //构建
                .build();

        //设置DraweeController
        simpleDraweeView.setController(controller);
    }
}
