package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2018/11/9.
 */
public class OFCDirectMemberModel implements Serializable {
    private List<DirectRecommendListBean> direct_recommend_list;

    public List<DirectRecommendListBean> getDirect_recommend_list() {
        return direct_recommend_list;
    }

    public void setDirect_recommend_list(List<DirectRecommendListBean> direct_recommend_list) {
        this.direct_recommend_list = direct_recommend_list;
    }

    public static class DirectRecommendListBean {
        /**
         * head : /head.png
         * nickname : hsm
         * invest_money : 0
         * grade_title : LP
         */

        private String head;
        private String nickname;
        private String invest_money;
        private String grade_title;

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

        public String getInvest_money() {
            return invest_money;
        }

        public void setInvest_money(String invest_money) {
            this.invest_money = invest_money;
        }

        public String getGrade_title() {
            return grade_title;
        }

        public void setGrade_title(String grade_title) {
            this.grade_title = grade_title;
        }
    }
}
