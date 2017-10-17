package com.huayi.statusbarutildemo;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import java.util.Random;

/**
 * 修改状态栏透明度和颜色
 */
public class Btn01Activity extends BaseActivity {

    private Btn01Activity activity;
    private Toolbar mToolbar;
    private Button mBtnChangeColor;
    private SeekBar mSbChangeAlpha;
    private TextView mTvStatusAlpha;

    private int mColor;
    private int mAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn01);
        activity = this;

        initView();

        // 设置toolbar
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            //允许点击导航按钮
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // 改变颜色
        mBtnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                mColor = 0xff000000 | random.nextInt(0xffffff);
                mToolbar.setBackgroundColor(mColor);
                StatusBarUtil.setColor(activity, mColor, mAlpha);//改变颜色和透明度
                //StatusBarUtil.setColor(activity,mColor);//改变颜色
            }
        });

        //设置状态栏透明度
        mSbChangeAlpha.setMax(255);//设置最大值为255
        mSbChangeAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                StatusBarUtil.setColor(activity, mColor, mAlpha);//改变颜色和透明度
                mTvStatusAlpha.setText(String.valueOf(mAlpha));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSbChangeAlpha.setProgress(StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);//默认值为112

    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mBtnChangeColor = (Button) findViewById(R.id.btn_change_color);
        mTvStatusAlpha = (TextView) findViewById(R.id.tv_status_alpha);
        mSbChangeAlpha = (SeekBar) findViewById(R.id.sb_change_alpha);
    }

    @Override
    protected void setStatusBar() {
        //此方法在setContentView中重写的，此时activity还没有被初始化，不能传入activity，否则mColor为null
        //mColor = ContextCompat.getColor(activity,R.color.colorPrimary);
        mColor = ContextCompat.getColor(this,R.color.colorPrimary);
        StatusBarUtil.setColor(this, mColor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
