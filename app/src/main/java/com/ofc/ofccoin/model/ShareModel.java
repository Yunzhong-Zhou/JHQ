package com.ofc.ofccoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/11/12.
 */
public class ShareModel implements Serializable {

    /**
     * head : /head/562.png
     * nickname : 18306043086
     * invite_code : 703696
     * commission_money : 0
     * grade : 1
     * grade_title : 普通会员
     * commission_param : {"contract_trading_service_charge":0,"contract_trading_profit":0,"contract_trading_same_level":0}
     * direct_performance_money : 0.00
     * direct_recommend : 0
     * team_performance_money : 0.00
     * team_recommend : 0
     * direct_recommend_IB : 0
     * direct_recommend_MIB : 0
     * direct_recommend_PIB : 0
     */

    private String head;
    private String nickname;
    private String invite_code;
    private String commission_money;
    private int grade;
    private String grade_title;
    private CommissionParamBean commission_param;
    private String direct_performance_money;
    private String direct_recommend;
    private String team_performance_money;
    private String team_recommend;
    private String direct_recommend_IB;
    private String direct_recommend_MIB;
    private String direct_recommend_PIB;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getCommission_money() {
        return commission_money;
    }

    public void setCommission_money(String commission_money) {
        this.commission_money = commission_money;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getGrade_title() {
        return grade_title;
    }

    public void setGrade_title(String grade_title) {
        this.grade_title = grade_title;
    }

    public CommissionParamBean getCommission_param() {
        return commission_param;
    }

    public void setCommission_param(CommissionParamBean commission_param) {
        this.commission_param = commission_param;
    }

    public String getDirect_performance_money() {
        return direct_performance_money;
    }

    public void setDirect_performance_money(String direct_performance_money) {
        this.direct_performance_money = direct_performance_money;
    }

    public String getDirect_recommend() {
        return direct_recommend;
    }

    public void setDirect_recommend(String direct_recommend) {
        this.direct_recommend = direct_recommend;
    }

    public String getTeam_performance_money() {
        return team_performance_money;
    }

    public void setTeam_performance_money(String team_performance_money) {
        this.team_performance_money = team_performance_money;
    }

    public String getTeam_recommend() {
        return team_recommend;
    }

    public void setTeam_recommend(String team_recommend) {
        this.team_recommend = team_recommend;
    }

    public String getDirect_recommend_IB() {
        return direct_recommend_IB;
    }

    public void setDirect_recommend_IB(String direct_recommend_IB) {
        this.direct_recommend_IB = direct_recommend_IB;
    }

    public String getDirect_recommend_MIB() {
        return direct_recommend_MIB;
    }

    public void setDirect_recommend_MIB(String direct_recommend_MIB) {
        this.direct_recommend_MIB = direct_recommend_MIB;
    }

    public String getDirect_recommend_PIB() {
        return direct_recommend_PIB;
    }

    public void setDirect_recommend_PIB(String direct_recommend_PIB) {
        this.direct_recommend_PIB = direct_recommend_PIB;
    }

    public static class CommissionParamBean {
        /**
         * contract_trading_service_charge : 0
         * contract_trading_profit : 0
         * contract_trading_same_level : 0
         */

        private String contract_trading_service_charge;
        private String contract_trading_profit;
        private String contract_trading_same_level;

        public String getContract_trading_service_charge() {
            return contract_trading_service_charge;
        }

        public void setContract_trading_service_charge(String contract_trading_service_charge) {
            this.contract_trading_service_charge = contract_trading_service_charge;
        }

        public String getContract_trading_profit() {
            return contract_trading_profit;
        }

        public void setContract_trading_profit(String contract_trading_profit) {
            this.contract_trading_profit = contract_trading_profit;
        }

        public String getContract_trading_same_level() {
            return contract_trading_same_level;
        }

        public void setContract_trading_same_level(String contract_trading_same_level) {
            this.contract_trading_same_level = contract_trading_same_level;
        }
    }
}
