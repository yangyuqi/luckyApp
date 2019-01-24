package com.yunqilai.consumer.luckyapp.Common.Utils.RQCode.decode;

import android.graphics.Bitmap;

public abstract class LuminanceSource extends com.google.zxing.LuminanceSource {

	protected LuminanceSource(int width, int height) {
		super(width, height);
	}

	public abstract Bitmap renderCroppedGreyScaleBitmap();
}
