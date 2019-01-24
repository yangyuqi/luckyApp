package com.yunqilai.consumer.luckyapp.Common.View;


import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yunqilai.consumer.R;

/**
 * 首页的认证提醒
 */
public class AttestationDialog extends Dialog{

	private ImageView tipIV;

	private DialogTouchhListener touchListener;

	public interface DialogTouchhListener {
		void onUp();
		void onDown();
	}

	public AttestationDialog(Context context) {
		super(context, R.style.attestation_dialog);
		init();
	}

	public AttestationDialog(Context context, DialogTouchhListener touchListener) {
		this(context);
		this.touchListener = touchListener;
	}

	private void init() {
		setContentView(R.layout.dialog_attestation);

		Window window = getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.MATCH_PARENT;
		window.setBackgroundDrawableResource(android.R.color.transparent);
		window.setAttributes(params);

		tipIV = (ImageView) findViewById(R.id.iv_icon);

	}

	public void setTouchListener(DialogTouchhListener touchListener) {
		this.touchListener = touchListener;
	}

	public void setTipImgRes(int resId){
		if(tipIV != null){
			tipIV.setImageResource(resId);
		}
	}


	private float downX,downY;
	private float distance = 100;//定义多少距离算滑动

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				downX = event.getX();
				downY = event.getY();
				break;
			case MotionEvent.ACTION_UP:
				float upY = event.getY();
				if(upY-downY > distance){
					if(touchListener != null){
						touchListener.onDown();
					}
				}else if(upY - downY < -distance){
					if(touchListener != null){
						touchListener.onUp();
					}
				}


				break;
		}

		return super.onTouchEvent(event);
	}
}
