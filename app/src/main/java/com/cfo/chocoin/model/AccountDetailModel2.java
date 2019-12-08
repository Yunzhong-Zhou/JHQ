package com.cfo.chocoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/6.
 */
public class AccountDetailModel2 implements Serializable {
    private String overflow_commission_money;
    private String common_usable_money;
    private String principal_money;
    private String commission_money;
    private String win_money;
    private String interest_money;
    private List<EarningListBean> earning_list;
    private List<ExpenditureListBean> expenditure_list;

    public String getOverflow_commission_money() {
        return overflow_commission_money;
    }

    public void setOverflow_commission_money(String overflow_commission_money) {
        this.overflow_commission_money = overflow_commission_money;
    }

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }

    public String getPrincipal_money() {
        return principal_money;
    }

    public void setPrincipal_money(String principal_money) {
        this.principal_money = principal_money;
    }

    public String getCommission_money() {
        return commission_money;
    }

    public void setCommission_money(String commission_money) {
        this.commission_money = commission_money;
    }

    public String getWin_money() {
        return win_money;
    }

    public void setWin_money(String win_money) {
        this.win_money = win_money;
    }

    public String getInterest_money() {
        return interest_money;
    }

    public void setInterest_money(String interest_money) {
        this.interest_money = interest_money;
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
         * title : 竞猜中奖
         * money : 0.8
         * status : 完成
         * created_at : 2019-06-05 10:21:34
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
         * title : 竞猜
         * money : 0
         * status : 完成
         * created_at : 2019-06-06 15:37:06
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
