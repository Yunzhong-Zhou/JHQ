package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/9/22.
 */
public class FenHong_ChartModel implements Serializable {
    /**
     * max : 1.0471
     * min : 1.0300
     * ofc_price_list : [{"price":"1.0471","created_at":"2020-09-22 12:47:35"},{"price":"1.0470","created_at":"2020-09-22 12:47:27"},{"price":"1.0469","created_at":"2020-09-22 12:41:52"},{"price":"1.0459","created_at":"2020-09-22 12:41:43"},{"price":"1.0409","created_at":"2020-09-22 12:40:56"},{"price":"1.0408","created_at":"2020-09-22 12:40:51"},{"price":"1.0407","created_at":"2020-09-22 12:40:43"},{"price":"1.0406","created_at":"2020-09-22 12:40:37"},{"price":"1.0405","created_at":"2020-09-22 12:40:30"},{"price":"1.0404","created_at":"2020-09-22 12:40:23"},{"price":"1.0403","created_at":"2020-09-22 12:39:33"},{"price":"1.0402","created_at":"2020-09-22 12:39:28"},{"price":"1.0401","created_at":"2020-09-22 12:39:22"},{"price":"1.0390","created_at":"2020-09-22 12:38:54"},{"price":"1.0380","created_at":"2020-09-22 12:38:48"},{"price":"1.0370","created_at":"2020-09-22 12:38:42"},{"price":"1.0360","created_at":"2020-09-22 12:38:37"},{"price":"1.0350","created_at":"2020-09-21 19:55:18"},{"price":"1.0320","created_at":"2020-09-21 14:18:27"},{"price":"1.0300","created_at":"2020-09-21 14:18:07"}]
     */

    private String max;
    private String min;
    private List<OfcPriceListBean> ofc_price_list;

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public List<OfcPriceListBean> getOfc_price_list() {
        return ofc_price_list;
    }

    public void setOfc_price_list(List<OfcPriceListBean> ofc_price_list) {
        this.ofc_price_list = ofc_price_list;
    }

    public static class OfcPriceListBean {
        /**
         * price : 1.0471
         * created_at : 2020-09-22 12:47:35
         */

        private String price;
        private String created_at;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
