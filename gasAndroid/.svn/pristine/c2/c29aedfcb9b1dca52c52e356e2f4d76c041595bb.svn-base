package com.yunqilai.consumer.wxapi;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AppRegister extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		final IWXAPI msgApi = WXAPIFactory.createWXAPI(context,ResponseCodeUtils.APPID, false);

		// 将该app注册到微信
		msgApi.registerApp(ResponseCodeUtils.APPID);
		Log.e("sssssssss","mmmm");
	}
}
