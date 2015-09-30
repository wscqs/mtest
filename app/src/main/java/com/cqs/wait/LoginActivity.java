package com.cqs.wait;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cqs.wait.db.DataHelper;
import com.cqs.wait.entity.UserInfo;
import com.marshalchen.common.commonUtils.basicUtils.BasicUtils;
import com.marshalchen.common.ui.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends Activity {


    @InjectView(R.id.account)
    EditText account;
    @InjectView(R.id.password)
    EditText password;

    @InjectView(R.id.login)
    Button login;

    @InjectView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @InjectView(R.id.register)
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SysApplication.getInstance().addActivity(this);
        ButterKnife.inject(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(account.getText())) {
                    ToastUtil.show(LoginActivity.this, "请输入用户名");
                }
                if (TextUtils.isEmpty(password.getText())) {
                    ToastUtil.show(LoginActivity.this, "请输入密码");
                }
                UserInfo ui = new UserInfo();

                ui.setUserName(account.getText().toString());
                ui.setPassword(password.getText().toString());

                DataHelper dh = new DataHelper(LoginActivity.this);
                UserInfo ui1=new UserInfo();
                ui1=dh.select(ui);
                if (ui1==null) {
                    ToastUtil.show(LoginActivity.this, "用户名或密码错误");


                }else {
//                    BasicUtils.sendIntent(LoginActivity.this, MainnActivity.class, "account", ui);
                    BasicUtils.sendIntent(LoginActivity.this, sign.class, "userInfo", ui);

                }
                dh.Close();
//                BasicUtils.sendIntent(LoginActivity.this, sign.class);
            }
        });
        tvForgetPassword.setVisibility(View.INVISIBLE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasicUtils.sendIntent(LoginActivity.this,RegisterActivity.class);
            }
        });
    }

}
