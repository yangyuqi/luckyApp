package com.yunqilai.consumer.luckyapp.HomePage.Model;

/**
 * Created by Administrator on 2017/6/28.
 */

public class SecondMessageBean {
    private String accessToken ;
    private int currentPage ;
    private int showCount ;
    private String type ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SecondMessageBean(String accessToken, int currentPage, int showCount, String type) {

        this.accessToken = accessToken;
        this.currentPage = currentPage;
        this.showCount = showCount;
        this.type = type;
    }
}
