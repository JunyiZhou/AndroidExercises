package com.example.songyang.healthmanager.rostrum.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.songyang.healthmanager.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by SongYang on 2016/4/13.
 */
public class RostrumDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rostrum_detail);

        initToorbar("一项运动预防颈椎病");
        initView();
    }

    public void initToorbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initView() {
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.item_adv);
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
