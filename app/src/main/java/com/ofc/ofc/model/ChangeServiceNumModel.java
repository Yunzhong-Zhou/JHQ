package com.ofc.ofc.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019-12-19.
 */
public class ChangeServiceNumModel implements Serializable {
    /**
     * service_center_grade : 1
     * service_center : {"area":0,"addr":"重庆","addr_detail":"详细地址","pic1":"/upload/service_center/2019-12-19/3425e94a644b08df06721efc8deb93ae.png","pic2":"/upload/service_center/2019-12-19/3425e94a644b08df06721efc8deb93ae.png","pic3":"/upload/service_center/2019-12-19/3425e94a644b08df06721efc8deb93ae.png","code":"604012"}
     */

    private int service_center_grade;
    private ServiceCenterBean service_center;

    public int getService_center_grade() {
        return service_center_grade;
    }

    public void setService_center_grade(int service_center_grade) {
        this.service_center_grade = service_center_grade;
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
         */

        private String area;
        private String addr;
        private String addr_detail;
        private String pic1;
        private String pic2;
        private String pic3;
        private String code;

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
    }
}
