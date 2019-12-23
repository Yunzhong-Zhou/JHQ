package com.ofc.ofc.model;

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
         * head : /head/133.png
         * nickname : 158****9474
         * contract_money : 0
         * grade_title : 普通会员
         */

        private String head;
        private String nickname;
        private int contract_money;
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

        public int getContract_money() {
            return contract_money;
        }

        public void setContract_money(int contract_money) {
            this.contract_money = contract_money;
        }

        public String getGrade_title() {
            return grade_title;
        }

        public void setGrade_title(String grade_title) {
            this.grade_title = grade_title;
        }
    }
}
