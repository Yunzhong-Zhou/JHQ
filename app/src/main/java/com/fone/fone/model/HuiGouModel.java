package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/3/1.
 */
public class HuiGouModel implements Serializable {
    /**
     * buy_back_ratio : 70
     * buy_back_company : xxx公司
     * buy_back_money : 140
     */

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    private String buy_back_ratio;
    private String buy_back_company;
    private String buy_back_money;
    private String trade_password;

    public String getTrade_password() {
        return trade_password;
    }

    public void setTrade_password(String trade_password) {
        this.trade_password = trade_password;
    }

    public String getBuy_back_ratio() {
        return buy_back_ratio;
    }

    public void setBuy_back_ratio(String buy_back_ratio) {
        this.buy_back_ratio = buy_back_ratio;
    }

    public String getBuy_back_company() {
        return buy_back_company;
    }

    public void setBuy_back_company(String buy_back_company) {
        this.buy_back_company = buy_back_company;
    }

    public String getBuy_back_money() {
        return buy_back_money;
    }

    public void setBuy_back_money(String buy_back_money) {
        this.buy_back_money = buy_back_money;
    }
}
