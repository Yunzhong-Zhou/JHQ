package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020/7/6.
 */
public class Fragment1Model implements Serializable {
    /**
     * hashrate_price : 500
     * usable_money : 8220.0000
     * usable_hashrate : 10
     * mill_id : 0bb7103aafcb1e0d09fab72a46344768
     * mill_package_cycle : 360
     * mill_computer_position : 中国福州
     * mill_node_number : 0x11111
     * mill_number : oxk333
     * mill_pledge_fil_money : 50.0000
     * mill_mining_cycle : 360
     * mill_production_value_fil_money : 100.0000
     * hk : a41350bf3dbf2e10226072af661abffd
     */

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
    private String hk;

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
