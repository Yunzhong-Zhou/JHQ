package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-31.
 */
public class LivenessModel implements Serializable {
    /**
     * tradeNo : 19123113081780819
     * score : 0.0
     * remark : 比对成功
     * code : 0
     */

    private String tradeNo;
    private String score;
    private String remark;
    private String code;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
