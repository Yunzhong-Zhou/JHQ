package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/5/26.
 */
public class LeaderboardModel implements Serializable {
    /**
     * rank_list : [{"head":"/head/193.png","nickname":"10000000013","win_money":"53505","win_num":691},{"head":"/head/78.png","nickname":"10000000004","win_money":"22656.6","win_num":678},{"head":"/head/117.png","nickname":"10000000005","win_money":"11478.6","win_num":692},{"head":"/head/73.png","nickname":"10000000014","win_money":"8472.6","win_num":720},{"head":"/head/6.png","nickname":"10000000016","win_money":"8024.4","win_num":714},{"head":"/head/182.png","nickname":"10000000018","win_money":"7714.8","win_num":714},{"head":"/head/81.png","nickname":"10000000017","win_money":"7624.8","win_num":710},{"head":"/head/134.png","nickname":"10000000019","win_money":"7556.4","win_num":713},{"head":"/head/198.png","nickname":"10000000015","win_money":"7533","win_num":706},{"head":"/head/177.png","nickname":"10000000020","win_money":"7480.8","win_num":700},{"head":"/head/36.png","nickname":"10000000012","win_money":"7133.4","win_num":705},{"head":"/head/189.png","nickname":"10000000011","win_money":"7079.4","win_num":694},{"head":"/head/150.png","nickname":"10000000003","win_money":"7034.4","win_num":703},{"head":"/head/153.png","nickname":"10000000002","win_money":"7025.4","win_num":669},{"head":"/head/195.png","nickname":"10000000009","win_money":"6867","win_num":694},{"head":"/head/80.png","nickname":"10000000007","win_money":"6863.4","win_num":696},{"head":"/head/196.png","nickname":"10000000010","win_money":"6775.2","win_num":685},{"head":"/head/110.png","nickname":"10000000001","win_money":"6737.4","win_num":675},{"head":"/head/67.png","nickname":"10000000008","win_money":"6703.2","win_num":660},{"head":"/head/188.png","nickname":"10000000006","win_money":"6609.6","win_num":681},{"head":"/head/28.png","nickname":"妖精","win_money":"4902","win_num":199},{"head":"/head/195.png","nickname":"敏哥","win_money":"3251","win_num":32},{"head":"/head/31.png","nickname":"小红帽","win_money":"1920","win_num":188},{"head":"/head/166.png","nickname":"block","win_money":"1313","win_num":164},{"head":"/head/324.png","nickname":"10000000883","win_money":"181.8","win_num":24},{"head":"/head/189.png","nickname":"01","win_money":"180","win_num":2},{"head":"/head/694.png","nickname":"09","win_money":"180","win_num":1},{"head":"/head/966.png","nickname":"10000000980","win_money":"174.6","win_num":24},{"head":"/head/953.png","nickname":"10000000691","win_money":"171","win_num":22},{"head":"/head/143.png","nickname":"10000000025","win_money":"167.4","win_num":26},{"head":"/head/615.png","nickname":"10000000552","win_money":"167.4","win_num":23},{"head":"/head/272.png","nickname":"10000000598","win_money":"167.4","win_num":29},{"head":"/head/841.png","nickname":"10000000511","win_money":"165.6","win_num":25},{"head":"/head/282.png","nickname":"10000000167","win_money":"162","win_num":21},{"head":"/head/553.png","nickname":"10000000352","win_money":"158.4","win_num":20},{"head":"/head/572.png","nickname":"10000000845","win_money":"156.6","win_num":20},{"head":"/head/240.png","nickname":"10000000689","win_money":"154.8","win_num":22},{"head":"/head/792.png","nickname":"10000000429","win_money":"147.6","win_num":19},{"head":"/head/356.png","nickname":"10000000223","win_money":"147.6","win_num":25},{"head":"/head/293.png","nickname":"10000000288","win_money":"144","win_num":20},{"head":"/head/286.png","nickname":"10000000806","win_money":"144","win_num":20},{"head":"/head/97.png","nickname":"10000000190","win_money":"142.2","win_num":21},{"head":"/head/776.png","nickname":"10000000493","win_money":"142.2","win_num":21},{"head":"/head/940.png","nickname":"10000000184","win_money":"142.2","win_num":21},{"head":"/head/868.png","nickname":"10000000539","win_money":"140.4","win_num":20},{"head":"/head/863.png","nickname":"10000000341","win_money":"140.4","win_num":23},{"head":"/head/463.png","nickname":"10000000679","win_money":"138.6","win_num":19},{"head":"/head/899.png","nickname":"10000000406","win_money":"138.6","win_num":21},{"head":"/head/924.png","nickname":"10000000973","win_money":"138.6","win_num":17},{"head":"/head/761.png","nickname":"10000000740","win_money":"138.6","win_num":19}]
     * my_rank : {"head":"/head/114.png","nickname":"阿斯顿马丁","win_money":"25.20","win_num":6,"rank":1001}
     */

    private MyRankBean my_rank;
    private List<RankListBean> rank_list;

    public MyRankBean getMy_rank() {
        return my_rank;
    }

    public void setMy_rank(MyRankBean my_rank) {
        this.my_rank = my_rank;
    }

    public List<RankListBean> getRank_list() {
        return rank_list;
    }

    public void setRank_list(List<RankListBean> rank_list) {
        this.rank_list = rank_list;
    }

    public static class MyRankBean {
        /**
         * head : /head/114.png
         * nickname : 阿斯顿马丁
         * win_money : 25.20
         * win_num : 6
         * rank : 1001
         */

        private String head;
        private String nickname;
        private String win_money;
        private int win_num;
        private int rank;

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

        public String getWin_money() {
            return win_money;
        }

        public void setWin_money(String win_money) {
            this.win_money = win_money;
        }

        public int getWin_num() {
            return win_num;
        }

        public void setWin_num(int win_num) {
            this.win_num = win_num;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
    }

    public static class RankListBean {
        /**
         * head : /head/193.png
         * nickname : 10000000013
         * win_money : 53505
         * win_num : 691
         */

        private String head;
        private String nickname;
        private String win_money;
        private int win_num;

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

        public String getWin_money() {
            return win_money;
        }

        public void setWin_money(String win_money) {
            this.win_money = win_money;
        }

        public int getWin_num() {
            return win_num;
        }

        public void setWin_num(int win_num) {
            this.win_num = win_num;
        }
    }
}
