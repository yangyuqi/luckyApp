package com.yunqilai.delivery.request.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import com.yunqilai.delivery.model.TokenData;
import com.yunqilai.delivery.request.base.network.OkHttpClientManager;
import com.yunqilai.delivery.request.base.network.Server;
import com.yunqilai.delivery.utils.Constants;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.manager.GlobalDataUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;


/**
 * 所有网络请求的基类
 * Created by KK on 2017/2/17.
 */

public class BaseRequester {

    protected Gson mGson = new Gson();

    protected void get(){

    }

    private Context curContext;

    protected void post(Context context, final String url, final Map<String, Object> params, final OnBaseRequestListener requestListener, final AbsBaseParser absBaseParser){
        post(context,url,mGson.toJson(params),requestListener,absBaseParser);
    }

    protected void post(Context context, final String url, final String json, final OnBaseRequestListener requestListener, final AbsBaseParser absBaseParser){
        curContext = context;

        if(!isNetWorkConnected(context)){
            requestListener.onNetworkError("网络不可用");
            return;
        }
        Log.d("HTTP","request : "+url+" "+json);
        OkHttpClientManager.postAsynJson(url,new OkHttpClientManager.ResultCallback<String>(){

            @Override
            public void onError(Request request, Exception e) {
                Log.d("HTTP","response Error");
                requestListener.onHttpFail(9999,"请求失败");
                e.printStackTrace();
            }

            @Override
            public void onResponse(String response) {
                Log.d("HTTP","response : " +response);
                ResponseResult responseResult = parse(response);
                //处理token失效
                if(responseResult!=null && responseResult.getCode()!=null){
                    switch (responseResult.getCode()){
                        case ErrorCode.ERR_ACCESS_TOKEN_INVALID:
                            GlobalDataUtil.removeObject(curContext, Constants.GLOBAL_DATA_KEY_ACCESS_TOKEN);
                            if(!isNetWorkConnected(curContext)){
                                requestListener.onNetworkError("网络不可用");
                                break;
                            }
                            //access_token无效
                            //取出refreshToken
                            String refreshToken = (String) GlobalDataUtil.getObject(curContext, Constants.GLOBAL_DATA_KEY_REFRESH_TOKEN);
                            // 通过接口获取新的access_token，此处要用到同步的请求
                            String gatUrl = Server.getUrl("user/getAccessToken");
                            Map<String,String> gatParam = new HashMap<>();
                            gatParam.put("refresh_token",refreshToken);
                            Log.d("HTTP","request : "+gatUrl+" "+mGson.toJson(gatParam));
                            OkHttpClientManager.postAsynJson(gatUrl,new OkHttpClientManager.ResultCallback<String>(){

                                @Override
                                public void onError(Request request, Exception e) {
                                    Log.d("HTTP","response Error");
                                    requestListener.onHttpFail(9999,"请求失败");
                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.d("HTTP","response : " +response);
                                    ResponseResult<TokenData> tempResult = null;
                                    try {
                                        tempResult = mGson.fromJson(response,ResponseResult.class);
                                    } catch (JsonSyntaxException e) {
                                        e.printStackTrace();
                                        requestListener.onHttpFail(9999,"请求失败");
                                        return;
                                    }
                                    switch (tempResult.getCode()) {
                                        case ErrorCode.ERR_REFRESH_TOKEN_INVALID:
                                            GlobalDataUtil.removeObject(curContext, Constants.GLOBAL_DATA_KEY_REFRESH_TOKEN);
                                            //refresh_token无效
                                            EventBus.getDefault().post(Event.GOTO_LOGIN);
//                                            requestListener.onHttpFail(9999,"请求失败");
                                            break;
                                        case "200":
                                            //新的token
                                            TokenData tokenData = mGson.fromJson(mGson.toJson(tempResult.getData()),TokenData.class);
                                            String newToken = tokenData.getAccess_token();
                                            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                                            jsonObject.remove("access_token");
                                            jsonObject.addProperty("access_token",newToken);
                                            //重新发送请求
                                            post(curContext,url,mGson.toJson(jsonObject),requestListener,absBaseParser);
                                            //保存新的access_token
                                            GlobalDataUtil.putObject(curContext, Constants.GLOBAL_DATA_KEY_ACCESS_TOKEN,newToken,true);
                                            break;
                                        default:
                                            requestListener.onHttpFail(9999,"请求失败");
                                    }
                                }
                            },gatParam);
                            break;
                        case ErrorCode.ERR_REFRESH_TOKEN_INVALID:
                            GlobalDataUtil.removeObject(curContext, Constants.GLOBAL_DATA_KEY_REFRESH_TOKEN);
                            //refresh_token无效
                            EventBus.getDefault().post(Event.GOTO_LOGIN);
                            break;
                        default:
                            absBaseParser.parse(response);
                    }
                }else{
                    requestListener.onHttpFail(9999,"请求失败");
                }
            }
        },json);
    }

    /**
     * 检查网络连接
     * @param context
     * @return
     */
    private boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
            }
        }
        return false;
    }

    private GsonDataParser mParser = new GsonDataParser();
    private ResponseResult parse(String data) {
        ResponseResult responseResult;
        try {
            responseResult = mParser.parseObject(data, ResponseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return responseResult;
    }
}
