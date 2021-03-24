package com.ghzk.ghzk.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/12/20.
 */
public class ZhiChuListModel implements Serializable {
    private List<OutMoneyListBean> out_money_list;

    public List<OutMoneyListBean> getOut_money_list() {
        return out_money_list;
    }

    public void setOut_money_list(List<OutMoneyListBean> out_money_list) {
        this.out_money_list = out_money_list;
    }

    public static class OutMoneyListBean {
        /**
         * title : 提币
         * money : 100
         * status : 通过
         * created_at : 2020-12-20 13:08:59
         */

        private String title;
        private String money;
        private String status;
        private String created_at;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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
