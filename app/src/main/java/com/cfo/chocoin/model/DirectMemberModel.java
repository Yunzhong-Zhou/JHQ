package com.cfo.chocoin.model;

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
         * head :
         * nickname : 阿斯顿马丁
         * created_at : 2018-11-08 17:22:30
         */

        private String head;
        private String nickname;
        private String created_at;
        private String amount_money;

        public String getAmount_money() {
            return amount_money;
        }

        public void setAmount_money(String amount_money) {
            this.amount_money = amount_money;
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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
