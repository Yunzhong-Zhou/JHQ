package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/11/16.
 */
public class USDTBuyModel implements Serializable {
    /**
     * usdt_cny_price : 7
     * common_usable_money : 9900.00
     */

    private String usdt_cny_price;
    private String common_usable_money;

    public String getUsdt_cny_price() {
        return usdt_cny_price;
    }

    public void setUsdt_cny_price(String usdt_cny_price) {
        this.usdt_cny_price = usdt_cny_price;
    }

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }
}
