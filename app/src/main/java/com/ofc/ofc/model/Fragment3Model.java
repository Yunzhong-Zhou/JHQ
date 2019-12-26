package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/5/22.
 */
public class Fragment3Model implements Serializable {
    /**
     * reality_money : 4.00
     * result : 0
     * contract_status : 1
     * profit_money : 0.00
     * call_margin_money : 0.00
     * contract_trading : {"bourse_title":"第一个","bourse_on":1,"result":1,"direction":1,"lever":10,"buy_price":"1000.00","sell_price":"900.00","buy_at":"2019-12-18 17:22:45","sell_at":"2019-12-18 17:23:52","earning_money":"100.00","bourse_on_title":"BTC永续/USDT","direction_title":"做空","show_buy_at":"2019-12-18","show_sell_price":"2019-12-18"}
     * bourse_matching_list : [{"bourse_on":1,"money":"10.00","earning_money":"100.00","bourse_on_title":"BTC永续/USDT"}]
     * newest_contract_list : [{"member_head":"/head/313.png","member_nickname":"186****7626","money":"1","yield_rate":0,"created_at":"2019-12-18"},{"member_head":"/head/562.png","member_nickname":"183****3086","money":"5","yield_rate":0,"created_at":"2019-12-18"}]
     * contract_trading_list : [{"bourse_on":1,"money":"10.00","earning_money":"100.00","bourse_on_title":"BTC永续/USDT"}]
     * contract_call_margin_list : [{"bourse_on":1,"money":"10.00","earning_money":"100.00","bourse_on_title":"BTC永续/USDT"}]
     */

    private String reality_money;
    private int result;
    private int contract_status;
    private int contract_make_terminate;
    private String profit_money;
    private String call_margin_money;
    private ContractTradingBean contract_trading;
    private List<BourseMatchingListBean> bourse_matching_list;
    private List<NewestContractListBean> newest_contract_list;
    private List<ContractTradingListBean> contract_trading_list;
    private List<ContractCallMarginListBean> contract_call_margin_list;

    public int getContract_make_terminate() {
        return contract_make_terminate;
    }

    public void setContract_make_terminate(int contract_make_terminate) {
        this.contract_make_terminate = contract_make_terminate;
    }

    public String getReality_money() {
        return reality_money;
    }

    public void setReality_money(String reality_money) {
        this.reality_money = reality_money;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getContract_status() {
        return contract_status;
    }

    public void setContract_status(int contract_status) {
        this.contract_status = contract_status;
    }

    public String getProfit_money() {
        return profit_money;
    }

    public void setProfit_money(String profit_money) {
        this.profit_money = profit_money;
    }

    public String getCall_margin_money() {
        return call_margin_money;
    }

    public void setCall_margin_money(String call_margin_money) {
        this.call_margin_money = call_margin_money;
    }

    public ContractTradingBean getContract_trading() {
        return contract_trading;
    }

    public void setContract_trading(ContractTradingBean contract_trading) {
        this.contract_trading = contract_trading;
    }

    public List<BourseMatchingListBean> getBourse_matching_list() {
        return bourse_matching_list;
    }

    public void setBourse_matching_list(List<BourseMatchingListBean> bourse_matching_list) {
        this.bourse_matching_list = bourse_matching_list;
    }

    public List<NewestContractListBean> getNewest_contract_list() {
        return newest_contract_list;
    }

    public void setNewest_contract_list(List<NewestContractListBean> newest_contract_list) {
        this.newest_contract_list = newest_contract_list;
    }

    public List<ContractTradingListBean> getContract_trading_list() {
        return contract_trading_list;
    }

    public void setContract_trading_list(List<ContractTradingListBean> contract_trading_list) {
        this.contract_trading_list = contract_trading_list;
    }

    public List<ContractCallMarginListBean> getContract_call_margin_list() {
        return contract_call_margin_list;
    }

    public void setContract_call_margin_list(List<ContractCallMarginListBean> contract_call_margin_list) {
        this.contract_call_margin_list = contract_call_margin_list;
    }

    public static class ContractTradingBean {
        /**
         * bourse_title : 第一个
         * bourse_on : 1
         * result : 1
         * direction : 1
         * lever : 10
         * buy_price : 1000.00
         * sell_price : 900.00
         * buy_at : 2019-12-18 17:22:45
         * sell_at : 2019-12-18 17:23:52
         * earning_money : 100.00
         * bourse_on_title : BTC永续/USDT
         * direction_title : 做空
         * show_buy_at : 2019-12-18
         * show_sell_price : 2019-12-18
         */

        private String bourse_title;
        private int bourse_on;
        private int result;
        private int direction;
        private int lever;
        private String buy_price;
        private String sell_price;
        private String buy_at;
        private String sell_at;
        private String earning_money;
        private String bourse_on_title;
        private String direction_title;
        private String show_buy_at;
        private String show_sell_at;

        public String getBourse_title() {
            return bourse_title;
        }

        public void setBourse_title(String bourse_title) {
            this.bourse_title = bourse_title;
        }

        public int getBourse_on() {
            return bourse_on;
        }

        public void setBourse_on(int bourse_on) {
            this.bourse_on = bourse_on;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getLever() {
            return lever;
        }

        public void setLever(int lever) {
            this.lever = lever;
        }

        public String getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(String buy_price) {
            this.buy_price = buy_price;
        }

        public String getSell_price() {
            return sell_price;
        }

        public void setSell_price(String sell_price) {
            this.sell_price = sell_price;
        }

        public String getBuy_at() {
            return buy_at;
        }

        public void setBuy_at(String buy_at) {
            this.buy_at = buy_at;
        }

        public String getSell_at() {
            return sell_at;
        }

        public void setSell_at(String sell_at) {
            this.sell_at = sell_at;
        }

        public String getEarning_money() {
            return earning_money;
        }

        public void setEarning_money(String earning_money) {
            this.earning_money = earning_money;
        }

        public String getBourse_on_title() {
            return bourse_on_title;
        }

        public void setBourse_on_title(String bourse_on_title) {
            this.bourse_on_title = bourse_on_title;
        }

        public String getDirection_title() {
            return direction_title;
        }

        public void setDirection_title(String direction_title) {
            this.direction_title = direction_title;
        }

        public String getShow_buy_at() {
            return show_buy_at;
        }

        public void setShow_buy_at(String show_buy_at) {
            this.show_buy_at = show_buy_at;
        }

        public String getShow_sell_at() {
            return show_sell_at;
        }

        public void setShow_sell_at(String show_sell_at) {
            this.show_sell_at = show_sell_at;
        }
    }

    public static class BourseMatchingListBean {

        /**
         * bourse_title : 第一个
         * bourse_icon :
         * bourse_on : 1
         * status : 1
         * bourse_on_title : BTC永续/USDT
         * status_title : 交易中
         */

        private String bourse_title;
        private String bourse_icon;
        private int bourse_on;
        private int status;
        private String bourse_on_title;
        private String status_title;

        public String getBourse_title() {
            return bourse_title;
        }

        public void setBourse_title(String bourse_title) {
            this.bourse_title = bourse_title;
        }

        public String getBourse_icon() {
            return bourse_icon;
        }

        public void setBourse_icon(String bourse_icon) {
            this.bourse_icon = bourse_icon;
        }

        public int getBourse_on() {
            return bourse_on;
        }

        public void setBourse_on(int bourse_on) {
            this.bourse_on = bourse_on;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getBourse_on_title() {
            return bourse_on_title;
        }

        public void setBourse_on_title(String bourse_on_title) {
            this.bourse_on_title = bourse_on_title;
        }

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }
    }

    public static class NewestContractListBean {
        /**
         * member_head : /head/313.png
         * member_nickname : 186****7626
         * money : 1
         * yield_rate : 0
         * created_at : 2019-12-18
         */

        private String member_head;
        private String member_nickname;
        private String money;
        private double profit_money;
        private String created_at;

        public String getMember_head() {
            return member_head;
        }

        public void setMember_head(String member_head) {
            this.member_head = member_head;
        }

        public String getMember_nickname() {
            return member_nickname;
        }

        public void setMember_nickname(String member_nickname) {
            this.member_nickname = member_nickname;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public double getProfit_money() {
            return profit_money;
        }

        public void setProfit_money(double profit_money) {
            this.profit_money = profit_money;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

    public static class ContractTradingListBean {
        /**
         * bourse_on : 1
         * money : 10.00
         * earning_money : 100.00
         * bourse_on_title : BTC永续/USDT
         */

        private int bourse_on;
        private String money;
        private String earning_money;
        private String bourse_on_title;

        public int getBourse_on() {
            return bourse_on;
        }

        public void setBourse_on(int bourse_on) {
            this.bourse_on = bourse_on;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getEarning_money() {
            return earning_money;
        }

        public void setEarning_money(String earning_money) {
            this.earning_money = earning_money;
        }

        public String getBourse_on_title() {
            return bourse_on_title;
        }

        public void setBourse_on_title(String bourse_on_title) {
            this.bourse_on_title = bourse_on_title;
        }
    }

    public static class ContractCallMarginListBean {

        /**
         * money : 1.00
         * created_at : 2019-12-18 21:36:03
         * show_created_at : 12-18 21:36
         */

        private String money;
        private String created_at;
        private String show_created_at;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getShow_created_at() {
            return show_created_at;
        }

        public void setShow_created_at(String show_created_at) {
            this.show_created_at = show_created_at;
        }
    }
}
