package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/8/10.
 */
public class HuiGouListModel implements Serializable {
    /**
     * id : 240aa3a74894b353420821dc35c399b2
     * money : 0.00
     * usdt_money : 0.00
     * created_at : 2020-08-10 13:25:10
     */

    private String id;
    private String money;
    private String usdt_money;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
