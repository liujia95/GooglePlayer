package me.liujia95.googleplayer.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import me.liujia95.googleplayer.R;
import me.liujia95.googleplayer.base.BaseFragment;
import me.liujia95.googleplayer.fragment.HomeFragment;
import me.liujia95.googleplayer.utils.LogUtils;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout          mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar               mToolBar;
    private ViewPager             mViewpager;
    private List<BaseFragment>    mFragments;
    private TabLayout             mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initListener();
        initToolBar();
        initData();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.home_drawerlayout);
        mToolBar = (Toolbar) findViewById(R.id.home_toolbar);
        mViewpager = (ViewPager) findViewById(R.id.home_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.home_tablayout);
    }

    private void initListener() {
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BaseFragment fragment = mFragments.get(position);
                fragment.loadData();
                LogUtils.d(position + ")加载中......");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化actionbar
     */
    private void initToolBar() {
        //actionbar设置
        setSupportActionBar(mToolBar);
        mToolBar.setTitle("GooglePlay");
        mToolBar.setLogo(R.drawable.ic_launcher);

        //创建开关
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_drawer_am);
        mDrawerToggle.syncState();

        //一定要放在syncState方法之后，syncState方法调用时会将图标重置
        mToolBar.setNavigationIcon(R.drawable.ic_drawer_am);
        mToolBar.setNavigationContentDescription("aaa");
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mFragments.add(new HomeFragment());
        }

        //设置适配器，tablayout和viewpager的绑定
        mViewpager.setAdapter(new HomeFragmentAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewpager);

        //因为viewpager初始化是不会走onPageSelected事件，要手动让它加载一次
        mViewpager.setCurrentItem(1);
        mViewpager.setCurrentItem(0);
    }

    private class HomeFragmentAdapter extends FragmentPagerAdapter {

        public HomeFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "title";
        }
    }
}
