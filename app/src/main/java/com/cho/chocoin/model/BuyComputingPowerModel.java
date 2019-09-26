package com.cho.chocoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/4.
 */
public class BuyComputingPowerModel implements Serializable {
    /**
     * usable_money2 : 2.03
     * min_invest_money : 100
     * max_invest_money : 100000
     * hk : b164d23a2d3869c3e3ce7db31f9253d8
     */

    private String usable_money2;
    private String min_invest_money;
    private String max_invest_money;
    private String hk;

    public String getUsable_money2() {
        return usable_money2;
    }

    public void setUsable_money2(String usable_money2) {
        this.usable_money2 = usable_money2;
    }

    public String getMin_invest_money() {
        return min_invest_money;
    }

    public void setMin_invest_money(String min_invest_money) {
        this.min_invest_money = min_invest_money;
    }

    public String getMax_invest_money() {
        return max_invest_money;
    }

    public void setMax_invest_money(String max_invest_money) {
        this.max_invest_money = max_invest_money;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }
}
