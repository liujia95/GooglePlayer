package me.liujia95.googleplayer.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.liujia95.googleplayer.R;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/6 23:24.
 */
public class HomeViewHolder extends RecyclerView.ViewHolder {
    private static TextView mTv1;
    private static TextView mTv2;


    public HomeViewHolder(View itemView) {
        super(itemView);
    }

    public static HomeViewHolder newInstance() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_tmp, null);
        mTv1 = (TextView) view.findViewById(R.id.tmp_tv_1);
        mTv2 = (TextView) view.findViewById(R.id.tmp_tv_2);
        return new HomeViewHolder(view);
    }

    public void setData(String data) {
        if (data != null) {
            mTv1.setText("主题：" + data);
            mTv2.setText("内容：" + data);
        }
    }
}
