package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/9.
 */

public class AvailableAmountModel implements Serializable {
    /**
     * usable_money : 0.0000
     * money_wallet_addr : 1234567890
     * withdrawal_service_charge : 3
     * hk : 585cb220e7a0562152da942ed0b2458b
     */

    private String usable_money;
    private String money_wallet_addr;
    private String withdrawal_service_charge;
    private String hk;

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
    }

    public String getMoney_wallet_addr() {
        return money_wallet_addr;
    }

    public void setMoney_wallet_addr(String money_wallet_addr) {
        this.money_wallet_addr = money_wallet_addr;
    }

    public String getWithdrawal_service_charge() {
        return withdrawal_service_charge;
    }

    public void setWithdrawal_service_charge(String withdrawal_service_charge) {
        this.withdrawal_service_charge = withdrawal_service_charge;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }
}
