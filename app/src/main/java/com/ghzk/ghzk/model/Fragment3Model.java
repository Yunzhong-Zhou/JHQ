package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/5/22.
 */
public class Fragment3Model implements Serializable {
    /**
     * usable_money : 100.0000
     * goods : {"id":"675f76eb37cbc5e02d22b17618bde805","title":"第三个商品","cover":"","cannot_buy_back_price":"200.00","can_buy_back_price":"100.00","status":1}
     */

    private String usable_money;
    private int max_num;
    private GoodsBean goods;

    public int getMax_num() {
        return max_num;
    }

    public void setMax_num(int max_num) {
        this.max_num = max_num;
    }

    public String getUsable_money() {
        return usable_money;
    }

    public void setUsable_money(String usable_money) {
        this.usable_money = usable_money;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * id : 675f76eb37cbc5e02d22b17618bde805
         * title : 第三个商品
         * cover :
         * cannot_buy_back_price : 200.00
         * can_buy_back_price : 100.00
         * status : 1
         */

        private String id;
        private String title;
        private String cover;
        private String cannot_buy_back_price;
        private String can_buy_back_price;
        private int status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCannot_buy_back_price() {
            return cannot_buy_back_price;
        }

        public void setCannot_buy_back_price(String cannot_buy_back_price) {
            this.cannot_buy_back_price = cannot_buy_back_price;
        }

        public String getCan_buy_back_price() {
            return can_buy_back_price;
        }

        public void setCan_buy_back_price(String can_buy_back_price) {
            this.can_buy_back_price = can_buy_back_price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
