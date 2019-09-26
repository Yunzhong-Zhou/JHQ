package com.cho.chocoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/9.
 */

public class WalletAddressModel implements Serializable {
    /**
     * eth_wallet_addr :
     * cho_wallet_addr :
     */

    private String eth_wallet_addr;
    private String cho_wallet_addr;

    public String getEth_wallet_addr() {
        return eth_wallet_addr;
    }

    public void setEth_wallet_addr(String eth_wallet_addr) {
        this.eth_wallet_addr = eth_wallet_addr;
    }

    public String getCho_wallet_addr() {
        return cho_wallet_addr;
    }

    public void setCho_wallet_addr(String cho_wallet_addr) {
        this.cho_wallet_addr = cho_wallet_addr;
    }
}
