package com.example.junyizhou.recyclerviewdemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.junyizhou.recyclerviewdemo.R;
import com.example.junyizhou.recyclerviewdemo.decoration.DividerGridItemDecoration;
import com.example.junyizhou.recyclerviewdemo.foundation.RecyclerListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunyiZhou on 2016/1/4.
 */
public class StaggeredGridVerticalFlowListDemoFragment extends RecyclerListFragment {

    private List<String> mDataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewTypeInPosition(int position) {
        return position % 3;
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent, int type) {
        if (type == 0) {
            return new CharFirstViewHolder(parent);
        } else if (type == 1) {
            return new CharSecondViewHolder(parent);
        } else {
            return new CharThirdViewHolder(parent);
        }
    }

    protected void initData() {
        mDataList = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDataList.add("" + (char) i);
        }
        setDataList(mDataList);
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        return new DividerGridItemDecoration(getActivity());
    }

    class CharFirstViewHolder extends ViewHolder<String> {

        TextView tvChar;

        public CharFirstViewHolder(ViewGroup parent) {
            this(LayoutInflater.from(getActivity()).inflate(R.layout.item_first, parent, false));
        }

        public CharFirstViewHolder(View view) {
            super(view);
            tvChar = (TextView) view.findViewById(R.id.tv_char);
        }

        @Override
        public void bind(String item, int position) {
            tvChar.setText(item);
        }
    }

    class CharSecondViewHolder extends ViewHolder<String> {

        TextView tvChar;

        public CharSecondViewHolder(ViewGroup parent) {
            this(LayoutInflater.from(getActivity()).inflate(R.layout.item_second, parent, false));
        }

        public CharSecondViewHolder(View view) {
            super(view);
            tvChar = (TextView) view.findViewById(R.id.tv_char);
        }

        @Override
        public void bind(String item, int position) {
            tvChar.setText(item);
        }
    }

    class CharThirdViewHolder extends ViewHolder<String> {

        TextView tvChar;

        public CharThirdViewHolder(ViewGroup parent) {
            this(LayoutInflater.from(getActivity()).inflate(R.layout.item_third, parent, false));
        }

        public CharThirdViewHolder(View view) {
            super(view);
            tvChar = (TextView) view.findViewById(R.id.tv_char);
        }

        @Override
        public void bind(String item, int position) {
            tvChar.setText(item);
        }
    }
}
