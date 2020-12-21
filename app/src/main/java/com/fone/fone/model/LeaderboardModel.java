package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/5/26.
 */
public class LeaderboardModel implements Serializable {
    /**
     * rank_list : [{"head":"/head/336.png","nickname":"FIL_zxms","hashrate":0,"change_game_win_time":0,"rank":1},{"head":"/head/260.png","nickname":"FIL_gcln","hashrate":0,"change_game_win_time":0,"rank":2},{"head":"/head/455.png","nickname":"FIL_phqy","hashrate":0,"change_game_win_time":0,"rank":3},{"head":"/head/396.png","nickname":"FIL_wjpn","hashrate":0,"change_game_win_time":0,"rank":4},{"head":"/head/434.png","nickname":"FIL_jsir","hashrate":0,"change_game_win_time":0,"rank":5},{"head":"/head/76.png","nickname":"FIL_omza","hashrate":0,"change_game_win_time":0,"rank":6},{"head":"/head/606.png","nickname":"FIL_pour广告费","hashrate":0,"change_game_win_time":0,"rank":7}]
     * my_rank : {"head":"/head/606.png","nickname":"FIL_pour广告费","hashrate":0,"change_game_win_time":0,"rank":7}
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
         * head : /head/606.png
         * nickname : FIL_pour广告费
         * hashrate : 0
         * change_game_win_time : 0
         * rank : 7
         */

        private String head;
        private String nickname;
        private String hashrate;
        private String change_game_win_time;
        private String rank;

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

        public String getHashrate() {
            return hashrate;
        }

        public void setHashrate(String hashrate) {
            this.hashrate = hashrate;
        }

        public String getChange_game_win_time() {
            return change_game_win_time;
        }

        public void setChange_game_win_time(String change_game_win_time) {
            this.change_game_win_time = change_game_win_time;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }
    }

    public static class RankListBean {
        /**
         * head : /head/336.png
         * nickname : FIL_zxms
         * hashrate : 0
         * change_game_win_time : 0
         * rank : 1
         */

        private String head;
        private String nickname;
        private String hashrate;
        private String change_game_win_time;
        private String rank;

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

        public String getHashrate() {
            return hashrate;
        }

        public void setHashrate(String hashrate) {
            this.hashrate = hashrate;
        }

        public String getChange_game_win_time() {
            return change_game_win_time;
        }

        public void setChange_game_win_time(String change_game_win_time) {
            this.change_game_win_time = change_game_win_time;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }
    }
}
