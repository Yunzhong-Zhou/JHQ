package com.cfo.chocoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019-10-22.
 */
public class NewcomerRewardModel implements Serializable {
    private String amount_sign_money;
    private String today_sign_money;

    public String getAmount_sign_money() {
        return amount_sign_money;
    }

    public void setAmount_sign_money(String amount_sign_money) {
        this.amount_sign_money = amount_sign_money;
    }

    public String getToday_sign_money() {
        return today_sign_money;
    }

    public void setToday_sign_money(String today_sign_money) {
        this.today_sign_money = today_sign_money;
    }

    private List<SignListBean> sign_list;

    public List<SignListBean> getSign_list() {
        return sign_list;
    }

    public void setSign_list(List<SignListBean> sign_list) {
        this.sign_list = sign_list;
    }

    public static class SignListBean {
        /**
         * date : 2019-09-21
         * status : 1
         */

        private String date;
        private int status;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
