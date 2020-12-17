package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/9.
 */

public class Fragment5Model implements Serializable {
    /**
     * nickname : FIL_pour
     * head : /head/606.png
     * grade : 1
     * grade_title : V0
     * usable_money : 0.0000
     * commission_money : 0.0000
     * fil_money : 0.0000
     * hashrate : 0
     * invite_code : pour
     */

    private String nickname;
    private String head;
    private int grade;
    private String grade_title;
    private String usable_money;
    private String commission_money;
    private String fil_money;
    private String hashrate;
    private String invite_code;
    private String game_win_time;

    public String getGame_win_time() {
        return game_win_time;
    }

    public void setGame_win_time(String game_win_time) {
        this.game_win_time = game_win_time;
    }

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getGrade_title() {
        return grade_title;
    }

    public void setGrade_title(String grade_title) {
        this.grade_title = grade_title;
    }

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
    }

    public String getCommission_money() {
        return commission_money;
    }

    public void setCommission_money(String commission_money) {
        this.commission_money = commission_money;
    }

    public String getFil_money() {
        return fil_money;
    }

    public void setFil_money(String fil_money) {
        this.fil_money = fil_money;
    }

    public String getHashrate() {
        return hashrate;
    }

    public void setHashrate(String hashrate) {
        this.hashrate = hashrate;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }
}
