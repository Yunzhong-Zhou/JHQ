package com.cho.chocoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/6.
 */
public class AccountDetailModel1 implements Serializable {
    /**
     * block_award_usable_money : 0
     * block_award_money : 6
     * wait_block_award_money : -6
     * earning_list : [{"money":"2","created_at":"2019-06-06 15:35:48","title":"区块奖励","status":"完成"},{"money":"2","created_at":"2019-06-06 15:13:39","title":"区块奖励","status":"完成"}]
     * expenditure_list : [{"block_award_money":"6.00","created_at":"2019-06-06 15:36:50","money":"6","title":"算力购买","status":"完成"}]
     */

    private String block_award_usable_money;
    private String block_award_money;
    private String wait_block_award_money;
    private List<EarningListBean> earning_list;
    private List<ExpenditureListBean> expenditure_list;

    public String getBlock_award_usable_money() {
        return block_award_usable_money;
    }

    public void setBlock_award_usable_money(String block_award_usable_money) {
        this.block_award_usable_money = block_award_usable_money;
    }

    public String getBlock_award_money() {
        return block_award_money;
    }

    public void setBlock_award_money(String block_award_money) {
        this.block_award_money = block_award_money;
    }

    public String getWait_block_award_money() {
        return wait_block_award_money;
    }

    public void setWait_block_award_money(String wait_block_award_money) {
        this.wait_block_award_money = wait_block_award_money;
    }

    public List<EarningListBean> getEarning_list() {
        return earning_list;
    }

    public void setEarning_list(List<EarningListBean> earning_list) {
        this.earning_list = earning_list;
    }

    public List<ExpenditureListBean> getExpenditure_list() {
        return expenditure_list;
    }

    public void setExpenditure_list(List<ExpenditureListBean> expenditure_list) {
        this.expenditure_list = expenditure_list;
    }

    public static class EarningListBean {
        /**
         * money : 2
         * created_at : 2019-06-06 15:35:48
         * title : 区块奖励
         * status : 完成
         */

        private String money;
        private String created_at;
        private String title;
        private String status;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class ExpenditureListBean {
        /**
         * block_award_money : 6.00
         * created_at : 2019-06-06 15:36:50
         * money : 6
         * title : 算力购买
         * status : 完成
         */

        private String block_award_money;
        private String created_at;
        private String money;
        private String title;
        private String status;

        public String getBlock_award_money() {
            return block_award_money;
        }

        public void setBlock_award_money(String block_award_money) {
            this.block_award_money = block_award_money;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
