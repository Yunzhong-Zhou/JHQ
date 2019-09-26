package com.cho.chocoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/2.
 */
public class RechargeDetailModel implements Serializable {
    /**
     * wechat : 84e3dd01b79e558168eea95a2b62654e_1
     * alipay : 84e3dd01b79e558168eea95a2b62654e_2
     * unionpay :
     * ewm :
     * ewm_qrcode :
     * bank_title :
     * bank_card_account :
     * bank_card_proceeds_name :
     * bank_address :
     * top_up : {"wallet_addr":"","sn":"TU1566635781495610","money_type":3,"pay_type":1,"eth_price":"307.78","cho_price":"1","cny_price":"7","txid":"","input_money":"100","money":"14.29","status":1,"status_rejected_cause":"","created_at":"2019-08-24 16:36:21","updated_at":"2019-08-24 16:36:21","money_type_title":"CNY","status_title":"待审核","show_created_at":"08-24 16:36","show_updated_at":"08-24 16:36"}
     */

    private String wechat;
    private String alipay;
    private String unionpay;
    private String ewm;
    private String ewm_qrcode;
    private String bank_title;
    private String bank_card_account;
    private String bank_card_proceeds_name;
    private String bank_address;
    private TopUpBean top_up;

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getUnionpay() {
        return unionpay;
    }

    public void setUnionpay(String unionpay) {
        this.unionpay = unionpay;
    }

    public String getEwm() {
        return ewm;
    }

    public void setEwm(String ewm) {
        this.ewm = ewm;
    }

    public String getEwm_qrcode() {
        return ewm_qrcode;
    }

    public void setEwm_qrcode(String ewm_qrcode) {
        this.ewm_qrcode = ewm_qrcode;
    }

    public String getBank_title() {
        return bank_title;
    }

    public void setBank_title(String bank_title) {
        this.bank_title = bank_title;
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

    public String getBank_address() {
        return bank_address;
    }

    public void setBank_address(String bank_address) {
        this.bank_address = bank_address;
    }

    public TopUpBean getTop_up() {
        return top_up;
    }

    public void setTop_up(TopUpBean top_up) {
        this.top_up = top_up;
    }

    public static class TopUpBean {
        /**
         * wallet_addr :
         * sn : TU1566635781495610
         * money_type : 3
         * pay_type : 1
         * eth_price : 307.78
         * cho_price : 1
         * cny_price : 7
         * txid :
         * input_money : 100
         * money : 14.29
         * status : 1
         * status_rejected_cause :
         * created_at : 2019-08-24 16:36:21
         * updated_at : 2019-08-24 16:36:21
         * money_type_title : CNY
         * status_title : 待审核
         * show_created_at : 08-24 16:36
         * show_updated_at : 08-24 16:36
         */

        private String id;
        private String wallet_addr;
        private String sn;
        private int money_type;
        private int pay_type;
        private int pay_detail_type;
        private String eth_price;
        private String cho_price;
        private String cny_price;
        private String txid;
        private String input_money;
        private String money;
        private int status;
        private String status_rejected_cause;
        private String created_at;
        private String updated_at;
        private String money_type_title;
        private String status_title;
        private String show_created_at;
        private String show_updated_at;

        public int getPay_detail_type() {
            return pay_detail_type;
        }

        public void setPay_detail_type(int pay_detail_type) {
            this.pay_detail_type = pay_detail_type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWallet_addr() {
            return wallet_addr;
        }

        public void setWallet_addr(String wallet_addr) {
            this.wallet_addr = wallet_addr;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public int getMoney_type() {
            return money_type;
        }

        public void setMoney_type(int money_type) {
            this.money_type = money_type;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
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

        public String getCny_price() {
            return cny_price;
        }

        public void setCny_price(String cny_price) {
            this.cny_price = cny_price;
        }

        public String getTxid() {
            return txid;
        }

        public void setTxid(String txid) {
            this.txid = txid;
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

        public String getMoney_type_title() {
            return money_type_title;
        }

        public void setMoney_type_title(String money_type_title) {
            this.money_type_title = money_type_title;
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
}
