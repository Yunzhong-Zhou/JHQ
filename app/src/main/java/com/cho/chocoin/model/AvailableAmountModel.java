package com.cho.chocoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/9.
 */

public class AvailableAmountModel implements Serializable {

    /**
     * wallet_addr :
     * common_usable_money : 502001.4
     * withdrawal_service_charge : 5
     * eth_price : 243.69
     * cho_price : 1
     * min_withdrawal_money : 10
     * max_withdrawal_money : 20000
     * hk : 14b7182c7cf935ce5cabc507fab60bea
     */
    private String bank_card_account;
    private String bank_card_proceeds_name;
    private String bank_title;
    private String bank_address;
    private String wallet_addr;
    private String common_usable_money;
    private String withdrawal_service_charge;
    private String eth_price;
    private String cho_price;
    private String cny_price;
    private String min_withdrawal_money;
    private String max_withdrawal_money;
    private String hk;

    public String getCny_price() {
        return cny_price;
    }

    public void setCny_price(String cny_price) {
        this.cny_price = cny_price;
    }

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

    public String getWallet_addr() {
        return wallet_addr;
    }

    public void setWallet_addr(String wallet_addr) {
        this.wallet_addr = wallet_addr;
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

    public String getEth_price() {
        return eth_price;
    }

    public void setEth_price(String eth_price) {
        this.eth_price = eth_price;
    }

    public String getCho_price() {
        return cho_price;
    }

    public void setCho_price(String cho_price) {
        this.cho_price = cho_price;
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
