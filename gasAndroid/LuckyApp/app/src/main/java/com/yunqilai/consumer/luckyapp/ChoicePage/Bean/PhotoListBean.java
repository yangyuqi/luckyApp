package com.yunqilai.consumer.luckyapp.ChoicePage.Bean;

/**
 * Created by Administrator on 2017/6/26.
 */

public class PhotoListBean {
    private int ad_slide_sequence ;
    private String path ;
    private int deleteStatus ;

    public int getAd_slide_sequence() {
        return ad_slide_sequence;
    }

    public void setAd_slide_sequence(int ad_slide_sequence) {
        this.ad_slide_sequence = ad_slide_sequence;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public PhotoListBean(int ad_slide_sequence, String path, int deleteStatus) {

        this.ad_slide_sequence = ad_slide_sequence;
        this.path = path;
        this.deleteStatus = deleteStatus;
    }
}
