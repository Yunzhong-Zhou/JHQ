package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-18.
 */
public class SetAddressModel implements Serializable {
    /**
     * usdt_wallet_addr : djdjdjdjdjd
     */

    private String usdt_wallet_addr;
    private String trade_password;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTrade_password() {
        return trade_password;
    }

    public void setTrade_password(String trade_password) {
        this.trade_password = trade_password;
    }

    public String getUsdt_wallet_addr() {
        return usdt_wallet_addr;
    }

    public void setUsdt_wallet_addr(String usdt_wallet_addr) {
        this.usdt_wallet_addr = usdt_wallet_addr;
    }
}
