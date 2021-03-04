package com.filter.filter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/4.
 */
public class SuanLiListModel implements Serializable {
    private List<InvestListBean> invest_list;

    public List<InvestListBean> getInvest_list() {
        return invest_list;
    }

    public void setInvest_list(List<InvestListBean> invest_list) {
        this.invest_list = invest_list;
    }

    public static class InvestListBean {
        /**
         * id : 35cb9482f4b487a4d084db19b2a4030a
         * max_bonus_money : 350000.00
         * amount_bonus_money : 0.00
         * interest_bonus_money : 0.00
         * commission_bonus_money : 0.00
         * money : 100000
         * status : 1
         * created_at : 2019-06-11 15:00:05
         * status_title : 进行中
         */

        private String id;
        private String max_bonus_money;
        private String amount_bonus_money;
        private String interest_bonus_money;
        private String commission_bonus_money;
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

        public String getMax_bonus_money() {
            return max_bonus_money;
        }

        public void setMax_bonus_money(String max_bonus_money) {
            this.max_bonus_money = max_bonus_money;
        }

        public String getAmount_bonus_money() {
            return amount_bonus_money;
        }

        public void setAmount_bonus_money(String amount_bonus_money) {
            this.amount_bonus_money = amount_bonus_money;
        }

        public String getInterest_bonus_money() {
            return interest_bonus_money;
        }

        public void setInterest_bonus_money(String interest_bonus_money) {
            this.interest_bonus_money = interest_bonus_money;
        }

        public String getCommission_bonus_money() {
            return commission_bonus_money;
        }

        public void setCommission_bonus_money(String commission_bonus_money) {
            this.commission_bonus_money = commission_bonus_money;
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
