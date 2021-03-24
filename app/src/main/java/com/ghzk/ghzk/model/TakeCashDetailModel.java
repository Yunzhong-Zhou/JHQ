package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/2.
 */
public class TakeCashDetailModel implements Serializable {
    /**
     * id : c6b892875c10fe3c322a7824
     * sn : WI1614312676525597
     * member_bank_card_account : 55545448885558
     * member_bank_card_proceeds_name : 阿斯顿马丁
     * member_bank_title : 招商银行
     * member_bank_address :
     * input_money : 100
     * money : 97
     * service_charge_money : 3
     * status : 1
     * status_rejected_cause :
     * verify_at : null
     * created_at : 2021-02-26 12:11:16
     * member_bank_icon :
     * status_title : 待审核
     */

    private String id;
    private String sn;
    private String member_bank_card_account;
    private String member_bank_card_proceeds_name;
    private String member_bank_title;
    private String member_bank_address;
    private String input_money;
    private String money;
    private String service_charge_money;
    private int status;
    private String status_rejected_cause;
    private String verify_at;
    private String created_at;
    private String member_bank_icon;
    private String status_title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMember_bank_card_account() {
        return member_bank_card_account;
    }

    public void setMember_bank_card_account(String member_bank_card_account) {
        this.member_bank_card_account = member_bank_card_account;
    }

    public String getMember_bank_card_proceeds_name() {
        return member_bank_card_proceeds_name;
    }

    public void setMember_bank_card_proceeds_name(String member_bank_card_proceeds_name) {
        this.member_bank_card_proceeds_name = member_bank_card_proceeds_name;
    }

    public String getMember_bank_title() {
        return member_bank_title;
    }

    public void setMember_bank_title(String member_bank_title) {
        this.member_bank_title = member_bank_title;
    }

    public String getMember_bank_address() {
        return member_bank_address;
    }

    public void setMember_bank_address(String member_bank_address) {
        this.member_bank_address = member_bank_address;
    }

    public String getInput_money() {
        return input_money;
    }

    public void setInput_money(String input_money) {
        this.input_money = input_money;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatus_rejected_cause() {
        return status_rejected_cause;
    }

    public void setStatus_rejected_cause(String status_rejected_cause) {
        this.status_rejected_cause = status_rejected_cause;
    }

    public String getVerify_at() {
        return verify_at;
    }

    public void setVerify_at(String verify_at) {
        this.verify_at = verify_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getMember_bank_icon() {
        return member_bank_icon;
    }

    public void setMember_bank_icon(String member_bank_icon) {
        this.member_bank_icon = member_bank_icon;
    }

    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
    }
}
