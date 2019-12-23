package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/9.
 */

public class AvailableAmountModel implements Serializable {
    /**
     * usdt_wallet_addr :
     * common_usable_money : 15.19
     * withdrawal_service_charge : 1
     * min_withdrawal_money : 10
     * max_withdrawal_money : 100000
     * hk : 1041ade50a696aa7a7bf29219bd5478e
     */

    private String usdt_wallet_addr;
    private String common_usable_money;
    private String withdrawal_service_charge;
    private String min_withdrawal_money;
    private String max_withdrawal_money;
    private String hk;

    public String getUsdt_wallet_addr() {
        return usdt_wallet_addr;
    }

    public void setUsdt_wallet_addr(String usdt_wallet_addr) {
        this.usdt_wallet_addr = usdt_wallet_addr;
    }

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }

    public String getWithdrawal_service_charge() {
        return withdrawal_service_charge;
    }

    public void setWithdrawal_service_charge(String withdrawal_service_charge) {
        this.withdrawal_service_charge = withdrawal_service_charge;
    }

    public String getMin_withdrawal_money() {
        return min_withdrawal_money;
    }

    public void setMin_withdrawal_money(String min_withdrawal_money) {
        this.min_withdrawal_money = min_withdrawal_money;
    }

    public String getMax_withdrawal_money() {
        return max_withdrawal_money;
    }

    public void setMax_withdrawal_money(String max_withdrawal_money) {
        this.max_withdrawal_money = max_withdrawal_money;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }
}
