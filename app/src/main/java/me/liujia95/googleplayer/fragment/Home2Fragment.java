package me.liujia95.googleplayer.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.liujia95.googleplayer.adapter.HomeAdapter;
import me.liujia95.googleplayer.base.BaseFragment;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/7 0:08.
 */
public class Home2Fragment extends BaseFragment {

    @Override
    protected LoadingUI.ResultState onStartLoadData() {

        return LoadingUI.ResultState.SUCCESS;
    }

    @Override
    protected View onInitSuccessView() {
        RecyclerView recyclerView = new RecyclerView(UIUtils.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
        recyclerView.setAdapter(new HomeAdapter(createDatas()));
        return recyclerView;
    }

    private List<String> createDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("" + i);
        }
        return datas;
    }
}
