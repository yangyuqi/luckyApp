package com.yunqilai.delivery.request.base;

/**
 * 业务请求结果监听器的父接口。
 * Created by KK on 2017/2/20.
 */
public interface OnBaseRequestListener<T> {
    /**
     * 网络状态错误回调方法，设备网络不可用等。
     *
     * @param errorMsg 附加错误信息
     */
    void onNetworkError(String errorMsg);

    /**
     * Http通讯失败
     *
     * @param statusCode
     * @param errorMsg
     */
    void onHttpFail(int statusCode, String errorMsg);

    /**
     * 业务失败
     *
     * @param statusCode
     * @param errorMsg
     */
    void onBusinessFail(String statusCode, String errorMsg);
}
