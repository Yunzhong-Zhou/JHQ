package com.filter.filter.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/2.
 */
public class RechargeDetailModel implements Serializable {
    /**
     * top_up : {"id":"2329c32c367b9bd590d9690b50c3e434","sn":"TU1608437131561005","money_wallet_addr":"f1a7ba5584f3413caf9eb00e7aeedb15","money_type":1,"input_money":"10000.0000","money":"10000.0000","status":1,"status_rejected_cause":"","verify_at":null,"created_at":"2020-12-20 12:05:31","money_type_title":"USDT","status_title":"待审核"}
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private TopUpBean top_up;

    public TopUpBean getTop_up() {
        return top_up;
    }

    public void setTop_up(TopUpBean top_up) {
        this.top_up = top_up;
    }

    public static class TopUpBean {
        /**
         * id : 2329c32c367b9bd590d9690b50c3e434
         * sn : TU1608437131561005
         * money_wallet_addr : f1a7ba5584f3413caf9eb00e7aeedb15
         * money_type : 1
         * input_money : 10000.0000
         * money : 10000.0000
         * status : 1
         * status_rejected_cause :
         * verify_at : null
         * created_at : 2020-12-20 12:05:31
         * money_type_title : USDT
         * status_title : 待审核
         */

        private String id;
        private String sn;
        private String money_wallet_addr;
        private int money_type;
        private String input_money;
        private String money;
        private int status;
        private String status_rejected_cause;
        private String verify_at;
        private String created_at;
        private String money_type_title;
        private String status_title;

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

        public String getMoney_wallet_addr() {
            return money_wallet_addr;
        }

        public void setMoney_wallet_addr(String money_wallet_addr) {
            this.money_wallet_addr = money_wallet_addr;
        }

        public int getMoney_type() {
            return money_type;
        }

        public void setMoney_type(int money_type) {
            this.money_type = money_type;
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

        public String getVerify_at() {
            return verify_at;
        }

        public void setVerify_at(String verify_at) {
            this.verify_at = verify_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
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
    }
}
