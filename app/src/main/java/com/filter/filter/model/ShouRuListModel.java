package com.filter.filter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/8/10.
 */
public class ShouRuListModel implements Serializable {
    private List<InMoneyListBean> in_money_list;

    public List<InMoneyListBean> getIn_money_list() {
        return in_money_list;
    }

    public void setIn_money_list(List<InMoneyListBean> in_money_list) {
        this.in_money_list = in_money_list;
    }

    public static class InMoneyListBean {
        /**
         * title : 转入
         * money : 10
         * status : 完成
         * created_at : 2020-12-20 16:06:28
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
