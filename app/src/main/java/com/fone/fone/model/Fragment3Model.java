package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/5/22.
 */
public class Fragment3Model implements Serializable {
    /**
     * change_game : {"id":"5ac8dbdf0b28b9a2ad429f16ee00b379","mill_id":"","mill":null,"change_game_participation_list":[]}
     * change_game_list : [{"period":"202011010130","win_member_id":"","created_at":"2020-11-01 01:30:15","win_member":null},{"period":"202010010130","win_member_id":"","created_at":"2020-10-01 01:30:13","win_member":null},{"period":"202009010130","win_member_id":"","created_at":"2020-09-01 01:30:12","win_member":null},{"period":"202008010130","win_member_id":"","created_at":"2020-08-01 01:30:12","win_member":null}]
     * member_list : [{"head":"/head/336.png","nickname":"FIL_zxms","change_game_win_time":0,"hashrate":0},{"head":"/head/455.png","nickname":"FIL_phqy","change_game_win_time":0,"hashrate":0},{"head":"/head/396.png","nickname":"FIL_wjpn","change_game_win_time":0,"hashrate":0},{"head":"/head/434.png","nickname":"FIL_jsir","change_game_win_time":0,"hashrate":0},{"head":"/head/76.png","nickname":"FIL_omza","change_game_win_time":0,"hashrate":0},{"head":"/head/606.png","nickname":"FIL_pour广告费","change_game_win_time":0,"hashrate":0}]
     * hk : c5a628d4cc7df8e7800afb9934ab2261
     */

    private ChangeGameBean change_game;
    private String hk;
    private List<ChangeGameListBean> change_game_list;
    private List<MemberListBean> member_list;

    public ChangeGameBean getChange_game() {
        return change_game;
    }

    public void setChange_game(ChangeGameBean change_game) {
        this.change_game = change_game;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }

    public List<ChangeGameListBean> getChange_game_list() {
        return change_game_list;
    }

    public void setChange_game_list(List<ChangeGameListBean> change_game_list) {
        this.change_game_list = change_game_list;
    }

    public List<MemberListBean> getMember_list() {
        return member_list;
    }

    public void setMember_list(List<MemberListBean> member_list) {
        this.member_list = member_list;
    }

    public static class ChangeGameBean {
        /**
         * id : 5ac8dbdf0b28b9a2ad429f16ee00b379
         * mill_id :
         * mill : null
         * change_game_participation_list : []
         */

        private String id;
        private String mill_id;
        private Object mill;
        private List<?> change_game_participation_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMill_id() {
            return mill_id;
        }

        public void setMill_id(String mill_id) {
            this.mill_id = mill_id;
        }

        public Object getMill() {
            return mill;
        }

        public void setMill(Object mill) {
            this.mill = mill;
        }

        public List<?> getChange_game_participation_list() {
            return change_game_participation_list;
        }

        public void setChange_game_participation_list(List<?> change_game_participation_list) {
            this.change_game_participation_list = change_game_participation_list;
        }
    }

    public static class ChangeGameListBean {
        /**
         * period : 202011010130
         * win_member_id :
         * created_at : 2020-11-01 01:30:15
         * win_member : null
         */

        private String period;
        private String win_member_id;
        private String created_at;
        private Object win_member;

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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Object getWin_member() {
            return win_member;
        }

        public void setWin_member(Object win_member) {
            this.win_member = win_member;
        }
    }

    public static class MemberListBean {
        /**
         * head : /head/336.png
         * nickname : FIL_zxms
         * change_game_win_time : 0
         * hashrate : 0
         */

        private String head;
        private String nickname;
        private int change_game_win_time;
        private int hashrate;

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

        public int getChange_game_win_time() {
            return change_game_win_time;
        }

        public void setChange_game_win_time(int change_game_win_time) {
            this.change_game_win_time = change_game_win_time;
        }

        public int getHashrate() {
            return hashrate;
        }

        public void setHashrate(int hashrate) {
            this.hashrate = hashrate;
        }
    }
}
