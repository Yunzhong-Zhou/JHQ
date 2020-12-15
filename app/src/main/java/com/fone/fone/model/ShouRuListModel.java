package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/8/10.
 */
public class ShouRuListModel implements Serializable {
    /**
     * id : a5fb7a454f48246cd167f655807e3132
     * money : 1.00
     * usdt_money : 1.07
     * ofc_price : 1.0670
     * created_at : 2020-09-23 20:46:02
     */

    private String id;
    private String money;
    private String usdt_money;
    private String ofc_price;
    private String created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUsdt_money() {
        return usdt_money;
    }

    public void setUsdt_money(String usdt_money) {
        this.usdt_money = usdt_money;
    }

    public String getOfc_price() {
        return ofc_price;
    }

    public void setOfc_price(String ofc_price) {
        this.ofc_price = ofc_price;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
