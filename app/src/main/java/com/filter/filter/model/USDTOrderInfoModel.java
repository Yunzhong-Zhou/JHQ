package com.filter.filter.model;

/**
 * Created by Mr.Z on 2020/11/17.
 */
public class USDTOrderInfoModel {
    /**
     * count_down : 1785
     * usdt_deal : {"id":"937b701135a0aac74a0403d235c340db","cny_money":"6.70","usdt_cny_price":"6.70","usdt_money":"1.0000","sn":"UM1610852868505551","usdt_buy_member_id":"e6275924f107bc01d7e45e1dc1f238b4","usdt_buy_member_head":"/head/775.png","usdt_buy_member_mobile":"10345678903","usdt_buy_member_nickname":"FIL_hyfj","usdt_sell_member_id":"6826555a5f6d636fa1d1405befa7db37","usdt_sell_member_head":"/head/84.png","usdt_sell_member_mobile":"10345678901","usdt_sell_member_nickname":"FIL_ixor","usdt_sell_member_bank_card_proceeds_name":"","usdt_sell_member_bank_card_account":"","usdt_sell_member_bank_title":"","usdt_sell_member_bank_address":"","status":1,"has_pay_at":"","created_at":"2021-01-17 11:07:48","status_title":"待付款","member_type":"buy","opposite_member_nickname":"FIL_ixor","opposite_member_mobile":"10345678901"}
     */

    private int count_down;
    private UsdtDealBean usdt_deal;

    public int getCount_down() {
        return count_down;
    }

    public void setCount_down(int count_down) {
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
         * id : 937b701135a0aac74a0403d235c340db
         * cny_money : 6.70
         * usdt_cny_price : 6.70
         * usdt_money : 1.0000
         * sn : UM1610852868505551
         * usdt_buy_member_id : e6275924f107bc01d7e45e1dc1f238b4
         * usdt_buy_member_head : /head/775.png
         * usdt_buy_member_mobile : 10345678903
         * usdt_buy_member_nickname : FIL_hyfj
         * usdt_sell_member_id : 6826555a5f6d636fa1d1405befa7db37
         * usdt_sell_member_head : /head/84.png
         * usdt_sell_member_mobile : 10345678901
         * usdt_sell_member_nickname : FIL_ixor
         * usdt_sell_member_bank_card_proceeds_name :
         * usdt_sell_member_bank_card_account :
         * usdt_sell_member_bank_title :
         * usdt_sell_member_bank_address :
         * status : 1
         * has_pay_at :
         * created_at : 2021-01-17 11:07:48
         * status_title : 待付款
         * member_type : buy
         * opposite_member_nickname : FIL_ixor
         * opposite_member_mobile : 10345678901
         */

        private String id;
        private String cny_money;
        private String usdt_cny_price;
        private String usdt_money;
        private String sn;
        private String usdt_buy_member_id;
        private String usdt_buy_member_head;
        private String usdt_buy_member_mobile;
        private String usdt_buy_member_nickname;
        private String usdt_sell_member_id;
        private String usdt_sell_member_head;
        private String usdt_sell_member_mobile;
        private String usdt_sell_member_nickname;
        private String usdt_sell_member_bank_card_proceeds_name;
        private String usdt_sell_member_bank_card_account;
        private String usdt_sell_member_bank_title;
        private String usdt_sell_member_bank_address;
        private int status;
        private String has_pay_at;
        private String created_at;
        private String status_title;
        private String member_type;
        private String opposite_member_nickname;
        private String opposite_member_mobile;

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

        public String getUsdt_money() {
            return usdt_money;
        }

        public void setUsdt_money(String usdt_money) {
            this.usdt_money = usdt_money;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getUsdt_buy_member_id() {
            return usdt_buy_member_id;
        }

        public void setUsdt_buy_member_id(String usdt_buy_member_id) {
            this.usdt_buy_member_id = usdt_buy_member_id;
        }

        public String getUsdt_buy_member_head() {
            return usdt_buy_member_head;
        }

        public void setUsdt_buy_member_head(String usdt_buy_member_head) {
            this.usdt_buy_member_head = usdt_buy_member_head;
        }

        public String getUsdt_buy_member_mobile() {
            return usdt_buy_member_mobile;
        }

        public void setUsdt_buy_member_mobile(String usdt_buy_member_mobile) {
            this.usdt_buy_member_mobile = usdt_buy_member_mobile;
        }

        public String getUsdt_buy_member_nickname() {
            return usdt_buy_member_nickname;
        }

        public void setUsdt_buy_member_nickname(String usdt_buy_member_nickname) {
            this.usdt_buy_member_nickname = usdt_buy_member_nickname;
        }

        public String getUsdt_sell_member_id() {
            return usdt_sell_member_id;
        }

        public void setUsdt_sell_member_id(String usdt_sell_member_id) {
            this.usdt_sell_member_id = usdt_sell_member_id;
        }

        public String getUsdt_sell_member_head() {
            return usdt_sell_member_head;
        }

        public void setUsdt_sell_member_head(String usdt_sell_member_head) {
            this.usdt_sell_member_head = usdt_sell_member_head;
        }

        public String getUsdt_sell_member_mobile() {
            return usdt_sell_member_mobile;
        }

        public void setUsdt_sell_member_mobile(String usdt_sell_member_mobile) {
            this.usdt_sell_member_mobile = usdt_sell_member_mobile;
        }

        public String getUsdt_sell_member_nickname() {
            return usdt_sell_member_nickname;
        }

        public void setUsdt_sell_member_nickname(String usdt_sell_member_nickname) {
            this.usdt_sell_member_nickname = usdt_sell_member_nickname;
        }

        public String getUsdt_sell_member_bank_card_proceeds_name() {
            return usdt_sell_member_bank_card_proceeds_name;
        }

        public void setUsdt_sell_member_bank_card_proceeds_name(String usdt_sell_member_bank_card_proceeds_name) {
            this.usdt_sell_member_bank_card_proceeds_name = usdt_sell_member_bank_card_proceeds_name;
        }

        public String getUsdt_sell_member_bank_card_account() {
            return usdt_sell_member_bank_card_account;
        }

        public void setUsdt_sell_member_bank_card_account(String usdt_sell_member_bank_card_account) {
            this.usdt_sell_member_bank_card_account = usdt_sell_member_bank_card_account;
        }

        public String getUsdt_sell_member_bank_title() {
            return usdt_sell_member_bank_title;
        }

        public void setUsdt_sell_member_bank_title(String usdt_sell_member_bank_title) {
            this.usdt_sell_member_bank_title = usdt_sell_member_bank_title;
        }

        public String getUsdt_sell_member_bank_address() {
            return usdt_sell_member_bank_address;
        }

        public void setUsdt_sell_member_bank_address(String usdt_sell_member_bank_address) {
            this.usdt_sell_member_bank_address = usdt_sell_member_bank_address;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getHas_pay_at() {
            return has_pay_at;
        }

        public void setHas_pay_at(String has_pay_at) {
            this.has_pay_at = has_pay_at;
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

        public String getMember_type() {
            return member_type;
        }

        public void setMember_type(String member_type) {
            this.member_type = member_type;
        }

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
    }
}
