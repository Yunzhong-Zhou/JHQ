package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/12/21.
 */
public class Fragment2BuyModel implements Serializable {
    /**
     * id : 50c67accb59d7364fdc7069a48363932
     * sn : IN1608527081505657
     * member_id : ec75507f915cb412b35299b11e672fa5
     * mill_id : 21d990190b6e3e2f08dada7e03ac062c
     * pay_type : 1
     * hashrate : 1
     * change_game_id :
     * last_interest_at : 2020-12-21 13:04:41
     * money : 500
     * mill_type : 2
     * mill_computer_position : 中国福州
     * mill_production_value_fil_money : 20.0000
     * mill_node_number : 0xeeee
     * mill_number : 0xwww
     * mill_mining_cycle : 360
     * verify_at : 2020-12-21 13:04:41
     * member_type : 1
     * member_head : /head/606.png
     * member_nickname : FIL_pour广告费
     * updated_at : 2020-12-21 13:04:41
     * created_at : 2020-12-21 13:04:41
     */

    private String id;
    private String sn;
    private String member_id;
    private String mill_id;
    private int pay_type;
    private int hashrate;
    private String change_game_id;
    private String last_interest_at;
    private int money;
    private int mill_type;
    private String mill_computer_position;
    private String mill_production_value_fil_money;
    private String mill_node_number;
    private String mill_number;
    private int mill_mining_cycle;
    private String verify_at;
    private int member_type;
    private String member_head;
    private String member_nickname;
    private String updated_at;
    private String created_at;

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

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMill_id() {
        return mill_id;
    }

    public void setMill_id(String mill_id) {
        this.mill_id = mill_id;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public int getHashrate() {
        return hashrate;
    }

    public void setHashrate(int hashrate) {
        this.hashrate = hashrate;
    }

    public String getChange_game_id() {
        return change_game_id;
    }

    public void setChange_game_id(String change_game_id) {
        this.change_game_id = change_game_id;
    }

    public String getLast_interest_at() {
        return last_interest_at;
    }

    public void setLast_interest_at(String last_interest_at) {
        this.last_interest_at = last_interest_at;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMill_type() {
        return mill_type;
    }

    public void setMill_type(int mill_type) {
        this.mill_type = mill_type;
    }

    public String getMill_computer_position() {
        return mill_computer_position;
    }

    public void setMill_computer_position(String mill_computer_position) {
        this.mill_computer_position = mill_computer_position;
    }

    public String getMill_production_value_fil_money() {
        return mill_production_value_fil_money;
    }

    public void setMill_production_value_fil_money(String mill_production_value_fil_money) {
        this.mill_production_value_fil_money = mill_production_value_fil_money;
    }

    public String getMill_node_number() {
        return mill_node_number;
    }

    public void setMill_node_number(String mill_node_number) {
        this.mill_node_number = mill_node_number;
    }

    public String getMill_number() {
        return mill_number;
    }

    public void setMill_number(String mill_number) {
        this.mill_number = mill_number;
    }

    public int getMill_mining_cycle() {
        return mill_mining_cycle;
    }

    public void setMill_mining_cycle(int mill_mining_cycle) {
        this.mill_mining_cycle = mill_mining_cycle;
    }

    public String getVerify_at() {
        return verify_at;
    }

    public void setVerify_at(String verify_at) {
        this.verify_at = verify_at;
    }

    public int getMember_type() {
        return member_type;
    }

    public void setMember_type(int member_type) {
        this.member_type = member_type;
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

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
