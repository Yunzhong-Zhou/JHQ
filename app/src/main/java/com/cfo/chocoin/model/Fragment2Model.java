package com.cfo.chocoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/4.
 */
public class Fragment2Model implements Serializable {

    private List<MoneylistBean> moneylist;

    public List<MoneylistBean> getMoneylist() {
        return moneylist;
    }

    public void setMoneylist(List<MoneylistBean> moneylist) {
        this.moneylist = moneylist;
    }

    public static class MoneylistBean {
        /**
         * money : 1
         */

        private int money;

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}
