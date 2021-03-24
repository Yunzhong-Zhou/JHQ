package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/12/23.
 */
public class JoinModel implements Serializable {
    private String change_game_id;
    private String member_head;
    private String member_nickname;
    private String index;

    public JoinModel(String change_game_id, String member_head, String member_nickname, String index) {
        this.change_game_id = change_game_id;
        this.member_head = member_head;
        this.member_nickname = member_nickname;
        this.index = index;
    }

    public String getChange_game_id() {
        return change_game_id;
    }

    public void setChange_game_id(String change_game_id) {
        this.change_game_id = change_game_id;
    }

    public String getMember_head() {
        return member_head;
    }

    public void setMember_head(String member_head) {
        this.member_head = member_head;
    }

    public String getMember_nickname() {
        return member_nickname;
    }

    public void setMember_nickname(String member_nickname) {
        this.member_nickname = member_nickname;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "JoinModel{" +
                "change_game_id='" + change_game_id + '\'' +
                ", member_head='" + member_head + '\'' +
                ", member_nickname='" + member_nickname + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
