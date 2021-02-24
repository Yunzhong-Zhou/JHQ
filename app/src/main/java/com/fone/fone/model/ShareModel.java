package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2018/11/12.
 */
public class ShareModel implements Serializable {
    /**
     * head : /head/795.png
     * nickname : 空气净化器_tntk
     * invite_code : tntk
     * grade : 1
     * commission_running_proportion : 0
     * commission_sell_proportion : 0
     * commission_money : 0
     * commission_same_level_proportion : 10
     * valid_direct_recommend : 0
     * hold_start_at : -
     * hold_end_at : -
     * hold_current_num : 0
     * hold_target_num : 0
     * tips_hold :
     * tips_upgrade : 距离下个等级还差5台业绩
     * direct_recommend : 0
     * team_recommend : 0
     * direct_performance_num : 0
     * team_performance_num : 0
     */

    private String head;
    private String nickname;
    private String invite_code;
    private int grade;
    private String commission_running_proportion;
    private String commission_sell_proportion;
    private String commission_money;
    private String commission_same_level_proportion;
    private String valid_direct_recommend;
    private String hold_start_at;
    private String hold_end_at;
    private String hold_current_num;
    private String hold_target_num;
    private String tips_hold;
    private String tips_upgrade;
    private String direct_recommend;
    private String team_recommend;
    private String direct_performance_num;
    private String team_performance_num;

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getCommission_running_proportion() {
        return commission_running_proportion;
    }

    public void setCommission_running_proportion(String commission_running_proportion) {
        this.commission_running_proportion = commission_running_proportion;
    }

    public String getCommission_sell_proportion() {
        return commission_sell_proportion;
    }

    public void setCommission_sell_proportion(String commission_sell_proportion) {
        this.commission_sell_proportion = commission_sell_proportion;
    }

    public String getCommission_money() {
        return commission_money;
    }

    public void setCommission_money(String commission_money) {
        this.commission_money = commission_money;
    }

    public String getCommission_same_level_proportion() {
        return commission_same_level_proportion;
    }

    public void setCommission_same_level_proportion(String commission_same_level_proportion) {
        this.commission_same_level_proportion = commission_same_level_proportion;
    }

    public String getValid_direct_recommend() {
        return valid_direct_recommend;
    }

    public void setValid_direct_recommend(String valid_direct_recommend) {
        this.valid_direct_recommend = valid_direct_recommend;
    }

    public String getHold_start_at() {
        return hold_start_at;
    }

    public void setHold_start_at(String hold_start_at) {
        this.hold_start_at = hold_start_at;
    }

    public String getHold_end_at() {
        return hold_end_at;
    }

    public void setHold_end_at(String hold_end_at) {
        this.hold_end_at = hold_end_at;
    }

    public String getHold_current_num() {
        return hold_current_num;
    }

    public void setHold_current_num(String hold_current_num) {
        this.hold_current_num = hold_current_num;
    }

    public String getHold_target_num() {
        return hold_target_num;
    }

    public void setHold_target_num(String hold_target_num) {
        this.hold_target_num = hold_target_num;
    }

    public String getTips_hold() {
        return tips_hold;
    }

    public void setTips_hold(String tips_hold) {
        this.tips_hold = tips_hold;
    }

    public String getTips_upgrade() {
        return tips_upgrade;
    }

    public void setTips_upgrade(String tips_upgrade) {
        this.tips_upgrade = tips_upgrade;
    }

    public String getDirect_recommend() {
        return direct_recommend;
    }

    public void setDirect_recommend(String direct_recommend) {
        this.direct_recommend = direct_recommend;
    }

    public String getTeam_recommend() {
        return team_recommend;
    }

    public void setTeam_recommend(String team_recommend) {
        this.team_recommend = team_recommend;
    }

    public String getDirect_performance_num() {
        return direct_performance_num;
    }

    public void setDirect_performance_num(String direct_performance_num) {
        this.direct_performance_num = direct_performance_num;
    }

    public String getTeam_performance_num() {
        return team_performance_num;
    }

    public void setTeam_performance_num(String team_performance_num) {
        this.team_performance_num = team_performance_num;
    }
}
