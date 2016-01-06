package me.liujia95.googleplayer.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import me.liujia95.googleplayer.adapter.viewholder.BaseHolder;
import me.liujia95.googleplayer.adapter.viewholder.LoadMoreHolder;
import me.liujia95.googleplayer.manager.ThreadPoolManager;
import me.liujia95.googleplayer.utils.Constants;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/6 11:17.
 */
public abstract class SuperBaseAdapter<T> extends BaseAdapter {

    protected List<T> mDatas;

    private static final int TYPE_LOADMORE = 0;
    private static final int TYPE_NORMAL   = 1;
    private LoadMoreHolder mLoadMoreHolder;

    public SuperBaseAdapter(List<T> datas) {
        mDatas = datas;
    }

    @Override
    public int getCount() {
        if (mDatas != null) {
            return mDatas.size() + 1;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mDatas != null) {
            return mDatas.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return TYPE_LOADMORE;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder viewholder = null;
        int viewType = getItemViewType(position);
        if (convertView == null) {

            if (viewType == TYPE_NORMAL) {
                viewholder = getItemHolder();
            } else if (viewType == TYPE_LOADMORE) {
                viewholder = getLoadMoreHolder();
            }

            convertView = viewholder.getRootView();
            convertView.setTag(viewholder);

        } else {
            viewholder = (BaseHolder) convertView.getTag();
        }
        //设置数据
        if (viewType == TYPE_NORMAL) {
            T datas = mDatas.get(position);
            viewholder.setData(datas);
        } else {
            if (hasLoadMore()) {
                //加载更多操作
                perfromLoadMore();
            } else {
                viewholder.setData(LoadMoreHolder.STATE_EMPTY);
            }
        }
        return convertView;
    }

    /**
     * 执行加载更多操作
     */
    private void perfromLoadMore() {
        //改变状态
        mLoadMoreHolder.setData(LoadMoreHolder.STATE_LOADING);

        //加载数据
        ThreadPoolManager.getLongPool().execute(new LoadMoreTask());

    }

    /**
     * 加载更多数据的任务
     */
    class LoadMoreTask implements Runnable {
        @Override
        public void run() {
            int state = LoadMoreHolder.STATE_LOADING;
            List<T> datas = null;
            try {
                datas = onLoadMoreData();
                if (datas == null || datas.size() == 0) {
                    state = LoadMoreHolder.STATE_EMPTY;
                } else {
                    //如果加载的数据大于等于一页的数量，则说明还有数据，显示加载更多，
                    //如果少于一页的数量，说明此次加载完了，不显示加载更多
                    if (datas.size() >= Constants.PAGER_SIZE) {
                        state = LoadMoreHolder.STATE_LOADING;
                    } else {
                        state = LoadMoreHolder.STATE_EMPTY;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                state = LoadMoreHolder.STATE_ERROR;
            }

            final int currentState = state;
            final List<T> finalDatas = datas;

            UIUtils.post(new Runnable() {
                @Override
                public void run() {
                    mLoadMoreHolder.setData(currentState);
                    mDatas.addAll(finalDatas);
                    notifyDataSetChanged();
                }
            });
        }
    }

    /**
     * 加载更多数据，具体什么类型的数据，需要子类复写
     *
     * @return
     */
    private List<T> onLoadMoreData() throws Exception {
        return null;
    }

    /**
     * 此界面是否有加载更多，默认有，如果希望没有，子类复写此方法即可
     *
     * @return
     */
    private boolean hasLoadMore() {
        return true;
    }

    protected abstract BaseHolder<T> getItemHolder();

    /**
     * 加载更多
     *
     * @return
     */
    public BaseHolder getLoadMoreHolder() {
        if (mLoadMoreHolder == null) {
            mLoadMoreHolder = new LoadMoreHolder();
        }
        return mLoadMoreHolder;
    }
}
