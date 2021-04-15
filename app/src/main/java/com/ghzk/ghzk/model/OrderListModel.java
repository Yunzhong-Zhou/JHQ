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
         * id : e936cb0957d27611f4f4d760db3cb461
         * order_goods_id : bd09a52351567242204ec7e965139c37
         * sn : GO1618311222985210
         * money : 0.00
         * status : 1
         * start_at : 2021-04-12 10:02:24
         * end_at : null
         * created_at : 2021-04-13 18:53:42
         * status_title : 使用中
         * use_time :
         */

        private String id;
        private String order_goods_id;
        private String sn;
        private String money;
        private int status;
        private String start_at;
        private Object end_at;
        private String created_at;
        private String status_title;
        private String use_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_goods_id() {
            return order_goods_id;
        }

        public void setOrder_goods_id(String order_goods_id) {
            this.order_goods_id = order_goods_id;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStart_at() {
            return start_at;
        }

        public void setStart_at(String start_at) {
            this.start_at = start_at;
        }

        public Object getEnd_at() {
            return end_at;
        }

        public void setEnd_at(Object end_at) {
            this.end_at = end_at;
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

        public String getUse_time() {
            return use_time;
        }

        public void setUse_time(String use_time) {
            this.use_time = use_time;
        }
    }
}
