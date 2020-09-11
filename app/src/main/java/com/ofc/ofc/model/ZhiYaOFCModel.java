package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/8/10.
 */
public class ZhiYaOFCModel implements Serializable {
    /**
     * invest_cycle : 360
     * ofc_index : 0.007
     * ofc_price : 210
     * ofc_usable_money : 90000.00
     */

    private String invest_cycle;
    private String ofc_index;
    private String ofc_price;
    private String ofc_usable_money;
    private String hk;

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }

    public String getInvest_cycle() {
        return invest_cycle;
    }

    public void setInvest_cycle(String invest_cycle) {
        this.invest_cycle = invest_cycle;
    }

    public String getOfc_index() {
        return ofc_index;
    }

    public void setOfc_index(String ofc_index) {
        this.ofc_index = ofc_index;
    }

    public String getOfc_price() {
        return ofc_price;
    }

    public void setOfc_price(String ofc_price) {
        this.ofc_price = ofc_price;
    }

    public String getOfc_usable_money() {
        return ofc_usable_money;
    }

    public void setOfc_usable_money(String ofc_usable_money) {
        this.ofc_usable_money = ofc_usable_money;
    }
}
