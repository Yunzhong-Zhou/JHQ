package com.filter.filter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/11/16.
 */
public class USDTListModel2 implements Serializable {
    private List<UsdtSellListBean> usdt_sell_list;

    public List<UsdtSellListBean> getUsdt_sell_list() {
        return usdt_sell_list;
    }

    public void setUsdt_sell_list(List<UsdtSellListBean> usdt_sell_list) {
        this.usdt_sell_list = usdt_sell_list;
    }

    public static class UsdtSellListBean {
        /**
         * id : 5d92c33f0c5af96a884c5d409396a9a4
         * member_head : /head/435.png
         * member_nickname : FIL_qeby
         * usdt_cny_price : 6.70
         * amount_money : 100.0000
         * current_money : 0.0000
         * status : 1
         * created_at : 2021-01-16 10:30:06
         * status_title : 进行中
         * residue_money : 100.0000
         */

        private String id;
        private String member_head;
        private String member_nickname;
        private String usdt_cny_price;
        private String amount_money;
        private String current_money;
        private int status;
        private String created_at;
        private String status_title;
        private String residue_money;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getUsdt_cny_price() {
            return usdt_cny_price;
        }

        public void setUsdt_cny_price(String usdt_cny_price) {
            this.usdt_cny_price = usdt_cny_price;
        }

        public String getAmount_money() {
            return amount_money;
        }

        public void setAmount_money(String amount_money) {
            this.amount_money = amount_money;
        }

        public String getCurrent_money() {
            return current_money;
        }

        public void setCurrent_money(String current_money) {
            this.current_money = current_money;
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

        public String getResidue_money() {
            return residue_money;
        }

        public void setResidue_money(String residue_money) {
            this.residue_money = residue_money;
        }
    }
}
