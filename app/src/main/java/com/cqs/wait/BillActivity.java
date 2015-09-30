package com.cqs.wait;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class BillActivity extends Activity {

    @InjectView(R.id.tv_bill)
    TextView tvBill;
    @InjectView(R.id.bt_lookbill)
    Button btLookbill;
    @InjectView(R.id.bt_exit)
    Button btExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ButterKnife.inject(this);
        SysApplication.getInstance().addActivity(this);
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvBill.setText("有限公司首次股东会董事会监事会决议.doc");
        copyFile("aaa.doc");

        btLookbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent  intent = new Intent("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri uri = null;
                try {

                    File file = new File(Environment.getExternalStorageDirectory(), "aaa.doc");
                    uri = Uri.fromFile(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.setDataAndType(uri, "application/msword");
                startActivity(intent);
            }
        });
    }
    private void copyFile( String fileName) {
        File file = new File(Environment.getExternalStorageDirectory(), fileName);

        if (file.exists() && file.length() > 0) {
        } else {
            try {
                InputStream is = getAssets().open(fileName);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = 0;
                while (((len = is.read(buffer)) != -1)) {
                    fos.write(buffer, 0, len);
                }
                fos.flush();
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
