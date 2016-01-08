package me.liujia95.googleplayer.fragment;

import android.view.View;
import android.widget.ListView;

import java.util.List;

import me.liujia95.googleplayer.R;
import me.liujia95.googleplayer.adapter.SuperBaseAdapter;
import me.liujia95.googleplayer.adapter.viewholder.BaseHolder;
import me.liujia95.googleplayer.adapter.viewholder.CategoryItemHolder;
import me.liujia95.googleplayer.adapter.viewholder.CategoryTitleHolder;
import me.liujia95.googleplayer.base.BaseFragment;
import me.liujia95.googleplayer.bean.CategoryBean;
import me.liujia95.googleplayer.protocol.CategoryProtocol;
import me.liujia95.googleplayer.utils.LogUtils;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/8 11:17.
 */
public class CategoryFragment extends BaseFragment {

    private CategoryProtocol   mProtocol;
    private List<CategoryBean> mDatas;

    @Override
    protected LoadingUI.ResultState onStartLoadData() {
        mProtocol = new CategoryProtocol();
        try {
            mDatas = mProtocol.loadData(0);
            LogUtils.d("size-----------------------" + mDatas.size());
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
        ListView listview = new ListView(UIUtils.getContext());
        listview.setBackgroundResource(R.color.bg);
        listview.setAdapter(new CategoryAdapter(mDatas));
        listview.setPadding(UIUtils.dp2px(4),0, UIUtils.dp2px(4),0);
        return listview;
    }

    private class CategoryAdapter extends SuperBaseAdapter<CategoryBean> {

        public CategoryAdapter(List<CategoryBean> datas) {
            super(datas);
        }

        @Override
        protected BaseHolder<CategoryBean> getItemHolder(int position) {
            CategoryBean bean = mDatas.get(position);
            if (bean.type == CategoryBean.TYPE_ITEM) {
                return new CategoryItemHolder();
            } else if (bean.type == CategoryBean.TYPE_TITLE) {
                return new CategoryTitleHolder();
            }
            throw new RuntimeException("CategoryBean类型异常");
        }

        @Override
        protected boolean hasLoadMore() {
            return false;
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 1;
        }

        @Override
        protected int getNormalViewType(int position) {
            CategoryBean bean = mDatas.get(position);
            if (bean.type == CategoryBean.TYPE_ITEM) {
                return super.getNormalViewType(position);
            } else {
                return super.getNormalViewType(position) + 1;
            }
        }
    }
}
