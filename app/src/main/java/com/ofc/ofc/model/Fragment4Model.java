package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/2.
 */
public class Fragment4Model implements Serializable {
    /**
     * ofc_top_up_max_money : 100000
     * ofc_top_up_min_money : 100
     * ofc_money : 0.00
     * usdt_top_up_max_money : 100000
     * usdt_top_up_min_money : 100
     * usdt_price : 1
     * aud_conver_usd : 0.6889
     * usable_money : 88895.01
     * id :
     */

    private String ofc_top_up_max_money;
    private String ofc_top_up_min_money;
    private String ofc_money;
    private String usdt_top_up_max_money;
    private String usdt_top_up_min_money;
    private String usdt_price;
    private String aud_conver_usd;
    private String usable_money;
    private String id;

    public String getOfc_top_up_max_money() {
        return ofc_top_up_max_money;
    }

    public void setOfc_top_up_max_money(String ofc_top_up_max_money) {
        this.ofc_top_up_max_money = ofc_top_up_max_money;
    }

    public String getOfc_top_up_min_money() {
        return ofc_top_up_min_money;
    }

    public void setOfc_top_up_min_money(String ofc_top_up_min_money) {
        this.ofc_top_up_min_money = ofc_top_up_min_money;
    }

    public String getOfc_money() {
        return ofc_money;
    }

    public void setOfc_money(String ofc_money) {
        this.ofc_money = ofc_money;
    }

    public String getUsdt_top_up_max_money() {
        return usdt_top_up_max_money;
    }

    public void setUsdt_top_up_max_money(String usdt_top_up_max_money) {
        this.usdt_top_up_max_money = usdt_top_up_max_money;
    }

    public String getUsdt_top_up_min_money() {
        return usdt_top_up_min_money;
    }

    public void setUsdt_top_up_min_money(String usdt_top_up_min_money) {
        this.usdt_top_up_min_money = usdt_top_up_min_money;
    }

    public String getUsdt_price() {
        return usdt_price;
    }

    public void setUsdt_price(String usdt_price) {
        this.usdt_price = usdt_price;
    }

    public String getAud_conver_usd() {
        return aud_conver_usd;
    }

    public void setAud_conver_usd(String aud_conver_usd) {
        this.aud_conver_usd = aud_conver_usd;
    }

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
