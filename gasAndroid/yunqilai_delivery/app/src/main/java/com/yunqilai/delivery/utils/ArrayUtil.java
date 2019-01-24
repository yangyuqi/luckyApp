package com.yunqilai.delivery.utils;

import android.text.InputFilter;

/**
 * 对数组的处理工具类
 * @author kk
 *
 */
public class ArrayUtil {
	
	/**
	 * 在InputFilter数组末尾追加元素的方法
	 * @param array
	 * @param filder
	 * @return
	 */
	public static InputFilter[] appendFilter(InputFilter[] array, InputFilter filder){
		InputFilter[] result = new InputFilter[array.length+1];
		System.arraycopy(array, 0, result, 0, array.length);
		result[result.length-1] = filder;
		return result;
	}
}
