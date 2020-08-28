package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/7/21.
 */
public class FenHongModel implements Serializable {
    /**
     * ofc_usable_money : 0.00
     * usdt_price : 1
     * ofc_price : 210
     * ofc_index : 0.007
     * toal_appreciation : 20900
     * last_appreciation : 0
     * common_usable_money : 0.00
     * interest_money : 0.00
     * contract_money : 0.00
     * ofc_invest_money : 0.00
     * ofc_issue_price : 1
     * ofc_price_list : [{"price":"210.0000","created_at":"2020-07-22 16:44:29"},{"price":"200.0000","created_at":"2020-07-22 16:44:17"}]
     */

    private String ofc_usable_money;
    private String ofc_amount_money;
    private String usdt_price;
    private String ofc_price;
    private String ofc_index;
    private String toal_appreciation;
    private String last_appreciation;
    private String common_usable_money;
    private String interest_money;
    private String contract_money;
    private String ofc_invest_money;
    private String ofc_issue_price;
    private List<OfcPriceListBean> ofc_price_list;

    public String getOfc_amount_money() {
        return ofc_amount_money;
    }

    public void setOfc_amount_money(String ofc_amount_money) {
        this.ofc_amount_money = ofc_amount_money;
    }

    public String getOfc_usable_money() {
        return ofc_usable_money;
    }

    public void setOfc_usable_money(String ofc_usable_money) {
        this.ofc_usable_money = ofc_usable_money;
    }

    public String getUsdt_price() {
        return usdt_price;
    }

    public void setUsdt_price(String usdt_price) {
        this.usdt_price = usdt_price;
    }

    public String getOfc_price() {
        return ofc_price;
    }

    public void setOfc_price(String ofc_price) {
        this.ofc_price = ofc_price;
    }

    public String getOfc_index() {
        return ofc_index;
    }

    public void setOfc_index(String ofc_index) {
        this.ofc_index = ofc_index;
    }

    public String getToal_appreciation() {
        return toal_appreciation;
    }

    public void setToal_appreciation(String toal_appreciation) {
        this.toal_appreciation = toal_appreciation;
    }

    public String getLast_appreciation() {
        return last_appreciation;
    }

    public void setLast_appreciation(String last_appreciation) {
        this.last_appreciation = last_appreciation;
    }

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }

    public String getInterest_money() {
        return interest_money;
    }

    public void setInterest_money(String interest_money) {
        this.interest_money = interest_money;
    }

    public String getContract_money() {
        return contract_money;
    }

    public void setContract_money(String contract_money) {
        this.contract_money = contract_money;
    }

    public String getOfc_invest_money() {
        return ofc_invest_money;
    }

    public void setOfc_invest_money(String ofc_invest_money) {
        this.ofc_invest_money = ofc_invest_money;
    }

    public String getOfc_issue_price() {
        return ofc_issue_price;
    }

    public void setOfc_issue_price(String ofc_issue_price) {
        this.ofc_issue_price = ofc_issue_price;
    }

    public List<OfcPriceListBean> getOfc_price_list() {
        return ofc_price_list;
    }

    public void setOfc_price_list(List<OfcPriceListBean> ofc_price_list) {
        this.ofc_price_list = ofc_price_list;
    }

    public static class OfcPriceListBean {
        /**
         * price : 210.0000
         * created_at : 2020-07-22 16:44:29
         */

        private String price;
        private String created_at;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
