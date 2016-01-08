package me.liujia95.googleplayer.adapter.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import me.liujia95.googleplayer.R;
import me.liujia95.googleplayer.bean.CategoryBean;
import me.liujia95.googleplayer.utils.Constants;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/8 11:39.
 */
public class CategoryItemHolder extends BaseHolder<CategoryBean> {
    private LinearLayout mItem1;
    private ImageView    mIcon1;
    private TextView     mName1;
    private LinearLayout mItem2;
    private ImageView    mIcon2;
    private TextView     mName2;
    private LinearLayout mItem3;
    private ImageView    mIcon3;
    private TextView     mName3;
    private BitmapUtils  mBitmapUtils;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_category, null);
        mItem1 = (LinearLayout) view.findViewById(R.id.item_category_item_1);
        mIcon1 = (ImageView) view.findViewById(R.id.item_category_icon_1);
        mName1 = (TextView) view.findViewById(R.id.item_category_name_1);
        mItem2 = (LinearLayout) view.findViewById(R.id.item_category_item_2);
        mIcon2 = (ImageView) view.findViewById(R.id.item_category_icon_2);
        mName2 = (TextView) view.findViewById(R.id.item_category_name_2);
        mItem3 = (LinearLayout) view.findViewById(R.id.item_category_item_3);
        mIcon3 = (ImageView) view.findViewById(R.id.item_category_icon_3);
        mName3 = (TextView) view.findViewById(R.id.item_category_name_3);

        mBitmapUtils = new BitmapUtils(UIUtils.getContext());

        return view;
    }

    @Override
    protected void refreshUI(CategoryBean data) {
        mName1.setText(data.name1);
        mName2.setText(data.name2);
        mName3.setText(data.name3);

        display(mIcon1, mItem1, data.icon1);
        display(mIcon2, mItem2, data.icon2);
        display(mIcon3, mItem3, data.icon3);
    }

    private void display(ImageView iv, ViewGroup container, String url) {
        if (TextUtils.isEmpty(url)) {
            container.setVisibility(View.INVISIBLE);
        } else {
            container.setVisibility(View.VISIBLE);
            mBitmapUtils.display(iv, Constants.BASE_IMAGE + url);
        }
    }
}
