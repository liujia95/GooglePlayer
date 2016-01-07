package me.liujia95.googleplayer.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import me.liujia95.googleplayer.R;
import me.liujia95.googleplayer.bean.SubjectBean;
import me.liujia95.googleplayer.utils.Constants;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/7 18:28.
 */
public class SubjectHolder extends BaseHolder<SubjectBean> {
    public  ImageView   mIvIcon;
    public  TextView    mTvTitle;
    private BitmapUtils mBitmapUtils;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_subject, null);
        mIvIcon = (ImageView) view.findViewById(R.id.item_subject_iv_icon);
        mTvTitle = (TextView) view.findViewById(R.id.item_subject_tv_title);
        mBitmapUtils = new BitmapUtils(UIUtils.getContext());
        return view;
    }

    @Override
    protected void refreshUI(SubjectBean data) {
        String url = Constants.BASE_IMAGE + data.url;
        mBitmapUtils.display(mIvIcon, url);
        mTvTitle.setText(data.des);
    }
}
