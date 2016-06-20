package com.example.songyang.healthmanager.treat.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.songyang.healthmanager.R;
import com.example.songyang.healthmanager.component.RecyclerListFragment;
import com.example.songyang.healthmanager.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SongYang on 2016/5/3.
 */
public class DoctorListFragment extends RecyclerListFragment {

    public static DoctorListFragment newInstance() {
        DoctorListFragment fragment = new DoctorListFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> doctorData = new ArrayList<>();
        doctorData.add("张医生");
        doctorData.add("王医生");
        doctorData.add("李医生");
        doctorData.add("赵医生");
        setDataList(doctorData);
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        return new DoctorViewHolder(parent);
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

    class DoctorViewHolder extends ViewHolder<String> {
        private TextView name;
        private TextView register;
        private TextView treat;

        public DoctorViewHolder(ViewGroup parent) {
            this(LayoutInflater.from(getActivity()).inflate(R.layout.item_doctor, parent, false));
        }

        public DoctorViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name_doctor);
            register = (TextView) view.findViewById(R.id.register_doctor);
            register.setOnClickListener(this);
            treat = (TextView) view.findViewById(R.id.treat_doctor);
            treat.setOnClickListener(this);
        }

        @Override
        public void bind(String item, int position) {
            name.setText(item);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);

            switch (v.getId()) {
                case R.id.register_doctor:
                    showDialog(TimeUtil.getCurrentTime(TimeUtil.TEMPLATE_DATE_CHINESE) + " " + (getAdapterPosition() + 1) + "号");
                    break;
                case R.id.treat_doctor:
                    Intent intent = new Intent(getActivity(), TreatDetailActivity.class);
                    intent.putExtra("CHAT_NAME", name.getText().toString());
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }

        public void showDialog(String message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());  //先得到构造器
            builder.setTitle("挂号信息"); //设置标题
            builder.setMessage("是否确认挂号\n" + message); //设置内容
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "挂号成功！", Toast.LENGTH_SHORT).show();
                    dialog.dismiss(); //关闭dialog
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "取消挂号！", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    }
}
