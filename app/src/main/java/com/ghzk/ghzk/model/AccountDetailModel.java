package com.ghzk.ghzk.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/6.
 */
public class AccountDetailModel implements Serializable {
    /**
     * usable_money : 100.0000
     * commission_money : 0.0000
     * earning_money : 100.0000
     * out_money_list : [{"title":"设备使用收益","money":"100","status":"完成","created_at":"2021-01-19 16:18:41"}]
     * in_money_list : [{"title":"设备使用收益","money":"100","status":"完成","created_at":"2021-01-19 16:18:41"}]
     */

    private String usable_money;
    private String commission_money;
    private String earning_money;
    private List<OutMoneyListBean> out_money_list;
    private List<InMoneyListBean> in_money_list;

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
    }

    public String getCommission_money() {
        return commission_money;
    }

    public void setCommission_money(String commission_money) {
        this.commission_money = commission_money;
    }

    public String getEarning_money() {
        return earning_money;
    }

    public void setEarning_money(String earning_money) {
        this.earning_money = earning_money;
    }

    public List<OutMoneyListBean> getOut_money_list() {
        return out_money_list;
    }

    public void setOut_money_list(List<OutMoneyListBean> out_money_list) {
        this.out_money_list = out_money_list;
    }

    public List<InMoneyListBean> getIn_money_list() {
        return in_money_list;
    }

    public void setIn_money_list(List<InMoneyListBean> in_money_list) {
        this.in_money_list = in_money_list;
    }

    public static class OutMoneyListBean {
        /**
         * title : 设备使用收益
         * money : 100
         * status : 完成
         * created_at : 2021-01-19 16:18:41
         */

        private String title;
        private String money;
        private String status;
        private String created_at;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

    public static class InMoneyListBean {
        /**
         * title : 设备使用收益
         * money : 100
         * status : 完成
         * created_at : 2021-01-19 16:18:41
         */

        private String title;
        private String money;
        private String status;
        private String created_at;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
