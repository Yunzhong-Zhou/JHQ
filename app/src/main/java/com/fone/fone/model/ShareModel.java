package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/11/12.
 */
public class ShareModel implements Serializable {
    /**
     * head : /head/606.png
     * nickname : FIL_pour广告费
     * invite_code : pour
     * grade : 1
     * commission_running_proportion : 0
     * commission_sell_proportion : 0
     * commission_money : 0
     * commission_same_level_proportion : 10
     * valid_direct_recommend : 0
     * hold_start_at : -
     * hold_end_at : -
     * hold_current_money : 500
     * hold_target_money : 0
     * hold_current_money_count : 0
     * hold_target_money_count : 0
     * tips_hold_target_money : 0
     * direct_recommend : 0
     * team_recommend : 0
     * direct_performance_money : 0
     * team_performance_money : 0
     * direct_performance_buy_invest_money : 0
     * team_performance_buy_invest_money : 0
     * direct_performance_all_invest_money : 0
     * team_performance_all_invest_money : 0
     * grade_count_list : {"grade_1":0,"grade_2":0,"grade_3":0}
     */

    private String tips_upgrade_money;
    private String tips_upgrade_money_count;

    public String getTips_upgrade_money_count() {
        return tips_upgrade_money_count;
    }

    public void setTips_upgrade_money_count(String tips_upgrade_money_count) {
        this.tips_upgrade_money_count = tips_upgrade_money_count;
    }

    public String getTips_upgrade_money() {
        return tips_upgrade_money;
    }

    public void setTips_upgrade_money(String tips_upgrade_money) {
        this.tips_upgrade_money = tips_upgrade_money;
    }

    private String head;
    private String nickname;
    private String invite_code;
    private int grade;
    private String commission_running_proportion;
    private String commission_sell_proportion;
    private String commission_money;
    private String commission_same_level_proportion;
    private String valid_direct_recommend;
    private String hold_start_at;
    private String hold_end_at;
    private String hold_current_money;
    private String hold_target_money;
    private String hold_current_money_count;
    private String hold_target_money_count;
    private String tips_hold_target_money;
    private String tips_hold_target_money_count;
    private String direct_recommend;
    private String team_recommend;
    private String direct_performance_money;
    private String team_performance_money;
    private String direct_performance_buy_invest_money;
    private String team_performance_buy_invest_money;
    private String direct_performance_all_invest_money;
    private String team_performance_all_invest_money;
    private GradeCountListBean grade_count_list;
    private String tips_hold;
    private String tips_upgrade;

    public String getTips_hold() {
        return tips_hold;
    }

    public void setTips_hold(String tips_hold) {
        this.tips_hold = tips_hold;
    }

    public String getTips_upgrade() {
        return tips_upgrade;
    }

    public void setTips_upgrade(String tips_upgrade) {
        this.tips_upgrade = tips_upgrade;
    }

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getCommission_running_proportion() {
        return commission_running_proportion;
    }

    public void setCommission_running_proportion(String commission_running_proportion) {
        this.commission_running_proportion = commission_running_proportion;
    }

    public String getCommission_sell_proportion() {
        return commission_sell_proportion;
    }

    public void setCommission_sell_proportion(String commission_sell_proportion) {
        this.commission_sell_proportion = commission_sell_proportion;
    }

    public String getCommission_money() {
        return commission_money;
    }

    public void setCommission_money(String commission_money) {
        this.commission_money = commission_money;
    }

    public String getCommission_same_level_proportion() {
        return commission_same_level_proportion;
    }

    public void setCommission_same_level_proportion(String commission_same_level_proportion) {
        this.commission_same_level_proportion = commission_same_level_proportion;
    }

    public String getValid_direct_recommend() {
        return valid_direct_recommend;
    }

    public void setValid_direct_recommend(String valid_direct_recommend) {
        this.valid_direct_recommend = valid_direct_recommend;
    }

    public String getHold_start_at() {
        return hold_start_at;
    }

    public void setHold_start_at(String hold_start_at) {
        this.hold_start_at = hold_start_at;
    }

    public String getHold_end_at() {
        return hold_end_at;
    }

    public void setHold_end_at(String hold_end_at) {
        this.hold_end_at = hold_end_at;
    }

    public String getHold_current_money() {
        return hold_current_money;
    }

    public void setHold_current_money(String hold_current_money) {
        this.hold_current_money = hold_current_money;
    }

    public String getHold_target_money() {
        return hold_target_money;
    }

    public void setHold_target_money(String hold_target_money) {
        this.hold_target_money = hold_target_money;
    }

    public String getHold_current_money_count() {
        return hold_current_money_count;
    }

    public void setHold_current_money_count(String hold_current_money_count) {
        this.hold_current_money_count = hold_current_money_count;
    }

    public String getHold_target_money_count() {
        return hold_target_money_count;
    }

    public void setHold_target_money_count(String hold_target_money_count) {
        this.hold_target_money_count = hold_target_money_count;
    }

    public String getTips_hold_target_money() {
        return tips_hold_target_money;
    }

    public void setTips_hold_target_money(String tips_hold_target_money) {
        this.tips_hold_target_money = tips_hold_target_money;
    }

    public String getTips_hold_target_money_count() {
        return tips_hold_target_money_count;
    }

    public void setTips_hold_target_money_count(String tips_hold_target_money_count) {
        this.tips_hold_target_money_count = tips_hold_target_money_count;
    }

    public String getDirect_recommend() {
        return direct_recommend;
    }

    public void setDirect_recommend(String direct_recommend) {
        this.direct_recommend = direct_recommend;
    }

    public String getTeam_recommend() {
        return team_recommend;
    }

    public void setTeam_recommend(String team_recommend) {
        this.team_recommend = team_recommend;
    }

    public String getDirect_performance_money() {
        return direct_performance_money;
    }

    public void setDirect_performance_money(String direct_performance_money) {
        this.direct_performance_money = direct_performance_money;
    }

    public String getTeam_performance_money() {
        return team_performance_money;
    }

    public void setTeam_performance_money(String team_performance_money) {
        this.team_performance_money = team_performance_money;
    }

    public String getDirect_performance_buy_invest_money() {
        return direct_performance_buy_invest_money;
    }

    public void setDirect_performance_buy_invest_money(String direct_performance_buy_invest_money) {
        this.direct_performance_buy_invest_money = direct_performance_buy_invest_money;
    }

    public String getTeam_performance_buy_invest_money() {
        return team_performance_buy_invest_money;
    }

    public void setTeam_performance_buy_invest_money(String team_performance_buy_invest_money) {
        this.team_performance_buy_invest_money = team_performance_buy_invest_money;
    }

    public String getDirect_performance_all_invest_money() {
        return direct_performance_all_invest_money;
    }

    public void setDirect_performance_all_invest_money(String direct_performance_all_invest_money) {
        this.direct_performance_all_invest_money = direct_performance_all_invest_money;
    }

    public String getTeam_performance_all_invest_money() {
        return team_performance_all_invest_money;
    }

    public void setTeam_performance_all_invest_money(String team_performance_all_invest_money) {
        this.team_performance_all_invest_money = team_performance_all_invest_money;
    }

    public GradeCountListBean getGrade_count_list() {
        return grade_count_list;
    }

    public void setGrade_count_list(GradeCountListBean grade_count_list) {
        this.grade_count_list = grade_count_list;
    }

    public static class GradeCountListBean {
        /**
         * grade_1 : 0
         * grade_2 : 0
         * grade_3 : 0
         */

        private String grade_1;
        private String grade_2;
        private String grade_3;
        private String grade_4;

        public String getGrade_1() {
            return grade_1;
        }

        public void setGrade_1(String grade_1) {
            this.grade_1 = grade_1;
        }

        public String getGrade_2() {
            return grade_2;
        }

        public void setGrade_2(String grade_2) {
            this.grade_2 = grade_2;
        }

        public String getGrade_3() {
            return grade_3;
        }

        public void setGrade_3(String grade_3) {
            this.grade_3 = grade_3;
        }

        public String getGrade_4() {
            return grade_4;
        }

        public void setGrade_4(String grade_4) {
            this.grade_4 = grade_4;
        }
    }
}
