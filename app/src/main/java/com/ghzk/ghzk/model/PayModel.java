package com.ghzk.ghzk.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/11.
 */
public class PayModel implements Serializable {

    private String alipay;
    /**
     * wecahtPay : {"appid":"wxe540385418282fe2","partnerid":"1607729670","prepayid":"wx121536492286792cd9afd4fa0d510c0000","noncestr":"6073f8914c919","timestamp":1618213009,"package":"Sign=WXPay","sign":"E041963D711F977BC9038D022B4CA6C9"}
     */

    private WecahtPayBean wecahtPay;

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public WecahtPayBean getWecahtPay() {
        return wecahtPay;
    }

    public void setWecahtPay(WecahtPayBean wecahtPay) {
        this.wecahtPay = wecahtPay;
    }

    public static class WecahtPayBean {
        /**
         * appid : wxe540385418282fe2
         * partnerid : 1607729670
         * prepayid : wx121536492286792cd9afd4fa0d510c0000
         * noncestr : 6073f8914c919
         * timestamp : 1618213009
         * package : Sign=WXPay
         * sign : E041963D711F977BC9038D022B4CA6C9
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;
        @SerializedName("package")
        private String packageX;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
