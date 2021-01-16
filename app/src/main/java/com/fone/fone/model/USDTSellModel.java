package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/11/16.
 */
public class USDTSellModel implements Serializable {
    /**
     * usdt_cny_price : 6.7
     * usable_money : 0.0000
     */

    private String usdt_cny_price;
    private String usable_money;

    public String getUsdt_cny_price() {
        return usdt_cny_price;
    }

    public void setUsdt_cny_price(String usdt_cny_price) {
        this.usdt_cny_price = usdt_cny_price;
    }

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
    }
}
