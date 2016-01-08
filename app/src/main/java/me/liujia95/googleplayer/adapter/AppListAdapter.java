package me.liujia95.googleplayer.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import me.liujia95.googleplayer.activity.AppDetailActivity;
import me.liujia95.googleplayer.adapter.viewholder.AppItemHolder;
import me.liujia95.googleplayer.adapter.viewholder.BaseHolder;
import me.liujia95.googleplayer.bean.AppInfoBean;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/8 16:02.
 */
public class AppListAdapter extends SuperBaseAdapter<AppInfoBean> implements AdapterView.OnItemClickListener {
    ListView mListView = new ListView(UIUtils.getContext());

    public AppListAdapter(List<AppInfoBean> datas, ListView listview) {
        super(datas);
        this.mListView = listview;
        //设置item点击事件
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected BaseHolder<AppInfoBean> getItemHolder(int position) {
        return new AppItemHolder();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(UIUtils.getContext(), AppDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        UIUtils.getContext().startActivity(intent);
    }
}
