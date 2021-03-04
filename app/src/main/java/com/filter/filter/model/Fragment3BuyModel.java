package com.filter.filter.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/2/27.
 */
public class Fragment3BuyModel implements Serializable {
    /**
     * order : {"id":"37a56f3a73b1f564e70719040e156bb3","goods_id":"675f76eb37cbc5e02d22b17618bde805","sn":"OR1614403764505349","member_id":"5304cafc529e02a7838ef07167666600","member_type":1,"member_head":"/head/160.png","member_nickname":"空气净化器_bqtu","buy_type":1,"operation_type":1,"pay_type":4,"num":1,"price":"200.00","money":"200.00","status":1,"status_rejected_cause":"","verify_at":null,"bank_title":"","bank_card_proceeds_name":"","bank_card_account":"","pay_name":"","pay_order_id":"","created_at":"2021-02-27 13:29:24","updated_at":"2021-02-27 13:29:24","deleted_at":null}
     */

    private OrderBean order;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * id : 37a56f3a73b1f564e70719040e156bb3
         * goods_id : 675f76eb37cbc5e02d22b17618bde805
         * sn : OR1614403764505349
         * member_id : 5304cafc529e02a7838ef07167666600
         * member_type : 1
         * member_head : /head/160.png
         * member_nickname : 空气净化器_bqtu
         * buy_type : 1
         * operation_type : 1
         * pay_type : 4
         * num : 1
         * price : 200.00
         * money : 200.00
         * status : 1
         * status_rejected_cause :
         * verify_at : null
         * bank_title :
         * bank_card_proceeds_name :
         * bank_card_account :
         * pay_name :
         * pay_order_id :
         * created_at : 2021-02-27 13:29:24
         * updated_at : 2021-02-27 13:29:24
         * deleted_at : null
         */

        private String id;
        private String goods_id;
        private String sn;
        private String member_id;
        private int member_type;
        private String member_head;
        private String member_nickname;
        private int buy_type;
        private int operation_type;
        private int pay_type;
        private int num;
        private String price;
        private String money;
        private int status;
        private String status_rejected_cause;
        private Object verify_at;
        private String bank_title;
        private String bank_card_proceeds_name;
        private String bank_card_account;
        private String pay_name;
        private String pay_order_id;
        private String created_at;
        private String updated_at;
        private Object deleted_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public int getMember_type() {
            return member_type;
        }

        public void setMember_type(int member_type) {
            this.member_type = member_type;
        }

        public String getMember_head() {
            return member_head;
        }

        public void setMember_head(String member_head) {
            this.member_head = member_head;
        }

        public String getMember_nickname() {
            return member_nickname;
        }

        public void setMember_nickname(String member_nickname) {
            this.member_nickname = member_nickname;
        }

        public int getBuy_type() {
            return buy_type;
        }

        public void setBuy_type(int buy_type) {
            this.buy_type = buy_type;
        }

        public int getOperation_type() {
            return operation_type;
        }

        public void setOperation_type(int operation_type) {
            this.operation_type = operation_type;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public Object getVerify_at() {
            return verify_at;
        }

        public void setVerify_at(Object verify_at) {
            this.verify_at = verify_at;
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

        public String getPay_name() {
            return pay_name;
        }

        public void setPay_name(String pay_name) {
            this.pay_name = pay_name;
        }

        public String getPay_order_id() {
            return pay_order_id;
        }

        public void setPay_order_id(String pay_order_id) {
            this.pay_order_id = pay_order_id;
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

        public Object getDeleted_at() {
            return deleted_at;
        }

        public void setDeleted_at(Object deleted_at) {
            this.deleted_at = deleted_at;
        }
    }
}
