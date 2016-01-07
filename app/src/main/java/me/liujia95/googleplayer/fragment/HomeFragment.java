package me.liujia95.googleplayer.fragment;

import android.view.View;
import android.widget.ListView;

import java.util.List;

import me.liujia95.googleplayer.R;
import me.liujia95.googleplayer.adapter.SuperBaseAdapter;
import me.liujia95.googleplayer.adapter.viewholder.AppItemHolder;
import me.liujia95.googleplayer.adapter.viewholder.BaseHolder;
import me.liujia95.googleplayer.adapter.viewholder.HomeTopHolder;
import me.liujia95.googleplayer.base.BaseFragment;
import me.liujia95.googleplayer.bean.AppInfoBean;
import me.liujia95.googleplayer.bean.HomeBean;
import me.liujia95.googleplayer.protocol.HomeProtocol;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/5 23:28.
 */
public class HomeFragment extends BaseFragment {

    private List<AppInfoBean> mDatas;
    private List<String>      mPictures;
    private HomeProtocol mProtocol;

    //这个方法本身就是在子线程
    @Override
    protected LoadingUI.ResultState onStartLoadData() {
        mProtocol = new HomeProtocol();
        try {
            HomeBean bean = mProtocol.loadData(0);

            if (bean == null) {
                return LoadingUI.ResultState.EMPTY;
            } else if (bean.list == null || bean.list.size() == 0) {
                return LoadingUI.ResultState.EMPTY;
            } else if (bean.picture == null || bean.picture.size() == 0) {
                return LoadingUI.ResultState.EMPTY;
            }

            //获得数据
            mDatas = bean.list;
            mPictures = bean.picture;

            return LoadingUI.ResultState.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingUI.ResultState.ERROR;
        }
    }

    @Override
    protected View onInitSuccessView() {
        ListView mListView = new ListView(UIUtils.getContext());
        mListView.setBackgroundResource(R.color.bg);

        HomeTopHolder topHolder = new HomeTopHolder();
        mListView.addHeaderView(topHolder.getRootView());
        topHolder.setData(mPictures);

        mListView.setAdapter(new HomeAdapter(mDatas));
        return mListView;
    }

    private class HomeAdapter extends SuperBaseAdapter<AppInfoBean> {

        public HomeAdapter(List<AppInfoBean> datas) {
            super(datas);
        }

        @Override
        protected BaseHolder<AppInfoBean> getItemHolder() {
            return new AppItemHolder();
        }

        @Override
        public List<AppInfoBean> onLoadMoreData() throws Exception {

            return loadMore(mDatas.size());
        }
    }

    private List<AppInfoBean> loadMore(int index) throws Exception {

        HomeBean bean = mProtocol.loadData(index);

        if (bean == null) {
            return null;
        }

        return bean.list;
    }
}
