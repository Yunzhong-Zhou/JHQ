package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/5/31.
 */

public class ScavengingPaymentModel implements Serializable {
    /**
     * head : /head/396.png
     * nickname : FIL_wjpn
     * usable_money : 9990.0000
     * trade_password : e10adc3949ba59abbe56e057f20f883e
     * hk : 271f8955e08118c18daad30136b234b3
     */

    private String head;
    private String nickname;
    private String usable_money;
    private String trade_password;
    private String hk;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
    }

    public String getTrade_password() {
        return trade_password;
    }

    public void setTrade_password(String trade_password) {
        this.trade_password = trade_password;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }
}
