package com.ofc.ofccoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-19.
 */
public class ServiceCenterModel implements Serializable {

    /**
     * head : /head/562.png
     * nickname : 18306043086
     * service_code : 604012
     * service_count : 0
     * service_performance : 0
     * amount_earning : 0
     * service_center : {"area":0,"addr":"重庆","addr_detail":"详细地址","pic1":"/upload/service_center/2019-12-19/3425e94a644b08df06721efc8deb93ae.png","pic2":"/upload/service_center/2019-12-19/3425e94a644b08df06721efc8deb93ae.png","pic3":"/upload/service_center/2019-12-19/3425e94a644b08df06721efc8deb93ae.png","code":"604012","created_at":"2019-12-19 14:45:04","show_created_at":"2019-12-19"}
     */

    private String head;
    private String nickname;
    private String service_code;
    private String service_count;
    private String service_performance;
    private String amount_earning;
    private ServiceCenterBean service_center;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getService_code() {
        return service_code;
    }

    public void setService_code(String service_code) {
        this.service_code = service_code;
    }

    public String getService_count() {
        return service_count;
    }

    public void setService_count(String service_count) {
        this.service_count = service_count;
    }

    public String getService_performance() {
        return service_performance;
    }

    public void setService_performance(String service_performance) {
        this.service_performance = service_performance;
    }

    public String getAmount_earning() {
        return amount_earning;
    }

    public void setAmount_earning(String amount_earning) {
        this.amount_earning = amount_earning;
    }

    public ServiceCenterBean getService_center() {
        return service_center;
    }

    public void setService_center(ServiceCenterBean service_center) {
        this.service_center = service_center;
    }

    public static class ServiceCenterBean {
        /**
         * area : 0
         * addr : 重庆
         * addr_detail : 详细地址
         * pic1 : /upload/service_center/2019-12-19/3425e94a644b08df06721efc8deb93ae.png
         * pic2 : /upload/service_center/2019-12-19/3425e94a644b08df06721efc8deb93ae.png
         * pic3 : /upload/service_center/2019-12-19/3425e94a644b08df06721efc8deb93ae.png
         * code : 604012
         * created_at : 2019-12-19 14:45:04
         * show_created_at : 2019-12-19
         */

        private String area;
        private String addr;
        private String addr_detail;
        private String pic1;
        private String pic2;
        private String pic3;
        private String code;
        private String created_at;
        private String show_created_at;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getShow_created_at() {
            return show_created_at;
        }

        public void setShow_created_at(String show_created_at) {
            this.show_created_at = show_created_at;
        }
    }
}
