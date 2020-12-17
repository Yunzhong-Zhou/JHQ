package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/2/10.
 */

public class MyProfileModel implements Serializable {
    /**
     * id : ec75507f915cb412b35299b11e672fa5
     * mobile : 18306043086
     * nickname : FIL_pour
     * head : /head/606.png
     * email :
     * invite_code : pour
     * grade : 1
     * grade_title : V0
     */

    private String id;
    private String mobile;
    private String nickname;
    private String head;
    private String email;
    private String invite_code;
    private int grade;
    private String grade_title;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getGrade_title() {
        return grade_title;
    }

    public void setGrade_title(String grade_title) {
        this.grade_title = grade_title;
    }
}
