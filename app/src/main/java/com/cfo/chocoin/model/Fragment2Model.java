package com.cfo.chocoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/4.
 */
public class Fragment2Model implements Serializable {
    /**
     * block_award_usable_money : 0.00
     * block_money : 1100.00
     * block_active_money : 0.00
     * block_award_money : 0.00
     * wait_block_award_money : 0
     * my_block_list : [{"money":"1000.00","has_money":"0.00","status":1,"created_at":"2019-06-05 11:24:10","status_title":"待激活","show_created_at":"2019-06-05"},{"money":"100.00","has_money":"100.00","status":2,"created_at":"2019-06-04 17:58:55","status_title":"已激活","show_created_at":"2019-06-04"}]
     * newest_block_all : [{"member_head":"/head/95.png","member_nickname":"阿斯顿马丁","money":"1000.00","created_at":"2019-06-05 11:24:10"},{"member_head":"/head/166.png","member_nickname":"测试","money":9030,"created_at":"2019-06-05"},{"member_head":"/head/195.png","member_nickname":"敏哥","money":17580,"created_at":"2019-06-05"},{"member_head":"/head/28.png","member_nickname":"妖精","money":12860,"created_at":"2019-06-05"},{"member_head":"/head/31.png","member_nickname":"小红帽","money":9490,"created_at":"2019-06-05"},{"member_head":"/head/95.png","member_nickname":"阿斯顿马丁","money":"100.00","created_at":"2019-06-04 17:58:55"}]
     */

    private String block_wait_active_money;
    private String block_award_usable_money;
    private String block_money;
    private String block_active_money;
    private String block_award_money;
    private String wait_block_award_money;
    private List<MyBlockListBean> my_block_list;
    private List<NewestBlockAllBean> newest_block_all;
    private String block_explain;

    public String getBlock_wait_active_money() {
        return block_wait_active_money;
    }

    public void setBlock_wait_active_money(String block_wait_active_money) {
        this.block_wait_active_money = block_wait_active_money;
    }

    public String getBlock_explain() {
        return block_explain;
    }

    public void setBlock_explain(String block_explain) {
        this.block_explain = block_explain;
    }

    public String getBlock_award_usable_money() {
        return block_award_usable_money;
    }

    public void setBlock_award_usable_money(String block_award_usable_money) {
        this.block_award_usable_money = block_award_usable_money;
    }

    public String getBlock_money() {
        return block_money;
    }

    public void setBlock_money(String block_money) {
        this.block_money = block_money;
    }

    public String getBlock_active_money() {
        return block_active_money;
    }

    public void setBlock_active_money(String block_active_money) {
        this.block_active_money = block_active_money;
    }

    public String getBlock_award_money() {
        return block_award_money;
    }

    public void setBlock_award_money(String block_award_money) {
        this.block_award_money = block_award_money;
    }

    public String getWait_block_award_money() {
        return wait_block_award_money;
    }

    public void setWait_block_award_money(String wait_block_award_money) {
        this.wait_block_award_money = wait_block_award_money;
    }

    public List<MyBlockListBean> getMy_block_list() {
        return my_block_list;
    }

    public void setMy_block_list(List<MyBlockListBean> my_block_list) {
        this.my_block_list = my_block_list;
    }

    public List<NewestBlockAllBean> getNewest_block_all() {
        return newest_block_all;
    }

    public void setNewest_block_all(List<NewestBlockAllBean> newest_block_all) {
        this.newest_block_all = newest_block_all;
    }

    public static class MyBlockListBean {
        /**
         * money : 1000.00
         * has_money : 0.00
         * status : 1
         * created_at : 2019-06-05 11:24:10
         * status_title : 待激活
         * show_created_at : 2019-06-05
         */

        private String money;
        private String has_money;
        private int status;
        private String created_at;
        private String status_title;
        private String show_created_at;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getHas_money() {
            return has_money;
        }

        public void setHas_money(String has_money) {
            this.has_money = has_money;
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

        public String getShow_created_at() {
            return show_created_at;
        }

        public void setShow_created_at(String show_created_at) {
            this.show_created_at = show_created_at;
        }
    }

    public static class NewestBlockAllBean {
        /**
         * member_head : /head/95.png
         * member_nickname : 阿斯顿马丁
         * money : 1000.00
         * created_at : 2019-06-05 11:24:10
         */

        private String member_head;
        private String member_nickname;
        private String money;
        private String created_at;

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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
