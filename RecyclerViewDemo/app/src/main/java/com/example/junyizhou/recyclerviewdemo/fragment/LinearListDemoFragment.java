package com.example.junyizhou.recyclerviewdemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.junyizhou.recyclerviewdemo.R;
import com.example.junyizhou.recyclerviewdemo.decoration.DividerItemDecoration;
import com.example.junyizhou.recyclerviewdemo.foundation.RecyclerListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunyiZhou on 2016/1/4.
 */
public class LinearListDemoFragment extends RecyclerListFragment {

    private List<String> mDataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        return new CharViewHolder(parent);
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
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        return new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
    }

    class CharViewHolder extends ViewHolder<String> {

        TextView tvChar;

        public CharViewHolder(ViewGroup parent) {
            this(LayoutInflater.from(getActivity()).inflate(R.layout.item_linear, parent, false));
        }

        public CharViewHolder(View view) {
            super(view);
            tvChar = (TextView) view.findViewById(R.id.tv_char);
        }

        @Override
        public void bind(String item, int position) {
            tvChar.setText(item);
        }
    }
}
