package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/10.
 */

public class MyTakeCashModel implements Serializable {
    /**
     * id : 2b8f007446b940cf4c1ff8c7
     * sn : WL1576578237525550
     * input_money : 10
     * money : 9
     * status : 1
     * created_at : 2019-12-17 18:23:57
     * status_title : 待审核
     */

    private String id;
    private String sn;
    private String input_money;
    private String money;
    private int status;
    private String created_at;
    private String status_title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getInput_money() {
        return input_money;
    }

    public void setInput_money(String input_money) {
        this.input_money = input_money;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
    }
}
