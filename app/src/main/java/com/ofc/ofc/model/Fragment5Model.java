package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/9.
 */

public class Fragment5Model implements Serializable {

    /**
     * nickname : 183****3086
     * head : /head/562.png
     * common_usable_money : 0.2
     * profit_money : 0
     * commission_money : 0
     * invite_code : 703696
     */

    private String nickname;
    private String head;
    private String common_usable_money;
    private String profit_money;
    private String commission_money;
    private String invite_code;
    private int grade;
    private int service_center_status;
    private String status_rejected_cause;

    public String getStatus_rejected_cause() {
        return status_rejected_cause;
    }

    public void setStatus_rejected_cause(String status_rejected_cause) {
        this.status_rejected_cause = status_rejected_cause;
    }

    public int getService_center_status() {
        return service_center_status;
    }

    public void setService_center_status(int service_center_status) {
        this.service_center_status = service_center_status;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getCommon_usable_money() {
        return common_usable_money;
    }

    public void setCommon_usable_money(String common_usable_money) {
        this.common_usable_money = common_usable_money;
    }

    public String getProfit_money() {
        return profit_money;
    }

    public void setProfit_money(String profit_money) {
        this.profit_money = profit_money;
    }

    public String getCommission_money() {
        return commission_money;
    }

    public void setCommission_money(String commission_money) {
        this.commission_money = commission_money;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }
}
