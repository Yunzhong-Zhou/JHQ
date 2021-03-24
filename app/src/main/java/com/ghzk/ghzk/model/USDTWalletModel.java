package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/12/20.
 */
public class USDTWalletModel implements Serializable {
    /**
     * usable_money : 9990.0000
     * change_game_win_money : 0.0000
     * commission_money : 0.0000
     * in_money : 9990.0000
     * out_money : 0.0000
     */

    private String top_up_id;

    public String getTop_up_id() {
        return top_up_id;
    }

    public void setTop_up_id(String top_up_id) {
        this.top_up_id = top_up_id;
    }

    private String usable_money;
    private String change_game_win_money;
    private String commission_money;
    private String in_money;
    private String out_money;

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
    }

    public String getChange_game_win_money() {
        return change_game_win_money;
    }

    public void setChange_game_win_money(String change_game_win_money) {
        this.change_game_win_money = change_game_win_money;
    }

    public String getCommission_money() {
        return commission_money;
    }

    public void setCommission_money(String commission_money) {
        this.commission_money = commission_money;
    }

    public String getIn_money() {
        return in_money;
    }

    public void setIn_money(String in_money) {
        this.in_money = in_money;
    }

    public String getOut_money() {
        return out_money;
    }

    public void setOut_money(String out_money) {
        this.out_money = out_money;
    }
}
