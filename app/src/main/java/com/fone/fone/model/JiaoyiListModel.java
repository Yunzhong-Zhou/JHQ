package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/10/27.
 */
public class JiaoyiListModel implements Serializable {

    private List<DoDrvtSellListBean> do_drvt_sell_list;

    public List<DoDrvtSellListBean> getDo_drvt_sell_list() {
        return do_drvt_sell_list;
    }

    public void setDo_drvt_sell_list(List<DoDrvtSellListBean> do_drvt_sell_list) {
        this.do_drvt_sell_list = do_drvt_sell_list;
    }

    public static class DoDrvtSellListBean {
        /**
         * type : sell
         * type_title : 出售
         * opposite_member_nickname : zhumimi
         * drvt_price : 1.0000
         * money : 10.00
         * usdt_money : 9.09
         * created_at : 2020/10/27/16/10
         */

        private String type;
        private String type_title;
        private String opposite_member_nickname;
        private String drvt_price;
        private String money;
        private String usdt_money;
        private String created_at;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType_title() {
            return type_title;
        }

        public void setType_title(String type_title) {
            this.type_title = type_title;
        }

        public String getOpposite_member_nickname() {
            return opposite_member_nickname;
        }

        public void setOpposite_member_nickname(String opposite_member_nickname) {
            this.opposite_member_nickname = opposite_member_nickname;
        }

        public String getDrvt_price() {
            return drvt_price;
        }

        public void setDrvt_price(String drvt_price) {
            this.drvt_price = drvt_price;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getUsdt_money() {
            return usdt_money;
        }

        public void setUsdt_money(String usdt_money) {
            this.usdt_money = usdt_money;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
