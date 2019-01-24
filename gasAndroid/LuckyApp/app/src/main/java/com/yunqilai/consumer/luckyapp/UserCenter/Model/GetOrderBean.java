package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-7-19.
 */

public class GetOrderBean {
    private String accessToken ;
    private String orderStatus ;
    private int  currentPage ;
    private int  showCount ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public GetOrderBean(String accessToken, String orderStatus, int currentPage, int showCount) {

        this.accessToken = accessToken;
        this.orderStatus = orderStatus;
        this.currentPage = currentPage;
        this.showCount = showCount;
    }
}
