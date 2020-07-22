package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/7/21.
 */
public class FenHongModel implements Serializable {
    /**
     * usdt_price : 1
     * ofc_price : null
     * ofc_index : 0.001
     * toal_appreciation : 0
     * last_appreciation : 0
     * common_usable_money : 98997.50
     * interest_money : 0.00
     * contract_money : 0.00
     * ofc_money : 0.00
     * ofc_issue_price : 1
     * ofc_price_list : []
     */

    private String usdt_price;
    private String ofc_price;
    private String ofc_index;
    private double toal_appreciation;
    private double last_appreciation;
    private String common_usable_money;
    private String interest_money;
    private String contract_money;
    private String ofc_money;
    private double ofc_issue_price;
    private List<?> ofc_price_list;

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

    public double getToal_appreciation() {
        return toal_appreciation;
    }

    public void setToal_appreciation(double toal_appreciation) {
        this.toal_appreciation = toal_appreciation;
    }

    public double getLast_appreciation() {
        return last_appreciation;
    }

    public void setLast_appreciation(double last_appreciation) {
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

    public String getOfc_money() {
        return ofc_money;
    }

    public void setOfc_money(String ofc_money) {
        this.ofc_money = ofc_money;
    }

    public double getOfc_issue_price() {
        return ofc_issue_price;
    }

    public void setOfc_issue_price(double ofc_issue_price) {
        this.ofc_issue_price = ofc_issue_price;
    }

    public List<?> getOfc_price_list() {
        return ofc_price_list;
    }

    public void setOfc_price_list(List<?> ofc_price_list) {
        this.ofc_price_list = ofc_price_list;
    }
}
