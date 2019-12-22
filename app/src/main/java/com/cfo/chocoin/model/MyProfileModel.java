package com.cfo.chocoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/10.
 */

public class MyProfileModel implements Serializable {
    /**
     * id : 5f961028d7235607fd3763283e3fcbcf
     * mobile : 18306043086
     * nickname : 18306043086
     * head : /head/562.png
     * invite_code : 703696
     * grade : 1
     * service_center_status : 3
     * service_code : 604012
     */

    private String id;
    private String mobile;
    private String nickname;
    private String head;
    private String invite_code;
    private int grade;
    private int auth;
    private int service_center_status;
    private String service_code;

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getService_center_status() {
        return service_center_status;
    }

    public void setService_center_status(int service_center_status) {
        this.service_center_status = service_center_status;
    }

    public String getService_code() {
        return service_code;
    }

    public void setService_code(String service_code) {
        this.service_code = service_code;
    }
}
