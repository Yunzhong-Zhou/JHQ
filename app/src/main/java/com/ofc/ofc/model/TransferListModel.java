package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019-12-18.
 */
public class TransferListModel implements Serializable {
    private List<ContractListBean> contract_list;

    public List<ContractListBean> getContract_list() {
        return contract_list;
    }

    public void setContract_list(List<ContractListBean> contract_list) {
        this.contract_list = contract_list;
    }

    public static class ContractListBean {
        /**
         * id : 5cb8dc856b77641494d16139c19e0d9b
         * sn : CT1576655025984955
         * reality_money : 4
         * profit_money : 0
         * call_margin_money : 0
         * status : 1
         * created_at : 2019-12-18 15:43:45
         */

        private String id;
        private String sn;
        private String reality_money;
        private String profit_money;
        private String call_margin_money;
        private int status;
        private String created_at;
        private String status_title;

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }

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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
