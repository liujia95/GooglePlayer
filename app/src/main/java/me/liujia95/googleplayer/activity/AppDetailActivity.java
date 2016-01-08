package me.liujia95.googleplayer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.liujia95.googleplayer.bean.AppInfoBean;
import me.liujia95.googleplayer.fragment.LoadingUI;
import me.liujia95.googleplayer.protocol.AppDetailProtocol;

/**
 * Created by Administrator on 2016/1/8 16:06.
 */
public class AppDetailActivity extends Activity {

    private AppInfoBean       mAppInfoBean;
    private AppDetailProtocol mProtocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoadingUI loadingUI = new LoadingUI(this) {
            @Override
            protected ResultState onLoadData() {
                return startLoadData();
            }

            @Override
            protected View onLoadSuccessView() {
                return startLoadSuccessView();
            }
        };
        setContentView(loadingUI);

        loadingUI.loadData();
    }

    private View startLoadSuccessView() {
        TextView tv = new TextView(this);
        tv.setText(mAppInfoBean.des);
        return tv;
    }

    private LoadingUI.ResultState startLoadData() {
        mProtocol = new AppDetailProtocol("com.besttone.hall");
        try {
            mAppInfoBean = mProtocol.loadData(0);
            if (mAppInfoBean == null) {
                return LoadingUI.ResultState.EMPTY;
            }
            return LoadingUI.ResultState.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingUI.ResultState.ERROR;
        }
    }
}
