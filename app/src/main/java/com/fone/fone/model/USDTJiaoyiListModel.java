package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/11/17.
 */
public class USDTJiaoyiListModel implements Serializable {
    private List<DoUsdtDealListBean> do_usdt_deal_list;

    public List<DoUsdtDealListBean> getDo_usdt_deal_list() {
        return do_usdt_deal_list;
    }

    public void setDo_usdt_deal_list(List<DoUsdtDealListBean> do_usdt_deal_list) {
        this.do_usdt_deal_list = do_usdt_deal_list;
    }

    public static class DoUsdtDealListBean {
        /**
         * id : 487328b300d78530b68a188d2ff0e8e4
         * type : buy
         * type_title : 购买
         * opposite_member_nickname : FIL_qeby
         * usdt_cny_price : 6.70
         * usdt_money : 1.0000
         * cny_money : null
         * status : 1
         * status_title : 待付款
         * created_at : 2021/01/16 10:43
         */

        private String id;
        private String type;
        private String type_title;
        private String opposite_member_nickname;
        private String usdt_cny_price;
        private String usdt_money;
        private Object cny_money;
        private int status;
        private String status_title;
        private String created_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType_title() {
            return type_title;
        }

        public void setType_title(String type_title) {
            this.type_title = type_title;
        }

        public String getOpposite_member_nickname() {
            return opposite_member_nickname;
        }

        public void setOpposite_member_nickname(String opposite_member_nickname) {
            this.opposite_member_nickname = opposite_member_nickname;
        }

        public String getUsdt_cny_price() {
            return usdt_cny_price;
        }

        public void setUsdt_cny_price(String usdt_cny_price) {
            this.usdt_cny_price = usdt_cny_price;
        }

        public String getUsdt_money() {
            return usdt_money;
        }

        public void setUsdt_money(String usdt_money) {
            this.usdt_money = usdt_money;
        }

        public Object getCny_money() {
            return cny_money;
        }

        public void setCny_money(Object cny_money) {
            this.cny_money = cny_money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
