package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-18.
 */
public class AddressManagementModel implements Serializable {
    /**
     * withdrawal_cny_switch : 1
     * withdrawal_usdt_switch : 1
     * withdrawal_bwin_switch : 1
     * usdt_wallet_addr :
     * bwin_wallet_addr :
     * member_bank_card_account : 62267876764884578468
     * member_bank_card_proceeds_name : 阿斯顿
     * member_bank_title : 中国民生银行
     */

    private String withdrawal_cny_switch;
    private String withdrawal_usdt_switch;
    private String withdrawal_bwin_switch;
    private String usdt_wallet_addr;
    private String bwin_wallet_addr;
    private String member_bank_card_account;
    private String member_bank_card_proceeds_name;
    private String member_bank_title;

    public String getWithdrawal_cny_switch() {
        return withdrawal_cny_switch;
    }

    public void setWithdrawal_cny_switch(String withdrawal_cny_switch) {
        this.withdrawal_cny_switch = withdrawal_cny_switch;
    }

    public String getWithdrawal_usdt_switch() {
        return withdrawal_usdt_switch;
    }

    public void setWithdrawal_usdt_switch(String withdrawal_usdt_switch) {
        this.withdrawal_usdt_switch = withdrawal_usdt_switch;
    }

    public String getWithdrawal_bwin_switch() {
        return withdrawal_bwin_switch;
    }

    public void setWithdrawal_bwin_switch(String withdrawal_bwin_switch) {
        this.withdrawal_bwin_switch = withdrawal_bwin_switch;
    }

    public String getUsdt_wallet_addr() {
        return usdt_wallet_addr;
    }

    public void setUsdt_wallet_addr(String usdt_wallet_addr) {
        this.usdt_wallet_addr = usdt_wallet_addr;
    }

    public String getBwin_wallet_addr() {
        return bwin_wallet_addr;
    }

    public void setBwin_wallet_addr(String bwin_wallet_addr) {
        this.bwin_wallet_addr = bwin_wallet_addr;
    }

    public String getMember_bank_card_account() {
        return member_bank_card_account;
    }

    public void setMember_bank_card_account(String member_bank_card_account) {
        this.member_bank_card_account = member_bank_card_account;
    }

    public String getMember_bank_card_proceeds_name() {
        return member_bank_card_proceeds_name;
    }

    public void setMember_bank_card_proceeds_name(String member_bank_card_proceeds_name) {
        this.member_bank_card_proceeds_name = member_bank_card_proceeds_name;
    }

    public String getMember_bank_title() {
        return member_bank_title;
    }

    public void setMember_bank_title(String member_bank_title) {
        this.member_bank_title = member_bank_title;
    }
}
