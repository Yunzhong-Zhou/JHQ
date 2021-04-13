package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/12/23.
 */
public class PayDetailModel implements Serializable {
    /**
     * bank_title : 中国银行
     * bank_card_proceeds_name : xxx公司
     * bank_card_account : 6666 6666 6666 6666
     * money : 7900.00
     */

    private String bank_title;
    private String bank_card_proceeds_name;
    private String bank_card_account;
    private String money;

    public String getBank_title() {
        return bank_title;
    }

    public void setBank_title(String bank_title) {
        this.bank_title = bank_title;
    }

    public String getBank_card_proceeds_name() {
        return bank_card_proceeds_name;
    }

    public void setBank_card_proceeds_name(String bank_card_proceeds_name) {
        this.bank_card_proceeds_name = bank_card_proceeds_name;
    }

    public String getBank_card_account() {
        return bank_card_account;
    }

    public void setBank_card_account(String bank_card_account) {
        this.bank_card_account = bank_card_account;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
