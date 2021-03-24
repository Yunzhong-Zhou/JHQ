package com.ghzk.ghzk.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/12/23.
 */
public class JoinDetailModel implements Serializable {
    /**
     * change_game : {"id":"ef347f2c756ea6bd86d697106fa93f3a","mill_id":"","period":"202011010130","win_num":"","win_at":"","win_member_id":"","status":2,"last_participation_at":"","status_title":"待开奖","win_member":{"head":"/head/606.png","nickname":"FIL_pour广告费"},"mill":{"id":"b6a39b54e0bc799b0617b1f3d258e8c2","hashrate":10,"mining_cycle":360,"production_value_fil_money":"10.0000","computer_position":"中国福州"},"change_game_participation_list":[{"change_game_id":"5ac8dbdf0b28b9a2ad429f16ee00b379","member_head":"/head/606.png","member_nickname":"FIL_pour广告费","index":0}]}
     * count_down : 0
     */

    private ChangeGameBean change_game;
    private int count_down;

    public ChangeGameBean getChange_game() {
        return change_game;
    }

    public void setChange_game(ChangeGameBean change_game) {
        this.change_game = change_game;
    }

    public int getCount_down() {
        return count_down;
    }

    public void setCount_down(int count_down) {
        this.count_down = count_down;
    }

    public static class ChangeGameBean {
        /**
         * id : ef347f2c756ea6bd86d697106fa93f3a
         * mill_id :
         * period : 202011010130
         * win_num :
         * win_at :
         * win_member_id :
         * status : 2
         * last_participation_at :
         * status_title : 待开奖
         * win_member : {"head":"/head/606.png","nickname":"FIL_pour广告费"}
         * mill : {"id":"b6a39b54e0bc799b0617b1f3d258e8c2","hashrate":10,"mining_cycle":360,"production_value_fil_money":"10.0000","computer_position":"中国福州"}
         * change_game_participation_list : [{"change_game_id":"5ac8dbdf0b28b9a2ad429f16ee00b379","member_head":"/head/606.png","member_nickname":"FIL_pour广告费","index":0}]
         */

        private String id;
        private String mill_id;
        private String period;
        private String win_num;
        private String win_at;
        private String win_member_id;
        private int status;
        private String last_participation_at;
        private String status_title;
        private WinMemberBean win_member;
        private MillBean mill;
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

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getWin_num() {
            return win_num;
        }

        public void setWin_num(String win_num) {
            this.win_num = win_num;
        }

        public String getWin_at() {
            return win_at;
        }

        public void setWin_at(String win_at) {
            this.win_at = win_at;
        }

        public String getWin_member_id() {
            return win_member_id;
        }

        public void setWin_member_id(String win_member_id) {
            this.win_member_id = win_member_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getLast_participation_at() {
            return last_participation_at;
        }

        public void setLast_participation_at(String last_participation_at) {
            this.last_participation_at = last_participation_at;
        }

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }

        public WinMemberBean getWin_member() {
            return win_member;
        }

        public void setWin_member(WinMemberBean win_member) {
            this.win_member = win_member;
        }

        public MillBean getMill() {
            return mill;
        }

        public void setMill(MillBean mill) {
            this.mill = mill;
        }

        public List<ChangeGameParticipationListBean> getChange_game_participation_list() {
            return change_game_participation_list;
        }

        public void setChange_game_participation_list(List<ChangeGameParticipationListBean> change_game_participation_list) {
            this.change_game_participation_list = change_game_participation_list;
        }

        public static class WinMemberBean {

            /**
             * id : ef5849eae33c2d8e0d18bbfd02f5f7a1
             * head : /head/400.png
             * nickname : FIL_tbjr
             * change_game_participation_created_at : 2020-12-25 19:21:07
             * change_game_participation_index : 5
             */

            private String id;
            private String head;
            private String nickname;
            private String change_game_participation_created_at;
            private int change_game_participation_index;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getChange_game_participation_created_at() {
                return change_game_participation_created_at;
            }

            public void setChange_game_participation_created_at(String change_game_participation_created_at) {
                this.change_game_participation_created_at = change_game_participation_created_at;
            }

            public int getChange_game_participation_index() {
                return change_game_participation_index;
            }

            public void setChange_game_participation_index(int change_game_participation_index) {
                this.change_game_participation_index = change_game_participation_index;
            }
        }

        public static class MillBean {
            /**
             * id : b6a39b54e0bc799b0617b1f3d258e8c2
             * hashrate : 10
             * mining_cycle : 360
             * production_value_fil_money : 10.0000
             * computer_position : 中国福州
             */

            private String id;
            private int hashrate;
            private int mining_cycle;
            private String production_value_fil_money;
            private String computer_position;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getHashrate() {
                return hashrate;
            }

            public void setHashrate(int hashrate) {
                this.hashrate = hashrate;
            }

            public int getMining_cycle() {
                return mining_cycle;
            }

            public void setMining_cycle(int mining_cycle) {
                this.mining_cycle = mining_cycle;
            }

            public String getProduction_value_fil_money() {
                return production_value_fil_money;
            }

            public void setProduction_value_fil_money(String production_value_fil_money) {
                this.production_value_fil_money = production_value_fil_money;
            }

            public String getComputer_position() {
                return computer_position;
            }

            public void setComputer_position(String computer_position) {
                this.computer_position = computer_position;
            }
        }

        public static class ChangeGameParticipationListBean {

            /**
             * change_game_id : c6765f9c57d39780a62cd8e556d7e5a2
             * member_id : bb4e48d2c569beae0f9068f77b0a261d
             * member_nickname : FIL_mnbj
             * member_head : /head/233.png
             * money : 500.0000
             * index : 9
             * created_at : 2020-12-25 21:21:31
             */

            private String change_game_id;
            private String member_id;
            private String member_nickname;
            private String member_head;
            private String money;
            private int index;
            private String created_at;

            public String getChange_game_id() {
                return change_game_id;
            }

            public void setChange_game_id(String change_game_id) {
                this.change_game_id = change_game_id;
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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }
    }
}
