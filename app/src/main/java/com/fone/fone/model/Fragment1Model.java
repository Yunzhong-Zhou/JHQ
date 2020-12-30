package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020/7/6.
 */
public class Fragment1Model implements Serializable {
    /**
     * usdt_cny_price : 6.7
     * hashrate_price : 500
     * usable_money : 220.0000
     * usable_hashrate : 10
     * mill_id : 4b33bb88f4759318e5f831c01a09f712
     * mill_package_cycle : 360
     * mill_computer_position : 中国福州
     * mill_node_number : 0x11111
     * mill_number : oxk333
     * mill_pledge_fil_money : 50.0000
     * mill_mining_cycle : 360
     * mill_production_value_fil_money : 100.0000
     * hk : ab8ec9406bfa6aaf88c66789e8be690d
     */

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String usdt_cny_price;
    private String hashrate_price;
    private String usable_money;
    private String usable_hashrate;
    private String mill_id;
    private String mill_package_cycle;
    private String mill_computer_position;
    private String mill_node_number;
    private String mill_number;
    private String mill_pledge_fil_money;
    private String mill_mining_cycle;
    private String mill_production_value_fil_money;
    private String mill_all_cny_price;
    private String hk;
    private String url;

    public String getMill_all_cny_price() {
        return mill_all_cny_price;
    }

    public void setMill_all_cny_price(String mill_all_cny_price) {
        this.mill_all_cny_price = mill_all_cny_price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsdt_cny_price() {
        return usdt_cny_price;
    }

    public void setUsdt_cny_price(String usdt_cny_price) {
        this.usdt_cny_price = usdt_cny_price;
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

    public String getMill_package_cycle() {
        return mill_package_cycle;
    }

    public void setMill_package_cycle(String mill_package_cycle) {
        this.mill_package_cycle = mill_package_cycle;
    }

    public String getMill_computer_position() {
        return mill_computer_position;
    }

    public void setMill_computer_position(String mill_computer_position) {
        this.mill_computer_position = mill_computer_position;
    }

    public String getMill_node_number() {
        return mill_node_number;
    }

    public void setMill_node_number(String mill_node_number) {
        this.mill_node_number = mill_node_number;
    }

    public String getMill_number() {
        return mill_number;
    }

    public void setMill_number(String mill_number) {
        this.mill_number = mill_number;
    }

    public String getMill_pledge_fil_money() {
        return mill_pledge_fil_money;
    }

    public void setMill_pledge_fil_money(String mill_pledge_fil_money) {
        this.mill_pledge_fil_money = mill_pledge_fil_money;
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
