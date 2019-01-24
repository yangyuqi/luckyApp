package com.yunqilai.delivery.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yunqilai.delivery.R;

/**
 * 通用的提示对话框
 */
public class TipDialog extends Dialog {

    private final String TAG = "TipDialog";

    private TextView titleTv;
    private TextView contentTv;
    private TextView negativeBtn;
    private TextView positiveBtn;

    private String mTitle;
    private String mPositive;
    private String mNegative;
    private String mContent;

    private OnTipDialogListener mOnTipDialogListener;

    private Context mContext;

    /**
     * TipDialog点击事件回调对象
     */
    public interface OnTipDialogListener {
        /**
         * 确认按钮点击事件回调方法
         */
        void onPositiveClick();

        /**
         * 取消按钮点击事件回调方法
         */
        void onNegativeClick();
    }

    public TipDialog(Context context, String title, OnTipDialogListener onTipDialogListener) {
        this(context);
        mContext = context;
        this.mTitle = title;
        this.mOnTipDialogListener = onTipDialogListener;
        init();
    }

    public TipDialog(Context context, String title, String content, OnTipDialogListener onTipDialogListener) {
        this(context);
        mContext = context;
        this.mTitle = title;
        this.mContent = content;
        this.mOnTipDialogListener = onTipDialogListener;
        init();
    }

    public TipDialog(Context context, String title, String positive, String negative, OnTipDialogListener onTipDialogListener) {
        this(context);
        mContext = context;
        this.mTitle = title;
        this.mPositive = positive;
        this.mNegative = negative;
        this.mOnTipDialogListener = onTipDialogListener;
        init();
    }

    public TipDialog(Context context, String title, String content, String positive, String negative, OnTipDialogListener onTipDialogListener) {
        this(context);
        mContext = context;
        this.mTitle = title;
        this.mContent = content;
        this.mPositive = positive;
        this.mNegative = negative;
        this.mOnTipDialogListener = onTipDialogListener;
        init();
    }

    private TipDialog(Context context) {
        super(context, R.style.style_dialog_tip);
    }

    public void setTitle(CharSequence string) {
        titleTv.setText(string);
    }

    public void setContent(CharSequence string) {
        contentTv.setText(string);
    }

    public void setOnTipDialogListener(OnTipDialogListener onTipDialogListener) {
        this.mOnTipDialogListener = onTipDialogListener;
    }

    private void init() {
        setContentView(R.layout.dialog_tip);

        initView();
        initValue();
        initEvent();
    }


    private void initView() {
        titleTv = (TextView) findViewById(R.id.tv_title);
        contentTv = (TextView) findViewById(R.id.tv_content);
        negativeBtn = (TextView) findViewById(R.id.btn_negative);
        positiveBtn = (TextView) findViewById(R.id.btn_positive);
    }

    private void initValue() {
        titleTv.setText(mTitle);
        if(mContent!=null){
            contentTv.setText(mContent);
            contentTv.setVisibility(View.VISIBLE);
        }else{
            contentTv.setVisibility(View.GONE);
        }
        positiveBtn.setText(mPositive);
        negativeBtn.setText(mNegative);
    }

    private void initEvent() {
        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTipDialogListener != null) {
                    mOnTipDialogListener.onNegativeClick();
                } else {
                    Log.w(TAG, "mOnTipDialogListener is null");
                }
                cancel();
            }
        });
        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTipDialogListener != null) {
                    mOnTipDialogListener.onPositiveClick();
                } else {
                    Log.w(TAG, "mOnTipDialogListener is null");
                }
                cancel();
            }
        });
    }
}
