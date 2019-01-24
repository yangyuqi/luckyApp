package com.yunqilai.delivery.request.base;

/**
 * Created by KK on 2017/2/20.
 */

public abstract class DefaultRequestListener implements OnBaseRequestListener {
    @Override
    public void onNetworkError(String errorMsg) {
        onRequestFail();
    }

    @Override
    public void onHttpFail(int statusCode, String errorMsg) {
        onRequestFail();
    }

    @Override
    public void onBusinessFail(String statusCode, String errorMsg) {
        onRequestFail();
    }

    protected abstract void onRequestFail();
}
