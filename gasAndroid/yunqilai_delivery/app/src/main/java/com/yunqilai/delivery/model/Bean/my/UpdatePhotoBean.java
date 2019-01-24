package com.yunqilai.delivery.model.Bean.my;

/**
 * Created by yangyuqi on 17-8-4.
 */

public class UpdatePhotoBean {
    private String accessToken ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public UpdatePhotoBean(String accessToken, String icon) {

        this.accessToken = accessToken;
        this.icon = icon;
    }

    private String icon ;
}
