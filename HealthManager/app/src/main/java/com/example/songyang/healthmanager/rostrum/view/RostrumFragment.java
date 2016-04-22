package com.example.songyang.healthmanager.rostrum.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.component.IOnItemClickListener;
import com.example.songyang.healthmanager.component.IOnItemLongClickListener;
import com.example.songyang.healthmanager.component.RecyclerListFragment;
import com.example.songyang.healthmanager.rostrum.presenter.IRostrumPresenter;
import com.example.songyang.healthmanager.rostrum.presenter.RostrumPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class RostrumFragment extends RecyclerListFragment implements IRostrumView, IOnItemClickListener, IOnItemLongClickListener {
    private IRostrumPresenter mRostrumPresenter;

    public RostrumFragment() {
        mRostrumPresenter = new RostrumPresenter(this);
    }

    public static RostrumFragment newInstance() {
        RostrumFragment fragment = new RostrumFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOnItemClickListener(this);
        setOnItemLongClickListener(this);

        mRostrumPresenter.load();
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        return new CharViewHolder(parent);
    }

    @Override
    public void setRostrumData(List rostrumData) {
        setDataList(rostrumData);
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        return null;
    }

    @Override
    public RecyclerView.ItemAnimator createItemAnimator() {
        DefaultItemAnimator mDefaultItemAnimator = new DefaultItemAnimator();
        mDefaultItemAnimator.setAddDuration(1000);
        mDefaultItemAnimator.setChangeDuration(1000);
        mDefaultItemAnimator.setMoveDuration(1000);
        mDefaultItemAnimator.setRemoveDuration(1000);
        return mDefaultItemAnimator;
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    class CharViewHolder extends ViewHolder<String> {
        private SimpleDraweeView simpleDraweeView;

        public CharViewHolder(ViewGroup parent) {
            this(LayoutInflater.from(getActivity()).inflate(R.layout.item_rostrum, parent, false));
        }

        public CharViewHolder(View view) {
            super(view);
            simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.item_adv);
        }

        @Override
        public void bind(String item, int position) {
            Uri imageUri = Uri.parse("http://www.nmg.xinhuanet.com/home/ws/2015-10/12/1116794592_14446212002811n.jpg");
            simpleDraweeView.setImageURI(imageUri);
            simpleDraweeView.setAspectRatio(1);
            //创建DraweeController
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    //加载的图片URI地址
                    .setUri(imageUri)
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
}
