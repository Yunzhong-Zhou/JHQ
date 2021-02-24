package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/9.
 */

public class Fragment5Model implements Serializable {
    /**
     * nickname : 空气净化器_tntk
     * head : /head/795.png
     * grade : 1
     * grade_title : 普通用户
     * usable_money : 0.0000
     * commission_money : 0.0000
     * earning_money : 0.0000
     * invite_code : tntk
     * order_goods_count : 0
     */

    private String nickname;
    private String head;
    private int grade;
    private String grade_title;
    private String usable_money;
    private String commission_money;
    private String earning_money;
    private String invite_code;
    private String order_goods_count;

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

    public String getEarning_money() {
        return earning_money;
    }

    public void setEarning_money(String earning_money) {
        this.earning_money = earning_money;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getOrder_goods_count() {
        return order_goods_count;
    }

    public void setOrder_goods_count(String order_goods_count) {
        this.order_goods_count = order_goods_count;
    }
}
