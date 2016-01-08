package me.liujia95.googleplayer.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import me.liujia95.googleplayer.base.BaseFragment;
import me.liujia95.googleplayer.protocol.RecommendProtocol;
import me.liujia95.googleplayer.utils.UIUtils;
import me.liujia95.googleplayer.view.ShakeListener;
import me.liujia95.googleplayer.view.StellarMap;

/**
 * Created by Administrator on 2016/1/8 14:32.
 */
public class RecommendFragment extends BaseFragment {


    private RecommendProtocol mProtocol;
    private List<String>      mDatas;

    private Random mRandom = new Random();
    private ShakeListener mShake;
    private StellarMap    mSm;

    @Override
    protected LoadingUI.ResultState onStartLoadData() {
        mProtocol = new RecommendProtocol();
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
        mSm = new StellarMap(UIUtils.getContext());
        //设置分区，切的越碎，放的越精准
        mSm.setInnerPadding(UIUtils.dp2px(8), UIUtils.dp2px(8), UIUtils.dp2px(8), UIUtils.dp2px(8));
        mSm.setRegularity(15, 15);
        mSm.setAdapter(new RecommendAdapter());
        //设置第一个页面显示
        mSm.setGroup(0, true);

        //添加摇一摇的功能
        mShake = new ShakeListener(UIUtils.getContext());
        mShake.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                int currentGroup = mSm.getCurrentGroup();

                mSm.setGroup(++currentGroup, true);
            }
        });

        return mSm;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mShake != null) {
            mShake.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mShake != null) {
            mShake.pause();
        }
    }

    class RecommendAdapter implements StellarMap.Adapter {

        public static final int GROUP_PAGECOUNT = 15;

        @Override
        public int getGroupCount() {
            if (mDatas != null) {
                int groupCount = mDatas.size() / GROUP_PAGECOUNT;
                if (mDatas.size() % GROUP_PAGECOUNT > 0) {
                    groupCount++;
                }
                return groupCount;
            }
            return 0;
        }

        @Override
        public int getCount(int group) {
            if (mDatas != null) {
                if (mDatas.size() % GROUP_PAGECOUNT > 0 && group == getGroupCount()) {
                    return mDatas.size() % GROUP_PAGECOUNT;
                }
                return GROUP_PAGECOUNT;
            }
            return 0;
        }

        @Override
        public View getView(int group, int position, View convertView) {

            int location = group * GROUP_PAGECOUNT + position;
            final String data = mDatas.get(location);

            TextView tv = new TextView(UIUtils.getContext());

            int alpha = 255;
            int red = mRandom.nextInt(200) + 20;
            int green = mRandom.nextInt(200) + 20;
            int blue = mRandom.nextInt(200) + 20;
            int argb = Color.argb(alpha, red, green, blue);
            tv.setTextColor(argb);
            tv.setTextSize(mRandom.nextInt(14) + 12);
            tv.setText(data);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), data, Toast.LENGTH_SHORT).show();
                }
            });

            return tv;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return 0;
        }
    }
}
