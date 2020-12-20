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
         * period : 202011010130
         * win_member_id :
         * last_participation_at : null
         * status : 2
         * created_at : 2020-11-01 01:30:15
         * status_title : 待开奖
         * win_member : null
         */

        private String period;
        private String win_member_id;
        private Object last_participation_at;
        private int status;
        private String created_at;
        private String status_title;
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

        public Object getLast_participation_at() {
            return last_participation_at;
        }

        public void setLast_participation_at(Object last_participation_at) {
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

        public Object getWin_member() {
            return win_member;
        }

        public void setWin_member(Object win_member) {
            this.win_member = win_member;
        }
    }
}
