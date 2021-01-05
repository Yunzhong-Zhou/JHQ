package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/4.
 */
public class Fragment2Model implements Serializable {
    /**
     * usable_money : 8710.0000
     * usable_hashrate : 9
     * mill_id : 21d990190b6e3e2f08dada7e03ac062c
     * mill_number : 0xwww
     * mill_mining_cycle : 360
     * mill_production_value_fil_money : 20.0000
     * hk : b29451ca869eac5050a847dc93f9c35f
     */

    private String hashrate_price;
    private String usable_money;
    private String usable_hashrate;
    private String mill_id;
    private String mill_number;
    private String mill_mining_cycle;
    private String mill_production_value_fil_money;
    private String hk;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHashrate_price() {
        return hashrate_price;
    }

    public void setHashrate_price(String hashrate_price) {
        this.hashrate_price = hashrate_price;
    }

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
    }

    public String getUsable_hashrate() {
        return usable_hashrate;
    }

    public void setUsable_hashrate(String usable_hashrate) {
        this.usable_hashrate = usable_hashrate;
    }

    public String getMill_id() {
        return mill_id;
    }

    public void setMill_id(String mill_id) {
        this.mill_id = mill_id;
    }

    public String getMill_number() {
        return mill_number;
    }

    public void setMill_number(String mill_number) {
        this.mill_number = mill_number;
    }

    public String getMill_mining_cycle() {
        return mill_mining_cycle;
    }

    public void setMill_mining_cycle(String mill_mining_cycle) {
        this.mill_mining_cycle = mill_mining_cycle;
    }

    public String getMill_production_value_fil_money() {
        return mill_production_value_fil_money;
    }

    public void setMill_production_value_fil_money(String mill_production_value_fil_money) {
        this.mill_production_value_fil_money = mill_production_value_fil_money;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }
}
