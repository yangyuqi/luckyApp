package com.yunqilai.delivery.model;

import java.io.Serializable;

/**
 * 拒单原因
 * Created by KK on 2017/6/7.
 */

public class RefuseOrderReason implements Serializable {
    private String id;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
