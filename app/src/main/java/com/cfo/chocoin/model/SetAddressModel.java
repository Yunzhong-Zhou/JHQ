package com.cfo.chocoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-18.
 */
public class SetAddressModel implements Serializable {
    /**
     * usdt_wallet_addr : djdjdjdjdjd
     */

    private String usdt_wallet_addr;

    public String getUsdt_wallet_addr() {
        return usdt_wallet_addr;
    }

    public void setUsdt_wallet_addr(String usdt_wallet_addr) {
        this.usdt_wallet_addr = usdt_wallet_addr;
    }
}
