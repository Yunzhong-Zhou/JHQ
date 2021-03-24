package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/12/20.
 */
public class FILWalletModel implements Serializable {
    /**
     * usable_fil_money : 0.0000
     * fil_money : 0.0000
     * estimate_fil_money : 0
     * in_fil_money : 0.0000
     * out_fil_money : 0.0000
     */

    private String usable_fil_money;
    private String fil_money;
    private String hashrate;
    private String in_fil_money;
    private String out_fil_money;

    public String getUsable_fil_money() {
        return usable_fil_money;
    }

    public void setUsable_fil_money(String usable_fil_money) {
        this.usable_fil_money = usable_fil_money;
    }

    public String getFil_money() {
        return fil_money;
    }

    public void setFil_money(String fil_money) {
        this.fil_money = fil_money;
    }

    public String getHashrate() {
        return hashrate;
    }

    public void setHashrate(String hashrate) {
        this.hashrate = hashrate;
    }

    public String getIn_fil_money() {
        return in_fil_money;
    }

    public void setIn_fil_money(String in_fil_money) {
        this.in_fil_money = in_fil_money;
    }

    public String getOut_fil_money() {
        return out_fil_money;
    }

    public void setOut_fil_money(String out_fil_money) {
        this.out_fil_money = out_fil_money;
    }
}
