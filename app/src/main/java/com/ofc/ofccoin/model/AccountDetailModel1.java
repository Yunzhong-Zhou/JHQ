package com.ofc.ofccoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/6.
 */
public class AccountDetailModel1 implements Serializable {
    /**
     * common_usable_money : 23.08
     * contract_money : 10
     * profit_money : 0
     * commission_money : 0
     * earning_list : [{"title":"充币","money":"6.89","status":"完成","created_at":"2019-12-18 12:11:59"},{"title":"充币","money":"10","status":"完成","created_at":"2019-12-18 12:11:01"},{"title":"充币","money":"10","status":"完成","created_at":"2019-12-18 11:47:37"},{"title":"转入","money":"1","status":"完成","created_at":"2019-12-17 19:56:58"},{"title":"转入","money":"1","status":"完成","created_at":"2019-12-17 19:44:14"},{"title":"转入","money":"10","status":"完成","created_at":"2019-12-17 19:22:12"},{"title":"充币","money":"10","status":"完成","created_at":"2019-12-17 14:47:51"},{"title":"充币","money":"6.89","status":"完成","created_at":"2019-12-17 14:47:37"},{"title":"签到","money":"0.1","status":"完成","created_at":"2019-12-17 10:55:13"},{"title":"签到","money":"0.2","status":"完成","created_at":"2019-12-16 18:35:24"}]
     * expenditure_list : [{"title":"转出","money":"1","status":"完成","created_at":"2019-12-18 15:47:56"},{"title":"申请合约","money":"5","status":"完成","created_at":"2019-12-18 15:43:45"},{"title":"提币","money":"10","status":"待审核","created_at":"2019-12-17 21:41:50"},{"title":"提币","money":"10","status":"待审核","created_at":"2019-12-17 18:23:57"},{"title":"转出","money":"2","status":"完成","created_at":"2019-12-17 17:52:55"}]
     */

    private String common_usable_money;
    private String contract_money;
    private String profit_money;
    private String commission_money;
    private List<EarningListBean> earning_list;
    private List<ExpenditureListBean> expenditure_list;

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }

    public String getContract_money() {
        return contract_money;
    }

    public void setContract_money(String contract_money) {
        this.contract_money = contract_money;
    }

    public String getProfit_money() {
        return profit_money;
    }

    public void setProfit_money(String profit_money) {
        this.profit_money = profit_money;
    }

    public String getCommission_money() {
        return commission_money;
    }

    public void setCommission_money(String commission_money) {
        this.commission_money = commission_money;
    }

    public List<EarningListBean> getEarning_list() {
        return earning_list;
    }

    public void setEarning_list(List<EarningListBean> earning_list) {
        this.earning_list = earning_list;
    }

    public List<ExpenditureListBean> getExpenditure_list() {
        return expenditure_list;
    }

    public void setExpenditure_list(List<ExpenditureListBean> expenditure_list) {
        this.expenditure_list = expenditure_list;
    }

    public static class EarningListBean {
        /**
         * title : 充币
         * money : 6.89
         * status : 完成
         * created_at : 2019-12-18 12:11:59
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

    public static class ExpenditureListBean {
        /**
         * title : 转出
         * money : 1
         * status : 完成
         * created_at : 2019-12-18 15:47:56
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
