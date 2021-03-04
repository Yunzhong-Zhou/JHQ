package com.filter.filter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2018/2/10.
 */

public class BankCardSettingModel implements Serializable {
    /**
     * member : {"mobile_state_code":"86","mobile":"18306043086","bank_card_account":"","bank_card_proceeds_name":"","bank_title":"","bank_address":"","trade_password":""}
     * bank_list : [{"title":"工商银行"},{"title":"农业银行"},{"title":"建设银行"},{"title":"中国银行"},{"title":"招商银行"},{"title":"交通银行"},{"title":"兴业银行"},{"title":"民生银行"},{"title":"光大银行"},{"title":"平安银行"},{"title":"中信银行"},{"title":"广发银行"},{"title":"上海浦东发展银行"},{"title":"中国邮政"}]
     */

    private MemberBean member;
    private List<BankListBean> bank_list;

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public List<BankListBean> getBank_list() {
        return bank_list;
    }

    public void setBank_list(List<BankListBean> bank_list) {
        this.bank_list = bank_list;
    }

    public static class MemberBean {
        /**
         * mobile_state_code : 86
         * mobile : 18306043086
         * bank_card_account :
         * bank_card_proceeds_name :
         * bank_title :
         * bank_address :
         * trade_password :
         */

        private String mobile_state_code;
        private String mobile;
        private String bank_card_account;
        private String bank_card_proceeds_name;
        private String bank_title;
        private String bank_address;
        private String trade_password;

        public String getMobile_state_code() {
            return mobile_state_code;
        }

        public void setMobile_state_code(String mobile_state_code) {
            this.mobile_state_code = mobile_state_code;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getBank_card_account() {
            return bank_card_account;
        }

        public void setBank_card_account(String bank_card_account) {
            this.bank_card_account = bank_card_account;
        }

        public String getBank_card_proceeds_name() {
            return bank_card_proceeds_name;
        }

        public void setBank_card_proceeds_name(String bank_card_proceeds_name) {
            this.bank_card_proceeds_name = bank_card_proceeds_name;
        }

        public String getBank_title() {
            return bank_title;
        }

        public void setBank_title(String bank_title) {
            this.bank_title = bank_title;
        }

        public String getBank_address() {
            return bank_address;
        }

        public void setBank_address(String bank_address) {
            this.bank_address = bank_address;
        }

        public String getTrade_password() {
            return trade_password;
        }

        public void setTrade_password(String trade_password) {
            this.trade_password = trade_password;
        }
    }

    public static class BankListBean {
        /**
         * title : 工商银行
         */

        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
