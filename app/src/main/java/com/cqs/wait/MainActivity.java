package com.cqs.wait;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cqs.wait.entity.VoteActivity;
import com.marshalchen.common.commonUtils.basicUtils.BasicUtils;


public class MainActivity extends Activity {

    @butterknife.InjectView(R.id.textView)
    TextView textView;
    @butterknife.InjectView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SysApplication.getInstance().addActivity(this);
        butterknife.ButterKnife.inject(this);
//        String a = "adf";
//        ImageLoaderConfiguration config = UniversalImageLoader.getDefaultImageLoaderConfiguration(getApplicationContext());
//        ImageLoader.getInstance().init(config);
//        ImageLoader.getInstance().displayImage("http://pic.cr173.com/up/2012-6/2012060511590916628.png", imageView);
        BasicUtils.sendIntent(this, LoginActivity.class);
        finish();
    }

}
