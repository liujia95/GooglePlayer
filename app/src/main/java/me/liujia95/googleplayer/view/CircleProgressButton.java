package me.liujia95.googleplayer.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.liujia95.googleplayer.R;

public class CircleProgressButton extends LinearLayout {

    private boolean mProgressEnable;
    private int     mProgress;
    private int     mMax;

    private Paint mPaint = new Paint();
    private RectF mOval;

    private ImageView mIv;
    private TextView  mTv;

    private float mStrokeWidth = 2;
    private int   mStrokeColor = Color.BLACK;

    public CircleProgressButton(Context context) {
        this(context, null);
    }

    public CircleProgressButton(Context context, AttributeSet set) {
        super(context, set);

        // 获得属性
        TypedArray ta = context.obtainStyledAttributes(set, R.styleable.CircleProgressButton);

        mStrokeWidth = ta.getFloat(R.styleable.CircleProgressButton_strokeWidth, mStrokeWidth);
        mStrokeColor = ta.getColor(R.styleable.CircleProgressButton_strokeColor, mStrokeColor);
        ta.recycle();

        // 挂载布局
        View.inflate(context, R.layout.view_circle, this);

        mIv = (ImageView) findViewById(R.id.iv);
        mTv = (TextView) findViewById(R.id.tv);
    }

    public void setProgressEnable(boolean enable) {
        this.mProgressEnable = enable;
    }

    public void setProgress(int progress) {
        this.mProgress = progress;
        invalidate();
    }

    public void setProgressText(String text) {
        mTv.setText(text);
    }

    public void setProgressIcon(int resId) {
        mIv.setImageResource(resId);
    }

    public void setMax(int max) {
        this.mMax = max;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mProgressEnable) {
            // 画扇形

            if (mOval == null) {
                float left = mIv.getLeft();
                float top = mIv.getTop();
                float right = mIv.getRight();
                float bottom = mIv.getBottom();

                mOval = new RectF(left, top, right, bottom);
            }

            float startAngle = -90;// 开始的角度

            if (mMax == 0) {
                mMax = 100;
            }

            float sweepAngle = 360 * mProgress * 1f / mMax;// 扫过的角度
            boolean useCenter = false;

            mPaint.setAntiAlias(true);// 设置抗锯齿
            mPaint.setStyle(Style.STROKE);
            mPaint.setStrokeWidth(mStrokeWidth);
            mPaint.setColor(mStrokeColor);

            canvas.drawArc(mOval, startAngle, sweepAngle, useCenter, mPaint);
        }
    }
}
