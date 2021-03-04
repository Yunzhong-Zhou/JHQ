package com.filter.filter.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-18.
 */
public class AddressManagementModel implements Serializable {
    /**
     * usdt_wallet_addr :
     * fil_wallet_addr :
     * member_bank_card_account :
     * member_bank_card_proceeds_name :
     * member_bank_title :
     */

    private String usdt_wallet_addr;
    private String fil_wallet_addr;
    private String member_bank_card_account;
    private String member_bank_card_proceeds_name;
    private String member_bank_title;

    public String getUsdt_wallet_addr() {
        return usdt_wallet_addr;
    }

    public void setUsdt_wallet_addr(String usdt_wallet_addr) {
        this.usdt_wallet_addr = usdt_wallet_addr;
    }

    public String getFil_wallet_addr() {
        return fil_wallet_addr;
    }

    public void setFil_wallet_addr(String fil_wallet_addr) {
        this.fil_wallet_addr = fil_wallet_addr;
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
