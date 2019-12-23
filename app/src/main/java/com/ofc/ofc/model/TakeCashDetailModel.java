package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/2.
 */
public class TakeCashDetailModel implements Serializable {
    /**
     * id : 2b8f007446b940cf4c1ff8c7
     * sn : WL1576578237525550
     * member_usdt_wallet_addr :
     * service_charge_money : 1
     * input_money : 10
     * money : 9
     * status : 1
     * status_rejected_cause :
     * created_at : 2019-12-17 18:23:57
     * updated_at : 2019-12-17 18:23:57
     * status_title : 待审核
     * show_created_at : 12-17 18:23
     * show_updated_at : 12-17 18:23
     */

    private String id;
    private String sn;
    private String member_usdt_wallet_addr;
    private String service_charge_money;
    private String input_money;
    private String money;
    private int status;
    private String status_rejected_cause;
    private String created_at;
    private String updated_at;
    private String status_title;
    private String show_created_at;
    private String show_updated_at;

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

    public String getMember_usdt_wallet_addr() {
        return member_usdt_wallet_addr;
    }

    public void setMember_usdt_wallet_addr(String member_usdt_wallet_addr) {
        this.member_usdt_wallet_addr = member_usdt_wallet_addr;
    }

    public String getService_charge_money() {
        return service_charge_money;
    }

    public void setService_charge_money(String service_charge_money) {
        this.service_charge_money = service_charge_money;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
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

    public String getShow_updated_at() {
        return show_updated_at;
    }

    public void setShow_updated_at(String show_updated_at) {
        this.show_updated_at = show_updated_at;
    }
}
