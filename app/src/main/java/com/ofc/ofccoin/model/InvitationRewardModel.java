package com.ofc.ofccoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-22.
 */
public class InvitationRewardModel implements Serializable {
    /**
     * nickname : 18306043086
     * head : /head/368.png
     * profit_money : 0
     * invite_code : 38272979
     * url : /register?invite_code=38272979
     */

    private String nickname;
    private String head;
    private String profit_money;
    private String invite_code;
    private String url;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getProfit_money() {
        return profit_money;
    }

    public void setProfit_money(String profit_money) {
        this.profit_money = profit_money;
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
