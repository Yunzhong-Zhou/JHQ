package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/8/10.
 */
public class HuiGouOFCModel implements Serializable {
    /**
     * ofc_price : 210
     * usdt_price : 1
     * ofc_usable_money : 0.00
     */

    private String ofc_price;
    private String usdt_price;
    private String ofc_usable_money;
    private String hk;

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }

    public String getOfc_price() {
        return ofc_price;
    }

    public void setOfc_price(String ofc_price) {
        this.ofc_price = ofc_price;
    }

    public String getUsdt_price() {
        return usdt_price;
    }

    public void setUsdt_price(String usdt_price) {
        this.usdt_price = usdt_price;
    }

    public String getOfc_usable_money() {
        return ofc_usable_money;
    }

    public void setOfc_usable_money(String ofc_usable_money) {
        this.ofc_usable_money = ofc_usable_money;
    }
}
