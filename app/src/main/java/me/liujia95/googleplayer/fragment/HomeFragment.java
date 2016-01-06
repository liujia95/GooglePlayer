package me.liujia95.googleplayer.fragment;

import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

import me.liujia95.googleplayer.R;
import me.liujia95.googleplayer.adapter.SuperBaseAdapter;
import me.liujia95.googleplayer.adapter.viewholder.AppItemHolder;
import me.liujia95.googleplayer.adapter.viewholder.BaseHolder;
import me.liujia95.googleplayer.base.BaseFragment;
import me.liujia95.googleplayer.bean.AppInfoBean;
import me.liujia95.googleplayer.bean.HomeBean;
import me.liujia95.googleplayer.utils.Constants;
import me.liujia95.googleplayer.utils.LogUtils;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/5 23:28.
 */
public class HomeFragment extends BaseFragment {

    private List<AppInfoBean> mDatas;
    private List<String>      mPictures;

    //这个方法本身就是在子线程
    @Override
    protected LoadingUI.ResultState onStartLoadData() {
        HttpUtils httpUtils = new HttpUtils();
        String url = Constants.BASE_SERVER + "home";

        RequestParams params = new RequestParams();
        params.addQueryStringParameter("index", "" + 0);
        try {
            ResponseStream responseStream = httpUtils.sendSync(HttpRequest.HttpMethod.GET, url, params);
            String json = responseStream.readString();
            LogUtils.d("json:" + json);

            //解析json
            Gson gson = new Gson();
            HomeBean bean = gson.fromJson(json, HomeBean.class);

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
        mListView.setAdapter(new HomeAdapter(mDatas));
        mListView.setBackgroundResource(R.color.bg);
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
    }
}
