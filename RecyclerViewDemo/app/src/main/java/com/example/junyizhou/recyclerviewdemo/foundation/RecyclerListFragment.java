package com.example.junyizhou.recyclerviewdemo.foundation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.junyizhou.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerListAdapter mRecyclerAdapter;

    private IOnItemClickListener mIOnItemClickListener;
    private IOnItemLongClickListener mIOnItemLongClickListener;

    private List dataList = new ArrayList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = findRecyclerView(view);

        if (mRecyclerView != null) {
            RecyclerView.LayoutManager lm = createLayoutManager();
            if (lm != null) {
                mRecyclerView.setLayoutManager(lm);
            }

            RecyclerView.ItemDecoration id = createItemDecoration();
            if (id != null) {
                mRecyclerView.addItemDecoration(id);
            }

            mRecyclerAdapter = new RecyclerListAdapter();
            mRecyclerView.setAdapter(mRecyclerAdapter);
        }
    }

    public void setOnItemClickListener(IOnItemClickListener listener){
        mIOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(IOnItemLongClickListener listener){
        mIOnItemLongClickListener = listener;
    }

    public ViewHolder getViewHolder(ViewGroup parent, int type) {
        return null;
    }

    public int getItemViewTypeCount() {
        return 1;
    }

    public int getItemViewTypeInPosition(int position) {
        return 0;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public RecyclerListAdapter getAdapter() {
        return mRecyclerAdapter;
    }

    public void setAdapter(RecyclerListAdapter recyclerListAdapter) {
        mRecyclerAdapter = recyclerListAdapter;
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    public abstract ViewHolder getViewHolder(ViewGroup parent);

    public abstract RecyclerView.LayoutManager createLayoutManager();

    public abstract RecyclerView.ItemDecoration createItemDecoration();

    protected RecyclerView findRecyclerView(View view) {
        return (RecyclerView) view.findViewById(R.id.list);
    }

    public class RecyclerListAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            int count = getItemViewTypeCount();

            if (count == 1) {
                return getViewHolder(parent);
            } else {
                return getViewHolder(parent, viewType);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return getItemViewTypeInPosition(position);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.bind(getDataList().get(position), position);
        }
    }

    public abstract class ViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final View mRootView;

        @SuppressWarnings("unchecked")
        public <VT extends View> VT getRootView() {
            return (VT) mRootView;
        }

        public ViewHolder(@NonNull View view) {
            super(view);
            mRootView = view;
            mRootView.setTag(this);
            mRootView.setOnClickListener(this);
            mRootView.setOnLongClickListener(this);
        }

        public abstract void bind(T item, int position);

        @Override
        public void onClick(View v) {
            if (mIOnItemClickListener != null) {
                mIOnItemClickListener.onItemClick(v, getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mIOnItemLongClickListener != null) {
                mIOnItemLongClickListener.onItemLongClick(v, getPosition());
            }
            return true;
        }
    }
}
