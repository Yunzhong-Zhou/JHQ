package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/5/26.
 */
public class MyGameModel implements Serializable {
    /**
     * win_money : 0.00
     * game_num : 1
     * win_num : 0
     * like_game_participation_list : [{"id":"7a92af7fdd6f0c82521fe0b82a1573a2","like_game_id":"fa7513559a990a237ce6b7c3cd634cca","like_game_period":"1906010002","bet_chain":2,"money":"100.00","bureau_win_money":"0","created_at":"2019-06-05 10:22:55","bet_chain_title":"侧链","history_like_game":{"id":"fa7513559a990a237ce6b7c3cd634cca","win_chain":1,"win_chain_title":"公链"}}]
     */

    private String win_money;
    private int game_num;
    private int win_num;
    private List<LikeGameParticipationListBean> like_game_participation_list;

    public String getWin_money() {
        return win_money;
    }

    public void setWin_money(String win_money) {
        this.win_money = win_money;
    }

    public int getGame_num() {
        return game_num;
    }

    public void setGame_num(int game_num) {
        this.game_num = game_num;
    }

    public int getWin_num() {
        return win_num;
    }

    public void setWin_num(int win_num) {
        this.win_num = win_num;
    }

    public List<LikeGameParticipationListBean> getLike_game_participation_list() {
        return like_game_participation_list;
    }

    public void setLike_game_participation_list(List<LikeGameParticipationListBean> like_game_participation_list) {
        this.like_game_participation_list = like_game_participation_list;
    }

    public static class LikeGameParticipationListBean {
        /**
         * id : 7a92af7fdd6f0c82521fe0b82a1573a2
         * like_game_id : fa7513559a990a237ce6b7c3cd634cca
         * like_game_period : 1906010002
         * bet_chain : 2
         * money : 100.00
         * bureau_win_money : 0
         * created_at : 2019-06-05 10:22:55
         * bet_chain_title : 侧链
         * history_like_game : {"id":"fa7513559a990a237ce6b7c3cd634cca","win_chain":1,"win_chain_title":"公链"}
         */

        private String id;
        private String like_game_id;
        private String like_game_period;
        private int bet_chain;
        private String money;
        private String bureau_win_money;
        private String created_at;
        private String bet_chain_title;
        private HistoryLikeGameBean history_like_game;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLike_game_id() {
            return like_game_id;
        }

        public void setLike_game_id(String like_game_id) {
            this.like_game_id = like_game_id;
        }

        public String getLike_game_period() {
            return like_game_period;
        }

        public void setLike_game_period(String like_game_period) {
            this.like_game_period = like_game_period;
        }

        public int getBet_chain() {
            return bet_chain;
        }

        public void setBet_chain(int bet_chain) {
            this.bet_chain = bet_chain;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getBureau_win_money() {
            return bureau_win_money;
        }

        public void setBureau_win_money(String bureau_win_money) {
            this.bureau_win_money = bureau_win_money;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getBet_chain_title() {
            return bet_chain_title;
        }

        public void setBet_chain_title(String bet_chain_title) {
            this.bet_chain_title = bet_chain_title;
        }

        public HistoryLikeGameBean getHistory_like_game() {
            return history_like_game;
        }

        public void setHistory_like_game(HistoryLikeGameBean history_like_game) {
            this.history_like_game = history_like_game;
        }

        public static class HistoryLikeGameBean {
            /**
             * id : fa7513559a990a237ce6b7c3cd634cca
             * win_chain : 1
             * win_chain_title : 公链
             */

            private String id;
            private int win_chain;
            private String win_chain_title;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getWin_chain() {
                return win_chain;
            }

            public void setWin_chain(int win_chain) {
                this.win_chain = win_chain;
            }

            public String getWin_chain_title() {
                return win_chain_title;
            }

            public void setWin_chain_title(String win_chain_title) {
                this.win_chain_title = win_chain_title;
            }
        }
    }
}
