package com.example.songyang.healthmanager.treat.view;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.component.IOnItemClickListener;
import com.example.songyang.healthmanager.component.IOnItemLongClickListener;
import com.example.songyang.healthmanager.component.RecyclerListFragment;
import com.example.songyang.healthmanager.treat.presenter.ITreatPresenter;
import com.example.songyang.healthmanager.treat.presenter.TreatPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by JunyiZhou on 2016/4/13.
 */
public class TreatFragment extends RecyclerListFragment implements ITreatView, IOnItemClickListener, IOnItemLongClickListener {
    private ITreatPresenter mTreatPresenter;
    private SimpleDraweeView simpleDraweeView;

    public TreatFragment() {
        mTreatPresenter = new TreatPresenter(this);
    }

    public static TreatFragment newInstance() {
        TreatFragment fragment = new TreatFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOnItemClickListener(this);
        setOnItemLongClickListener(this);

        mTreatPresenter.load();
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent, int type) {
        if (type == 0) {
            return new SearchViewHolder(parent);
        } else {
            return new DepartmentViewHolder(parent);
        }
    }

    @Override
    public int getItemViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewTypeInPosition(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        return manager;
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

    @Override
    public void setDepartmentData(List departments) {
        setDataList(departments);
    }

    class DepartmentViewHolder extends ViewHolder<String> {
        private TextView department;

        public DepartmentViewHolder(ViewGroup parent) {
            this(LayoutInflater.from(getActivity()).inflate(R.layout.item_department, parent, false));
        }

        public DepartmentViewHolder(View view) {
            super(view);
            department = (TextView) view.findViewById(R.id.tv_department);
        }

        @Override
        public void bind(String item, int position) {
            department.setText(item);
        }
    }

    class SearchViewHolder extends ViewHolder<String> {

        public SearchViewHolder(ViewGroup parent) {
            this(LayoutInflater.from(getActivity()).inflate(R.layout.item_search, parent, false));
        }

        public SearchViewHolder(View view) {
            super(view);
        }

        @Override
        public void bind(String item, int position) {

        }
    }
}
