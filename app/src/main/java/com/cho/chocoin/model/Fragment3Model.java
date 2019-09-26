package com.cho.chocoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/5/22.
 */
public class Fragment3Model implements Serializable {
    /**
     * win_prompt : {"period":"","win_member_id":"","win_money":""}
     * usable_money : 400591.4
     * like_game : {"id":"c0af5ae29eb44c472133cdc0ed7bc614","period":"1906100002","amount_money_1":"62.00","amount_money_2":"330.00","created_at":"2019-06-10 13:05:49","like_game_participation_list":[{"like_game_id":"c0af5ae29eb44c472133cdc0ed7bc614","member_id":"8367de79067e59df8c8c039109b805c0","member_nickname":"阿斯顿马丁","member_head":"/head/119.png","bet_chain":2,"money":"300.00","created_at":"2019-06-11 12:48:55"},{"like_game_id":"c0af5ae29eb44c472133cdc0ed7bc614","member_id":"bc9edf3d73b55a01090597814beb80af","member_nickname":"66","member_head":"/head/161.png","bet_chain":1,"money":"12.00","created_at":"2019-06-11 11:12:22"},{"like_game_id":"c0af5ae29eb44c472133cdc0ed7bc614","member_id":"0677aee3e43a22d22edf896698021ff3","member_nickname":"77","member_head":"/head/200.png","bet_chain":2,"money":"30.00","created_at":"2019-06-10 13:48:14"},{"like_game_id":"c0af5ae29eb44c472133cdc0ed7bc614","member_id":"0677aee3e43a22d22edf896698021ff3","member_nickname":"77","member_head":"/head/200.png","bet_chain":1,"money":"50.00","created_at":"2019-06-10 13:47:40"}]}
     * amount_money : 392
     * my_like_game_participation_list : [{"like_game_id":"c0af5ae29eb44c472133cdc0ed7bc614","member_id":"8367de79067e59df8c8c039109b805c0","member_nickname":"阿斯顿马丁","member_head":"/head/119.png","bet_chain":2,"money":"300.00","created_at":"2019-06-11 12:48:55"}]
     * count_down : -110590
     * public_chain_money : 0
     * broadside_chain_money : 300
     */

    private String url;
    private WinPromptBean win_prompt;
    private String usable_money;
    private LikeGameBean like_game;
    private String amount_money;
    private Long count_down;
    private String public_chain_money;
    private String broadside_chain_money;
    private List<MyLikeGameParticipationListBean> my_like_game_participation_list;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WinPromptBean getWin_prompt() {
        return win_prompt;
    }

    public void setWin_prompt(WinPromptBean win_prompt) {
        this.win_prompt = win_prompt;
    }

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
    }

    public LikeGameBean getLike_game() {
        return like_game;
    }

    public void setLike_game(LikeGameBean like_game) {
        this.like_game = like_game;
    }

    public String getAmount_money() {
        return amount_money;
    }

    public void setAmount_money(String amount_money) {
        this.amount_money = amount_money;
    }

    public Long getCount_down() {
        return count_down;
    }

    public void setCount_down(Long count_down) {
        this.count_down = count_down;
    }

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

    public List<MyLikeGameParticipationListBean> getMy_like_game_participation_list() {
        return my_like_game_participation_list;
    }

    public void setMy_like_game_participation_list(List<MyLikeGameParticipationListBean> my_like_game_participation_list) {
        this.my_like_game_participation_list = my_like_game_participation_list;
    }

    public static class WinPromptBean {
        /**
         * period :
         * win_member_id :
         * win_money :
         */

        private String period;
        private String win_member_id;
        private String win_money;

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getWin_member_id() {
            return win_member_id;
        }

        public void setWin_member_id(String win_member_id) {
            this.win_member_id = win_member_id;
        }

        public String getWin_money() {
            return win_money;
        }

        public void setWin_money(String win_money) {
            this.win_money = win_money;
        }
    }

    public static class LikeGameBean {
        /**
         * id : c0af5ae29eb44c472133cdc0ed7bc614
         * period : 1906100002
         * amount_money_1 : 62.00
         * amount_money_2 : 330.00
         * created_at : 2019-06-10 13:05:49
         * like_game_participation_list : [{"like_game_id":"c0af5ae29eb44c472133cdc0ed7bc614","member_id":"8367de79067e59df8c8c039109b805c0","member_nickname":"阿斯顿马丁","member_head":"/head/119.png","bet_chain":2,"money":"300.00","created_at":"2019-06-11 12:48:55"},{"like_game_id":"c0af5ae29eb44c472133cdc0ed7bc614","member_id":"bc9edf3d73b55a01090597814beb80af","member_nickname":"66","member_head":"/head/161.png","bet_chain":1,"money":"12.00","created_at":"2019-06-11 11:12:22"},{"like_game_id":"c0af5ae29eb44c472133cdc0ed7bc614","member_id":"0677aee3e43a22d22edf896698021ff3","member_nickname":"77","member_head":"/head/200.png","bet_chain":2,"money":"30.00","created_at":"2019-06-10 13:48:14"},{"like_game_id":"c0af5ae29eb44c472133cdc0ed7bc614","member_id":"0677aee3e43a22d22edf896698021ff3","member_nickname":"77","member_head":"/head/200.png","bet_chain":1,"money":"50.00","created_at":"2019-06-10 13:47:40"}]
         */

        private String id;
        private String period;
        private String amount_money_1;
        private String amount_money_2;
        private String amount_money;
        private String created_at;
        private List<LikeGameParticipationListBean> like_game_participation_list;

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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public List<LikeGameParticipationListBean> getLike_game_participation_list() {
            return like_game_participation_list;
        }

        public void setLike_game_participation_list(List<LikeGameParticipationListBean> like_game_participation_list) {
            this.like_game_participation_list = like_game_participation_list;
        }

        public static class LikeGameParticipationListBean {
            /**
             * like_game_id : c0af5ae29eb44c472133cdc0ed7bc614
             * member_id : 8367de79067e59df8c8c039109b805c0
             * member_nickname : 阿斯顿马丁
             * member_head : /head/119.png
             * bet_chain : 2
             * money : 300.00
             * created_at : 2019-06-11 12:48:55
             */

            private String like_game_id;
            private String member_id;
            private String member_nickname;
            private String member_head;
            private int bet_chain;
            private String money;
            private String show_created_at;

            public String getLike_game_id() {
                return like_game_id;
            }

            public void setLike_game_id(String like_game_id) {
                this.like_game_id = like_game_id;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
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

            public String getShow_created_at() {
                return show_created_at;
            }

            public void setShow_created_at(String show_created_at) {
                this.show_created_at = show_created_at;
            }
        }
    }

    public static class MyLikeGameParticipationListBean {
        /**
         * like_game_id : c0af5ae29eb44c472133cdc0ed7bc614
         * member_id : 8367de79067e59df8c8c039109b805c0
         * member_nickname : 阿斯顿马丁
         * member_head : /head/119.png
         * bet_chain : 2
         * money : 300.00
         * created_at : 2019-06-11 12:48:55
         */

        private String like_game_id;
        private String member_id;
        private String member_nickname;
        private String member_head;
        private int bet_chain;
        private String money;
        private String show_created_at;

        public String getLike_game_id() {
            return like_game_id;
        }

        public void setLike_game_id(String like_game_id) {
            this.like_game_id = like_game_id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
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

        public String getShow_created_at() {
            return show_created_at;
        }

        public void setShow_created_at(String show_created_at) {
            this.show_created_at = show_created_at;
        }
    }
}
