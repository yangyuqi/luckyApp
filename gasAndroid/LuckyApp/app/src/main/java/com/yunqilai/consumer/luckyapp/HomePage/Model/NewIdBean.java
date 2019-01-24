package com.yunqilai.consumer.luckyapp.HomePage.Model;

/**
 * Created by yangyuqi on 17-8-4.
 */

public class NewIdBean {
    private String id ;
    private int currentPage ;
    private int showCount ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public NewIdBean(String id, int currentPage, int showCount) {

        this.id = id;
        this.currentPage = currentPage;
        this.showCount = showCount;
    }
}
