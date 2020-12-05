package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/10/25.
 */
public class DRVTBuyModel implements Serializable {

    /**
     * ofc_price : 1.0630
     * usdt_price : 1
     * common_usable_money : 709.40
     * hk : 09480aa8f090ecf2190f0c8ed24a5c3d
     */

    private String ofc_price;
    private String usdt_price;
    private String common_usable_money;
    private String hk;

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

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }
}
