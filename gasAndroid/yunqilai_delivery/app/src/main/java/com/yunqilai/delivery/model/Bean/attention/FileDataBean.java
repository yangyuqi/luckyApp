package com.yunqilai.delivery.model.Bean.attention;

/**
 * Created by yangyuqi on 17-8-2.
 */

public class FileDataBean {
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileDataBean(String path) {

        this.path = path;
    }

    private String path ;
}
