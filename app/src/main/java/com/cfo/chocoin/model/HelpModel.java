package com.cfo.chocoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/6/25.
 */
public class HelpModel implements Serializable {
    /**
     * id : 0b4ae8abd1ce5dffadf9b62ae2133e74
     * title : 帮助中心2
     * cover :
     * digest : 帮助中心2
     * url : http://192.168.0.188/wechat/article/detail?id=0b4ae8abd1ce5dffadf9b62ae2133e74
     */

    private String id;
    private String title;
    private String cover;
    private String digest;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
