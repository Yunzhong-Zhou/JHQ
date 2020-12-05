package com.fone.fone.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/7/6.
 */
public class Fragment1Model implements Serializable {
    /**
     * usable_money : 0
     * service_charge : 10
     * change_game : {"id":"433cb2995ceb069b748182c2e9a9c5ca","period":"202007052225","1_amount_money":"","2_amount_money":"","init_at":"22:55","win_at":"23:25","status":1,"created_at":"2020-07-05 22:25:06"}
     * my_current_change_game_participation_list : []
     * game_time : 1800
     * count_down : -60183
     * my_all_change_game_participation_list : []
     * history_change_game_list : []
     * hk : 03830634a5895e894e05e2a73a21e944
     */

    private String usable_money;
    private String service_charge;
    private ChangeGameBean change_game;
    private Long game_time;
    private Long count_down;
    private String hk;
    private List<MyCurrentChangeGameParticipationListBean> my_current_change_game_participation_list;
    private List<MyAllChangeGameParticipationListBean> my_all_change_game_participation_list;
    private List<HistoryChangeGameListBean> history_change_game_list;


    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
    }

    public String getService_charge() {
        return service_charge;
    }

    public void setService_charge(String service_charge) {
        this.service_charge = service_charge;
    }

    public ChangeGameBean getChange_game() {
        return change_game;
    }

    public void setChange_game(ChangeGameBean change_game) {
        this.change_game = change_game;
    }

    public Long getGame_time() {
        return game_time;
    }

    public void setGame_time(Long game_time) {
        this.game_time = game_time;
    }

    public Long getCount_down() {
        return count_down;
    }

    public void setCount_down(Long count_down) {
        this.count_down = count_down;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }


    public List<MyCurrentChangeGameParticipationListBean> getMy_current_change_game_participation_list() {
        return my_current_change_game_participation_list;
    }

    public void setMy_current_change_game_participation_list(List<MyCurrentChangeGameParticipationListBean> my_current_change_game_participation_list) {
        this.my_current_change_game_participation_list = my_current_change_game_participation_list;
    }

    public List<MyAllChangeGameParticipationListBean> getMy_all_change_game_participation_list() {
        return my_all_change_game_participation_list;
    }

    public void setMy_all_change_game_participation_list(List<MyAllChangeGameParticipationListBean> my_all_change_game_participation_list) {
        this.my_all_change_game_participation_list = my_all_change_game_participation_list;
    }

    public List<HistoryChangeGameListBean> getHistory_change_game_list() {
        return history_change_game_list;
    }

    public void setHistory_change_game_list(List<HistoryChangeGameListBean> history_change_game_list) {
        this.history_change_game_list = history_change_game_list;
    }

    public static class ChangeGameBean {
        /**
         * id : 433cb2995ceb069b748182c2e9a9c5ca
         * period : 202007052225
         * 1_amount_money :
         * 2_amount_money :
         * init_at : 22:55
         * win_at : 23:25
         * status : 1
         * created_at : 2020-07-05 22:25:06
         */

        private String id;
        private String period;
        @SerializedName("1_amount_money")
        private String _$1_amount_money;
        @SerializedName("2_amount_money")
        private String _$2_amount_money;
        private String init_at;
        private String win_at;
        private int status;
        private String status_title;

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }

        private String created_at;

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

        public String get_$1_amount_money() {
            return _$1_amount_money;
        }

        public void set_$1_amount_money(String _$1_amount_money) {
            this._$1_amount_money = _$1_amount_money;
        }

        public String get_$2_amount_money() {
            return _$2_amount_money;
        }

        public void set_$2_amount_money(String _$2_amount_money) {
            this._$2_amount_money = _$2_amount_money;
        }

        public String getInit_at() {
            return init_at;
        }

        public void setInit_at(String init_at) {
            this.init_at = init_at;
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
    }

    public static class MyCurrentChangeGameParticipationListBean {
        /**
         * id : 6ea39ce71a5fe1a8186cd944825579e8
         * change_game_id : 433cb2995ceb069b748182c2e9a9c5ca
         * member_id : d128755d863e771d79874992d80655a2
         * type : 1
         * money : 108
         * service_charge_money : 12.00
         * created_at : 2020-07-06 19:32:31
         */

        private String id;
        private String change_game_id;
        private String member_id;
        private int type;
        private String money;
        private String service_charge_money;
        private String created_at;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getService_charge_money() {
            return service_charge_money;
        }

        public void setService_charge_money(String service_charge_money) {
            this.service_charge_money = service_charge_money;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

    public static class MyAllChangeGameParticipationListBean {
        /**
         * id : 80bc8a9f75c5f0c95088b013825a27c3
         * change_game_id : 433cb2995ceb069b748182c2e9a9c5ca
         * member_id : d128755d863e771d79874992d80655a2
         * type : 2
         * money : 36
         * service_charge_money : 4.00
         * bureau_win_money : 0.00
         * created_at : 2020-07-06 20:16:11
         * change_game : {"id":"433cb2995ceb069b748182c2e9a9c5ca","period":"202007052225","1_amount_money":260196.3,"2_amount_money":80060.4,"init_num":"9218.88","init_at":"2020-07-06 20:33:00","win_num":"","win_at":"","win_rise_fall":1,"status":2}
         */

        private String id;
        private String change_game_id;
        private String member_id;
        private int type;
        private String money;
        private String service_charge_money;
        private String bureau_win_money;
        private String created_at;
        @SerializedName("change_game")
        private ChangeGameBean change_gameX;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getService_charge_money() {
            return service_charge_money;
        }

        public void setService_charge_money(String service_charge_money) {
            this.service_charge_money = service_charge_money;
        }

        public String getBureau_win_money() {
            return bureau_win_money;
        }

        public void setBureau_win_money(String bureau_win_money) {
            this.bureau_win_money = bureau_win_money;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public ChangeGameBean getChange_gameX() {
            return change_gameX;
        }

        public void setChange_gameX(ChangeGameBean change_gameX) {
            this.change_gameX = change_gameX;
        }

        public static class ChangeGameBean {
            /**
             * id : 433cb2995ceb069b748182c2e9a9c5ca
             * period : 202007052225
             * 1_amount_money : 260196.3
             * 2_amount_money : 80060.4
             * init_num : 9218.88
             * init_at : 2020-07-06 20:33:00
             * win_num :
             * win_at :
             * win_rise_fall : 1
             * status : 2
             */

            private String id;
            private String period;
            @SerializedName("1_amount_money")
            private String _$1_amount_money;
            @SerializedName("2_amount_money")
            private String _$2_amount_money;
            private String init_num;
            private String init_at;
            private String win_num;
            private String win_at;
            private String win_rise_fall;
            private int status;
            private String status_title;

            public String getStatus_title() {
                return status_title;
            }

            public void setStatus_title(String status_title) {
                this.status_title = status_title;
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

            public String get_$1_amount_money() {
                return _$1_amount_money;
            }

            public void set_$1_amount_money(String _$1_amount_money) {
                this._$1_amount_money = _$1_amount_money;
            }

            public String get_$2_amount_money() {
                return _$2_amount_money;
            }

            public void set_$2_amount_money(String _$2_amount_money) {
                this._$2_amount_money = _$2_amount_money;
            }

            public String getInit_num() {
                return init_num;
            }

            public void setInit_num(String init_num) {
                this.init_num = init_num;
            }

            public String getInit_at() {
                return init_at;
            }

            public void setInit_at(String init_at) {
                this.init_at = init_at;
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

            public String getWin_rise_fall() {
                return win_rise_fall;
            }

            public void setWin_rise_fall(String win_rise_fall) {
                this.win_rise_fall = win_rise_fall;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }

    public static class HistoryChangeGameListBean {
        /**
         * id : 433cb2995ceb069b748182c2e9a9c5ca
         * period : 202007052225
         * 1_amount_money : 289107
         * 2_amount_money : 88956
         * init_num : 9218.88
         * init_at : 2020-07-06 20:33:00
         * win_num :
         * win_at :
         * win_rise_fall : 1
         * status : 2
         */

        private String id;
        private String period;
        @SerializedName("1_amount_money")
        private String _$1_amount_money;
        @SerializedName("2_amount_money")
        private String _$2_amount_money;
        private String init_num;
        private String init_at;
        private String win_num;
        private String win_at;
        private String win_rise_fall;
        private int status;
        private String status_title;

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
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

        public String get_$1_amount_money() {
            return _$1_amount_money;
        }

        public void set_$1_amount_money(String _$1_amount_money) {
            this._$1_amount_money = _$1_amount_money;
        }

        public String get_$2_amount_money() {
            return _$2_amount_money;
        }

        public void set_$2_amount_money(String _$2_amount_money) {
            this._$2_amount_money = _$2_amount_money;
        }

        public String getInit_num() {
            return init_num;
        }

        public void setInit_num(String init_num) {
            this.init_num = init_num;
        }

        public String getInit_at() {
            return init_at;
        }

        public void setInit_at(String init_at) {
            this.init_at = init_at;
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

        public String getWin_rise_fall() {
            return win_rise_fall;
        }

        public void setWin_rise_fall(String win_rise_fall) {
            this.win_rise_fall = win_rise_fall;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
