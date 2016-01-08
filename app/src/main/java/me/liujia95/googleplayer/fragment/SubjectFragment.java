package me.liujia95.googleplayer.fragment;

import android.view.View;
import android.widget.ListView;

import java.util.List;

import me.liujia95.googleplayer.R;
import me.liujia95.googleplayer.adapter.SuperBaseAdapter;
import me.liujia95.googleplayer.adapter.viewholder.BaseHolder;
import me.liujia95.googleplayer.adapter.viewholder.SubjectHolder;
import me.liujia95.googleplayer.base.BaseFragment;
import me.liujia95.googleplayer.bean.SubjectBean;
import me.liujia95.googleplayer.protocol.SubjectProtocol;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/7 18:07.
 */
public class SubjectFragment extends BaseFragment {

    private SubjectProtocol   mProtocol;
    private List<SubjectBean> mDatas;

    @Override
    protected LoadingUI.ResultState onStartLoadData() {
        mProtocol = new SubjectProtocol();

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
        ListView listView = new ListView(UIUtils.getContext());
        listView.setBackgroundResource(R.color.bg);
        listView.setAdapter(new SubjectAdapter(mDatas));
        return listView;
    }

    private class SubjectAdapter extends SuperBaseAdapter<SubjectBean> {

        public SubjectAdapter(List<SubjectBean> datas) {
            super(datas);
        }

        @Override
        protected BaseHolder<SubjectBean> getItemHolder(int position) {
            return new SubjectHolder();
        }

        @Override
        public List<SubjectBean> onLoadMoreData() throws Exception {
            return mProtocol.loadData(mDatas.size());
        }
    }
}
