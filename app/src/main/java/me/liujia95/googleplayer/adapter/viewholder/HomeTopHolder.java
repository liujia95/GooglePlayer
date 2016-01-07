package me.liujia95.googleplayer.adapter.viewholder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import me.liujia95.googleplayer.R;
import me.liujia95.googleplayer.animation.DepthPageTransformer;
import me.liujia95.googleplayer.utils.Constants;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/7 14:25.
 */
public class HomeTopHolder extends BaseHolder<List<String>> {
    LinearLayout mContainerIndicator;
    ViewPager    mViewPager;
    private BitmapUtils          mBitmapUtils;
    private List<String>         mDatas;
    private AutoBroadcastPicTask mAutoTask;


    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_home_picture, null);
        mViewPager = (ViewPager) view.findViewById(R.id.item_home_picture_pager);
        mContainerIndicator = (LinearLayout) view.findViewById(R.id.item_home_picture_container_indicator);

        //bitmap初始化工具类
        mBitmapUtils = new BitmapUtils(UIUtils.getContext());

        initListener(); //初始化监听
        initAnimation();//初始化动画

        return view;
    }

    @Override
    protected void refreshUI(List<String> data) {
        mDatas = data;

        mViewPager.setAdapter(new HomeTopAdapter());

        //给轮播图加载点
        for (int i = 0; i < data.size(); i++) {
            ImageView point = new ImageView(UIUtils.getContext());
            if (i == 0) {
                point.setImageResource(R.drawable.indicator_selected);
            } else {
                point.setImageResource(R.drawable.indicator_normal);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = UIUtils.dp2px(6);
            layoutParams.bottomMargin = UIUtils.dp2px(6);
            point.setLayoutParams(layoutParams);
            mContainerIndicator.addView(point);
        }

        //从中间开始轮播
        int centerCount = Integer.MAX_VALUE / 2;
        centerCount = centerCount - centerCount % mDatas.size();
        mViewPager.setCurrentItem(centerCount);

        //开始自动轮播
        if (mAutoTask == null) {
            mAutoTask = new AutoBroadcastPicTask();
        }
        mAutoTask.run();
    }

    /**
     * 添加动画
     */
    private void initAnimation() {
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
    }

    /**
     * 自动轮播任务
     */
    private class AutoBroadcastPicTask implements Runnable {

        @Override
        public void run() {
            int currentItem = mViewPager.getCurrentItem();
            mViewPager.setCurrentItem(++currentItem);

            UIUtils.postDelayed(this, 2000);
        }

        /**
         * 开始轮播
         */
        public void start() {
            stop();//开始之前先停止，以免重复开始
            UIUtils.postDelayed(this, 2000);
        }

        /**
         * 停止轮播
         */
        public void stop() {
            UIUtils.removeCallbacks(this);
        }
    }

    /**
     * 初始化监听器
     */
    private void initListener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % mDatas.size();

                selectPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mAutoTask.stop();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        mAutoTask.start();
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 根据角标设置选中的点
     *
     * @param index
     */
    private void selectPoint(int index) {
        for (int i = 0; i < mContainerIndicator.getChildCount(); i++) {
            ImageView point = (ImageView) mContainerIndicator.getChildAt(i);
            if (index == i) {
                point.setImageResource(R.drawable.indicator_selected);
            } else {
                point.setImageResource(R.drawable.indicator_normal);
            }
        }
    }

    private class HomeTopAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            position = position % mDatas.size();

            ImageView iv = new ImageView(UIUtils.getContext());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            String url = Constants.BASE_IMAGE + mDatas.get(position);
            mBitmapUtils.display(iv, url);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
