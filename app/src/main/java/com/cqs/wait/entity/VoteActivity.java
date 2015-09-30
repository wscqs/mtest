package com.cqs.wait.entity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cqs.wait.R;
import com.cqs.wait.SysApplication;
import com.cqs.wait.db.DataHelper;
import com.marshalchen.common.ui.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VoteActivity extends ActionBarActivity {

    @InjectView(R.id.oppose)
    RadioButton oppose;
    @InjectView(R.id.agree)
    RadioButton agree;
    @InjectView(R.id.abstain)
    RadioButton abstain;
    @InjectView(R.id.rg)
    RadioGroup rg;
    @InjectView(R.id.bt_submit)
    Button btSubmit;
    String userName;
    @InjectView(R.id.bt_exit)
    Button btExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        ButterKnife.inject(this);
        SysApplication.getInstance().addActivity(this);
        agree.setChecked(true);
        UserInfo userInfo = getIntent().getParcelableExtra("userInfo");
        userName = userInfo.getUserName();
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHelper dh = new DataHelper(VoteActivity.this);
                switch (rg.getCheckedRadioButtonId()) {
                    case R.id.agree:
                        ToastUtil.show(VoteActivity.this, "您提交了赞成，提交成功");
                        dh.vote(userName, "1");
                        break;
                    case R.id.oppose:
                        ToastUtil.show(VoteActivity.this, "您提交了反对，提交成功");
                        dh.vote(userName, "2");
                        break;
                    case R.id.abstain:
                        dh.vote(userName, "3");
                        ToastUtil.show(VoteActivity.this, "您提交了弃权，提交成功");
                        break;

                }
                dh.Close();
            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
    }

}
