package com.yunqilai.delivery.model.Bean.Info;

/**
 * Created by yangyuqi on 17-8-2.
 */

public class PutInfoBean {
    private String accessToken ;
    private String tankStatus ;
    private int currentPage ;
    private int showCount ;
    private String keyword ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTankStatus() {
        return tankStatus;
    }

    public void setTankStatus(String tankStatus) {
        this.tankStatus = tankStatus;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public PutInfoBean(String accessToken, String tankStatus, int currentPage, int showCount, String keyword) {

        this.accessToken = accessToken;
        this.tankStatus = tankStatus;
        this.currentPage = currentPage;
        this.showCount = showCount;
        this.keyword = keyword;
    }
}
