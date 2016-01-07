package me.liujia95.googleplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import me.liujia95.googleplayer.adapter.viewholder.HomeViewHolder;
import me.liujia95.googleplayer.adapter.viewholder.LoadMore2Holder;

/**
 * Created by Administrator on 2016/1/6 23:22.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NORMAL   = 0;
    private static final int TYPE_LOADMORE = 1;
    private final List<String> mDatas;

    public HomeAdapter(List<String> datas) {
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOADMORE) {
            return LoadMore2Holder.newInstance();
        } else if (viewType == TYPE_NORMAL) {
            return HomeViewHolder.newInstance();
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_NORMAL) {
            HomeViewHolder viewholder = (HomeViewHolder) holder;
            String data = mDatas.get(position);
            viewholder.setData(data);
        } else if (viewType == TYPE_LOADMORE) {
            LoadMore2Holder viewholder = (LoadMore2Holder) holder;
            //加载更多
            viewholder.refreshUI(LoadMore2Holder.STATE_LOADING);
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size() + 1;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() == position) {
            return TYPE_LOADMORE;
        }
        return TYPE_NORMAL;
    }
}
