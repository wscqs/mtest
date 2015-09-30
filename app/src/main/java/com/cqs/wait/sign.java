package com.cqs.wait;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cqs.wait.db.DataHelper;
import com.cqs.wait.entity.UserInfo;
import com.marshalchen.common.commonUtils.basicUtils.BasicUtils;
import com.marshalchen.common.ui.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class sign extends Activity {

    @InjectView(R.id.bt_sign)
    Button btSign;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        SysApplication.getInstance().addActivity(this);
        ButterKnife.inject(this);
        final UserInfo userInfo = getIntent().getParcelableExtra("userInfo");
        btSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(sign.this,"签到成功");
                DataHelper dh=new DataHelper(sign.this);
                dh.sign(userInfo.getUserName(),"YES");
                BasicUtils.sendIntent(sign.this,MainnActivity.class,"userInfo", userInfo);
            }
        });
    }


}
