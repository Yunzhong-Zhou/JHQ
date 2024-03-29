package com.ghzk.ghzk.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/12/21.
 */
public class MachineDetailModel implements Serializable {

    /**
     * top_up : {"id":"eeb093f0909d573e22ffa5199410bd72","goods_brand":"万豪酒店","buy_type":2,"operation_type":1,"source_type":1,"money":"100.00","status":6,"goods_sn":"s006","put_business":"餐饮","put_title":"万豪华（国贸店）","put_address":"重庆","install_at":"2021-03-02 22:01:48","run_at":"2021-03-02 22:02:04","earning_money":"120.00","created_at":"2021-03-02 21:56:33","buy_type_title":"能回购","operation_type_title":"代运营","source_type_title":"购买","status_title":"永久转出","earning_ratio":40,"earning_list":[{"order_goods_id":"eeb093f0909d573e22ffa5199410bd72","money":"100.0000","created_at":"2021-03-02 22:02:50"},{"order_goods_id":"eeb093f0909d573e22ffa5199410bd72","money":"100.0000","created_at":"2021-03-02 22:02:49"},{"order_goods_id":"eeb093f0909d573e22ffa5199410bd72","money":"100.0000","created_at":"2021-03-02 22:02:48"}]}
     * is_transfer : 1
     * is_buy_back : 1
     * is_take_back : 1
     */

    private OrderGoodsBean order_goods;
    private int is_transfer;
    private int is_buy_back;
    private int is_take_back;

    public OrderGoodsBean getOrder_goods() {
        return order_goods;
    }

    public void setOrder_goods(OrderGoodsBean order_goods) {
        this.order_goods = order_goods;
    }

    public int getIs_transfer() {
        return is_transfer;
    }

    public void setIs_transfer(int is_transfer) {
        this.is_transfer = is_transfer;
    }

    public int getIs_buy_back() {
        return is_buy_back;
    }

    public void setIs_buy_back(int is_buy_back) {
        this.is_buy_back = is_buy_back;
    }

    public int getIs_take_back() {
        return is_take_back;
    }

    public void setIs_take_back(int is_take_back) {
        this.is_take_back = is_take_back;
    }

    public static class OrderGoodsBean {
        /**
         * id : eeb093f0909d573e22ffa5199410bd72
         * goods_brand : 万豪酒店
         * buy_type : 2
         * operation_type : 1
         * source_type : 1
         * money : 100.00
         * status : 6
         * goods_sn : s006
         * put_business : 餐饮
         * put_title : 万豪华（国贸店）
         * put_address : 重庆
         * install_at : 2021-03-02 22:01:48
         * run_at : 2021-03-02 22:02:04
         * earning_money : 120.00
         * created_at : 2021-03-02 21:56:33
         * buy_type_title : 能回购
         * operation_type_title : 代运营
         * source_type_title : 购买
         * status_title : 永久转出
         * earning_ratio : 40
         * earning_list : [{"order_goods_id":"eeb093f0909d573e22ffa5199410bd72","money":"100.0000","created_at":"2021-03-02 22:02:50"},{"order_goods_id":"eeb093f0909d573e22ffa5199410bd72","money":"100.0000","created_at":"2021-03-02 22:02:49"},{"order_goods_id":"eeb093f0909d573e22ffa5199410bd72","money":"100.0000","created_at":"2021-03-02 22:02:48"}]
         */

        private String id;
        private String goods_brand;
        private int buy_type;
        private int operation_type;
        private int source_type;
        private String money;
        private int status;
        private String goods_sn;
        private String put_business;
        private String put_title;
        private String put_address;
        private String install_at;
        private String run_at;
        private String earning_money;
        private String created_at;
        private String buy_type_title;
        private String operation_type_title;
        private String source_type_title;
        private String status_title;
        private int earning_ratio;
        private String buy_back_apply_at;
        private String buy_back_money;
        private String return_at;
        private String transfer_out_at;
        private String transfer_in_member_id;
        private String transfer_out_member_id;
        private String transfer_in_member_mobile;
        private String transfer_out_member_mobile;
        private String wait_settle_money;
        private String status_buy_back_apply_rejected_cause;

        public String getStatus_buy_back_apply_rejected_cause() {
            return status_buy_back_apply_rejected_cause;
        }

        public void setStatus_buy_back_apply_rejected_cause(String status_buy_back_apply_rejected_cause) {
            this.status_buy_back_apply_rejected_cause = status_buy_back_apply_rejected_cause;
        }

        public String getWait_settle_money() {
            return wait_settle_money;
        }

        public void setWait_settle_money(String wait_settle_money) {
            this.wait_settle_money = wait_settle_money;
        }

        public String getTransfer_in_member_mobile() {
            return transfer_in_member_mobile;
        }

        public void setTransfer_in_member_mobile(String transfer_in_member_mobile) {
            this.transfer_in_member_mobile = transfer_in_member_mobile;
        }

        public String getTransfer_out_member_mobile() {
            return transfer_out_member_mobile;
        }

        public void setTransfer_out_member_mobile(String transfer_out_member_mobile) {
            this.transfer_out_member_mobile = transfer_out_member_mobile;
        }

        public String getTransfer_out_member_id() {
            return transfer_out_member_id;
        }

        public void setTransfer_out_member_id(String transfer_out_member_id) {
            this.transfer_out_member_id = transfer_out_member_id;
        }

        public String getTransfer_in_member_id() {
            return transfer_in_member_id;
        }

        public void setTransfer_in_member_id(String transfer_in_member_id) {
            this.transfer_in_member_id = transfer_in_member_id;
        }

        public String getTransfer_out_at() {
            return transfer_out_at;
        }

        public void setTransfer_out_at(String transfer_out_at) {
            this.transfer_out_at = transfer_out_at;
        }

        private List<EarningListBean> earning_list;

        public String getReturn_at() {
            return return_at;
        }

        public void setReturn_at(String return_at) {
            this.return_at = return_at;
        }

        public String getBuy_back_money() {
            return buy_back_money;
        }

        public void setBuy_back_money(String buy_back_money) {
            this.buy_back_money = buy_back_money;
        }

        public String getBuy_back_apply_at() {
            return buy_back_apply_at;
        }

        public void setBuy_back_apply_at(String buy_back_apply_at) {
            this.buy_back_apply_at = buy_back_apply_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoods_brand() {
            return goods_brand;
        }

        public void setGoods_brand(String goods_brand) {
            this.goods_brand = goods_brand;
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

        public String getInstall_at() {
            return install_at;
        }

        public void setInstall_at(String install_at) {
            this.install_at = install_at;
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

        public int getEarning_ratio() {
            return earning_ratio;
        }

        public void setEarning_ratio(int earning_ratio) {
            this.earning_ratio = earning_ratio;
        }

        public List<EarningListBean> getEarning_list() {
            return earning_list;
        }

        public void setEarning_list(List<EarningListBean> earning_list) {
            this.earning_list = earning_list;
        }

        public static class EarningListBean {
            /**
             * order_goods_id : eeb093f0909d573e22ffa5199410bd72
             * money : 100.0000
             * created_at : 2021-03-02 22:02:50
             */

            private String order_goods_id;
            private String money;
            private String created_at;

            public String getOrder_goods_id() {
                return order_goods_id;
            }

            public void setOrder_goods_id(String order_goods_id) {
                this.order_goods_id = order_goods_id;
            }

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
        }
    }
}
