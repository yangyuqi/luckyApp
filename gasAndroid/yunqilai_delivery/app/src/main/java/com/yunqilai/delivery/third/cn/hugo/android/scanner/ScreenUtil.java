package com.yunqilai.delivery.third.cn.hugo.android.scanner;

import android.util.DisplayMetrics;
import android.view.Window;

public class ScreenUtil {
	/**
	 * 获取屏幕的宽度
	 * @param w
	 * @return
	 */
	public static int getScreenWidth(Window w) {
		return getDisplayMetrics(w).widthPixels;
	}
	
	/**
	 * 获取屏幕的高度
	 * @param w
	 * @return
	 */
	public static int getScreenHeight(Window w) {
		return getDisplayMetrics(w).heightPixels;
	}
	
	/**
	 * 获取屏幕的密度
	 * @param w
	 * @return
	 */
	public static float getScreenDensity(Window w) {
		return getDisplayMetrics(w).density;
	}
	
	/**
	 * 获取屏幕密度的DPI
	 * @param w
	 * @return
	 */
	public static int getScreenDensityDpi(Window w) {
		return getDisplayMetrics(w).densityDpi;
	}

	private static DisplayMetrics getDisplayMetrics(Window w) {
		DisplayMetrics metric = new DisplayMetrics();
		w.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric;
	}

}
