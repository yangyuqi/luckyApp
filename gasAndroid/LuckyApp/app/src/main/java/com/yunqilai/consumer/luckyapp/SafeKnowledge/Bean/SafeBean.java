package com.yunqilai.consumer.luckyapp.SafeKnowledge.Bean;

/**
 * Created by yangyuqi on 17-7-27.
 */

public class SafeBean {
    private String title ;
    private String content ;
    private int type ;
    private String url ;
    private String id ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SafeBean(String title, String content, int type, String url, String id) {

        this.title = title;
        this.content = content;
        this.type = type;
        this.url = url;
        this.id = id;
    }
}
