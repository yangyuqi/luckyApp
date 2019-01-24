package com.yunqilai.delivery.ui.inputFilter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * 只输入中文的拦截器
 * @author kk
 *
 */
public class OnlyChineseInputFilter implements InputFilter {

	@Override
	public CharSequence filter(CharSequence source, int start, int end,
							   Spanned dest, int dstart, int dend) {

		// 只能输入汉字
		if (!source.toString().matches("[\u4e00-\u9fa5]+")) {
			return "";
		}

		return source;
	}

}
