package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/5/26.
 */
public class PastListModel implements Serializable {

    private List<LikeGameListBean> like_game_list;

    public List<LikeGameListBean> getLike_game_list() {
        return like_game_list;
    }

    public void setLike_game_list(List<LikeGameListBean> like_game_list) {
        this.like_game_list = like_game_list;
    }

    public static class LikeGameListBean {
        /**
         * id : 7bbb8dd91a6b44560faf3cbd9f63226e
         * period : 1906100001
         * amount_money_1 : 5
         * amount_money_2 : 30
         * win_chain : 1
         * created_at : 2019-06-10 12:05:27
         * win_chain_title : 公链
         */

        private String id;
        private String period;
        private String amount_money_1;
        private String amount_money_2;
        private int win_chain;
        private String created_at;
        private String win_chain_title;
        private String amount_money;

        public String getAmount_money() {
            return amount_money;
        }

        public void setAmount_money(String amount_money) {
            this.amount_money = amount_money;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getAmount_money_1() {
            return amount_money_1;
        }

        public void setAmount_money_1(String amount_money_1) {
            this.amount_money_1 = amount_money_1;
        }

        public String getAmount_money_2() {
            return amount_money_2;
        }

        public void setAmount_money_2(String amount_money_2) {
            this.amount_money_2 = amount_money_2;
        }

        public int getWin_chain() {
            return win_chain;
        }

        public void setWin_chain(int win_chain) {
            this.win_chain = win_chain;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getWin_chain_title() {
            return win_chain_title;
        }

        public void setWin_chain_title(String win_chain_title) {
            this.win_chain_title = win_chain_title;
        }
    }
}
