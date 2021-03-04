package com.filter.filter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/4.
 */
public class MyQuKuaiModel implements Serializable {
    private List<BlockListBean> block_list;

    public List<BlockListBean> getBlock_list() {
        return block_list;
    }

    public void setBlock_list(List<BlockListBean> block_list) {
        this.block_list = block_list;
    }

    public static class BlockListBean {
        /**
         * id : 7f3a75652b55b108e06bacb06cdc654e
         * money : 100
         * has_money : 0.00
         * status : 1
         * created_at : 2019-06-04 17:58:55
         * status_title : 待激活
         */

        private String id;
        private String money;
        private String has_money;
        private int status;
        private String created_at;
        private String status_title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getHas_money() {
            return has_money;
        }

        public void setHas_money(String has_money) {
            this.has_money = has_money;
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
