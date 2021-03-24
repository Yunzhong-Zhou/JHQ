package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-18.
 */
public class TransferModel implements Serializable {
    /**
     * usdt_price : 1
     * fil_price : 1
     * usable_fil_money : 0.0000
     * hk : d92a59d3e6458004ab93fc8f26fca226
     */

    private String usdt_price;
    private String fil_price;
    private String usable_fil_money;
    private String hk;

    public String getUsdt_price() {
        return usdt_price;
    }

    public void setUsdt_price(String usdt_price) {
        this.usdt_price = usdt_price;
    }

    public String getFil_price() {
        return fil_price;
    }

    public void setFil_price(String fil_price) {
        this.fil_price = fil_price;
    }

    public String getUsable_fil_money() {
        return usable_fil_money;
    }

    public void setUsable_fil_money(String usable_fil_money) {
        this.usable_fil_money = usable_fil_money;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }
}
