package com.yunqilai.delivery.ui.inputFilter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * 身份证号输入的拦截器
 * @author kk
 *
 */
public class IDCardNumInputFilter implements InputFilter {
	/**
	 * 允许输入的
	 */
	private String allow = "0123456789Xx" ;//
	
	@Override
	public CharSequence filter(CharSequence source, int start, int end,
							   Spanned dest, int dstart, int dend) {
		if(!allow.contains(source)){
			return "";
		}

		return source;
	}

}
