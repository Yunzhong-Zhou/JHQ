package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/7/21.
 */
public class FenHongModel implements Serializable {
    /**
     * drvt_buy_list : [{"id":"e9140f25bc374ceb4c25bdffbc352af1","member_head":"/head.png","member_nickname":"03","drvt_price":"1.5201","amount_money":"100.00","current_money":"0.00","status":1,"created_at":"2020-10-30 14:58:12","status_title":"Underway"},{"id":"1f7ede93f7bb3405e960a39c10f2e027","member_head":"/head.png","member_nickname":"03","drvt_price":"1.1000","amount_money":"100.00","current_money":"0.00","status":1,"created_at":"2020-11-01 11:18:02","status_title":"Underway"},{"id":"87b8301f37d6a5fcd08219165ca571db","member_head":"/head.png","member_nickname":"19","drvt_price":"1.0800","amount_money":"1000.00","current_money":"0.00","status":1,"created_at":"2020-10-30 14:56:08","status_title":"Underway"},{"id":"f3da4f04ff90cd55b7af89190d1711e3","member_head":"/head.png","member_nickname":"yorte","drvt_price":"1.0800","amount_money":"1000.00","current_money":"0.00","status":1,"created_at":"2020-11-01 22:13:22","status_title":"Underway"}]
     * drvt_buy_max_drvt_price : 2.0000
     * drvt_buy_amount_money : 2200.00
     * ofc_usable_money : 0.0000
     * ofc_amount_money : 0.0000
     * usdt_price : 1.00
     * ofc_price : 1.0970
     * ofc_index : 0.0060
     * toal_appreciation : 9.70
     * last_appreciation : 0.27
     * common_usable_money : 0.00
     * interest_money : 0.00
     * contract_money : 0.00
     * ofc_invest_money : 0.00
     * ofc_issue_price : 1.00
     * ofc_price_list : [{"price":"1.0970","created_at":"2020-11-04 09:56:35"},{"price":"1.0940","created_at":"2020-11-03 12:20:47"},{"price":"1.0910","created_at":"2020-11-02 00:28:57"},{"price":"1.0880","created_at":"2020-11-01 00:07:02"},{"price":"1.0850","created_at":"2020-10-31 01:08:48"},{"price":"1.0820","created_at":"2020-10-30 06:16:43"},{"price":"1.0790","created_at":"2020-10-29 01:52:05"},{"price":"1.0760","created_at":"2020-10-28 00:03:27"},{"price":"1.0730","created_at":"2020-10-27 01:05:52"},{"price":"1.0700","created_at":"2020-10-26 01:15:50"},{"price":"1.0660","created_at":"2020-10-25 00:41:26"},{"price":"1.0630","created_at":"2020-10-24 00:06:07"},{"price":"1.0590","created_at":"2020-10-23 00:46:45"},{"price":"1.0560","created_at":"2020-10-22 00:19:27"},{"price":"1.0530","created_at":"2020-10-21 00:13:04"},{"price":"1.0490","created_at":"2020-10-20 00:22:06"},{"price":"1.0460","created_at":"2020-10-19 00:18:51"},{"price":"1.0430","created_at":"2020-10-18 00:13:53"},{"price":"1.0400","created_at":"2020-10-17 00:23:41"},{"price":"1.0370","created_at":"2020-10-16 00:12:56"},{"price":"1.0330","created_at":"2020-10-15 00:13:11"},{"price":"1.0290","created_at":"2020-10-14 00:12:23"},{"price":"1.0250","created_at":"2020-10-13 00:10:22"},{"price":"1.0190","created_at":"2020-10-12 00:13:09"},{"price":"1.0150","created_at":"2020-10-11 08:22:17"},{"price":"1.0100","created_at":"2020-10-10 04:45:16"},{"price":"1.0050","created_at":"2020-10-09 09:14:11"},{"price":"1.0030","created_at":"2020-10-07 23:55:08"},{"price":"1.0020","created_at":"2020-10-07 08:41:28"},{"price":"1.0000","created_at":"2020-10-06 15:30:55"}]
     * direct_performance_drvt_buy_money : 0.00
     * team_performance_drvt_buy_money : 0
     * direct_performance_ofc_money : 0.00
     * team_performance_ofc_money : 0
     * hk : 7ac1b7a1d2b7af866ac41c2a7f185555
     */

    private String drvt_buy_max_drvt_price;
    private String drvt_buy_amount_money;
    private String ofc_usable_money;
    private String ofc_amount_money;
    private String usdt_price;
    private String ofc_price;
    private String ofc_index;
    private String toal_appreciation;
    private String last_appreciation;
    private String common_usable_money;
    private String interest_money;
    private String contract_money;
    private String ofc_invest_money;
    private String ofc_issue_price;
    private String direct_performance_drvt_buy_money;
    private String team_performance_drvt_buy_money;
    private String direct_performance_ofc_money;
    private String team_performance_ofc_money;
    private String hk;
    private List<DrvtBuyListBean> drvt_buy_list;
    private List<OfcPriceListBean> ofc_price_list;

    public String getDrvt_buy_max_drvt_price() {
        return drvt_buy_max_drvt_price;
    }

    public void setDrvt_buy_max_drvt_price(String drvt_buy_max_drvt_price) {
        this.drvt_buy_max_drvt_price = drvt_buy_max_drvt_price;
    }

    public String getDrvt_buy_amount_money() {
        return drvt_buy_amount_money;
    }

    public void setDrvt_buy_amount_money(String drvt_buy_amount_money) {
        this.drvt_buy_amount_money = drvt_buy_amount_money;
    }

    public String getOfc_usable_money() {
        return ofc_usable_money;
    }

    public void setOfc_usable_money(String ofc_usable_money) {
        this.ofc_usable_money = ofc_usable_money;
    }

    public String getOfc_amount_money() {
        return ofc_amount_money;
    }

    public void setOfc_amount_money(String ofc_amount_money) {
        this.ofc_amount_money = ofc_amount_money;
    }

    public String getUsdt_price() {
        return usdt_price;
    }

    public void setUsdt_price(String usdt_price) {
        this.usdt_price = usdt_price;
    }

    public String getOfc_price() {
        return ofc_price;
    }

    public void setOfc_price(String ofc_price) {
        this.ofc_price = ofc_price;
    }

    public String getOfc_index() {
        return ofc_index;
    }

    public void setOfc_index(String ofc_index) {
        this.ofc_index = ofc_index;
    }

    public String getToal_appreciation() {
        return toal_appreciation;
    }

    public void setToal_appreciation(String toal_appreciation) {
        this.toal_appreciation = toal_appreciation;
    }

    public String getLast_appreciation() {
        return last_appreciation;
    }

    public void setLast_appreciation(String last_appreciation) {
        this.last_appreciation = last_appreciation;
    }

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }

    public String getInterest_money() {
        return interest_money;
    }

    public void setInterest_money(String interest_money) {
        this.interest_money = interest_money;
    }

    public String getContract_money() {
        return contract_money;
    }

    public void setContract_money(String contract_money) {
        this.contract_money = contract_money;
    }

    public String getOfc_invest_money() {
        return ofc_invest_money;
    }

    public void setOfc_invest_money(String ofc_invest_money) {
        this.ofc_invest_money = ofc_invest_money;
    }

    public String getOfc_issue_price() {
        return ofc_issue_price;
    }

    public void setOfc_issue_price(String ofc_issue_price) {
        this.ofc_issue_price = ofc_issue_price;
    }

    public String getDirect_performance_drvt_buy_money() {
        return direct_performance_drvt_buy_money;
    }

    public void setDirect_performance_drvt_buy_money(String direct_performance_drvt_buy_money) {
        this.direct_performance_drvt_buy_money = direct_performance_drvt_buy_money;
    }

    public String getTeam_performance_drvt_buy_money() {
        return team_performance_drvt_buy_money;
    }

    public void setTeam_performance_drvt_buy_money(String team_performance_drvt_buy_money) {
        this.team_performance_drvt_buy_money = team_performance_drvt_buy_money;
    }

    public String getDirect_performance_ofc_money() {
        return direct_performance_ofc_money;
    }

    public void setDirect_performance_ofc_money(String direct_performance_ofc_money) {
        this.direct_performance_ofc_money = direct_performance_ofc_money;
    }

    public String getTeam_performance_ofc_money() {
        return team_performance_ofc_money;
    }

    public void setTeam_performance_ofc_money(String team_performance_ofc_money) {
        this.team_performance_ofc_money = team_performance_ofc_money;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }

    public List<DrvtBuyListBean> getDrvt_buy_list() {
        return drvt_buy_list;
    }

    public void setDrvt_buy_list(List<DrvtBuyListBean> drvt_buy_list) {
        this.drvt_buy_list = drvt_buy_list;
    }

    public List<OfcPriceListBean> getOfc_price_list() {
        return ofc_price_list;
    }

    public void setOfc_price_list(List<OfcPriceListBean> ofc_price_list) {
        this.ofc_price_list = ofc_price_list;
    }

    public static class DrvtBuyListBean {
        /**
         * id : e9140f25bc374ceb4c25bdffbc352af1
         * member_head : /head.png
         * member_nickname : 03
         * drvt_price : 1.5201
         * amount_money : 100.00
         * current_money : 0.00
         * status : 1
         * created_at : 2020-10-30 14:58:12
         * status_title : Underway
         */

        private String id;
        private String member_head;
        private String member_nickname;
        private String drvt_price;
        private String amount_money;
        private String current_money;
        private int status;
        private String created_at;
        private String status_title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getDrvt_price() {
            return drvt_price;
        }

        public void setDrvt_price(String drvt_price) {
            this.drvt_price = drvt_price;
        }

        public String getAmount_money() {
            return amount_money;
        }

        public void setAmount_money(String amount_money) {
            this.amount_money = amount_money;
        }

        public String getCurrent_money() {
            return current_money;
        }

        public void setCurrent_money(String current_money) {
            this.current_money = current_money;
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
    }

    public static class OfcPriceListBean {
        /**
         * price : 1.0970
         * created_at : 2020-11-04 09:56:35
         */

        private String price;
        private String created_at;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
