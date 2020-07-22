package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/7/21.
 */
public class FenHongListModel implements Serializable {
    /**
     * invest : {"id":"3ebf0f0130fdc976f8251f9ace75fdbe","type":1,"ofc_money":"0.00","ofc_price":"1.00","interest_money":"0.03","status":1,"created_at":"2020-07-21 19:29:56","type_title":"Buy","status_title":"Underway","unfreeze_at":"2021-07-16","appreciation":0,"interest_list":[{"invest_id":"3ebf0f0130fdc976f8251f9ace75fdbe","rate":"0.0010","money":"0.01","created_at":"2020-07-22 15:40:56"},{"invest_id":"3ebf0f0130fdc976f8251f9ace75fdbe","rate":"0.0010","money":"0.01","created_at":"2020-07-22 15:37:22"},{"invest_id":"3ebf0f0130fdc976f8251f9ace75fdbe","rate":"0.0000","money":"0.01","created_at":"2020-07-22 15:31:47"}]}
     */

    private InvestBean invest;

    public InvestBean getInvest() {
        return invest;
    }

    public void setInvest(InvestBean invest) {
        this.invest = invest;
    }

    public static class InvestBean {
        /**
         * id : 3ebf0f0130fdc976f8251f9ace75fdbe
         * type : 1
         * ofc_money : 0.00
         * ofc_price : 1.00
         * interest_money : 0.03
         * status : 1
         * created_at : 2020-07-21 19:29:56
         * type_title : Buy
         * status_title : Underway
         * unfreeze_at : 2021-07-16
         * appreciation : 0
         * interest_list : [{"invest_id":"3ebf0f0130fdc976f8251f9ace75fdbe","rate":"0.0010","money":"0.01","created_at":"2020-07-22 15:40:56"},{"invest_id":"3ebf0f0130fdc976f8251f9ace75fdbe","rate":"0.0010","money":"0.01","created_at":"2020-07-22 15:37:22"},{"invest_id":"3ebf0f0130fdc976f8251f9ace75fdbe","rate":"0.0000","money":"0.01","created_at":"2020-07-22 15:31:47"}]
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
        private List<InterestListBean> interest_list;

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

        public List<InterestListBean> getInterest_list() {
            return interest_list;
        }

        public void setInterest_list(List<InterestListBean> interest_list) {
            this.interest_list = interest_list;
        }

        public static class InterestListBean {
            /**
             * invest_id : 3ebf0f0130fdc976f8251f9ace75fdbe
             * rate : 0.0010
             * money : 0.01
             * created_at : 2020-07-22 15:40:56
             */

            private String invest_id;
            private String rate;
            private String money;
            private String created_at;

            public String getInvest_id() {
                return invest_id;
            }

            public void setInvest_id(String invest_id) {
                this.invest_id = invest_id;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }
    }
}
