package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/11/16.
 */
public class USDTSellModel implements Serializable {
    /**
     * usdt_cny_price : 6.7
     * usable_money : 19990.0000
     * bank_card_account :
     * bank_card_proceeds_name :
     * bank_title :
     * bank_address :
     */

    private String usdt_cny_price;
    private String usable_money;
    private String bank_card_account;
    private String bank_card_proceeds_name;
    private String bank_title;
    private String bank_address;

    public String getUsdt_cny_price() {
        return usdt_cny_price;
    }

    public void setUsdt_cny_price(String usdt_cny_price) {
        this.usdt_cny_price = usdt_cny_price;
    }

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
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
}
