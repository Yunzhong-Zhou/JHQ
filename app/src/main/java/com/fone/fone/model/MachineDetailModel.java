package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/12/21.
 */
public class MachineDetailModel implements Serializable {

    /**
     * top_up : {"id":"ac29978e56d429088a040a7fa6620c68","buy_type":1,"operation_type":1,"source_type":1,"money":"200.00","status":1,"goods_sn":"","put_business":"","put_title":"","put_address":"","install_at":null,"run_at":null,"earning_money":"0.00","created_at":"2021-02-26 13:23:13","buy_type_title":"不能回购","operation_type_title":"代运营","source_type_title":"购买","status_title":"待发货","earning_ratio":60,"earning_list":[{"id":"c6b892875c10fe3c322a7824","sn":"WI1614312676525597","input_money":"100.0000","money":"97.0000","status":1,"created_at":"2021-02-26 12:11:16","status_title":"待审核"}]}
     */

    private TopUpBean top_up;
    /**
     * is_transfer : 1
     * is_buy_back : 1
     * is_take_back : 1
     */

    private int is_transfer;
    private int is_buy_back;
    private int is_take_back;

    public TopUpBean getTop_up() {
        return top_up;
    }

    public void setTop_up(TopUpBean top_up) {
        this.top_up = top_up;
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

    public static class TopUpBean {
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
         * install_at : null
         * run_at : null
         * earning_money : 0.00
         * created_at : 2021-02-26 13:23:13
         * buy_type_title : 不能回购
         * operation_type_title : 代运营
         * source_type_title : 购买
         * status_title : 待发货
         * earning_ratio : 60
         * earning_list : [{"id":"c6b892875c10fe3c322a7824","sn":"WI1614312676525597","input_money":"100.0000","money":"97.0000","status":1,"created_at":"2021-02-26 12:11:16","status_title":"待审核"}]
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
        private String install_at;
        private String run_at;
        private String earning_money;
        private String created_at;
        private String buy_type_title;
        private String operation_type_title;
        private String source_type_title;
        private String status_title;
        private String earning_ratio;
        private List<EarningListBean> earning_list;

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

        public String getEarning_ratio() {
            return earning_ratio;
        }

        public void setEarning_ratio(String earning_ratio) {
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
             * id : c6b892875c10fe3c322a7824
             * sn : WI1614312676525597
             * input_money : 100.0000
             * money : 97.0000
             * status : 1
             * created_at : 2021-02-26 12:11:16
             * status_title : 待审核
             */

            private String id;
            private String sn;
            private String input_money;
            private String money;
            private int status;
            private String created_at;
            private String status_title;

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

            public String getInput_money() {
                return input_money;
            }

            public void setInput_money(String input_money) {
                this.input_money = input_money;
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

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getStatus_title() {
                return status_title;
            }

            public void setStatus_title(String status_title) {
                this.status_title = status_title;
            }
        }
    }

}
