package com.filter.filter.model;

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
         * id : d4929ae2d24ea744750649b3ea8014df
         * mill_id : 3505db76debb8ef52d5267b8ae87acef
         * period : 202012261129
         * win_member_id : bb4e48d2c569beae0f9068f77b0a261d
         * win_num : 24.06670000
         * win_at : 2020-12-26 11:29:00
         * status : 3
         * created_at : 2020-12-25 21:21:31
         * count_down : 0
         * status_title : 已处理
         * win_member : {"id":"bb4e48d2c569beae0f9068f77b0a261d","head":"/head/233.png","nickname":"FIL_mnbj"}
         * mill : {"id":"3505db76debb8ef52d5267b8ae87acef","hashrate":192,"computer_position":"福州 - 中国移动","production_value_fil_money":"100.0000","mining_cycle":540}
         */

        private String id;
        private String mill_id;
        private String period;
        private String win_member_id;
        private String win_num;
        private String win_at;
        private int status;
        private String created_at;
        private int count_down;
        private String status_title;
        private WinMemberBean win_member;
        private MillBean mill;

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

        public String getWin_member_id() {
            return win_member_id;
        }

        public void setWin_member_id(String win_member_id) {
            this.win_member_id = win_member_id;
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

        public int getCount_down() {
            return count_down;
        }

        public void setCount_down(int count_down) {
            this.count_down = count_down;
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
             * id : bb4e48d2c569beae0f9068f77b0a261d
             * head : /head/233.png
             * nickname : FIL_mnbj
             */

            private String id;
            private String head;
            private String nickname;

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
        }

        public static class MillBean {
            /**
             * id : 3505db76debb8ef52d5267b8ae87acef
             * hashrate : 192
             * computer_position : 福州 - 中国移动
             * production_value_fil_money : 100.0000
             * mining_cycle : 540
             */

            private String id;
            private String hashrate;
            private String computer_position;
            private String production_value_fil_money;
            private String mining_cycle;

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

            public String getComputer_position() {
                return computer_position;
            }

            public void setComputer_position(String computer_position) {
                this.computer_position = computer_position;
            }

            public String getProduction_value_fil_money() {
                return production_value_fil_money;
            }

            public void setProduction_value_fil_money(String production_value_fil_money) {
                this.production_value_fil_money = production_value_fil_money;
            }

            public String getMining_cycle() {
                return mining_cycle;
            }

            public void setMining_cycle(String mining_cycle) {
                this.mining_cycle = mining_cycle;
            }
        }
    }
}
