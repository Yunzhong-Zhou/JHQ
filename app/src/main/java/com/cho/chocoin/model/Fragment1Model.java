package com.cho.chocoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/4.
 */
public class Fragment1Model implements Serializable {
    /**
     * overflow_commission_money : 5000
     * invest_money : 2000
     * max_bonus_money : 3000
     * amount_bonus_money : 3000
     * grade : 1
     * plan_percent : 1
     * newest_invest_list : [{"member_head":"/head/166.png","member_nickname":"block","money":1640,"created_at":"2019-06-15"},{"member_head":"/head/195.png","member_nickname":"敏哥","money":13520,"created_at":"2019-06-15"},{"member_head":"/head/28.png","member_nickname":"妖精","money":13550,"created_at":"2019-06-15"},{"member_head":"/head/31.png","member_nickname":"小红帽","money":12060,"created_at":"2019-06-15"}]
     */

    private String overflow_commission_money;
    private String invest_money;
    private String max_bonus_money;
    private String amount_bonus_money;
    private int grade;
    private String grade_title;
    private String plan_percent;
    private List<NewestInvestListBean> newest_invest_list;
    private String invest_explain;

    public String getGrade_title() {
        return grade_title;
    }

    public void setGrade_title(String grade_title) {
        this.grade_title = grade_title;
    }

    public String getInvest_explain() {
        return invest_explain;
    }

    public void setInvest_explain(String invest_explain) {
        this.invest_explain = invest_explain;
    }

    public String getOverflow_commission_money() {
        return overflow_commission_money;
    }

    public void setOverflow_commission_money(String overflow_commission_money) {
        this.overflow_commission_money = overflow_commission_money;
    }

    public String getInvest_money() {
        return invest_money;
    }

    public void setInvest_money(String invest_money) {
        this.invest_money = invest_money;
    }

    public String getMax_bonus_money() {
        return max_bonus_money;
    }

    public void setMax_bonus_money(String max_bonus_money) {
        this.max_bonus_money = max_bonus_money;
    }

    public String getAmount_bonus_money() {
        return amount_bonus_money;
    }

    public void setAmount_bonus_money(String amount_bonus_money) {
        this.amount_bonus_money = amount_bonus_money;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getPlan_percent() {
        return plan_percent;
    }

    public void setPlan_percent(String plan_percent) {
        this.plan_percent = plan_percent;
    }

    public List<NewestInvestListBean> getNewest_invest_list() {
        return newest_invest_list;
    }

    public void setNewest_invest_list(List<NewestInvestListBean> newest_invest_list) {
        this.newest_invest_list = newest_invest_list;
    }

    public static class NewestInvestListBean {
        /**
         * member_head : /head/166.png
         * member_nickname : block
         * money : 1640
         * created_at : 2019-06-15
         */

        private String member_head;
        private String member_nickname;
        private String money;
        private String created_at;

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
