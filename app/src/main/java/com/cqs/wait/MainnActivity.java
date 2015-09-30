package com.cqs.wait;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cqs.wait.entity.UserInfo;
import com.cqs.wait.entity.VoteActivity;
import com.marshalchen.common.commonUtils.basicUtils.BasicUtils;
import com.marshalchen.common.commonUtils.urlUtils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainnActivity extends Activity {

    @InjectView(R.id.iv_usertou)
    ImageView ivUsertou;
    @InjectView(R.id.user_name)
    TextView userName;
    @InjectView(R.id.bt_look)
    Button btLook;
    @InjectView(R.id.bt_vote)
    Button btVote;
    @InjectView(R.id.bt_exit)
    Button btExit;
    @InjectView(R.id.result)
    Button result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainn);
        SysApplication.getInstance().addActivity(this);
        ButterKnife.inject(this);
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SysApplication.getInstance().exit();

            }
        });

        final UserInfo userInfo = (UserInfo) getIntent().getParcelableExtra("userInfo");
        if (userInfo.getUserIcon() != null) {

            ImageLoaderConfiguration config = UniversalImageLoader.getDefaultImageLoaderConfiguration(getApplicationContext());
            ImageLoader.getInstance().init(config);
            ImageLoader.getInstance().displayImage(userInfo.getUserIcon(), ivUsertou);
        }

        userName.setText("用户名： " + userInfo.getUserName().toString());
        btLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasicUtils.sendIntent(MainnActivity.this, BillActivity.class);
            }
        });
        btVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasicUtils.sendIntent(MainnActivity.this, VoteActivity.class, "userInfo", userInfo);
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasicUtils.sendIntent(MainnActivity.this, ResultActivity.class);
            }
        });
    }

}
