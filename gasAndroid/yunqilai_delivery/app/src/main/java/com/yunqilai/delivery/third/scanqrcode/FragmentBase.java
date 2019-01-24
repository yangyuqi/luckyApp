package com.yunqilai.delivery.third.scanqrcode;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

public class FragmentBase extends Fragment {

	protected static final int MSG_CLICK = 4000;
	protected static boolean mIsClickEnable = true;

	protected static Handler mClickhanHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (MSG_CLICK == msg.what) {
				mIsClickEnable = true;
			}
			super.handleMessage(msg);
		}
	};

	public FragmentBase() {

	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public static boolean isClickEnable() {
		if (mIsClickEnable) {
			mIsClickEnable = false;
			Message msg = new Message();
			msg.what = MSG_CLICK;
			mClickhanHandler.sendMessageDelayed(msg, 500);
			return true;
		} else {
			return false;
		}
	}

}