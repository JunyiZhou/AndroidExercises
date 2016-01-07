package com.example.junyizhou.recyclerviewdemo.foundation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.junyizhou.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunyiZhou on 2015/12/31.
 * <p/>
 * 带有RecyclerView的Fragment基类
 */
public abstract class RecyclerListFragment extends Fragment {
    private static String ROOT_TAG = "ROOT_TAG";//ViewHolder中rootView的tag

    private RecyclerView mRecyclerView;//RecyclerView实例
    private RecyclerListAdapter mRecyclerAdapter;//RecyclerView适配器实例

    private IOnItemClickListener mIOnItemClickListener;//item点击事件监听实例
    private IOnItemLongClickListener mIOnItemLongClickListener;//item长按事件监听实例
    private OnItemTouchListener mOnItemTouchListener;//item触摸事件监听实例

    private List dataList = new ArrayList();//RecyclerView数据

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

        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);

        if (mRecyclerView != null) {
            RecyclerView.LayoutManager lm = createLayoutManager();//RecyclerView的LayoutManager，主要是控制显示的布局模式，线性、网格等待
            if (lm != null) {
                mRecyclerView.setLayoutManager(lm);
            }

            RecyclerView.ItemDecoration id = createItemDecoration();//RecyclerView的分割线
            if (id != null) {
                mRecyclerView.addItemDecoration(id);
            }

            RecyclerView.ItemAnimator ia = createItemAnimator();//RecyclerView的动画
            if (ia != null) {
                mRecyclerView.setItemAnimator(ia);
            }

            mRecyclerAdapter = new RecyclerListAdapter();
            mRecyclerView.setAdapter(mRecyclerAdapter);//加载适配器
        }
    }

    /**
     * 获取当前RecyclerView实例
     *
     * @return 当前RecyclerView实例
     */
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    /**
     * 获取当前RecyclerView的adapter
     *
     * @return 当前RecyclerView的adapter
     */
    public RecyclerListAdapter getAdapter() {
        return mRecyclerAdapter;
    }

    /**
     * 获取ViewHolder
     * 抽象方法，当type数量为1的时候被调用，需要实现
     *
     * @param parent ViewHolder的父布局
     * @return ViewHolder实例
     */
    public abstract ViewHolder getViewHolder(ViewGroup parent);

    /**
     * 创建RecyclerView的LayoutManager
     * 抽象方法，子类必须实现，不可以返回null，否则会报错
     *
     * @return RecyclerView的LayoutManager实例
     */
    public abstract RecyclerView.LayoutManager createLayoutManager();

    /**
     * 创建RecyclerView的分割线
     * 抽象方法，子类可以返回null
     *
     * @return RecyclerView的分割线实例
     */
    public abstract RecyclerView.ItemDecoration createItemDecoration();

    /**
     * 创建RecyclerView的动画
     * 抽象方法，子类可以返回null
     *
     * @return RecyclerView的动画
     */
    public abstract RecyclerView.ItemAnimator createItemAnimator();

    /**
     * 设置item点击事件监听
     *
     * @param listener item点击事件监听
     */
    public void setOnItemClickListener(IOnItemClickListener listener) {
        mIOnItemClickListener = listener;
    }

    /**
     * 设置item长按事件监听
     *
     * @param listener item长按事件监听
     */
    public void setOnItemLongClickListener(IOnItemLongClickListener listener) {
        mIOnItemLongClickListener = listener;
    }

    /**
     * 设置item触摸事件监听
     *
     * @param listener item触摸事件监听
     */
    public void setOnItemTouchListener(OnItemTouchListener listener) {
        mOnItemTouchListener = listener;
    }

    /**
     * 根据type，来获取对应的ViewHolder
     *
     * @param parent ViewHolder所在的父布局
     * @param type   ViewHolder类型
     * @return ViewHolder实例
     */
    public ViewHolder getViewHolder(ViewGroup parent, int type) {
        return null;
    }

    /**
     * 获取item类型数量
     *
     * @return item类型数量
     */
    public int getItemViewTypeCount() {
        return 1;
    }

    /**
     * 根据position获取对应的type
     *
     * @param position item在RecyclerView中的位置
     * @return type
     */
    public int getItemViewTypeInPosition(int position) {
        return 0;
    }

    /**
     * 获取数据list
     *
     * @return 数据list
     */
    public List getDataList() {
        return dataList;
    }

    /**
     * 设置数据list
     *
     * @param dataList 数据list
     */
    public void setDataList(List dataList) {
        if (dataList == null) {
            dataList = new ArrayList();
        }
        this.dataList = dataList;
    }

    /**
     * 添加数据
     *
     * @param position 添加数据的位置
     * @param data     数据
     * @param <T>      数据类型
     */
    public <T> void addData(int position, T data) {
        if (mRecyclerAdapter == null || dataList == null || dataList.size() < position) {
            return;
        }
        dataList.add(position, data);
        mRecyclerAdapter.notifyItemInserted(position);
        mRecyclerAdapter.notifyItemRangeChanged(position, dataList.size());
    }

    /**
     * 删除数据
     *
     * @param position 删除数据的位置
     */
    public void removeData(int position) {
        if (mRecyclerAdapter == null || dataList == null || dataList.size() < position) {
            return;
        }
        dataList.remove(position);
        mRecyclerAdapter.notifyItemRemoved(position);
        mRecyclerAdapter.notifyItemRangeChanged(position, dataList.size());
    }

    /**
     * RecyclerView适配器封装子类
     */
    public class RecyclerListAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            int count = getItemViewTypeCount();//item type数量

            if (count == 1) {//如果item type数量为1，则调用不需要区分type的getViewHolder方法
                return getViewHolder(parent);
            } else {//如果item type数量不为1，则调用需要区分type的getViewHolder方法
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
            viewHolder.bind(getDataList().get(position), position);//将对应position的数据和position传给ViewHolder中的bind方法，在bind方法中处理数据的填装
        }
    }

    /**
     * RecyclerView 对应的ViewHolder
     *
     * @param <T> 数据泛型
     */
    public abstract class ViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final View mRootView;

        /**
         * 获取RootView
         *
         * @param <VT> RootView的ViewHolder实例类型
         * @return RootView的ViewHolder实例
         */
        @SuppressWarnings("unchecked")
        public <VT extends View> VT getRootView() {
            return (VT) mRootView;
        }

        public ViewHolder(@NonNull View view) {
            super(view);
            mRootView = view;
            mRootView.setTag(ROOT_TAG);
            mRootView.setOnClickListener(this);
            mRootView.setOnLongClickListener(this);
        }

        /**
         * 数据与view元素bind方法
         *
         * @param item     数据
         * @param position 位置
         */
        public abstract void bind(T item, int position);

        @Override
        public void onClick(View v) {
            //item点击事件监听设置，通过RootView的tag进行区分
            if (mIOnItemClickListener != null && v.getTag() != null && v.getTag().equals(ROOT_TAG)) {
                mIOnItemClickListener.onItemClick(v, getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            //item长按事件监听设置，通过RootView的tag进行区分
            if (mIOnItemLongClickListener != null && v.getTag() != null && v.getTag().equals(ROOT_TAG)) {
                mIOnItemLongClickListener.onItemLongClick(v, getPosition());
            }
            return true;
        }
    }
}
