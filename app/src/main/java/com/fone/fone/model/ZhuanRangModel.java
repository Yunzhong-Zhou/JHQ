package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/5/31.
 */

public class ZhuanRangModel implements Serializable {
    /**
     * trade_password : e10adc3949ba59abbe56e057f20f883e
     * goods_sn :
     */

    private String trade_password;
    private String goods_sn;
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

    public String getGoods_sn() {
        return goods_sn;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
    }
}
