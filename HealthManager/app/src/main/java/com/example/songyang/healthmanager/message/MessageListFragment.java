package com.example.songyang.healthmanager.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.component.RecyclerListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SongYang on 2016/5/3.
 */
public class MessageListFragment extends RecyclerListFragment {

    public static MessageListFragment newInstance() {
        MessageListFragment fragment = new MessageListFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> messageData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            messageData.add(String.valueOf(i));
        }
        setDataList(messageData);
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        return new MessageViewHolder(parent);
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

    class MessageViewHolder extends ViewHolder<String> {
        private TextView messageTitle;
        private TextView messageContent;

        public MessageViewHolder(ViewGroup parent) {
            this(LayoutInflater.from(getActivity()).inflate(R.layout.item_message, parent, false));
        }

        public MessageViewHolder(View view) {
            super(view);

            messageTitle = (TextView) view.findViewById(R.id.title_message);
            messageContent = (TextView) view.findViewById(R.id.content_message);
        }

        @Override
        public void bind(String item, int position) {
            switch (position % 3) {
                case 0:
                    messageTitle.setText("慢性病提醒");
                    messageContent.setText("根据您近期的健康数据，系统建议您到附近医院进行系统检查。");
                    break;
                case 1:
                    messageTitle.setText("高危疾病提醒");
                    messageContent.setText("根据您近期的健康数据，系统建议您到附近医院进行系统检查。");
                    break;
                case 2:
                    messageTitle.setText("医疗咨询推送");
                    messageContent.setText("一项运动预防颈椎病");
                    break;
                default:
                    messageTitle.setText("医疗咨询推送");
                    messageContent.setText("一项运动预防颈椎病");
                    break;
            }
        }
    }
}
