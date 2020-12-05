package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-18.
 */
public class SetAddressModel implements Serializable {
    /**
     * usdt_wallet_addr :
     * ofc_wallet_addr : sfgddfghhhhgfddssfggh
     * trade_password : e10adc3949ba59abbe56e057f20f883e
     */

    private String usdt_wallet_addr;
    private String ofc_wallet_addr;
    private String trade_password;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUsdt_wallet_addr() {
        return usdt_wallet_addr;
    }

    public void setUsdt_wallet_addr(String usdt_wallet_addr) {
        this.usdt_wallet_addr = usdt_wallet_addr;
    }

    public String getOfc_wallet_addr() {
        return ofc_wallet_addr;
    }

    public void setOfc_wallet_addr(String ofc_wallet_addr) {
        this.ofc_wallet_addr = ofc_wallet_addr;
    }

    public String getTrade_password() {
        return trade_password;
    }

    public void setTrade_password(String trade_password) {
        this.trade_password = trade_password;
    }
}
