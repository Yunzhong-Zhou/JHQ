package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-18.
 */
public class SetAddressModel implements Serializable {
    /**
     * usdt_wallet_addr :
     * fil_wallet_addr :
     * trade_password :
     */

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private String usdt_wallet_addr;
    private String fil_wallet_addr;
    private String trade_password;

    public String getUsdt_wallet_addr() {
        return usdt_wallet_addr;
    }

    public void setUsdt_wallet_addr(String usdt_wallet_addr) {
        this.usdt_wallet_addr = usdt_wallet_addr;
    }

    public String getFil_wallet_addr() {
        return fil_wallet_addr;
    }

    public void setFil_wallet_addr(String fil_wallet_addr) {
        this.fil_wallet_addr = fil_wallet_addr;
    }

    public String getTrade_password() {
        return trade_password;
    }

    public void setTrade_password(String trade_password) {
        this.trade_password = trade_password;
    }
}
