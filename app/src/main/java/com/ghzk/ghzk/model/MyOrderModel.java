package com.ghzk.ghzk.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/2/27.
 */
public class MyOrderModel implements Serializable {
    /**
     * usable_money : 0.0000
     * order_list : [{"id":"76ce448783b5d37c8f59d2e33e92b2c7","sn":"OR1618281489535351","buy_type":2,"operation_type":1,"num":1,"price":"7900.00","money":"7900.00","status":1,"created_at":"2021-04-13 10:38:09","buy_type_title":"能回购","operation_type_title":"代运营","status_title":"待付款"}]
     */

    private String usable_money;
    private List<OrderListBean> order_list;

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
    }

    public List<OrderListBean> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<OrderListBean> order_list) {
        this.order_list = order_list;
    }

    public static class OrderListBean {
        /**
         * id : 76ce448783b5d37c8f59d2e33e92b2c7
         * sn : OR1618281489535351
         * buy_type : 2
         * operation_type : 1
         * num : 1
         * price : 7900.00
         * money : 7900.00
         * status : 1
         * created_at : 2021-04-13 10:38:09
         * buy_type_title : 能回购
         * operation_type_title : 代运营
         * status_title : 待付款
         */

        private String id;
        private String sn;
        private int buy_type;
        private int operation_type;
        private int num;
        private String price;
        private String money;
        private int status;
        private String created_at;
        private String buy_type_title;
        private String operation_type_title;
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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getBuy_type_title() {
            return buy_type_title;
        }

        public void setBuy_type_title(String buy_type_title) {
            this.buy_type_title = buy_type_title;
        }

        public String getOperation_type_title() {
            return operation_type_title;
        }

        public void setOperation_type_title(String operation_type_title) {
            this.operation_type_title = operation_type_title;
        }

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }
    }
}
