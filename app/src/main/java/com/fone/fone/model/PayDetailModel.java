package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/12/23.
 */
public class PayDetailModel implements Serializable {
    /**
     * invest : {"cny_money":"33500.00","bank_title":"","bank_card_proceeds_name":"","bank_card_account":""}
     */

    private InvestBean invest;

    public InvestBean getInvest() {
        return invest;
    }

    public void setInvest(InvestBean invest) {
        this.invest = invest;
    }

    public static class InvestBean {
        /**
         * cny_money : 33500.00
         * bank_title :
         * bank_card_proceeds_name :
         * bank_card_account :
         */

        private String cny_money;
        private String bank_title;
        private String bank_card_proceeds_name;
        private String bank_card_account;

        public String getCny_money() {
            return cny_money;
        }

        public void setCny_money(String cny_money) {
            this.cny_money = cny_money;
        }

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
    }
}
