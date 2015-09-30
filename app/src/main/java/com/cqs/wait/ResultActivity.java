package com.cqs.wait;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cqs.wait.db.DataHelper;
import com.cqs.wait.entity.UserInfo;
import com.cqs.wait.entity.VoteActivity;
import com.marshalchen.common.commonUtils.logUtils.Logs;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ResultActivity extends Activity {

    @InjectView(R.id.tv_shi)
    TextView tvShi;
    @InjectView(R.id.tv_fan)
    TextView tvFan;
    @InjectView(R.id.tv_ying)
    TextView tvYing;
    @InjectView(R.id.tv_zan)
    TextView tvZan;
    @InjectView(R.id.tv_biao)
    TextView tvBiao;
    @InjectView(R.id.tv_zhuang)
    TextView tvZhuang;
    @InjectView(R.id.tv_qing)
    TextView tvQing;
    @InjectView(R.id.tv_qi)
    TextView tvQi;
    @InjectView(R.id.bt_exit)
    Button btExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        SysApplication.getInstance().addActivity(this);
        ButterKnife.inject(this);
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        DataHelper dh = new DataHelper(ResultActivity.this);

        List<UserInfo> userInfos = dh.GetUserList();
        int ying = userInfos.size();
        int shi=0;
        int zan=0;
        int fan=0;
        int qi=0;
        for(UserInfo userInfo:userInfos){
            Logs.e(userInfo.toString());
            if(userInfo.getSign().equals("YES")){
                shi++;
            }
            if("1".equals(userInfo.getVotes())){
                zan++;
            }
            if("2".equals(userInfo.getVotes())){
                fan++;
            }
            if("3".equals(userInfo.getVotes())){
                qi++;
            }
        }
        tvYing.setText("应到人数  "+ying+" 人");
        tvShi.setText("实到人数  "+shi+" 人");
        tvQing.setText("请假人数  "+"0"+" 人");
        tvZan.setText("赞成人数  "+zan+" 人");
        tvFan.setText("反对人数  "+fan +" 人");
        tvQi.setText("弃权人数  "+qi +" 人");
        if(zan*0.5>ying){
            tvZhuang.setText("通过");
        }
        tvZhuang.setText("不通过");
    }

}
