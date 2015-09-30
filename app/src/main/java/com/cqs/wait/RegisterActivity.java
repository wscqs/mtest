package com.cqs.wait;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cqs.wait.db.DataHelper;
import com.cqs.wait.entity.UserInfo;
import com.marshalchen.common.commonUtils.basicUtils.BasicUtils;
import com.marshalchen.common.commonUtils.basicUtils.StringUtils;
import com.marshalchen.common.commonUtils.urlUtils.UniversalImageLoader;
import com.marshalchen.common.ui.ToastUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class RegisterActivity extends Activity {


    @InjectView(R.id.iv_tou)
    ImageView ivTou;
    @InjectView(R.id.bt_tou)
    Button btTou;
    @InjectView(R.id.account)
    EditText account;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.register)
    Button register;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        SysApplication.getInstance().addActivity(this);
        btTou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(account.getText())){
                    ToastUtil.show(RegisterActivity.this,"请输入用户名");
                }
                if(TextUtils.isEmpty(password.getText())){
                    ToastUtil.show(RegisterActivity.this,"请输入密码");
                }
                UserInfo ui=new UserInfo();

                ui.setUserName(account.getText().toString());
                ui.setPassword(password.getText().toString());
                ui.setUserIcon(path);
                DataHelper dh=new DataHelper(RegisterActivity.this);
                dh.SaveUserInfo(ui);
                dh.Close();
                ToastUtil.show(RegisterActivity.this, "恭喜您" + account.getText() + "注册成功");
                BasicUtils.sendIntent(RegisterActivity.this,sign.class,"userInfo",ui);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(1==requestCode){
            if (data != null) {
                Uri uri = data.getData();
                if (!TextUtils.isEmpty(uri.getAuthority())) {
                    Cursor cursor = getContentResolver().query(uri,
                            new String[] { MediaStore.Images.Media.DATA },null, null, null);
                    if (null == cursor) {
                        Toast.makeText(this, "图片没找到", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    cursor.moveToFirst();
                    path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    cursor.close();
                } else {
                    path = uri.getPath();
                }
                path="file://"+path;
                ImageLoaderConfiguration config = UniversalImageLoader.getDefaultImageLoaderConfiguration(getApplicationContext());
                ImageLoader.getInstance().init(config);
                ImageLoader.getInstance().displayImage(path, ivTou);
            }else{
                Toast.makeText(this, "图片没找到", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
