package me.liujia95.googleplayer.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.liujia95.googleplayer.R;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/6 23:48.
 */
public class LoadMore2Holder extends RecyclerView.ViewHolder {

    private static LinearLayout mContainerLoading;
    private static LinearLayout mContainerRetry;
    private static TextView     mTvRetry;

    public static final int STATE_ERROR   = 0;//加载失败
    public static final int STATE_LOADING = 1;//正在加载中
    public static final int STATE_EMPTY   = 2;//加载为空

    public LoadMore2Holder(View itemView) {
        super(itemView);
    }

    public static LoadMore2Holder newInstance() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_load_more, null);
        mContainerLoading = (LinearLayout) view.findViewById(R.id.item_loadmore_container_loading);
        mContainerRetry = (LinearLayout) view.findViewById(R.id.item_loadmore_container_retry);
        mTvRetry = (TextView) view.findViewById(R.id.item_loadmore_tv_retry);
        return new LoadMore2Holder(view);
    }

    public void refreshUI(Integer data) {
        switch (data) {
            case STATE_ERROR:
                mContainerRetry.setVisibility(View.VISIBLE);
                mContainerLoading.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
                mContainerRetry.setVisibility(View.GONE);
                mContainerLoading.setVisibility(View.VISIBLE);
                break;
            case STATE_EMPTY:
                mContainerRetry.setVisibility(View.GONE);
                mContainerLoading.setVisibility(View.GONE);
                break;
        }
    }
}
