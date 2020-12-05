package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/2.
 */
public class RechargeDetailModel implements Serializable {
    /**
     * top_up : {"id":"536aa2b4cd74f61eec216b7f7809ca05","sn":"TU1576565271101529","type":1,"wallet_addr":"啊啊啊啊","input_money":"10","money":"10","status":1,"status_rejected_cause":"","created_at":"2019-12-17 14:47:51","updated_at":"2019-12-17 14:47:51","status_title":"待审核","show_created_at":"12-17 14:47","show_updated_at":"12-17 14:47"}
     * aud_wire_transfer : {"bank_title":"xxx","bank_card_proceeds_name":"xxx","bank_card_account":"xxx","bank_swift_code":"xxx","bank_aba_code":"xxx"}
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private TopUpBean top_up;
    private AudWireTransferBean aud_wire_transfer;

    public TopUpBean getTop_up() {
        return top_up;
    }

    public void setTop_up(TopUpBean top_up) {
        this.top_up = top_up;
    }

    public AudWireTransferBean getAud_wire_transfer() {
        return aud_wire_transfer;
    }

    public void setAud_wire_transfer(AudWireTransferBean aud_wire_transfer) {
        this.aud_wire_transfer = aud_wire_transfer;
    }

    public static class TopUpBean {
        /**
         * id : 536aa2b4cd74f61eec216b7f7809ca05
         * sn : TU1576565271101529
         * type : 1
         * wallet_addr : 啊啊啊啊
         * input_money : 10
         * money : 10
         * status : 1
         * status_rejected_cause :
         * created_at : 2019-12-17 14:47:51
         * updated_at : 2019-12-17 14:47:51
         * status_title : 待审核
         * show_created_at : 12-17 14:47
         * show_updated_at : 12-17 14:47
         */

        private String id;
        private String sn;
        private int type;
        private String wallet_addr;
        private String input_money;
        private String money;
        private String usdt_price;
        private int status;
        private String status_rejected_cause;
        private String created_at;
        private String updated_at;
        private String status_title;
        private String show_created_at;
        private String show_updated_at;

        public String getUsdt_price() {
            return usdt_price;
        }

        public void setUsdt_price(String usdt_price) {
            this.usdt_price = usdt_price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getWallet_addr() {
            return wallet_addr;
        }

        public void setWallet_addr(String wallet_addr) {
            this.wallet_addr = wallet_addr;
        }

        public String getInput_money() {
            return input_money;
        }

        public void setInput_money(String input_money) {
            this.input_money = input_money;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_rejected_cause() {
            return status_rejected_cause;
        }

        public void setStatus_rejected_cause(String status_rejected_cause) {
            this.status_rejected_cause = status_rejected_cause;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }

        public String getShow_created_at() {
            return show_created_at;
        }

        public void setShow_created_at(String show_created_at) {
            this.show_created_at = show_created_at;
        }

        public String getShow_updated_at() {
            return show_updated_at;
        }

        public void setShow_updated_at(String show_updated_at) {
            this.show_updated_at = show_updated_at;
        }
    }

    public static class AudWireTransferBean {
        /**
         * bank_title : xxx
         * bank_card_proceeds_name : xxx
         * bank_card_account : xxx
         * bank_swift_code : xxx
         * bank_aba_code : xxx
         */

        private String bank_title;
        private String bank_card_proceeds_name;
        private String bank_card_account;
        private String bank_swift_code;
        private String bank_aba_code;

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

        public String getBank_swift_code() {
            return bank_swift_code;
        }

        public void setBank_swift_code(String bank_swift_code) {
            this.bank_swift_code = bank_swift_code;
        }

        public String getBank_aba_code() {
            return bank_aba_code;
        }

        public void setBank_aba_code(String bank_aba_code) {
            this.bank_aba_code = bank_aba_code;
        }
    }
}
