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
import me.liujia95.googleplayer.animation.ZoomOutPageTransformer;
import me.liujia95.googleplayer.base.BaseFragment;
import me.liujia95.googleplayer.fragment.AppFragment;
import me.liujia95.googleplayer.fragment.CategoryFragment;
import me.liujia95.googleplayer.fragment.GameFragment;
import me.liujia95.googleplayer.fragment.HomeFragment;
import me.liujia95.googleplayer.fragment.HotFragment;
import me.liujia95.googleplayer.fragment.RecommendFragment;
import me.liujia95.googleplayer.fragment.SubjectFragment;
import me.liujia95.googleplayer.utils.LogUtils;
import me.liujia95.googleplayer.utils.UIUtils;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout          mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar               mToolBar;
    private ViewPager             mViewpager;
    private List<BaseFragment>    mFragments;
    private TabLayout             mTabLayout;
    private String[]              mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initListener();
        initAnimation();
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
     * 初始化动画
     */
    private void initAnimation() {
        mViewpager.setPageTransformer(true, new ZoomOutPageTransformer());
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
        //mToolBar.setNavigationIcon(R.drawable.ic_drawer_am);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new AppFragment());
        mFragments.add(new GameFragment());
        mFragments.add(new SubjectFragment());
        mFragments.add(new RecommendFragment());
        mFragments.add(new CategoryFragment());
        mFragments.add(new HotFragment());

        //获取tabs的title
        mTitles = UIUtils.getStringArray(R.array.pagers);

        //设置适配器，tablayout和viewpager的绑定
        mViewpager.setAdapter(new HomeFragmentAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewpager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

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
            return mTitles[position];
        }
    }
}
