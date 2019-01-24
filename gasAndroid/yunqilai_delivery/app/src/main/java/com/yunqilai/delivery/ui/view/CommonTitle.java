package com.yunqilai.delivery.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunqilai.delivery.R;


/**
 * 通用的界面顶部标题栏
 * Created by KK on 2016/1/7.
 */
public class CommonTitle extends LinearLayout {

    private LinearLayout mLlLeft;
    private TextView mTvLeft;
    private TextView mTvTitle;
    private TextView mTvRight;
    private ImageView mIvRightIcon;
    private ImageView mIvLeftIcon;

    private OnTitleClickListener mOnTitleClickListener;

    public interface OnTitleClickListener {
        void onLeftClick();

        void onRightClick();
    }

    public CommonTitle(Context context) {
        this(context, null, 0);
    }

    public CommonTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.view_common_title, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonTitle);
        initView(typedArray);
        typedArray.recycle();
        initEvent();

    }

    private void initView(TypedArray typedArray) {
        mLlLeft = (LinearLayout) findViewById(R.id.ll_left);
        mTvLeft = (TextView) findViewById(R.id.tv_left);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvRight = (TextView) findViewById(R.id.tv_right);
        mIvRightIcon = (ImageView) findViewById(R.id.iv_right);
        mIvLeftIcon = (ImageView) findViewById(R.id.iv_left);

        String leftStr = typedArray.getString(R.styleable.CommonTitle_leftStr);
        String rightStr = typedArray.getString(R.styleable.CommonTitle_rightStr);
        String titleStr = typedArray.getString(R.styleable.CommonTitle_titleStr);
        int ivRightId = typedArray.getResourceId(R.styleable.CommonTitle_rightIcon, 0);
        int ivLeftId = typedArray.getResourceId(R.styleable.CommonTitle_leftIcon, 0);
        boolean showLeft = typedArray.getBoolean(R.styleable.CommonTitle_showLeft, false);
        boolean showLeftIcon = typedArray.getBoolean(R.styleable.CommonTitle_showLeftIcon, true);
        boolean showRight = typedArray.getBoolean(R.styleable.CommonTitle_showRight, false);
        boolean showRightIcon = typedArray.getBoolean(R.styleable.CommonTitle_showRightIcon, false);
        float textSize = typedArray.getDimension(R.styleable.CommonTitle_textSize, 0);
        int textColor = typedArray.getColor(R.styleable.CommonTitle_textColor, 0);

        mTvLeft.setText(leftStr);
        mTvTitle.setText(titleStr);
        mTvRight.setText(rightStr);
        if (ivLeftId != 0) {
            mIvLeftIcon.setImageResource(ivLeftId);
        }
        if (ivRightId != 0) {
            mIvRightIcon.setImageResource(ivRightId);
        }
        mIvLeftIcon.setVisibility(showLeftIcon ? View.VISIBLE : View.GONE);
        mTvLeft.setVisibility(showLeft ? View.VISIBLE : View.GONE);
        mTvRight.setVisibility(showRight ? View.VISIBLE : View.GONE);
        mIvRightIcon.setVisibility(showRightIcon ? View.VISIBLE : View.GONE);

        if (textSize != 0) {
            mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        if (textColor != 0) {
            mTvTitle.setTextColor(textColor);
            mTvLeft.setTextColor(textColor);
            mTvRight.setTextColor(textColor);
        }

    }

    private void initEvent() {
        mLlLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTitleClickListener != null) {
                    mOnTitleClickListener.onLeftClick();
                }
            }
        });

        mTvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTitleClickListener != null) {
                    mOnTitleClickListener.onRightClick();
                }
            }
        });
        mIvRightIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTitleClickListener != null) {
                    mOnTitleClickListener.onRightClick();
                }
            }
        });
    }

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.mOnTitleClickListener = onTitleClickListener;
    }


    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setTitle(@StringRes int titleStrId) {
        mTvTitle.setText(titleStrId);
    }

    public void setRightStr(String rightStr) {
        mTvRight.setText(rightStr);
    }

    public void setRightStr(@StringRes int resId) {
        mTvRight.setText(resId);
    }

    public void setRightIcon(@DrawableRes int rightIconId) {
        mIvRightIcon.setImageResource(rightIconId);
    }

    public void showRight(boolean isShow) {
        mTvRight.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void showRightIcon(boolean isShow) {
        mIvRightIcon.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void setRightEnable(boolean isEnable) {
        mTvRight.setEnabled(isEnable);
        mIvRightIcon.setEnabled(isEnable);
    }
}

