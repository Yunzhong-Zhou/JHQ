package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/1/16.
 */
public class TransferRecordModel implements Serializable {
    /**
     * id : 797c81fd21018a668f0f93bec3f04ed7
     * money : 100
     * from_member_id : 12b7d501fd4001b0e4e2a0c24beae3b9
     * from_member_nickname : 阿斯顿马丁
     * to_member_id : 82a2c1940d4d669e8d13652325e915db
     * to_member_nickname : 1
     * created_at : 2019-07-15 17:54:15
     * status_title : 完成
     * show_created_at : 2019-07-15
     * type : 1
     * nickname : 1
     * type_title : 转出
     */

    private String id;
    private String money;
    private String from_member_id;
    private String from_member_nickname;
    private String to_member_id;
    private String to_member_nickname;
    private String created_at;
    private String status_title;
    private String show_created_at;
    private int type;
    private String nickname;
    private String type_title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFrom_member_id() {
        return from_member_id;
    }

    public void setFrom_member_id(String from_member_id) {
        this.from_member_id = from_member_id;
    }

    public String getFrom_member_nickname() {
        return from_member_nickname;
    }

    public void setFrom_member_nickname(String from_member_nickname) {
        this.from_member_nickname = from_member_nickname;
    }

    public String getTo_member_id() {
        return to_member_id;
    }

    public void setTo_member_id(String to_member_id) {
        this.to_member_id = to_member_id;
    }

    public String getTo_member_nickname() {
        return to_member_nickname;
    }

    public void setTo_member_nickname(String to_member_nickname) {
        this.to_member_nickname = to_member_nickname;
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

    public String getShow_created_at() {
        return show_created_at;
    }

    public void setShow_created_at(String show_created_at) {
        this.show_created_at = show_created_at;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getType_title() {
        return type_title;
    }

    public void setType_title(String type_title) {
        this.type_title = type_title;
    }
}
