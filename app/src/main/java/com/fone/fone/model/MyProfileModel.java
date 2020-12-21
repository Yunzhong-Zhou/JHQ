package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/10.
 */

public class MyProfileModel implements Serializable {
    /**
     * mobile : 18306043086
     * nickname : FIL_pour广告费
     * nickname_update : 2
     * head : /head/606.png
     * email :
     */

    private String mobile;
    private String nickname;
    private int nickname_update;
    private String head;
    private String email;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getNickname_update() {
        return nickname_update;
    }

    public void setNickname_update(int nickname_update) {
        this.nickname_update = nickname_update;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
