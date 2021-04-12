package com.ghzk.ghzk.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/12.
 */
public class OrderListModel implements Serializable {
    private List<GoodsOrderListBean> goods_order_list;

    public List<GoodsOrderListBean> getGoods_order_list() {
        return goods_order_list;
    }

    public void setGoods_order_list(List<GoodsOrderListBean> goods_order_list) {
        this.goods_order_list = goods_order_list;
    }

    public static class GoodsOrderListBean {
        /**
         * created_at :
         */

        private String created_at;

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
