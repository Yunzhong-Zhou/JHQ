package com.cho.chocoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2018/11/12.
 */
public class ShareModel implements Serializable {
    /**
     * head : /head/93.png
     * nickname : 阿斯顿马丁
     * invite_code : 62761239
     * grade : 1
     * grade_title : 青铜会员
     * grade_performance_1 : 0
     * grade_performance_2 : 10000
     * grade_performance_3 : 50000
     * grade_performance_4 : 200000
     * grade_performance_5 : 500000
     * current_direct_performance_money : 0.00
     * difference_direct_performance_money : 10000
     * direct_recommend : 1
     * team_recommend : 0
     * direct_performance_money : 0.00
     * team_performance_money : 0.00
     * direct_invest_money : 0.00
     * team_invest_money : 0.00
     * grade_count_list : {"grade_1":1,"grade_2":0,"grade_3":0,"grade_4":0,"grade_5":0}
     * performance_list : [{"start_at":"06.15","end_at":"07.15","performance_money":"0","status":1,"status_title":"进行中"}]
     */

    private String head;
    private String nickname;
    private String invite_code;
    private int grade;
    private String grade_title;
    private int grade_performance_1;
    private int grade_performance_2;
    private int grade_performance_3;
    private int grade_performance_4;
    private int grade_performance_5;
    private String current_direct_performance_money;
    private int difference_direct_performance_money;
    private int direct_recommend;
    private int team_recommend;
    private String direct_performance_money;
    private String team_performance_money;
    private String direct_invest_money;
    private String team_invest_money;
    private GradeCountListBean grade_count_list;
    private List<PerformanceListBean> performance_list;

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

    public int getGrade_performance_1() {
        return grade_performance_1;
    }

    public void setGrade_performance_1(int grade_performance_1) {
        this.grade_performance_1 = grade_performance_1;
    }

    public int getGrade_performance_2() {
        return grade_performance_2;
    }

    public void setGrade_performance_2(int grade_performance_2) {
        this.grade_performance_2 = grade_performance_2;
    }

    public int getGrade_performance_3() {
        return grade_performance_3;
    }

    public void setGrade_performance_3(int grade_performance_3) {
        this.grade_performance_3 = grade_performance_3;
    }

    public int getGrade_performance_4() {
        return grade_performance_4;
    }

    public void setGrade_performance_4(int grade_performance_4) {
        this.grade_performance_4 = grade_performance_4;
    }

    public int getGrade_performance_5() {
        return grade_performance_5;
    }

    public void setGrade_performance_5(int grade_performance_5) {
        this.grade_performance_5 = grade_performance_5;
    }

    public String getCurrent_direct_performance_money() {
        return current_direct_performance_money;
    }

    public void setCurrent_direct_performance_money(String current_direct_performance_money) {
        this.current_direct_performance_money = current_direct_performance_money;
    }

    public int getDifference_direct_performance_money() {
        return difference_direct_performance_money;
    }

    public void setDifference_direct_performance_money(int difference_direct_performance_money) {
        this.difference_direct_performance_money = difference_direct_performance_money;
    }

    public int getDirect_recommend() {
        return direct_recommend;
    }

    public void setDirect_recommend(int direct_recommend) {
        this.direct_recommend = direct_recommend;
    }

    public int getTeam_recommend() {
        return team_recommend;
    }

    public void setTeam_recommend(int team_recommend) {
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

    public String getDirect_invest_money() {
        return direct_invest_money;
    }

    public void setDirect_invest_money(String direct_invest_money) {
        this.direct_invest_money = direct_invest_money;
    }

    public String getTeam_invest_money() {
        return team_invest_money;
    }

    public void setTeam_invest_money(String team_invest_money) {
        this.team_invest_money = team_invest_money;
    }

    public GradeCountListBean getGrade_count_list() {
        return grade_count_list;
    }

    public void setGrade_count_list(GradeCountListBean grade_count_list) {
        this.grade_count_list = grade_count_list;
    }

    public List<PerformanceListBean> getPerformance_list() {
        return performance_list;
    }

    public void setPerformance_list(List<PerformanceListBean> performance_list) {
        this.performance_list = performance_list;
    }

    public static class GradeCountListBean {
        /**
         * grade_1 : 1
         * grade_2 : 0
         * grade_3 : 0
         * grade_4 : 0
         * grade_5 : 0
         */

        private int grade_1;
        private int grade_2;
        private int grade_3;
        private int grade_4;
        private int grade_5;

        public int getGrade_1() {
            return grade_1;
        }

        public void setGrade_1(int grade_1) {
            this.grade_1 = grade_1;
        }

        public int getGrade_2() {
            return grade_2;
        }

        public void setGrade_2(int grade_2) {
            this.grade_2 = grade_2;
        }

        public int getGrade_3() {
            return grade_3;
        }

        public void setGrade_3(int grade_3) {
            this.grade_3 = grade_3;
        }

        public int getGrade_4() {
            return grade_4;
        }

        public void setGrade_4(int grade_4) {
            this.grade_4 = grade_4;
        }

        public int getGrade_5() {
            return grade_5;
        }

        public void setGrade_5(int grade_5) {
            this.grade_5 = grade_5;
        }
    }

    public static class PerformanceListBean {
        /**
         * start_at : 06.15
         * end_at : 07.15
         * performance_money : 0
         * status : 1
         * status_title : 进行中
         */

        private String start_at;
        private String end_at;
        private String performance_money;
        private int status;
        private String status_title;

        public String getStart_at() {
            return start_at;
        }

        public void setStart_at(String start_at) {
            this.start_at = start_at;
        }

        public String getEnd_at() {
            return end_at;
        }

        public void setEnd_at(String end_at) {
            this.end_at = end_at;
        }

        public String getPerformance_money() {
            return performance_money;
        }

        public void setPerformance_money(String performance_money) {
            this.performance_money = performance_money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }
    }
}
