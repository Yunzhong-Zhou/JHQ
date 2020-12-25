package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/5/26.
 */
public class JoinListModel implements Serializable {
    private List<ChangeGameListBean> change_game_list;

    public List<ChangeGameListBean> getChange_game_list() {
        return change_game_list;
    }

    public void setChange_game_list(List<ChangeGameListBean> change_game_list) {
        this.change_game_list = change_game_list;
    }

    public static class ChangeGameListBean {
        /**
         * id : ef347f2c756ea6bd86d697106fa93f3a
         * period : 202011010130
         * win_member_id :
         * last_participation_at : null
         * status : 2
         * created_at : 2020-11-01 01:30:15
         * status_title : 待开奖
         * win_member : {"head":"/head/606.png","nickname":"FIL_pour广告费"}
         * mill : {"id":"b6a39b54e0bc799b0617b1f3d258e8c2","hashrate":10,"mining_cycle":360,"production_value_fil_money":"10.0000","computer_position":"中国福州"}
         */

        private String id;
        private String period;
        private String win_member_id;
        private String last_participation_at;
        private int status;
        private String created_at;
        private String status_title;
        private WinMemberBean win_member;
        private MillBean mill;

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

        public String getWin_member_id() {
            return win_member_id;
        }

        public void setWin_member_id(String win_member_id) {
            this.win_member_id = win_member_id;
        }

        public String getLast_participation_at() {
            return last_participation_at;
        }

        public void setLast_participation_at(String last_participation_at) {
            this.last_participation_at = last_participation_at;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
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

        public static class WinMemberBean {
            /**
             * head : /head/606.png
             * nickname : FIL_pour广告费
             */

            private String head;
            private String nickname;

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
            private String hashrate;
            private String mining_cycle;
            private String production_value_fil_money;
            private String computer_position;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getHashrate() {
                return hashrate;
            }

            public void setHashrate(String hashrate) {
                this.hashrate = hashrate;
            }

            public String getMining_cycle() {
                return mining_cycle;
            }

            public void setMining_cycle(String mining_cycle) {
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
    }
}
