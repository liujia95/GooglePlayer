package me.liujia95.googleplayer.adapter.viewholder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import me.liujia95.googleplayer.R;
import me.liujia95.googleplayer.bean.AppInfoBean;
import me.liujia95.googleplayer.utils.Constants;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/6 13:19.
 */
public class AppItemHolder extends BaseHolder<AppInfoBean> {

    private TextView  mTvTitle;
    private TextView  mTvSize;
    private TextView  mTvDes;
    private ImageView mIvIcon;
    private RatingBar mRbStars;
    private BitmapUtils mBitmapUtils;


    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_app_info, null);
        mIvIcon = (ImageView) view.findViewById(R.id.item_appinfo_iv_icon);
        mTvTitle = (TextView) view.findViewById(R.id.item_appinfo_tv_title);
        mRbStars = (RatingBar) view.findViewById(R.id.item_appinfo_rb_stars);
        mTvSize = (TextView) view.findViewById(R.id.item_appinfo_tv_size);
        mTvDes = (TextView) view.findViewById(R.id.item_appinfo_tv_des);

        mBitmapUtils = new BitmapUtils(UIUtils.getContext());

        return view;
    }

    @Override
    protected void refreshUI(AppInfoBean data) {
        mTvTitle.setText(data.name);
        mTvDes.setText(data.des);
        mTvSize.setText(Formatter.formatFileSize(UIUtils.getContext(), data.size));
        mRbStars.setRating(data.stars);

        //设置默认的图片
        mIvIcon.setImageResource(R.drawable.ic_default);

        String url = Constants.BASE_IMAGE+data.iconUrl;
        mBitmapUtils.display(mIvIcon,url);
    }
}
