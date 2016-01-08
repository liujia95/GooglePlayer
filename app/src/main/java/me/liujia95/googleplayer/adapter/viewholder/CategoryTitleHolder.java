package me.liujia95.googleplayer.adapter.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import me.liujia95.googleplayer.bean.CategoryBean;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/8 11:39.
 */
public class CategoryTitleHolder extends BaseHolder<CategoryBean> {

    private TextView mTv;

    @Override
    protected View initView() {
        mTv = new TextView(UIUtils.getContext());
        mTv.setBackgroundColor(Color.WHITE);
        mTv.setTextSize(18);
        mTv.setTextColor(Color.GRAY);
        mTv.setPadding(UIUtils.dp2px(8), UIUtils.dp2px(8), UIUtils.dp2px(8), UIUtils.dp2px(8));
        return mTv;
    }

    @Override
    protected void refreshUI(CategoryBean data) {
        mTv.setText(data.title);
    }
}
