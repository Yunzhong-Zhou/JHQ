package com.cfo.chocoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/6.
 */
public class AccountDetailModel1 implements Serializable {
    /**
     * common_usable_money : 708.04
     * insurance_usable_money : 0.00
     * win_money : 178.2
     * commission_money : 0
     * earning_list : [{"title":"充值","money":"1000.74","status":"完成","created_at":"2019-10-21 15:45:16"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:40:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:38:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:36:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:36:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:34:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:34:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:32:02"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:32:02"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:30:02"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:30:02"},{"title":"保险返还","money":"0.4","status":"完成","created_at":"2019-09-28 16:28:02"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:28:02"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:28:02"},{"title":"保险返还","money":"0.4","status":"完成","created_at":"2019-09-28 16:26:02"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:26:02"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 16:24:01"},{"title":"保险返还","money":"0.4","status":"完成","created_at":"2019-09-28 16:24:01"},{"title":"保险返还","money":"0.4","status":"完成","created_at":"2019-09-28 16:22:01"},{"title":"保险返还","money":"0.4","status":"完成","created_at":"2019-09-28 16:20:01"},{"title":"保险返还","money":"0.4","status":"完成","created_at":"2019-09-28 16:18:01"},{"title":"保险返还","money":"0.4","status":"完成","created_at":"2019-09-28 16:16:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 11:38:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 11:36:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 11:34:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 11:32:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 11:30:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 11:28:01"},{"title":"保险返还","money":"0.2","status":"完成","created_at":"2019-09-28 11:26:01"}]
     * expenditure_list : [{"title":"转出","money":"100","status":"完成","created_at":"2019-10-22 11:41:42"},{"title":"转出","money":"100","status":"完成","created_at":"2019-10-22 11:37:01"},{"title":"提现","money":"100","status":"待审核","created_at":"2019-10-21 15:45:41"},{"title":"投保","money":"100","status":"完成","created_at":"2019-09-28 16:27:08"},{"title":"投保","money":"100","status":"完成","created_at":"2019-09-28 16:22:38"},{"title":"投保","money":"200","status":"完成","created_at":"2019-09-28 16:14:02"},{"title":"投保","money":"100","status":"完成","created_at":"2019-09-28 11:24:17"}]
     */

    private String common_usable_money;
    private String insurance_usable_money;
    private String win_money;
    private String commission_money;
    private List<EarningListBean> earning_list;
    private List<ExpenditureListBean> expenditure_list;

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }

    public String getInsurance_usable_money() {
        return insurance_usable_money;
    }

    public void setInsurance_usable_money(String insurance_usable_money) {
        this.insurance_usable_money = insurance_usable_money;
    }

    public String getWin_money() {
        return win_money;
    }

    public void setWin_money(String win_money) {
        this.win_money = win_money;
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
         * title : 充值
         * money : 1000.74
         * status : 完成
         * created_at : 2019-10-21 15:45:16
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
         * money : 100
         * status : 完成
         * created_at : 2019-10-22 11:41:42
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
