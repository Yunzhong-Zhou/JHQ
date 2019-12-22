package com.ofc.ofccoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/5/31.
 */

public class ScavengingPaymentModel implements Serializable {
    /**
     * head : /head/313.png
     * nickname : 18680817626
     * common_usable_money : 17.19
     * transfer_service_charge : 1
     * hk : cbbc6f7394770ed92061b6027b64a25e
     */

    private String head;
    private String nickname;
    private String common_usable_money;
    private String transfer_service_charge;
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

    public String getTransfer_service_charge() {
        return transfer_service_charge;
    }

    public void setTransfer_service_charge(String transfer_service_charge) {
        this.transfer_service_charge = transfer_service_charge;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }
}
