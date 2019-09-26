package com.cho.chocoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/9.
 */

public class Fragment5Model implements Serializable {
    /**
     * nickname : 阿斯顿马丁
     * head : /head/119.png
     * grade : 1
     * common_usable_money : 502001.4
     * invest_usable_money : 0
     * block_usable_money : 0
     * block_award_usable_money : 0
     * invite_code : 056068
     */

    private String nickname;
    private String head;
    private int grade;
    private String grade_title;
    private String common_usable_money;
    private String invest_usable_money;
    private String block_usable_money;
    private String block_award_usable_money;
    private String invite_code;

    public String getGrade_title() {
        return grade_title;
    }

    public void setGrade_title(String grade_title) {
        this.grade_title = grade_title;
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

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }

    public String getInvest_usable_money() {
        return invest_usable_money;
    }

    public void setInvest_usable_money(String invest_usable_money) {
        this.invest_usable_money = invest_usable_money;
    }

    public String getBlock_usable_money() {
        return block_usable_money;
    }

    public void setBlock_usable_money(String block_usable_money) {
        this.block_usable_money = block_usable_money;
    }

    public String getBlock_award_usable_money() {
        return block_award_usable_money;
    }

    public void setBlock_award_usable_money(String block_award_usable_money) {
        this.block_award_usable_money = block_award_usable_money;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }
}
