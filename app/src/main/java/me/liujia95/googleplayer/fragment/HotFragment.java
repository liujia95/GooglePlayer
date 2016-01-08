package me.liujia95.googleplayer.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import me.liujia95.googleplayer.base.BaseFragment;
import me.liujia95.googleplayer.protocol.HotProtocol;
import me.liujia95.googleplayer.utils.UIUtils;
import me.liujia95.googleplayer.view.FlowLayout;

/**
 * Created by Administrator on 2016/1/8 9:48.
 */
public class HotFragment extends BaseFragment {

    private HotProtocol  mProtocol;
    private List<String> mDatas;

    @Override
    protected LoadingUI.ResultState onStartLoadData() {
        mProtocol = new HotProtocol();
        try {
            mDatas = mProtocol.loadData(0);
            if (mDatas == null || mDatas.size() == 0) {
                return LoadingUI.ResultState.EMPTY;
            }
            return LoadingUI.ResultState.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingUI.ResultState.EMPTY;
        }
    }

    @Override
    protected View onInitSuccessView() {
        ScrollView scrollView = new ScrollView(UIUtils.getContext());

        FlowLayout flowLayout = new FlowLayout(UIUtils.getContext());
        scrollView.addView(flowLayout);
        flowLayout.setPadding(UIUtils.dp2px(8), UIUtils.dp2px(8), UIUtils.dp2px(8), UIUtils.dp2px(8));
        flowLayout.setSpace(UIUtils.dp2px(8), UIUtils.dp2px(8));

        Random rd = new Random();
        for (int i = 0; i < mDatas.size(); i++) {
            TextView tv = new TextView(UIUtils.getContext());
            tv.setText(mDatas.get(i));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(14);
            tv.setPadding(UIUtils.dp2px(5), UIUtils.dp2px(5), UIUtils.dp2px(5), UIUtils.dp2px(5));

            GradientDrawable normal = new GradientDrawable();
            normal.setShape(GradientDrawable.RECTANGLE);
            normal.setCornerRadius(UIUtils.dp2px(8));

            int alpha = 255;
            int red = rd.nextInt(200) + 20;
            int green = rd.nextInt(200) + 20;
            int blue = rd.nextInt(200) + 20;
            int argb = Color.argb(alpha, red, green, blue);
            normal.setColor(argb);

            GradientDrawable pressed = new GradientDrawable();
            pressed.setShape(GradientDrawable.RECTANGLE);
            pressed.setCornerRadius(UIUtils.dp2px(8));
            pressed.setColor(Color.GRAY);

            StateListDrawable selector = new StateListDrawable();
            selector.addState(new int[]{android.R.attr.state_pressed}, pressed);
            selector.addState(new int[]{}, normal);

            tv.setBackgroundDrawable(selector);

            final int index = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), mDatas.get(index), Toast.LENGTH_SHORT).show();
                }
            });

            flowLayout.addView(tv);
        }

        return scrollView;
    }
}
