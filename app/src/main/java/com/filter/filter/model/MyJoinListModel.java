package com.filter.filter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/5/26.
 */
public class MyJoinListModel implements Serializable {
    private List<ChangeGameParticipationListBean> change_game_participation_list;

    public List<ChangeGameParticipationListBean> getChange_game_participation_list() {
        return change_game_participation_list;
    }

    public void setChange_game_participation_list(List<ChangeGameParticipationListBean> change_game_participation_list) {
        this.change_game_participation_list = change_game_participation_list;
    }

    public static class ChangeGameParticipationListBean {
        /**
         * id : 04b65aaeb65558c80d9b8c2a39602715
         * change_game_id : 0663ddb5288851c41d51f037e822671e
         * member_id : 53022abff878a1f6ec05e5492ede2925
         * created_at : 2020-12-31 01:14:43
         * change_game : {"id":"0663ddb5288851c41d51f037e822671e","mill_id":"3742c2c206dc49c843f0e7cedee477f4","period":"","win_member_id":"","win_num":"","win_at":"","last_participation_at":"","status":1,"created_at":"2020-12-31 01:08:03","count_down":0,"status_title":"进行中","win_member":{"id":"bb4e48d2c569beae0f9068f77b0a261d","head":"/head/233.png","nickname":"FIL_mnbj"},"mill":{"id":"3742c2c206dc49c843f0e7cedee477f4","hashrate":"","computer_position":"福建·中国移动","production_value_fil_money":"0.0000","mining_cycle":""}}
         */

        private String id;
        private String change_game_id;
        private String member_id;
        private String created_at;
        private ChangeGameBean change_game;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public ChangeGameBean getChange_game() {
            return change_game;
        }

        public void setChange_game(ChangeGameBean change_game) {
            this.change_game = change_game;
        }

        public static class ChangeGameBean {
            /**
             * id : 0663ddb5288851c41d51f037e822671e
             * mill_id : 3742c2c206dc49c843f0e7cedee477f4
             * period :
             * win_member_id :
             * win_num :
             * win_at :
             * last_participation_at :
             * status : 1
             * created_at : 2020-12-31 01:08:03
             * count_down : 0
             * status_title : 进行中
             * win_member : {"id":"bb4e48d2c569beae0f9068f77b0a261d","head":"/head/233.png","nickname":"FIL_mnbj"}
             * mill : {"id":"3742c2c206dc49c843f0e7cedee477f4","hashrate":"","computer_position":"福建·中国移动","production_value_fil_money":"0.0000","mining_cycle":""}
             */

            private String id;
            private String mill_id;
            private String period;
            private String win_member_id;
            private String win_num;
            private String win_at;
            private String last_participation_at;
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
                 * id : 3742c2c206dc49c843f0e7cedee477f4
                 * hashrate :
                 * computer_position : 福建·中国移动
                 * production_value_fil_money : 0.0000
                 * mining_cycle :
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

}
