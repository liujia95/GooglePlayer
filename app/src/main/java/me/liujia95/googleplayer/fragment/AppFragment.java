package me.liujia95.googleplayer.fragment;

import android.view.View;
import android.widget.ListView;

import java.util.List;

import me.liujia95.googleplayer.R;
import me.liujia95.googleplayer.adapter.AppListAdapter;
import me.liujia95.googleplayer.base.BaseFragment;
import me.liujia95.googleplayer.bean.AppInfoBean;
import me.liujia95.googleplayer.protocol.AppProtocol;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/7 13:31.
 */
public class AppFragment extends BaseFragment {

    private AppProtocol       mProtocol;
    private List<AppInfoBean> mDatas;

    @Override
    protected LoadingUI.ResultState onStartLoadData() {
        mProtocol = new AppProtocol();
        try {
            mDatas = mProtocol.loadData(0);
            if (mDatas == null || mDatas.size() == 0) {
                return LoadingUI.ResultState.EMPTY;
            }
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
        mListView.setAdapter(new AppAdapter(mDatas, mListView));
        return mListView;
    }

    private class AppAdapter extends AppListAdapter {

        public AppAdapter(List<AppInfoBean> datas, ListView listview) {
            super(datas, listview);
        }

        @Override
        public List<AppInfoBean> onLoadMoreData() throws Exception {
            return mProtocol.loadData(mDatas.size());
        }
    }
}
