package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/11.
 */
public class GetBuyModel implements Serializable {
    /**
     * common_usable_money : 490791.4
     * min_block_money : 100
     * max_block_money : 50000
     */

    private String common_usable_money;
    private String min_block_money;
    private String max_block_money;

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }

    public String getMin_block_money() {
        return min_block_money;
    }

    public void setMin_block_money(String min_block_money) {
        this.min_block_money = min_block_money;
    }

    public String getMax_block_money() {
        return max_block_money;
    }

    public void setMax_block_money(String max_block_money) {
        this.max_block_money = max_block_money;
    }
}
