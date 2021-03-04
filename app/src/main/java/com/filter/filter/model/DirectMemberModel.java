package com.filter.filter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2018/11/9.
 */
public class DirectMemberModel implements Serializable {
    /**
     * direct_recommend_list : [{"head":"/head/795.png","nickname":"空气净化器_tntk","grade_title":"普通用户","order_goods_count":0}]
     * grade_1 : 1
     * grade_2 : 0
     * grade_3 : 0
     * grade_4 : 0
     */

    private String grade_1;
    private String grade_2;
    private String grade_3;
    private String grade_4;
    private List<DirectRecommendListBean> direct_recommend_list;

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

    public List<DirectRecommendListBean> getDirect_recommend_list() {
        return direct_recommend_list;
    }

    public void setDirect_recommend_list(List<DirectRecommendListBean> direct_recommend_list) {
        this.direct_recommend_list = direct_recommend_list;
    }

    public static class DirectRecommendListBean {
        /**
         * head : /head/795.png
         * nickname : 空气净化器_tntk
         * grade_title : 普通用户
         * order_goods_count : 0
         */

        private String head;
        private String nickname;
        private String grade_title;
        private int order_goods_count;

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

        public int getOrder_goods_count() {
            return order_goods_count;
        }

        public void setOrder_goods_count(int order_goods_count) {
            this.order_goods_count = order_goods_count;
        }
    }
}
