package com.huayi.statusbarutildemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * StatusBarUtil 的使用
 * 沉浸状态栏的使用
 * 可以在4.4及其以上系统中实现 沉浸式状态栏/状态栏变色，支持设置状态栏透明度
 * 1-引入依赖
 * 2-在setContentView方法执行后，使用StatusBarUtil
 * 注意，在有DrawerLayout的界面中，DrawerLayout中要添加属性：android:fitsSystemWindows="true"
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MainActivity activity;
    private Button btn01;
    private Button btn02;
    private Button btn03;
    private Button btn04;
    private Button btn05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        btn01 = (Button) findViewById(R.id.btn_main_btn01);
        btn02 = (Button) findViewById(R.id.btn_main_btn02);
        btn03 = (Button) findViewById(R.id.btn_main_btn03);
        btn04 = (Button) findViewById(R.id.btn_main_btn04);
        btn05 = (Button) findViewById(R.id.btn_main_btn05);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
        btn04.setOnClickListener(this);
        btn05.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_btn01:
                toNextActivity(Btn01Activity.class);
                break;
            case R.id.btn_main_btn02:
                toNextActivity(Btn02Activity.class);
                break;
            case R.id.btn_main_btn03:
                toNextActivity(Btn03Activity.class);
                break;
            case R.id.btn_main_btn04:
                toNextActivity(Btn04Activity.class);
                break;
            case R.id.btn_main_btn05:
                toNextActivity(Btn05Activity.class);
                break;
            default:
                break;
        }
    }

    private void toNextActivity(Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        startActivity(intent);
    }
}
