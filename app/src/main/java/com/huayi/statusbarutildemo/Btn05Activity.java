package com.huayi.statusbarutildemo;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.jaeger.library.StatusBarUtil;

/**
 * 为包含 DrawerLayout 的界面设置状态栏颜色（也可以设置半透明和全透明）
 * 注意，DrawerLayout中要添加属性：android:fitsSystemWindows="true"
 */
public class Btn05Activity extends BaseActivity {

    private Btn05Activity activity;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private Button btn_change_color;
    private SeekBar sb_change_alpha;

    private int mStatusBarColor;
    private int mAlpha = StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;

    //状态栏颜色
    int color1;
    int color2;
    int color3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn05);

        activity = this;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        btn_change_color = (Button) findViewById(R.id.btn_change_color);
        sb_change_alpha = (SeekBar) findViewById(R.id.sb_change_alpha);

        color1 = ContextCompat.getColor(this, android.R.color.holo_red_light);
        color2 = ContextCompat.getColor(this, android.R.color.holo_green_light);
        color3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);

        //设置Toolbar
        setSupportActionBar(mToolbar);
        //toolbar关联mDrawerLayout，可点击导航图标弹出抽屉菜单；不结合则只能左/右划拉出抽屉菜单
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        //通过ActionBarDrawerToggle让Toolbar关联mDrawerLayout
        mDrawerLayout.addDrawerListener(toggle);
        //让Toolbar与DrawerLayout状态同步，导航图标会更改成系统默认图标
        toggle.syncState();

        //设置状态栏颜色
        btn_change_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mStatusBarColor == color1) {
                    mStatusBarColor = color2;
                } else if (mStatusBarColor == color2) {
                    mStatusBarColor = color3;
                } else if (mStatusBarColor == color3) {
                    mStatusBarColor = color1;
                } else {
                    mStatusBarColor = color1;
                }

                //修改状态和颜色
                StatusBarUtil.setColorForDrawerLayout(activity, mDrawerLayout, mStatusBarColor, mAlpha);
            }
        });

        //设置状态栏透明度
        sb_change_alpha.setMax(255);
        sb_change_alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                //设置透明度
                StatusBarUtil.setTranslucentForDrawerLayout(activity, mDrawerLayout, mAlpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_change_alpha.setProgress(StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    @Override
    protected void setStatusBar() {
        mStatusBarColor = ContextCompat.getColor(this, R.color.colorPrimary);
        //使用以下两方法，会使滑出的抽屉布局填充到状态栏中去
        //设置透明度和颜色
        StatusBarUtil.setColorForDrawerLayout(this,
                (DrawerLayout) findViewById(R.id.drawer_layout), mStatusBarColor, mAlpha);
        //设置透明度
        //StatusBarUtil.setTranslucentForDrawerLayout(this, (DrawerLayout) findViewById(R.id.drawer_layout), mAlpha);
    }
}
