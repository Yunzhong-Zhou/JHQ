package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/11/16.
 */
public class USDTListModel2 implements Serializable {
    private List<UsdtDealListBean> usdt_deal_list;

    public List<UsdtDealListBean> getUsdt_deal_list() {
        return usdt_deal_list;
    }

    public void setUsdt_deal_list(List<UsdtDealListBean> usdt_deal_list) {
        this.usdt_deal_list = usdt_deal_list;
    }

    public static class UsdtDealListBean {
        /**
         * id : f918d9636dbb6cef827ea53c2dc8f4c9
         * buy_member_id : 8db69f40f118446f89ed5d402e3f7cbe
         * buy_member_head : /head.png
         * buy_member_nickname : 阿斯顿马丁
         * usdt_cny_price : 7.0000
         * money : 100.00
         * status : 1
         * created_at : 2020-11-16 20:24:03
         * status_title : 待匹配
         */

        private String id;
        private String buy_member_id;
        private String buy_member_head;
        private String buy_member_nickname;
        private String usdt_cny_price;
        private String money;
        private int status;
        private String created_at;
        private String status_title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBuy_member_id() {
            return buy_member_id;
        }

        public void setBuy_member_id(String buy_member_id) {
            this.buy_member_id = buy_member_id;
        }

        public String getBuy_member_head() {
            return buy_member_head;
        }

        public void setBuy_member_head(String buy_member_head) {
            this.buy_member_head = buy_member_head;
        }

        public String getBuy_member_nickname() {
            return buy_member_nickname;
        }

        public void setBuy_member_nickname(String buy_member_nickname) {
            this.buy_member_nickname = buy_member_nickname;
        }

        public String getUsdt_cny_price() {
            return usdt_cny_price;
        }

        public void setUsdt_cny_price(String usdt_cny_price) {
            this.usdt_cny_price = usdt_cny_price;
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

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }
    }
}
