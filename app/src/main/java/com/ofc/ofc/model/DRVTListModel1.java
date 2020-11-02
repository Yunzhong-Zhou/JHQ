package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/10/25.
 */
public class DRVTListModel1 implements Serializable {
    private List<DrvtBuyListBean> drvt_buy_list;

    public List<DrvtBuyListBean> getDrvt_buy_list() {
        return drvt_buy_list;
    }

    public void setDrvt_buy_list(List<DrvtBuyListBean> drvt_buy_list) {
        this.drvt_buy_list = drvt_buy_list;
    }

    public static class DrvtBuyListBean {
        /**
         * id : 81943c1c32c99d884fdb8be2c4ad0025
         * member_head : /head.png
         * member_nickname : zhumimi
         * drvt_price : 1.1000
         * amount_money : 100.00
         * current_money : 0.00
         * status : 1
         * created_at : 2020-10-27 16:02:14
         * status_title : 进行中
         */

        private String id;
        private String member_head;
        private String member_nickname;
        private String drvt_price;
        private String amount_money;
        private String current_money;
        private int status;
        private String created_at;
        private String status_title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getDrvt_price() {
            return drvt_price;
        }

        public void setDrvt_price(String drvt_price) {
            this.drvt_price = drvt_price;
        }

        public String getAmount_money() {
            return amount_money;
        }

        public void setAmount_money(String amount_money) {
            this.amount_money = amount_money;
        }

        public String getCurrent_money() {
            return current_money;
        }

        public void setCurrent_money(String current_money) {
            this.current_money = current_money;
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
