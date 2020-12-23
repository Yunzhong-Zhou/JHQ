package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/5/22.
 */
public class Fragment3Model implements Serializable {
    /**
     * change_game : {"id":"5ac8dbdf0b28b9a2ad429f16ee00b379","mill_id":"","mill":null,"change_game_participation_list":[{"change_game_id":"5ac8dbdf0b28b9a2ad429f16ee00b379","member_head":"/head/606.png","member_nickname":"FIL_pour广告费","index":0}]}
     * change_game_list : [{"period":"202011010130","win_member_id":"","created_at":"2020-11-01 01:30:15","win_member":null},{"period":"202010010130","win_member_id":"","created_at":"2020-10-01 01:30:13","win_member":null},{"period":"202009010130","win_member_id":"","created_at":"2020-09-01 01:30:12","win_member":null},{"period":"202008010130","win_member_id":"","created_at":"2020-08-01 01:30:12","win_member":null}]
     * member_list : [{"head":"/head/434.png","nickname":"FIL_jsir","change_game_win_time":0,"hashrate":11},{"head":"/head/606.png","nickname":"FIL_pour广告费","change_game_win_time":0,"hashrate":6},{"head":"/upload/head/2020-12-22/bf567d6279a6acd3f0ed7827a9abc6a9.png","nickname":"zhumimi","change_game_win_time":0,"hashrate":3},{"head":"/head/336.png","nickname":"FIL_zxms","change_game_win_time":0,"hashrate":0},{"head":"/head/260.png","nickname":"FIL_gcln","change_game_win_time":0,"hashrate":0},{"head":"/head/455.png","nickname":"FIL_phqy","change_game_win_time":0,"hashrate":0},{"head":"/head/396.png","nickname":"FIL_wjpn","change_game_win_time":0,"hashrate":0},{"head":"/upload/head/2020-12-21/029f0454982854602faf5d34329d8fa0.png","nickname":"shining","change_game_win_time":0,"hashrate":0}]
     * win_prompt : {"period":"","win_member_id":"","hashrate":0}
     * hk : 5fd7f78431d0c6d05dd401646a6d3003
     */

    private ChangeGameBean change_game;
    private WinPromptBean win_prompt;
    private String hk;
    private List<ChangeGameListBean> change_game_list;
    private List<MemberListBean> member_list;

    public ChangeGameBean getChange_game() {
        return change_game;
    }

    public void setChange_game(ChangeGameBean change_game) {
        this.change_game = change_game;
    }

    public WinPromptBean getWin_prompt() {
        return win_prompt;
    }

    public void setWin_prompt(WinPromptBean win_prompt) {
        this.win_prompt = win_prompt;
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
         * change_game_participation_list : [{"change_game_id":"5ac8dbdf0b28b9a2ad429f16ee00b379","member_head":"/head/606.png","member_nickname":"FIL_pour广告费","index":0}]
         */

        private String id;
        private String mill_id;
        private String mill;
        private List<ChangeGameParticipationListBean> change_game_participation_list;

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

        public String getMill() {
            return mill;
        }

        public void setMill(String mill) {
            this.mill = mill;
        }

        public List<ChangeGameParticipationListBean> getChange_game_participation_list() {
            return change_game_participation_list;
        }

        public void setChange_game_participation_list(List<ChangeGameParticipationListBean> change_game_participation_list) {
            this.change_game_participation_list = change_game_participation_list;
        }

        public static class ChangeGameParticipationListBean {
            /**
             * change_game_id : 5ac8dbdf0b28b9a2ad429f16ee00b379
             * member_head : /head/606.png
             * member_nickname : FIL_pour广告费
             * index : 0
             */

            private String change_game_id;
            private String member_head;
            private String member_nickname;
            private String index;

            public String getChange_game_id() {
                return change_game_id;
            }

            public void setChange_game_id(String change_game_id) {
                this.change_game_id = change_game_id;
            }

            public String getMember_head() {
                return member_head;
            }

            public void setMember_head(String member_head) {
                this.member_head = member_head;
            }

            public String getMember_nickname() {
                return member_nickname;
            }

            public void setMember_nickname(String member_nickname) {
                this.member_nickname = member_nickname;
            }

            public String getIndex() {
                return index;
            }

            public void setIndex(String index) {
                this.index = index;
            }
        }
    }

    public static class WinPromptBean {
        /**
         * period :
         * win_member_id :
         * hashrate : 0
         */

        private String period;
        private String win_member_id;
        private String hashrate;

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

        public String getHashrate() {
            return hashrate;
        }

        public void setHashrate(String hashrate) {
            this.hashrate = hashrate;
        }
    }

    public static class ChangeGameListBean {
        /**
         * period : 202011010130
         * win_member_id :
         * created_at : 2020-11-01 01:30:15
         * win_member : null
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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
         * head : /head/434.png
         * nickname : FIL_jsir
         * change_game_win_time : 0
         * hashrate : 11
         */

        private String head;
        private String nickname;
        private String change_game_win_time;
        private String hashrate;

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

        public String getChange_game_win_time() {
            return change_game_win_time;
        }

        public void setChange_game_win_time(String change_game_win_time) {
            this.change_game_win_time = change_game_win_time;
        }

        public String getHashrate() {
            return hashrate;
        }

        public void setHashrate(String hashrate) {
            this.hashrate = hashrate;
        }
    }
}
