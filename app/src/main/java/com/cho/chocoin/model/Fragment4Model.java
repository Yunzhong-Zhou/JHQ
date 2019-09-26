package com.cho.chocoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/2.
 */
public class Fragment4Model implements Serializable {
    /**
     * eth : {"wallet_addr":"请在提交后，查看充币地址！","top_up_min_money":"0.2","top_up_max_money":"2000","price":"307.78"}
     * cho : {"wallet_addr":"请在提交后，查看充币地址！","top_up_min_money":"1","top_up_max_money":"10000","price":"1"}
     * cny : {"top_up_min_money":"100","top_up_max_money":"5000","price":"7"}
     * wechat : 84e3dd01b79e558168eea95a2b62654e_1
     * alipay : 84e3dd01b79e558168eea95a2b62654e_2
     * unionpay :
     * ewm : 84e3dd01b79e558168eea95a2b62654e_4
     * common_usable_money : 0
     * principal_money : 999999
     * id :
     */

    private EthBean eth;
    private ChoBean cho;
    private CnyBean cny;
    private String wechat;
    private String alipay;
    private String unionpay;
    private String ewm;
    private String common_usable_money;
    private int principal_money;
    private String id;

    public EthBean getEth() {
        return eth;
    }

    public void setEth(EthBean eth) {
        this.eth = eth;
    }

    public ChoBean getCho() {
        return cho;
    }

    public void setCho(ChoBean cho) {
        this.cho = cho;
    }

    public CnyBean getCny() {
        return cny;
    }

    public void setCny(CnyBean cny) {
        this.cny = cny;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getUnionpay() {
        return unionpay;
    }

    public void setUnionpay(String unionpay) {
        this.unionpay = unionpay;
    }

    public String getEwm() {
        return ewm;
    }

    public void setEwm(String ewm) {
        this.ewm = ewm;
    }

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }

    public int getPrincipal_money() {
        return principal_money;
    }

    public void setPrincipal_money(int principal_money) {
        this.principal_money = principal_money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class EthBean {
        /**
         * wallet_addr : 请在提交后，查看充币地址！
         * top_up_min_money : 0.2
         * top_up_max_money : 2000
         * price : 307.78
         */

        private String wallet_addr;
        private String top_up_min_money;
        private String top_up_max_money;
        private String price;

        public String getWallet_addr() {
            return wallet_addr;
        }

        public void setWallet_addr(String wallet_addr) {
            this.wallet_addr = wallet_addr;
        }

        public String getTop_up_min_money() {
            return top_up_min_money;
        }

        public void setTop_up_min_money(String top_up_min_money) {
            this.top_up_min_money = top_up_min_money;
        }

        public String getTop_up_max_money() {
            return top_up_max_money;
        }

        public void setTop_up_max_money(String top_up_max_money) {
            this.top_up_max_money = top_up_max_money;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    public static class ChoBean {
        /**
         * wallet_addr : 请在提交后，查看充币地址！
         * top_up_min_money : 1
         * top_up_max_money : 10000
         * price : 1
         */

        private String wallet_addr;
        private String top_up_min_money;
        private String top_up_max_money;
        private String price;

        public String getWallet_addr() {
            return wallet_addr;
        }

        public void setWallet_addr(String wallet_addr) {
            this.wallet_addr = wallet_addr;
        }

        public String getTop_up_min_money() {
            return top_up_min_money;
        }

        public void setTop_up_min_money(String top_up_min_money) {
            this.top_up_min_money = top_up_min_money;
        }

        public String getTop_up_max_money() {
            return top_up_max_money;
        }

        public void setTop_up_max_money(String top_up_max_money) {
            this.top_up_max_money = top_up_max_money;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    public static class CnyBean {
        /**
         * top_up_min_money : 100
         * top_up_max_money : 5000
         * price : 7
         */

        private String top_up_min_money;
        private String top_up_max_money;
        private String price;

        public String getTop_up_min_money() {
            return top_up_min_money;
        }

        public void setTop_up_min_money(String top_up_min_money) {
            this.top_up_min_money = top_up_min_money;
        }

        public String getTop_up_max_money() {
            return top_up_max_money;
        }

        public void setTop_up_max_money(String top_up_max_money) {
            this.top_up_max_money = top_up_max_money;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
