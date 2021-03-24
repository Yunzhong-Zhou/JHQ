package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/12/17.
 */
public class RegisteredModel implements Serializable {
    /**
     * id : 078705507819bbe863c9897dfb01d333
     * mobile : 18306043084
     * mobile_state_code : 86
     * head : /head/455.png
     * nickname : FIL_phqy
     * invite_code : phqy
     * fresh_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwNzg3MDU1MDc4MTliYmU4NjNjOTg5N2RmYjAxZDMzMyIsImlzcyI6Imh0dHA6XC9cL3N2bi5mYml2MnJheS5jb21cL2FwaVwvbWVtYmVyXC9yZWdpc3RlciIsImlhdCI6MTYwODIwNjE0MiwiZXhwIjoxNjM5NzQyMTQyLCJuYmYiOjE2MDgyMDYxNDIsImp0aSI6IjMxOWE4NjVlYmZhNTBlMjI1MDAxMTZmZWFjYTFhMWM1In0.-I2Wc4V9bVZ7EMeyA-FtTm3TXCjTtWWyBvUoNzonC5E
     */

    private String id;
    private String mobile;
    private String mobile_state_code;
    private String head;
    private String nickname;
    private String invite_code;
    private String fresh_token;

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

    public String getMobile_state_code() {
        return mobile_state_code;
    }

    public void setMobile_state_code(String mobile_state_code) {
        this.mobile_state_code = mobile_state_code;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getFresh_token() {
        return fresh_token;
    }

    public void setFresh_token(String fresh_token) {
        this.fresh_token = fresh_token;
    }
}
