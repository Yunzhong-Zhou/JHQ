package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/10.
 */

public class MyTakeCashModel implements Serializable {
    /**
     * id : a13b63ccaa10a71518066c7a
     * sn : WI1608439977985010
     * money_type : 1
     * input_money : 100.0000
     * money : 97.0000
     * status : 1
     * created_at : 2020-12-20 12:52:57
     * money_type_title : USDT
     * status_title : 待审核
     */

    private String id;
    private String sn;
    private int money_type;
    private String input_money;
    private String money;
    private int status;
    private String created_at;
    private String money_type_title;
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

    public int getMoney_type() {
        return money_type;
    }

    public void setMoney_type(int money_type) {
        this.money_type = money_type;
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

    public String getMoney_type_title() {
        return money_type_title;
    }

    public void setMoney_type_title(String money_type_title) {
        this.money_type_title = money_type_title;
    }

    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
    }
}
