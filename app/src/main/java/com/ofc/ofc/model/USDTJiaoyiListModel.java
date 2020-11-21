package com.ofc.ofc.model;

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
         * type : buy
         * type_title : 购买
         * opposite_member_nickname :
         * usdt_cny_money : null
         * money : 100.00
         * status_title : 待匹配
         * created_at : 2020/11/16/20/24
         */

        private String id;
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String type;
        private String type_title;
        private String opposite_member_nickname;
        private String usdt_cny_money;
        private String money;
        private String status_title;
        private String created_at;

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

        public String getUsdt_cny_money() {
            return usdt_cny_money;
        }

        public void setUsdt_cny_money(String usdt_cny_money) {
            this.usdt_cny_money = usdt_cny_money;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
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
