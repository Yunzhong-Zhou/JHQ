package com.cho.chocoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/5/31.
 */

public class ScavengingPaymentModel implements Serializable {
    /**
     * head : /head/248.png
     * nickname : 1
     * common_usable_money : 0
     * hk : 571d0925d8f40bce103a5e92c22da6f8
     */

    private String head;
    private String nickname;
    private String common_usable_money;
    private String hk;

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

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }
}
