package com.ofc.ofccoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019-12-19.
 */
public class ServiceCenter_MoreModel implements Serializable {
    private List<ServiceCenterListBean> service_center_list;

    public List<ServiceCenterListBean> getService_center_list() {
        return service_center_list;
    }

    public void setService_center_list(List<ServiceCenterListBean> service_center_list) {
        this.service_center_list = service_center_list;
    }

    public static class ServiceCenterListBean {
        /**
         * area : 20
         * addr : 山西省 太原市
         * addr_detail : Gggghh
         * pic1 : /upload/service_center/2019-12-19/7b6b42e3232748356600f3c63634f76c.jpg
         * pic2 : /upload/service_center/2019-12-19/13774bd2cc9b648ea7f275380d6cec41.jpg
         * pic3 : /upload/service_center/2019-12-19/8c6dd56225f1eb3124b0438e5bfa63c8.jpg
         * code : 739350
         */

        private int area;
        private String addr;
        private String addr_detail;
        private String pic1;
        private String pic2;
        private String pic3;
        private String code;

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getAddr_detail() {
            return addr_detail;
        }

        public void setAddr_detail(String addr_detail) {
            this.addr_detail = addr_detail;
        }

        public String getPic1() {
            return pic1;
        }

        public void setPic1(String pic1) {
            this.pic1 = pic1;
        }

        public String getPic2() {
            return pic2;
        }

        public void setPic2(String pic2) {
            this.pic2 = pic2;
        }

        public String getPic3() {
            return pic3;
        }

        public void setPic3(String pic3) {
            this.pic3 = pic3;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
