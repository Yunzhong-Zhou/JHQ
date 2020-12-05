package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/2.
 */
public class MyRechargeModel implements Serializable {
    /**
     * id : 536aa2b4cd74f61eec216b7f7809ca05
     * sn : TU1576565271101529
     * type : 1
     * input_money : 10
     * money : 10
     * status : 1
     * created_at : 2019-12-17 14:47:51
     * type_title : USDT
     * status_title : 待审核
     */

    private String id;
    private String sn;
    private int type;
    private String input_money;
    private String money;
    private int status;
    private String created_at;
    private String type_title;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getType_title() {
        return type_title;
    }

    public void setType_title(String type_title) {
        this.type_title = type_title;
    }

    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
    }
}
