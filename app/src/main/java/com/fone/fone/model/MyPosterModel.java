package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/11/12.
 */
public class MyPosterModel implements Serializable {
    /**
     * head : /head/97.png
     * nickname : 阿斯顿马丁
     * invite_code : 29180764
     * url : /register?invite_code=29180764
     */

    private String head;
    private String nickname;
    private String invite_code;
    private String url;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
