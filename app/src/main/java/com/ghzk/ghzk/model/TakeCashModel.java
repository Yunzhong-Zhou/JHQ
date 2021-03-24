package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/5/25.
 */
public class TakeCashModel implements Serializable {
    /**
     * id : c536a0e3c5f33aa2f015ace31362b85a
     * sn : WI1559531923975498
     * member_id : e860799fbb2244df57d53158908b2ef5
     * money_type : 1
     * input_money : 5.66
     * member_type : 1
     * wallet_addr : 饿？鹤顶红打工的郭德纲大哥大个
     * service_charge_money : 0.283
     * money : 5.377
     * updated_at : 2019-06-03 11:18:43
     * created_at : 2019-06-03 11:18:43
     */

    private String id;
    private String sn;
    private String member_id;
    private int money_type;
    private String input_money;
    private int member_type;
    private String wallet_addr;
    private String service_charge_money;
    private String money;
    private String updated_at;
    private String created_at;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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

    public int getMember_type() {
        return member_type;
    }

    public void setMember_type(int member_type) {
        this.member_type = member_type;
    }

    public String getWallet_addr() {
        return wallet_addr;
    }

    public void setWallet_addr(String wallet_addr) {
        this.wallet_addr = wallet_addr;
    }

    public String getService_charge_money() {
        return service_charge_money;
    }

    public void setService_charge_money(String service_charge_money) {
        this.service_charge_money = service_charge_money;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
