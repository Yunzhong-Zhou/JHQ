package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/7/21.
 */
public class AccountDetailModel implements Serializable {
    /**
     * ofc_usable_money : 360.00
     * ofc_invest_money : 110.00
     * ofc_commission_money : 0.00
     * ofc_amount_money : 470
     * interest_money : 0.99
     * appreciation : 0.04
     * invest_list : [{"id":"c2781e789273df9dacd16472959ff51d","type":3,"ofc_money":"100.00","ofc_price":"218.00","interest_money":"0.90","status":1,"created_at":"2020-08-31 13:32:36","type_title":"质押","status_title":"进行中","unfreeze_at":"2021-08-26","appreciation":"0.00"},{"id":"c029d6616ee4e7c981d2484311972955","type":1,"ofc_money":"10.00","ofc_price":"217.00","interest_money":"0.09","status":1,"created_at":"2020-08-31 13:30:02","type_title":"自购","status_title":"进行中","unfreeze_at":"2021-08-26","appreciation":"0.46"}]
     * earning_list : [{"title":"充币","money":"500.00","status":"完成","created_at":"2020-08-31 13:32:07"},{"title":"OFC(自购)","money":"10.00","status":"进行中","created_at":"2020-08-31 13:30:02"}]
     * expenditure_list : [{"title":"充币","money":"10.00","status":"完成","created_at":"2020-08-31 14:25:55"},{"title":"充币","money":"10.00","status":"完成","created_at":"2020-08-31 14:24:59"},{"title":"提币","money":"10.00","status":"通过","created_at":"2020-08-31 13:36:29"},{"title":"充币","money":"10.00","status":"完成","created_at":"2020-08-31 13:34:52"}]
     */

    private String ofc_usable_money;
    private String ofc_invest_money;
    private String ofc_commission_money;
    private String ofc_amount_money;
    private String interest_money;
    private String appreciation;
    private List<InvestListBean> invest_list;
    private List<EarningListBean> earning_list;
    private List<ExpenditureListBean> expenditure_list;

    public String getOfc_usable_money() {
        return ofc_usable_money;
    }

    public void setOfc_usable_money(String ofc_usable_money) {
        this.ofc_usable_money = ofc_usable_money;
    }

    public String getOfc_invest_money() {
        return ofc_invest_money;
    }

    public void setOfc_invest_money(String ofc_invest_money) {
        this.ofc_invest_money = ofc_invest_money;
    }

    public String getOfc_commission_money() {
        return ofc_commission_money;
    }

    public void setOfc_commission_money(String ofc_commission_money) {
        this.ofc_commission_money = ofc_commission_money;
    }

    public String getOfc_amount_money() {
        return ofc_amount_money;
    }

    public void setOfc_amount_money(String ofc_amount_money) {
        this.ofc_amount_money = ofc_amount_money;
    }

    public String getInterest_money() {
        return interest_money;
    }

    public void setInterest_money(String interest_money) {
        this.interest_money = interest_money;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public List<InvestListBean> getInvest_list() {
        return invest_list;
    }

    public void setInvest_list(List<InvestListBean> invest_list) {
        this.invest_list = invest_list;
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

    public static class InvestListBean {
        /**
         * id : c2781e789273df9dacd16472959ff51d
         * type : 3
         * ofc_money : 100.00
         * ofc_price : 218.00
         * interest_money : 0.90
         * status : 1
         * created_at : 2020-08-31 13:32:36
         * type_title : 质押
         * status_title : 进行中
         * unfreeze_at : 2021-08-26
         * appreciation : 0.00
         */

        private String id;
        private int type;
        private String ofc_money;
        private String ofc_price;
        private String interest_money;
        private int status;
        private String created_at;
        private String type_title;
        private String status_title;
        private String unfreeze_at;
        private String appreciation;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getOfc_money() {
            return ofc_money;
        }

        public void setOfc_money(String ofc_money) {
            this.ofc_money = ofc_money;
        }

        public String getOfc_price() {
            return ofc_price;
        }

        public void setOfc_price(String ofc_price) {
            this.ofc_price = ofc_price;
        }

        public String getInterest_money() {
            return interest_money;
        }

        public void setInterest_money(String interest_money) {
            this.interest_money = interest_money;
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

        public String getType_title() {
            return type_title;
        }

        public void setType_title(String type_title) {
            this.type_title = type_title;
        }

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }

        public String getUnfreeze_at() {
            return unfreeze_at;
        }

        public void setUnfreeze_at(String unfreeze_at) {
            this.unfreeze_at = unfreeze_at;
        }

        public String getAppreciation() {
            return appreciation;
        }

        public void setAppreciation(String appreciation) {
            this.appreciation = appreciation;
        }
    }

    public static class EarningListBean {
        /**
         * title : 充币
         * money : 500.00
         * status : 完成
         * created_at : 2020-08-31 13:32:07
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
         * title : 充币
         * money : 10.00
         * status : 完成
         * created_at : 2020-08-31 14:25:55
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
