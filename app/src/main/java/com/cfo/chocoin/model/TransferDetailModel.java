package com.cfo.chocoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019-12-18.
 */
public class TransferDetailModel implements Serializable {
    /**
     * id : 5cb8dc856b77641494d16139c19e0d9b
     * sn : CT1576655025984955
     * reality_money : 4
     * profit_money : 0
     * call_margin_money : 0
     * status : 1
     * trading_count : 0
     * reality_count : 0
     * created_at : 2019-12-18 15:43:45
     * status_title : 等待中
     * contract_trading_list : [{"sn":"T201912181721","contract_id":"5cb8dc856b77641494d16139c19e0d9b","bourse_title":"第一个","bourse_on":1,"result":1,"direction":1,"lever":10,"ratio":0,"buy_price":"1000","sell_price":"900","buy_at":"2019-12-18 17:22:45","sell_at":"2019-12-18 17:23:52","earning_money":"100.00","created_at":"2019-12-18 17:22:28","bourse_on_title":"BTC永续/USDT","direction_title":"做空"}]
     * contract_call_margin_list : [{"sn":"T201912181721","contract_id":"5cb8dc856b77641494d16139c19e0d9b","bourse_title":"第一个","bourse_on":1,"result":1,"direction":1,"lever":10,"ratio":0,"buy_price":"1000","sell_price":"900","buy_at":"2019-12-18 17:22:45","sell_at":"2019-12-18 17:23:52","earning_money":"100.00","created_at":"2019-12-18 17:22:28","bourse_on_title":"BTC永续/USDT","direction_title":"做空"}]
     */

    private String id;
    private String sn;
    private String reality_money;
    private String profit_money;
    private String call_margin_money;
    private int status;
    private int trading_count;
    private int reality_count;
    private String show_created_at;
    private String show_updated_at;
    private String status_title;
    private List<ContractTradingListBean> contract_trading_list;
    private List<ContractCallMarginListBean> contract_call_margin_list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getReality_money() {
        return reality_money;
    }

    public void setReality_money(String reality_money) {
        this.reality_money = reality_money;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTrading_count() {
        return trading_count;
    }

    public void setTrading_count(int trading_count) {
        this.trading_count = trading_count;
    }

    public int getReality_count() {
        return reality_count;
    }

    public void setReality_count(int reality_count) {
        this.reality_count = reality_count;
    }

    public String getShow_created_at() {
        return show_created_at;
    }

    public void setShow_created_at(String show_created_at) {
        this.show_created_at = show_created_at;
    }

    public String getShow_updated_at() {
        return show_updated_at;
    }

    public void setShow_updated_at(String show_updated_at) {
        this.show_updated_at = show_updated_at;
    }

    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
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

    public static class ContractTradingListBean {
        /**
         * sn : T201912181721
         * contract_id : 5cb8dc856b77641494d16139c19e0d9b
         * bourse_title : 第一个
         * bourse_on : 1
         * result : 1
         * direction : 1
         * lever : 10
         * ratio : 0
         * buy_price : 1000
         * sell_price : 900
         * buy_at : 2019-12-18 17:22:45
         * sell_at : 2019-12-18 17:23:52
         * earning_money : 100.00
         * created_at : 2019-12-18 17:22:28
         * bourse_on_title : BTC永续/USDT
         * direction_title : 做空
         */

        private String sn;
        private String contract_id;
        private String bourse_title;
        private int bourse_on;
        private int result;
        private int direction;
        private int lever;
        private int ratio;
        private String buy_price;
        private String sell_price;
        private String buy_at;
        private String sell_at;
        private String earning_money;
        private String created_at;
        private String bourse_on_title;
        private String direction_title;

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getContract_id() {
            return contract_id;
        }

        public void setContract_id(String contract_id) {
            this.contract_id = contract_id;
        }

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

        public int getRatio() {
            return ratio;
        }

        public void setRatio(int ratio) {
            this.ratio = ratio;
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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
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
    }

    public static class ContractCallMarginListBean {
        /**
         * sn : T201912181721
         * contract_id : 5cb8dc856b77641494d16139c19e0d9b
         * bourse_title : 第一个
         * bourse_on : 1
         * result : 1
         * direction : 1
         * lever : 10
         * ratio : 0
         * buy_price : 1000
         * sell_price : 900
         * buy_at : 2019-12-18 17:22:45
         * sell_at : 2019-12-18 17:23:52
         * earning_money : 100.00
         * created_at : 2019-12-18 17:22:28
         * bourse_on_title : BTC永续/USDT
         * direction_title : 做空
         */

        private String sn;
        private String contract_id;
        private String bourse_title;
        private int bourse_on;
        private int result;
        private int direction;
        private int lever;
        private int ratio;
        private String buy_price;
        private String sell_price;
        private String buy_at;
        private String sell_at;
        private String earning_money;
        private String created_at;
        private String bourse_on_title;
        private String direction_title;

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getContract_id() {
            return contract_id;
        }

        public void setContract_id(String contract_id) {
            this.contract_id = contract_id;
        }

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

        public int getRatio() {
            return ratio;
        }

        public void setRatio(int ratio) {
            this.ratio = ratio;
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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
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
    }
}
