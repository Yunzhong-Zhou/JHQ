package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/12/23.
 */
public class JoinDetailModel implements Serializable {
    /**
     * change_game : {"id":"ef347f2c756ea6bd86d697106fa93f3a","mill_id":"","period":"202011010130","win_num":"","win_at":null,"status":2,"last_participation_at":null,"status_title":"待开奖","mill":null,"change_game_participation_list":[]}
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
         * win_at : null
         * status : 2
         * last_participation_at : null
         * status_title : 待开奖
         * mill : null
         * change_game_participation_list : []
         */

        private String id;
        private String mill_id;
        private String period;
        private String win_num;
        private Object win_at;
        private int status;
        private Object last_participation_at;
        private String status_title;
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

        public Object getWin_at() {
            return win_at;
        }

        public void setWin_at(Object win_at) {
            this.win_at = win_at;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getLast_participation_at() {
            return last_participation_at;
        }

        public void setLast_participation_at(Object last_participation_at) {
            this.last_participation_at = last_participation_at;
        }

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
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
}
