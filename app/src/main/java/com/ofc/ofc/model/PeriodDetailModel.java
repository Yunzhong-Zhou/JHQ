package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/5/22.
 */
public class PeriodDetailModel implements Serializable {
    /**
     * like_game : {"id":"7bbb8dd91a6b44560faf3cbd9f63226e","period":"1906100001","amount_money_1":"5","amount_money_2":"30","win_chain":1,"status":2,"win_chain_title":"公链","amount_money":"35","history_like_game_participation_list":[{"like_game_id":"7bbb8dd91a6b44560faf3cbd9f63226e","member_nickname":"77","member_head":"/head/200.png","bet_chain":2,"money":"20","bureau_win_money":"0.00","created_at":"2019-06-10 13:04:16","bet_chain_title":"侧链"},{"like_game_id":"7bbb8dd91a6b44560faf3cbd9f63226e","member_nickname":"77","member_head":"/head/200.png","bet_chain":2,"money":"10","bureau_win_money":"0.00","created_at":"2019-06-10 13:03:36","bet_chain_title":"侧链"},{"like_game_id":"7bbb8dd91a6b44560faf3cbd9f63226e","member_nickname":"77","member_head":"/head/200.png","bet_chain":1,"money":"5","bureau_win_money":"4.00","created_at":"2019-06-10 13:03:29","bet_chain_title":"公链"}]}
     * bureau_win_money : 0
     * my_like_game_participation_list : [{"like_game_id":"7bbb8dd91a6b44560faf3cbd9f63226e","member_nickname":"77","member_head":"/head/200.png","bet_chain":2,"money":"10","bureau_win_money":"0.00","created_at":"2019-06-10 13:03:36","bet_chain_title":"侧链"}]
     */

    private String public_chain_money;
    private String broadside_chain_money;
    private LikeGameBean like_game;
    private String bureau_win_money;
    private List<MyLikeGameParticipationListBean> my_like_game_participation_list;

    public String getPublic_chain_money() {
        return public_chain_money;
    }

    public void setPublic_chain_money(String public_chain_money) {
        this.public_chain_money = public_chain_money;
    }

    public String getBroadside_chain_money() {
        return broadside_chain_money;
    }

    public void setBroadside_chain_money(String broadside_chain_money) {
        this.broadside_chain_money = broadside_chain_money;
    }

    public LikeGameBean getLike_game() {
        return like_game;
    }

    public void setLike_game(LikeGameBean like_game) {
        this.like_game = like_game;
    }

    public String getBureau_win_money() {
        return bureau_win_money;
    }

    public void setBureau_win_money(String bureau_win_money) {
        this.bureau_win_money = bureau_win_money;
    }

    public List<MyLikeGameParticipationListBean> getMy_like_game_participation_list() {
        return my_like_game_participation_list;
    }

    public void setMy_like_game_participation_list(List<MyLikeGameParticipationListBean> my_like_game_participation_list) {
        this.my_like_game_participation_list = my_like_game_participation_list;
    }

    public static class LikeGameBean {
        /**
         * id : 7bbb8dd91a6b44560faf3cbd9f63226e
         * period : 1906100001
         * amount_money_1 : 5
         * amount_money_2 : 30
         * win_chain : 1
         * status : 2
         * win_chain_title : 公链
         * amount_money : 35
         * history_like_game_participation_list : [{"like_game_id":"7bbb8dd91a6b44560faf3cbd9f63226e","member_nickname":"77","member_head":"/head/200.png","bet_chain":2,"money":"20","bureau_win_money":"0.00","created_at":"2019-06-10 13:04:16","bet_chain_title":"侧链"},{"like_game_id":"7bbb8dd91a6b44560faf3cbd9f63226e","member_nickname":"77","member_head":"/head/200.png","bet_chain":2,"money":"10","bureau_win_money":"0.00","created_at":"2019-06-10 13:03:36","bet_chain_title":"侧链"},{"like_game_id":"7bbb8dd91a6b44560faf3cbd9f63226e","member_nickname":"77","member_head":"/head/200.png","bet_chain":1,"money":"5","bureau_win_money":"4.00","created_at":"2019-06-10 13:03:29","bet_chain_title":"公链"}]
         */

        private String id;
        private String period;
        private String amount_money_1;
        private String amount_money_2;
        private int win_chain;
        private int status;
        private String win_chain_title;
        private String amount_money;
        private List<HistoryLikeGameParticipationListBean> history_like_game_participation_list;

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getWin_chain_title() {
            return win_chain_title;
        }

        public void setWin_chain_title(String win_chain_title) {
            this.win_chain_title = win_chain_title;
        }

        public String getAmount_money() {
            return amount_money;
        }

        public void setAmount_money(String amount_money) {
            this.amount_money = amount_money;
        }

        public List<HistoryLikeGameParticipationListBean> getHistory_like_game_participation_list() {
            return history_like_game_participation_list;
        }

        public void setHistory_like_game_participation_list(List<HistoryLikeGameParticipationListBean> history_like_game_participation_list) {
            this.history_like_game_participation_list = history_like_game_participation_list;
        }

        public static class HistoryLikeGameParticipationListBean {
            /**
             * like_game_id : 7bbb8dd91a6b44560faf3cbd9f63226e
             * member_nickname : 77
             * member_head : /head/200.png
             * bet_chain : 2
             * money : 20
             * bureau_win_money : 0.00
             * created_at : 2019-06-10 13:04:16
             * bet_chain_title : 侧链
             */

            private String like_game_id;
            private String member_nickname;
            private String member_head;
            private int bet_chain;
            private String money;
            private String bureau_win_money;
            private String show_created_at;
            private String bet_chain_title;

            public String getLike_game_id() {
                return like_game_id;
            }

            public void setLike_game_id(String like_game_id) {
                this.like_game_id = like_game_id;
            }

            public String getMember_nickname() {
                return member_nickname;
            }

            public void setMember_nickname(String member_nickname) {
                this.member_nickname = member_nickname;
            }

            public String getMember_head() {
                return member_head;
            }

            public void setMember_head(String member_head) {
                this.member_head = member_head;
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

            public String getShow_created_at() {
                return show_created_at;
            }

            public void setShow_created_at(String show_created_at) {
                this.show_created_at = show_created_at;
            }

            public String getBet_chain_title() {
                return bet_chain_title;
            }

            public void setBet_chain_title(String bet_chain_title) {
                this.bet_chain_title = bet_chain_title;
            }
        }
    }

    public static class MyLikeGameParticipationListBean {
        /**
         * like_game_id : 7bbb8dd91a6b44560faf3cbd9f63226e
         * member_nickname : 77
         * member_head : /head/200.png
         * bet_chain : 2
         * money : 10
         * bureau_win_money : 0.00
         * created_at : 2019-06-10 13:03:36
         * bet_chain_title : 侧链
         */

        private String like_game_id;
        private String member_nickname;
        private String member_head;
        private int bet_chain;
        private String money;
        private String bureau_win_money;
        private String show_created_at;
        private String bet_chain_title;

        public String getLike_game_id() {
            return like_game_id;
        }

        public void setLike_game_id(String like_game_id) {
            this.like_game_id = like_game_id;
        }

        public String getMember_nickname() {
            return member_nickname;
        }

        public void setMember_nickname(String member_nickname) {
            this.member_nickname = member_nickname;
        }

        public String getMember_head() {
            return member_head;
        }

        public void setMember_head(String member_head) {
            this.member_head = member_head;
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

        public String getShow_created_at() {
            return show_created_at;
        }

        public void setShow_created_at(String show_created_at) {
            this.show_created_at = show_created_at;
        }

        public String getBet_chain_title() {
            return bet_chain_title;
        }

        public void setBet_chain_title(String bet_chain_title) {
            this.bet_chain_title = bet_chain_title;
        }
    }
}
