package com.huayi.statusbarutildemo;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

/**
 * 全屏背景图片/颜色时，设置状态栏透明度
 */
public class Btn03Activity extends BaseActivity {

    private Btn03Activity activity;
    private TextView mTvStatusAlpha;
    private RelativeLayout mRootLayout;
    private Button mBtnChangeBackground;
    private Button mBtnChangeTransparent;
    private SeekBar mSbChangeAlpha;

    private boolean isBgChanged;
    private boolean isTransparent = true;
    private int mAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn03);
        activity = this;
        mRootLayout = (RelativeLayout) findViewById(R.id.root_layout);
        mBtnChangeBackground = (Button) findViewById(R.id.btn_change_background);
        mBtnChangeTransparent = (Button) findViewById(R.id.btn_change_transparent);
        mTvStatusAlpha = (TextView) findViewById(R.id.tv_status_alpha);
        mSbChangeAlpha = (SeekBar) findViewById(R.id.sb_change_alpha);

        mBtnChangeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBgChanged = !isBgChanged;
                if (isBgChanged) {
                    mRootLayout.setBackgroundDrawable(ContextCompat.getDrawable(activity,R.drawable.bg_girl));
                    //背景设置成颜色实现透明效果
                    //mRootLayout.setBackgroundColor(ContextCompat.getColor(activity,android.R.color.holo_green_light));
                } else {
                    mRootLayout.setBackgroundDrawable(ContextCompat.getDrawable(activity,R.drawable.bg_monkey));
                    //背景设置成颜色实现透明效果
                    //mRootLayout.setBackgroundColor(ContextCompat.getColor(activity,android.R.color.holo_orange_light));
                }
            }
        });

        mBtnChangeTransparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTransparent = !isTransparent;
                if (!isTransparent) {
                    //半透明
                    mSbChangeAlpha.setVisibility(View.VISIBLE);
                    mTvStatusAlpha.setVisibility(View.VISIBLE);
                    setSeekBar();
                } else {
                    //全透明
                    mSbChangeAlpha.setVisibility(View.GONE);
                    mTvStatusAlpha.setVisibility(View.GONE);
                    //设置状态栏全透明
                    StatusBarUtil.setTransparent(activity);//这里不起作用
                    //上述方法只在首次进入activity初始化时有效，当后续修改过透明度时，该方法失去作用
                    //需要通过StatusBarUtil.setTranslucent(activity, mAlpha);来修改透明度
                }
            }
        });


    }

    private void setSeekBar() {
        mSbChangeAlpha.setMax(255);
        mSbChangeAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                StatusBarUtil.setTranslucent(activity, mAlpha);//设置状态栏透明度
                mTvStatusAlpha.setText(String.valueOf(mAlpha));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSbChangeAlpha.setProgress(StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    @Override
    protected void setStatusBar() {
        //设置状态栏全透明
        StatusBarUtil.setTransparent(this);
        //使状态栏半透明
        //适用于图片作为背景的界面,此时需要图片填充到状态栏
        //StatusBarUtil.setTranslucent(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);//默认透明度
    }

}
