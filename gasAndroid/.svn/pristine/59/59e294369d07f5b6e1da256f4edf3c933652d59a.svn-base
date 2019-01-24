package com.yunqilai.delivery.request.base;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * 所有接口返回数据解析器的父类。
 * Created by KK on 2017/2/17.
 */
public abstract class AbsBaseParser {

    protected GsonDataParser mDataParser;
    protected WeakReference<OnBaseRequestListener> mOnBaseRequestListener;

    public AbsBaseParser(OnBaseRequestListener onBaseRequestListener) {
        this.mOnBaseRequestListener = new WeakReference<>(onBaseRequestListener);
        mDataParser = new GsonDataParser();
    }

    public void parse(String data) {
        if (mOnBaseRequestListener.get() == null) {
            return;
        }

        try {
            ResponseResult responseResult = null;
            try {
                responseResult = mDataParser.parseObject(data, ResponseResult.class);
            } catch (Exception e) {
                e.printStackTrace();
                parseError("9999");
            }

            //parseData(responseResult.getData() == null ? null : mDataParser.toDataStr(responseResult.getData()));
            switch (responseResult.getCode()) {
                case "200"://成功
                    parseData(responseResult.getData() == null ? null : mDataParser.toDataStr(responseResult.getData()));
                    break;
                case "300"://未知错误
                    businessError(responseResult.getCode(), responseResult.getMsg());
                    break;
                case "1003"://密码错误
                    parseData(responseResult.getCode());
                    break;
                case "1004"://用户不存在
                    parseData(responseResult.getCode());
                    break;
                case "1011"://用户已存在
                    parseData(responseResult.getCode());
                    break;
                case "1012"://原密码不正确
                    parseData(responseResult.getCode());
                    break;
                case "3000"://不是最新版本
                    parseData(mDataParser.toDataStr(responseResult.getData()));
                    break;
                case "3001"://是最新版本
                    parseData(responseResult.getCode());
                    break;
                case "3002"://是最新版本
                    parseData(responseResult.getCode());
                    break;
                case "0001"://信息错误
                    businessError(responseResult.getCode(), responseResult.getMsg());
                case "9999"://系统错误
                    businessError(responseResult.getCode(), responseResult.getMsg());
                    break;
                default:
                    businessError(responseResult.getCode(), responseResult.getMsg());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            parseError("9999");

        }

    }

    protected abstract void parseData(@Nullable String data);

    protected void parseError(@Nullable String errorCode) {
        OnBaseRequestListener onBaseRequestListener = mOnBaseRequestListener.get();
        if(onBaseRequestListener != null){
            onBaseRequestListener.onBusinessFail(errorCode,"解析出错");
        }
    }

    protected void businessError(@Nullable String errorCode, @Nullable String msg){
        OnBaseRequestListener onBaseRequestListener = mOnBaseRequestListener.get();
        if(onBaseRequestListener != null){
            onBaseRequestListener.onBusinessFail(errorCode,msg);
        }
    }
}
