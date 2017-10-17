package com.huayi.statusbarutildemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.Random;

/**
 * 在Fragment中使用
 */
public class Btn04Activity extends BaseActivity {

    private ViewPager mVpHome;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn04);
        mVpHome = (ViewPager) findViewById(R.id.vp_home);

        initFragment();

        //设置PageChange监听
        mVpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        break;
                    default:
                        Random random = new Random();
                        int color = 0xff000000 | random.nextInt(0xffffff);
                        if (mFragmentList.get(position) instanceof SimpleFragment) {
                            ((SimpleFragment) mFragmentList.get(position)).setTvTitleBackgroundColor(color);
                        }
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置适配器
        mVpHome.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });

        mVpHome.setCurrentItem(0);

    }

    private void initFragment() {
        mFragmentList.add(new ImageFragment());
        mFragmentList.add(new SimpleFragment());
        mFragmentList.add(new SimpleFragment());
        mFragmentList.add(new SimpleFragment());
    }

    //展示第1个Fragment
    public void show001(View view) {
        mVpHome.setCurrentItem(0);
    }

    //展示第2个Fragment
    public void show002(View view) {
        mVpHome.setCurrentItem(1);
    }

    //展示第3个Fragment
    public void show003(View view) {
        mVpHome.setCurrentItem(2);
    }

    //展示第4个Fragment
    public void show004(View view) {
        mVpHome.setCurrentItem(3);
    }

    @Override
    protected void setStatusBar() {
        //使用了以下方法，界面内容会填充到状态栏中去
        //为 fragment 头部是 ImageView 的设置状态栏透明
        //参数：fragment 对应的 activity，需要向下偏移的 View
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);//使用默认透明度112
        //为 fragment 头部是 ImageView 的设置状态栏透明
        //参数：fragment 对应的 activity，状态栏透明度，需要向下偏移的 View(needOffsetView)
        //StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
    }
}
