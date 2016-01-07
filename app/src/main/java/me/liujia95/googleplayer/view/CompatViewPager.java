package me.liujia95.googleplayer.view;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/1/7 16:30.
 */
public class CompatViewPager extends ViewPager {

    public CompatViewPager(Context context) {
        super(context);
    }

    public CompatViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        //3.0以下请求父容器不要拦截
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        return super.dispatchTouchEvent(ev);
    }
}
