package com.ofc.ofc.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/7/7.
 */
public class Fragment1DetailModel implements Serializable {
    /**
     * change_game : {"id":"830ec174c9c00d075d642851cc96f826","period":"202007062033","1_amount_money":0,"2_amount_money":0,"init_num":"9258.53","init_at":"08:30","win_num":"","win_at":"09:00","win_rise_fall":1,"status":2,"rise_ratio":"54.55","fall_ratio":"45.45"}
     * my_change_game_participation_list : [{"id":"ecd0364b7fd7ee035c9a8349d724cf8d","change_game_id":"830ec174c9c00d075d642851cc96f826","member_id":"d128755d863e771d79874992d80655a2","type":2,"money":45,"service_charge_money":"5.00","bureau_win_money":"0.00","created_at":"2020-07-06 20:44:11"},{"id":"14f3cab84727ba4e6f8b8eca94fd8bfa","change_game_id":"830ec174c9c00d075d642851cc96f826","member_id":"d128755d863e771d79874992d80655a2","type":1,"money":54,"service_charge_money":"6.00","bureau_win_money":"0.00","created_at":"2020-07-06 20:43:59"}]
     */

    private ChangeGameBean change_game;
    private List<MyChangeGameParticipationListBean> my_change_game_participation_list;
    private List<HistoryChangeGameListBean> history_change_game_list;

    public ChangeGameBean getChange_game() {
        return change_game;
    }

    public void setChange_game(ChangeGameBean change_game) {
        this.change_game = change_game;
    }

    public List<MyChangeGameParticipationListBean> getMy_change_game_participation_list() {
        return my_change_game_participation_list;
    }

    public void setMy_change_game_participation_list(List<MyChangeGameParticipationListBean> my_change_game_participation_list) {
        this.my_change_game_participation_list = my_change_game_participation_list;
    }

    public List<HistoryChangeGameListBean> getHistory_change_game_list() {
        return history_change_game_list;
    }

    public void setHistory_change_game_list(List<HistoryChangeGameListBean> history_change_game_list) {
        this.history_change_game_list = history_change_game_list;
    }


    public static class ChangeGameBean {
        /**
         * id : 830ec174c9c00d075d642851cc96f826
         * period : 202007062033
         * 1_amount_money : 0
         * 2_amount_money : 0
         * init_num : 9258.53
         * init_at : 08:30
         * win_num :
         * win_at : 09:00
         * win_rise_fall : 1
         * status : 2
         * rise_ratio : 54.55
         * fall_ratio : 45.45
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
        private int win_rise_fall;
        private int status;
        private String rise_ratio;
        private String fall_ratio;

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

        public int getWin_rise_fall() {
            return win_rise_fall;
        }

        public void setWin_rise_fall(int win_rise_fall) {
            this.win_rise_fall = win_rise_fall;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRise_ratio() {
            return rise_ratio;
        }

        public void setRise_ratio(String rise_ratio) {
            this.rise_ratio = rise_ratio;
        }

        public String getFall_ratio() {
            return fall_ratio;
        }

        public void setFall_ratio(String fall_ratio) {
            this.fall_ratio = fall_ratio;
        }
    }

    public static class MyChangeGameParticipationListBean {
        /**
         * id : ecd0364b7fd7ee035c9a8349d724cf8d
         * change_game_id : 830ec174c9c00d075d642851cc96f826
         * member_id : d128755d863e771d79874992d80655a2
         * type : 2
         * money : 45
         * service_charge_money : 5.00
         * bureau_win_money : 0.00
         * created_at : 2020-07-06 20:44:11
         */

        private String id;
        private String change_game_id;
        private String member_id;
        private int type;
        private int money;
        private String service_charge_money;
        private String bureau_win_money;
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

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
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
    }

    public static class HistoryChangeGameListBean {
        /**
         * id : debb7e1d777974c23bd901e0428e1c51
         * period : 202007071133
         * 1_amount_money : 0
         * 2_amount_money : 0
         * init_num : 9284.48
         * init_at : 12:03
         * win_num : 9303.32
         * win_at : 12:33
         * win_rise_fall : 1
         * status : 3
         * created_at : 2020-07-07 11:33:20
         */

        private String id;
        private String period;
        @SerializedName("1_amount_money")
        private int _$1_amount_money;
        @SerializedName("2_amount_money")
        private int _$2_amount_money;
        private String init_num;
        private String init_at;
        private String win_num;
        private String win_at;
        private int win_rise_fall;
        private int status;
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

        public int get_$1_amount_money() {
            return _$1_amount_money;
        }

        public void set_$1_amount_money(int _$1_amount_money) {
            this._$1_amount_money = _$1_amount_money;
        }

        public int get_$2_amount_money() {
            return _$2_amount_money;
        }

        public void set_$2_amount_money(int _$2_amount_money) {
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

        public int getWin_rise_fall() {
            return win_rise_fall;
        }

        public void setWin_rise_fall(int win_rise_fall) {
            this.win_rise_fall = win_rise_fall;
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
}
