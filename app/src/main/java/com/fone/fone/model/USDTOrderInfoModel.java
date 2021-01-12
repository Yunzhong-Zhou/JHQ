package com.fone.fone.model;

/**
 * Created by Mr.Z on 2020/11/17.
 */
public class USDTOrderInfoModel {

    /**
     * count_down : -66631
     * usdt_deal : {"id":"7aab08d63e1422df2fc796d674ad2f0a","cny_money":"0.00","usdt_cny_price":"7.0000","money":"100.00","sn":"UD1605513676561025","sell_member_head":"/head.png","sell_member_mobile":"18306043086","sell_member_bank_card_proceeds_name":"阿斯顿","sell_member_bank_card_account":"4514844854840855","sell_member_bank_title":"工商银行","sell_member_bank_address":"","status":2,"matching_at":"2020-11-16 19:02:52","has_pay_at":"","status_title":"待付款"}
     */

    private long count_down;
    private UsdtDealBean usdt_deal;

    public long getCount_down() {
        return count_down;
    }

    public void setCount_down(long count_down) {
        this.count_down = count_down;
    }

    public UsdtDealBean getUsdt_deal() {
        return usdt_deal;
    }

    public void setUsdt_deal(UsdtDealBean usdt_deal) {
        this.usdt_deal = usdt_deal;
    }

    public static class UsdtDealBean {
        /**
         * id : 7aab08d63e1422df2fc796d674ad2f0a
         * cny_money : 0.00
         * usdt_cny_price : 7.0000
         * money : 100.00
         * sn : UD1605513676561025
         * sell_member_head : /head.png
         * sell_member_mobile : 18306043086
         * sell_member_bank_card_proceeds_name : 阿斯顿
         * sell_member_bank_card_account : 4514844854840855
         * sell_member_bank_title : 工商银行
         * sell_member_bank_address :
         * status : 2
         * matching_at : 2020-11-16 19:02:52
         * has_pay_at :
         * status_title : 待付款
         */

        private String id;
        private String cny_money;
        private String usdt_cny_price;
        private String money;
        private String sn;
        private String sell_member_head;
        private String sell_member_mobile;
        private String sell_member_bank_card_proceeds_name;
        private String sell_member_bank_card_account;
        private String sell_member_bank_title;
        private String sell_member_bank_address;
        private int status;
        private String matching_at;
        private String has_pay_at;
        private String status_title;

        private String member_type;
        private String sell_member_nickname;

        private String opposite_member_nickname;
        private String opposite_member_mobile;

        public String getOpposite_member_nickname() {
            return opposite_member_nickname;
        }

        public void setOpposite_member_nickname(String opposite_member_nickname) {
            this.opposite_member_nickname = opposite_member_nickname;
        }

        public String getOpposite_member_mobile() {
            return opposite_member_mobile;
        }

        public void setOpposite_member_mobile(String opposite_member_mobile) {
            this.opposite_member_mobile = opposite_member_mobile;
        }

        public String getSell_member_nickname() {
            return sell_member_nickname;
        }

        public void setSell_member_nickname(String sell_member_nickname) {
            this.sell_member_nickname = sell_member_nickname;
        }

        public String getMember_type() {
            return member_type;
        }

        public void setMember_type(String member_type) {
            this.member_type = member_type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCny_money() {
            return cny_money;
        }

        public void setCny_money(String cny_money) {
            this.cny_money = cny_money;
        }

        public String getUsdt_cny_price() {
            return usdt_cny_price;
        }

        public void setUsdt_cny_price(String usdt_cny_price) {
            this.usdt_cny_price = usdt_cny_price;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getSell_member_head() {
            return sell_member_head;
        }

        public void setSell_member_head(String sell_member_head) {
            this.sell_member_head = sell_member_head;
        }

        public String getSell_member_mobile() {
            return sell_member_mobile;
        }

        public void setSell_member_mobile(String sell_member_mobile) {
            this.sell_member_mobile = sell_member_mobile;
        }

        public String getSell_member_bank_card_proceeds_name() {
            return sell_member_bank_card_proceeds_name;
        }

        public void setSell_member_bank_card_proceeds_name(String sell_member_bank_card_proceeds_name) {
            this.sell_member_bank_card_proceeds_name = sell_member_bank_card_proceeds_name;
        }

        public String getSell_member_bank_card_account() {
            return sell_member_bank_card_account;
        }

        public void setSell_member_bank_card_account(String sell_member_bank_card_account) {
            this.sell_member_bank_card_account = sell_member_bank_card_account;
        }

        public String getSell_member_bank_title() {
            return sell_member_bank_title;
        }

        public void setSell_member_bank_title(String sell_member_bank_title) {
            this.sell_member_bank_title = sell_member_bank_title;
        }

        public String getSell_member_bank_address() {
            return sell_member_bank_address;
        }

        public void setSell_member_bank_address(String sell_member_bank_address) {
            this.sell_member_bank_address = sell_member_bank_address;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMatching_at() {
            return matching_at;
        }

        public void setMatching_at(String matching_at) {
            this.matching_at = matching_at;
        }

        public String getHas_pay_at() {
            return has_pay_at;
        }

        public void setHas_pay_at(String has_pay_at) {
            this.has_pay_at = has_pay_at;
        }

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }
    }
}
