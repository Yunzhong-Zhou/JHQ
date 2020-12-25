package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/2.
 */
public class Fragment4Model implements Serializable {
    /**
     * usable_money : 10000.0000
     * change_game_win_money : 0.0000
     * commission_money : 0.0000
     * usable_fil_money : 0.0000
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
    private String usable_fil_money;

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

    public String getUsable_fil_money() {
        return usable_fil_money;
    }

    public void setUsable_fil_money(String usable_fil_money) {
        this.usable_fil_money = usable_fil_money;
    }
}
