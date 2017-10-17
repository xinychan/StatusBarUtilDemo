package com.huayi.statusbarutildemo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

/**
 * 为使用 ImageView 作为头部的界面设置状态栏透明
 * 需要准备一个需要向下偏移的 View，以填充 ImageView 的头部透明距离
 */
public class Btn02Activity extends BaseActivity {

    private Btn02Activity activity;
    //private View mViewNeedOffset;//需要向下偏移的 View
    private View view_need_offset_line;//需要向下偏移的 View
    private SeekBar mSbChangeAlpha;
    private TextView mTvStatusAlpha;

    private int mAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn02);
        activity = this;
        //mViewNeedOffset = findViewById(R.id.view_need_offset);
        view_need_offset_line = findViewById(R.id.view_need_offset_line);
        mTvStatusAlpha = (TextView) findViewById(R.id.tv_status_alpha);
        mSbChangeAlpha = (SeekBar) findViewById(R.id.sb_change_alpha);

        //设置透明度
        mSbChangeAlpha.setMax(255);
        mSbChangeAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                //为使用 ImageView 作为头部的界面设置状态栏透明
                //needOffsetView 需要向下偏移的 View
                //StatusBarUtil.setTranslucentForImageView(activity, mAlpha, mViewNeedOffset);
                StatusBarUtil.setTranslucentForImageView(activity, mAlpha, view_need_offset_line);
                mTvStatusAlpha.setText(String.valueOf(mAlpha));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSbChangeAlpha.setProgress(StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);//使用默认透明度112
    }

    @Override
    protected void setStatusBar() {
        //为头部是 ImageView 的界面设置状态栏透明(使用默认透明度112)
        //mViewNeedOffset = findViewById(R.id.view_need_offset);
        view_need_offset_line = findViewById(R.id.view_need_offset_line);
        //needOffsetView 需要向下偏移的 View
        StatusBarUtil.setTranslucentForImageView(this, view_need_offset_line);
    }

}
