package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/5/22.
 */
public class Fragment3Model implements Serializable {
    /**
     * change_game : {"id":"5ac8dbdf0b28b9a2ad429f16ee00b379","mill_id":"b6a39b54e0bc799b0617b1f3d258e8c2","mill":{"id":"b6a39b54e0bc799b0617b1f3d258e8c2","hashrate":10,"mining_cycle":360,"production_value_fil_money":"10.0000","computer_position":"中国福州"},"change_game_participation_list":[{"change_game_id":"5ac8dbdf0b28b9a2ad429f16ee00b379","member_head":"/head/606.png","member_nickname":"FIL_pour广告费","index":0}]}
     * change_game_list : [{"id":"ef347f2c756ea6bd86d697106fa93f3a","period":"202011010130","win_member_id":"","created_at":"2020-11-01 01:30:15","win_member":{"change_game_id":"5ac8dbdf0b28b9a2ad429f16ee00b379","member_head":"/head/606.png","member_nickname":"FIL_pour广告费"},"mill":{"change_game_id":"5ac8dbdf0b28b9a2ad429f16ee00b379","member_head":"/head/606.png","member_nickname":"FIL_pour广告费"}},{"id":"f58500ec46ca660b10b5303db6c05e16","period":"202010010130","win_member_id":"","created_at":"2020-10-01 01:30:13","win_member":null,"mill":null},{"id":"00a18cad3042fba8e5cfe780f0760998","period":"202009010130","win_member_id":"","created_at":"2020-09-01 01:30:12","win_member":null,"mill":null},{"id":"337639d9e20ec130b5ba82f02b19dd61","period":"202008010130","win_member_id":"","created_at":"2020-08-01 01:30:12","win_member":null,"mill":null}]
     * member_list : [{"head":"/upload/head/2020-12-23/726c0afeda7a584939b9ce953dab1c55.jpg","nickname":"FIL_pour广告费","change_game_win_time":0,"hashrate":36},{"head":"/head/434.png","nickname":"hsm","change_game_win_time":0,"hashrate":21},{"head":"/upload/head/2020-12-22/bf567d6279a6acd3f0ed7827a9abc6a9.png","nickname":"zhumimi","change_game_win_time":0,"hashrate":14},{"head":"/head/336.png","nickname":"FIL_zxms","change_game_win_time":0,"hashrate":0},{"head":"/head/260.png","nickname":"FIL_gcln","change_game_win_time":0,"hashrate":0},{"head":"/head/455.png","nickname":"FIL_phqy","change_game_win_time":0,"hashrate":0},{"head":"/head/396.png","nickname":"FIL_wjpn","change_game_win_time":0,"hashrate":0},{"head":"/head/653.png","nickname":"FIL_ypel","change_game_win_time":0,"hashrate":0},{"head":"/head/645.png","nickname":"FIL_adwy","change_game_win_time":0,"hashrate":0},{"head":"/head/469.png","nickname":"FIL_nvsw","change_game_win_time":0,"hashrate":0}]
     * win_prompt : {"period":"","win_member_id":"","hashrate":0}
     * hk : 39f9a670d5f637b90c9fa1b09513063d
     */

    private int is_change_game_participation;

    public int getIs_change_game_participation() {
        return is_change_game_participation;
    }

    public void setIs_change_game_participation(int is_change_game_participation) {
        this.is_change_game_participation = is_change_game_participation;
    }

    private ChangeGameBean change_game;
    private WinPromptBean win_prompt;
    private String hk;
    private String url;
    private List<ChangeGameListBean> change_game_list;
    private List<MemberListBean> member_list;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
         * mill_id : b6a39b54e0bc799b0617b1f3d258e8c2
         * mill : {"id":"b6a39b54e0bc799b0617b1f3d258e8c2","hashrate":10,"mining_cycle":360,"production_value_fil_money":"10.0000","computer_position":"中国福州"}
         * change_game_participation_list : [{"change_game_id":"5ac8dbdf0b28b9a2ad429f16ee00b379","member_head":"/head/606.png","member_nickname":"FIL_pour广告费","index":0}]
         */

        private String id;
        private String mill_id;
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

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

    public static class MemberListBean {
        /**
         * head : /upload/head/2020-12-23/726c0afeda7a584939b9ce953dab1c55.jpg
         * nickname : FIL_pour广告费
         * change_game_win_time : 0
         * hashrate : 36
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
