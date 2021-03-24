package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/9.
 */

public class AvailableAmountModel implements Serializable {
    /**
     * bank_card_account :
     * bank_card_proceeds_name :
     * bank_title :
     * bank_address :
     * usable_money : 0.0000
     * min_withdrawal_money : 100
     * max_withdrawal_money : 10000
     * withdrawal_service_charge : 3
     * hk : 491384de48cbcf514fc205590e868ef3
     */

    private String bank_icon;

    public String getBank_icon() {
        return bank_icon;
    }

    public void setBank_icon(String bank_icon) {
        this.bank_icon = bank_icon;
    }

    private String bank_card_account;
    private String bank_card_proceeds_name;
    private String bank_title;
    private String bank_address;
    private String usable_money;
    private String min_withdrawal_money;
    private String max_withdrawal_money;
    private String withdrawal_service_charge;
    private String hk;

    public String getBank_card_account() {
        return bank_card_account;
    }

    public void setBank_card_account(String bank_card_account) {
        this.bank_card_account = bank_card_account;
    }

    public String getBank_card_proceeds_name() {
        return bank_card_proceeds_name;
    }

    public void setBank_card_proceeds_name(String bank_card_proceeds_name) {
        this.bank_card_proceeds_name = bank_card_proceeds_name;
    }

    public String getBank_title() {
        return bank_title;
    }

    public void setBank_title(String bank_title) {
        this.bank_title = bank_title;
    }

    public String getBank_address() {
        return bank_address;
    }

    public void setBank_address(String bank_address) {
        this.bank_address = bank_address;
    }

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
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
