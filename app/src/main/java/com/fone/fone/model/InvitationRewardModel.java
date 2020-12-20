package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-22.
 */
public class InvitationRewardModel implements Serializable {
    /**
     * invite_code : pour
     * head : /head/606.png
     * nickname : FIL_pour广告费
     * change_game_win_money : 0
     * url : http://app.bwnx.org/register?invite_code=pour
     */

    private String invite_code;
    private String head;
    private String nickname;
    private String change_game_win_money;
    private String url;

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
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

    public String getChange_game_win_money() {
        return change_game_win_money;
    }

    public void setChange_game_win_money(String change_game_win_money) {
        this.change_game_win_money = change_game_win_money;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
