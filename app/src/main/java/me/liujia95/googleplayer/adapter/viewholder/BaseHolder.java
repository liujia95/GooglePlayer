package me.liujia95.googleplayer.adapter.viewholder;

import android.view.View;

/**
 * Created by Administrator on 2016/1/6 12:20.
 */
public abstract class BaseHolder<T> {

    private View mRootView;
    private T    mData;

    public BaseHolder() {
        mRootView = initView();
    }

    public View getRootView() {
        return mRootView;
    }

    public void setData(T data) {
        mData = data;
        refreshUI(data);
    }

    protected abstract View initView();

    protected abstract void refreshUI(T data);
}
