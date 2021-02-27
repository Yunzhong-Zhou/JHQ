package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/12/22.
 */
public class MyMachineModel implements Serializable {
    /**
     * earning_money : 0.0000
     * has_run_count : 0
     * wait_install_count : 0
     * amount_count : 0
     * agency_count : 0
     * self_count : 0
     * order_goods_list : [{"id":"ac29978e56d429088a040a7fa6620c68","buy_type":1,"operation_type":1,"source_type":1,"money":"200.00","status":1,"goods_sn":"","put_business":"","put_title":"","put_address":"","run_at":null,"earning_money":"0.00","created_at":"2021-02-26 13:23:13","buy_type_title":"不能回购","operation_type_title":"代运营","source_type_title":"购买","status_title":"待发货"},{"id":"8cff84db8745493e226c355796da0ac1","buy_type":1,"operation_type":1,"source_type":1,"money":"200.00","status":1,"goods_sn":"","put_business":"","put_title":"","put_address":"","run_at":null,"earning_money":"0.00","created_at":"2021-02-26 13:20:32","buy_type_title":"不能回购","operation_type_title":"代运营","source_type_title":"购买","status_title":"待发货"},{"id":"607d1e914a626f3511c7c196788bb423","buy_type":1,"operation_type":1,"source_type":1,"money":"200.00","status":1,"goods_sn":"","put_business":"","put_title":"","put_address":"","run_at":null,"earning_money":"0.00","created_at":"2021-02-26 13:19:18","buy_type_title":"不能回购","operation_type_title":"代运营","source_type_title":"购买","status_title":"待发货"},{"id":"c8e1cfe351d0701fdba96652ef1fcdeb","buy_type":1,"operation_type":1,"source_type":1,"money":"200.00","status":1,"goods_sn":"","put_business":"","put_title":"","put_address":"","run_at":null,"earning_money":"0.00","created_at":"2021-02-26 13:17:29","buy_type_title":"不能回购","operation_type_title":"代运营","source_type_title":"购买","status_title":"待发货"},{"id":"080c8802f47aa20a30d6b90eae10e195","buy_type":1,"operation_type":1,"source_type":1,"money":"200.00","status":1,"goods_sn":"","put_business":"","put_title":"","put_address":"","run_at":null,"earning_money":"0.00","created_at":"2021-02-26 13:13:25","buy_type_title":"不能回购","operation_type_title":"代运营","source_type_title":"购买","status_title":"待发货"},{"id":"806b58d7757d4d7dc27737d66f6bbca1","buy_type":1,"operation_type":1,"source_type":1,"money":"200.00","status":1,"goods_sn":"","put_business":"","put_title":"","put_address":"","run_at":null,"earning_money":"0.00","created_at":"2021-02-26 13:10:27","buy_type_title":"不能回购","operation_type_title":"代运营","source_type_title":"购买","status_title":"待发货"},{"id":"e425a8a73324d787b59860c9794aeed6","buy_type":1,"operation_type":1,"source_type":1,"money":"200.00","status":1,"goods_sn":"","put_business":"","put_title":"","put_address":"","run_at":null,"earning_money":"0.00","created_at":"2021-02-26 13:03:03","buy_type_title":"不能回购","operation_type_title":"代运营","source_type_title":"购买","status_title":"待发货"},{"id":"3028f308875a5e326bc8d62ae70e4198","buy_type":1,"operation_type":1,"source_type":1,"money":"200.00","status":1,"goods_sn":"","put_business":"","put_title":"","put_address":"","run_at":null,"earning_money":"0.00","created_at":"2021-02-26 13:01:55","buy_type_title":"不能回购","operation_type_title":"代运营","source_type_title":"购买","status_title":"待发货"},{"id":"0163b4447a36f4f62b8280b924fa03e6","buy_type":1,"operation_type":1,"source_type":1,"money":"200.00","status":1,"goods_sn":"","put_business":"","put_title":"","put_address":"","run_at":null,"earning_money":"0.00","created_at":"2021-02-26 12:59:18","buy_type_title":"不能回购","operation_type_title":"代运营","source_type_title":"购买","status_title":"待发货"},{"id":"ef6c170e2b41440b610731fcb81633b9","buy_type":1,"operation_type":1,"source_type":1,"money":"200.00","status":1,"goods_sn":"","put_business":"","put_title":"","put_address":"","run_at":null,"earning_money":"0.00","created_at":"2021-02-26 12:58:46","buy_type_title":"不能回购","operation_type_title":"代运营","source_type_title":"购买","status_title":"待发货"}]
     */

    private String earning_money;
    private String has_run_count;
    private String wait_install_count;
    private String amount_count;
    private String agency_count;
    private String self_count;
    private List<OrderGoodsListBean> order_goods_list;

    public String getEarning_money() {
        return earning_money;
    }

    public void setEarning_money(String earning_money) {
        this.earning_money = earning_money;
    }

    public String getHas_run_count() {
        return has_run_count;
    }

    public void setHas_run_count(String has_run_count) {
        this.has_run_count = has_run_count;
    }

    public String getWait_install_count() {
        return wait_install_count;
    }

    public void setWait_install_count(String wait_install_count) {
        this.wait_install_count = wait_install_count;
    }

    public String getAmount_count() {
        return amount_count;
    }

    public void setAmount_count(String amount_count) {
        this.amount_count = amount_count;
    }

    public String getAgency_count() {
        return agency_count;
    }

    public void setAgency_count(String agency_count) {
        this.agency_count = agency_count;
    }

    public String getSelf_count() {
        return self_count;
    }

    public void setSelf_count(String self_count) {
        this.self_count = self_count;
    }

    public List<OrderGoodsListBean> getOrder_goods_list() {
        return order_goods_list;
    }

    public void setOrder_goods_list(List<OrderGoodsListBean> order_goods_list) {
        this.order_goods_list = order_goods_list;
    }

    public static class OrderGoodsListBean {
        /**
         * id : ac29978e56d429088a040a7fa6620c68
         * buy_type : 1
         * operation_type : 1
         * source_type : 1
         * money : 200.00
         * status : 1
         * goods_sn :
         * put_business :
         * put_title :
         * put_address :
         * run_at : null
         * earning_money : 0.00
         * created_at : 2021-02-26 13:23:13
         * buy_type_title : 不能回购
         * operation_type_title : 代运营
         * source_type_title : 购买
         * status_title : 待发货
         */

        private String id;
        private int buy_type;
        private int operation_type;
        private int source_type;
        private String money;
        private int status;
        private String goods_sn;
        private String put_business;
        private String put_title;
        private String put_address;
        private String run_at;
        private String earning_money;
        private String created_at;
        private String buy_type_title;
        private String operation_type_title;
        private String source_type_title;
        private String status_title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getBuy_type() {
            return buy_type;
        }

        public void setBuy_type(int buy_type) {
            this.buy_type = buy_type;
        }

        public int getOperation_type() {
            return operation_type;
        }

        public void setOperation_type(int operation_type) {
            this.operation_type = operation_type;
        }

        public int getSource_type() {
            return source_type;
        }

        public void setSource_type(int source_type) {
            this.source_type = source_type;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getGoods_sn() {
            return goods_sn;
        }

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn;
        }

        public String getPut_business() {
            return put_business;
        }

        public void setPut_business(String put_business) {
            this.put_business = put_business;
        }

        public String getPut_title() {
            return put_title;
        }

        public void setPut_title(String put_title) {
            this.put_title = put_title;
        }

        public String getPut_address() {
            return put_address;
        }

        public void setPut_address(String put_address) {
            this.put_address = put_address;
        }

        public String getRun_at() {
            return run_at;
        }

        public void setRun_at(String run_at) {
            this.run_at = run_at;
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

        public String getBuy_type_title() {
            return buy_type_title;
        }

        public void setBuy_type_title(String buy_type_title) {
            this.buy_type_title = buy_type_title;
        }

        public String getOperation_type_title() {
            return operation_type_title;
        }

        public void setOperation_type_title(String operation_type_title) {
            this.operation_type_title = operation_type_title;
        }

        public String getSource_type_title() {
            return source_type_title;
        }

        public void setSource_type_title(String source_type_title) {
            this.source_type_title = source_type_title;
        }

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }
    }
}
