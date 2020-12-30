package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2018/11/9.
 */
public class DirectMemberModel implements Serializable {
    private List<DirectRecommendListBean> direct_recommend_list;

    public List<DirectRecommendListBean> getDirect_recommend_list() {
        return direct_recommend_list;
    }

    public void setDirect_recommend_list(List<DirectRecommendListBean> direct_recommend_list) {
        this.direct_recommend_list = direct_recommend_list;
    }

    public static class DirectRecommendListBean {
        /**
         * head : /head/645.png
         * nickname : FIL_adwy
         * grade_title : V0
         * amount_performance_money : 0
         * change_game_invest_money : 0.0000
         * buy_invest_money : 0.0000
         * all_invest_money : 0.0000
         */

        private String head;
        private String nickname;
        private String grade_title;
        private String amount_performance_money;
        private String amount_change_game_money;
        private String buy_invest_money;
        private String all_invest_money;
        private String invite_code;

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
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

        public String getGrade_title() {
            return grade_title;
        }

        public void setGrade_title(String grade_title) {
            this.grade_title = grade_title;
        }

        public String getAmount_performance_money() {
            return amount_performance_money;
        }

        public void setAmount_performance_money(String amount_performance_money) {
            this.amount_performance_money = amount_performance_money;
        }

        public String getAmount_change_game_money() {
            return amount_change_game_money;
        }

        public void setAmount_change_game_money(String amount_change_game_money) {
            this.amount_change_game_money = amount_change_game_money;
        }

        public String getBuy_invest_money() {
            return buy_invest_money;
        }

        public void setBuy_invest_money(String buy_invest_money) {
            this.buy_invest_money = buy_invest_money;
        }

        public String getAll_invest_money() {
            return all_invest_money;
        }

        public void setAll_invest_money(String all_invest_money) {
            this.all_invest_money = all_invest_money;
        }
    }
}
