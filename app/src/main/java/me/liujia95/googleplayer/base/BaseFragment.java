package me.liujia95.googleplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import me.liujia95.googleplayer.fragment.LoadingUI;
import me.liujia95.googleplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/6 8:36.
 */
public abstract class BaseFragment extends Fragment {

    private LoadingUI mLoadingUI;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLoadingUI == null) {
            mLoadingUI = new LoadingUI(UIUtils.getContext()) {
                @Override
                protected ResultState onLoadData() {
                    return onStartLoadData();
                }

                @Override
                protected View onLoadSuccessView() {
                    return onInitSuccessView();
                }
            };
        } else {
            //这么操作是为了避免only one parent错误
            ViewParent parent = mLoadingUI.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(mLoadingUI);
            }
        }
        return mLoadingUI;

    }

    protected abstract LoadingUI.ResultState onStartLoadData();

    protected abstract View onInitSuccessView();

    public void loadData(){
        if(mLoadingUI!=null){
            mLoadingUI.loadData();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLoadingUI.loadData();
    }
}
