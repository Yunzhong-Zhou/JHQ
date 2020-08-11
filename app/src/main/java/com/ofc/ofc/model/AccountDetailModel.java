package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/7/21.
 */
public class AccountDetailModel implements Serializable {

    /**
     * ofc_money : 0.00
     * ofc_commission_money : 0.00
     * interest_money : 0.00
     * appreciation : 0
     * unfreeze_money : 0
     * earning_list : [{"id":"3ebf0f0130fdc976f8251f9ace75fdbe","type":1,"ofc_money":"0.00","ofc_price":"1.00","interest_money":"0.00","status":1,"created_at":"2020-07-21 19:29:56","type_title":"Buy","status_title":"Underway","unfreeze_at":"2021-07-16","appreciation":0}]
     * expenditure_list : [{"id":"3ebf0f0130fdc976f8251f9ace75fdbe","type":1,"ofc_money":"0.00","ofc_price":"1.00","interest_money":"0.00","status":1,"created_at":"2020-07-21 19:29:56","type_title":"Buy","status_title":"Underway","unfreeze_at":"2021-07-16","appreciation":0}]
     */

    private String ofc_usable_money;
    private String ofc_invest_money;
    private String ofc_commission_money;
    private String interest_money;
    private String appreciation;
    private String unfreeze_money;
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

    public String getUnfreeze_money() {
        return unfreeze_money;
    }

    public void setUnfreeze_money(String unfreeze_money) {
        this.unfreeze_money = unfreeze_money;
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
         * id : 3ebf0f0130fdc976f8251f9ace75fdbe
         * type : 1
         * ofc_money : 0.00
         * ofc_price : 1.00
         * interest_money : 0.00
         * status : 1
         * created_at : 2020-07-21 19:29:56
         * type_title : Buy
         * status_title : Underway
         * unfreeze_at : 2021-07-16
         * appreciation : 0
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
        private int appreciation;

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

        public int getAppreciation() {
            return appreciation;
        }

        public void setAppreciation(int appreciation) {
            this.appreciation = appreciation;
        }
    }

    public static class ExpenditureListBean {
        /**
         * id : 3ebf0f0130fdc976f8251f9ace75fdbe
         * type : 1
         * ofc_money : 0.00
         * ofc_price : 1.00
         * interest_money : 0.00
         * status : 1
         * created_at : 2020-07-21 19:29:56
         * type_title : Buy
         * status_title : Underway
         * unfreeze_at : 2021-07-16
         * appreciation : 0
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
        private int appreciation;

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

        public int getAppreciation() {
            return appreciation;
        }

        public void setAppreciation(int appreciation) {
            this.appreciation = appreciation;
        }
    }
}
