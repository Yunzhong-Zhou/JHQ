package com.ofc.ofccoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-18.
 */
public class TransferModel implements Serializable {
    /**
     * common_usable_money : 7.19
     */

    private String common_usable_money;

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }
}
