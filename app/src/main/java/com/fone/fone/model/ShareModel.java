package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/11/12.
 */
public class ShareModel implements Serializable {
    /**
     * head :
     * nickname : 阿斯顿
     * invite_code : 52292167
     * grade : 1
     * grade_title : 白银
     * recommend_grade : 0
     * recommend_grade_title : V0
     * commission_proportion_running : 0
     * commission_proportion_same_level : 10
     * commission_money : 0
     * direct_performance_money : 0.00
     * valid_direct_recommend : 0
     * upgrade_warn : 3
     * upgrade_money_warn : 5000
     * recommend_hold_start_at : null
     * recommend_hold_end_at : null
     * recommend_hold_current_money : 0.00
     * recommend_hold_target_money : 0.00
     * recommend_hold_target_warn : 0
     * recommend_hold_target_money_warn : 0
     * direct_recommend : 0
     * team_performance_money : 0.00
     * team_recommend : 0
     * recommend_grade_count_list : {"recommend_grade_0":0,"recommend_grade_1":0,"recommend_grade_2":0,"recommend_grade_3":0,"recommend_grade_4":0,"recommend_grade_5":0,"recommend_grade_6":0}
     */

    private String head;
    private String nickname;
    private String invite_code;
    private int grade;
    private String grade_title;
    private int recommend_grade;
    private String recommend_grade_title;
    private String commission_proportion_running;
    private String commission_proportion_same_level;
    private String commission_money;
    private String direct_performance_money;
    private String valid_direct_recommend;
    private String upgrade_warn;
    private String upgrade_money_warn;
    private String recommend_hold_start_at;
    private String recommend_hold_end_at;
    private String recommend_hold_current_money;
    private String recommend_hold_target_money;
    private String recommend_hold_current;
    private String recommend_hold_target;
    private String direct_recommend;
    private String team_performance_money;
    private String team_recommend;
    private RecommendGradeCountListBean recommend_grade_count_list;

    private String direct_contract_money;
    private String team_contract_money;
    private String contract_commission_money;
    private String contract_commission_proportion;

    public String getDirect_contract_money() {
        return direct_contract_money;
    }

    public void setDirect_contract_money(String direct_contract_money) {
        this.direct_contract_money = direct_contract_money;
    }

    public String getTeam_contract_money() {
        return team_contract_money;
    }

    public void setTeam_contract_money(String team_contract_money) {
        this.team_contract_money = team_contract_money;
    }

    public String getContract_commission_money() {
        return contract_commission_money;
    }

    public void setContract_commission_money(String contract_commission_money) {
        this.contract_commission_money = contract_commission_money;
    }

    public String getContract_commission_proportion() {
        return contract_commission_proportion;
    }

    public void setContract_commission_proportion(String contract_commission_proportion) {
        this.contract_commission_proportion = contract_commission_proportion;
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

    public String getGrade_title() {
        return grade_title;
    }

    public void setGrade_title(String grade_title) {
        this.grade_title = grade_title;
    }

    public int getRecommend_grade() {
        return recommend_grade;
    }

    public void setRecommend_grade(int recommend_grade) {
        this.recommend_grade = recommend_grade;
    }

    public String getRecommend_grade_title() {
        return recommend_grade_title;
    }

    public void setRecommend_grade_title(String recommend_grade_title) {
        this.recommend_grade_title = recommend_grade_title;
    }

    public String getCommission_proportion_running() {
        return commission_proportion_running;
    }

    public void setCommission_proportion_running(String commission_proportion_running) {
        this.commission_proportion_running = commission_proportion_running;
    }

    public String getCommission_proportion_same_level() {
        return commission_proportion_same_level;
    }

    public void setCommission_proportion_same_level(String commission_proportion_same_level) {
        this.commission_proportion_same_level = commission_proportion_same_level;
    }

    public String getCommission_money() {
        return commission_money;
    }

    public void setCommission_money(String commission_money) {
        this.commission_money = commission_money;
    }

    public String getDirect_performance_money() {
        return direct_performance_money;
    }

    public void setDirect_performance_money(String direct_performance_money) {
        this.direct_performance_money = direct_performance_money;
    }

    public String getValid_direct_recommend() {
        return valid_direct_recommend;
    }

    public void setValid_direct_recommend(String valid_direct_recommend) {
        this.valid_direct_recommend = valid_direct_recommend;
    }

    public String getUpgrade_warn() {
        return upgrade_warn;
    }

    public void setUpgrade_warn(String upgrade_warn) {
        this.upgrade_warn = upgrade_warn;
    }

    public String getUpgrade_money_warn() {
        return upgrade_money_warn;
    }

    public void setUpgrade_money_warn(String upgrade_money_warn) {
        this.upgrade_money_warn = upgrade_money_warn;
    }

    public String getRecommend_hold_start_at() {
        return recommend_hold_start_at;
    }

    public void setRecommend_hold_start_at(String recommend_hold_start_at) {
        this.recommend_hold_start_at = recommend_hold_start_at;
    }

    public String getRecommend_hold_end_at() {
        return recommend_hold_end_at;
    }

    public void setRecommend_hold_end_at(String recommend_hold_end_at) {
        this.recommend_hold_end_at = recommend_hold_end_at;
    }

    public String getRecommend_hold_current_money() {
        return recommend_hold_current_money;
    }

    public void setRecommend_hold_current_money(String recommend_hold_current_money) {
        this.recommend_hold_current_money = recommend_hold_current_money;
    }

    public String getRecommend_hold_target_money() {
        return recommend_hold_target_money;
    }

    public void setRecommend_hold_target_money(String recommend_hold_target_money) {
        this.recommend_hold_target_money = recommend_hold_target_money;
    }

    public String getRecommend_hold_target() {
        return recommend_hold_target;
    }

    public void setRecommend_hold_target(String recommend_hold_target) {
        this.recommend_hold_target = recommend_hold_target;
    }

    public String getRecommend_hold_current() {
        return recommend_hold_current;
    }

    public void setRecommend_hold_current(String recommend_hold_current) {
        this.recommend_hold_current = recommend_hold_current;
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

    public RecommendGradeCountListBean getRecommend_grade_count_list() {
        return recommend_grade_count_list;
    }

    public void setRecommend_grade_count_list(RecommendGradeCountListBean recommend_grade_count_list) {
        this.recommend_grade_count_list = recommend_grade_count_list;
    }

    public static class RecommendGradeCountListBean {
        /**
         * recommend_grade_0 : 0
         * recommend_grade_1 : 0
         * recommend_grade_2 : 0
         * recommend_grade_3 : 0
         * recommend_grade_4 : 0
         * recommend_grade_5 : 0
         * recommend_grade_6 : 0
         */

        private String recommend_grade_0;
        private String recommend_grade_1;
        private String recommend_grade_2;
        private String recommend_grade_3;
        private String recommend_grade_4;
        private String recommend_grade_5;
        private String recommend_grade_6;

        public String getRecommend_grade_0() {
            return recommend_grade_0;
        }

        public void setRecommend_grade_0(String recommend_grade_0) {
            this.recommend_grade_0 = recommend_grade_0;
        }

        public String getRecommend_grade_1() {
            return recommend_grade_1;
        }

        public void setRecommend_grade_1(String recommend_grade_1) {
            this.recommend_grade_1 = recommend_grade_1;
        }

        public String getRecommend_grade_2() {
            return recommend_grade_2;
        }

        public void setRecommend_grade_2(String recommend_grade_2) {
            this.recommend_grade_2 = recommend_grade_2;
        }

        public String getRecommend_grade_3() {
            return recommend_grade_3;
        }

        public void setRecommend_grade_3(String recommend_grade_3) {
            this.recommend_grade_3 = recommend_grade_3;
        }

        public String getRecommend_grade_4() {
            return recommend_grade_4;
        }

        public void setRecommend_grade_4(String recommend_grade_4) {
            this.recommend_grade_4 = recommend_grade_4;
        }

        public String getRecommend_grade_5() {
            return recommend_grade_5;
        }

        public void setRecommend_grade_5(String recommend_grade_5) {
            this.recommend_grade_5 = recommend_grade_5;
        }

        public String getRecommend_grade_6() {
            return recommend_grade_6;
        }

        public void setRecommend_grade_6(String recommend_grade_6) {
            this.recommend_grade_6 = recommend_grade_6;
        }
    }
}
