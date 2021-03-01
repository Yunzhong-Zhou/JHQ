package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/2/27.
 */
public class CityFriendModel implements Serializable {
    /**
     * head : /head/160.png
     * nickname : 空气净化器_bqtu
     * service_code : 567276
     * service_count : 1
     * service_performance_num : 0
     * earning_money : 100
     * service_center_list : [{"area":123123,"province":"浙江","city":"舟山","district":"普陀区","address":"123123213","pic1":"/upload/service-center/2021-02-28/6b3b5dae805b37e6bf41e31639ce4605.png","pic2":"/upload/service-center/2021-02-28/3d9352f8604ee97ae704acc846612398.png","pic3":"/upload/service-center/2021-02-28/3d9352f8604ee97ae704acc846612398.png","code":"567276","created_at":"2021-02-28 00:48:11"}]
     */

    private String head;
    private String nickname;
    private String service_code;
    private String service_count;
    private String service_performance_num;
    private String earning_money;
    private List<ServiceCenterListBean> service_center_list;

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

    public String getService_performance_num() {
        return service_performance_num;
    }

    public void setService_performance_num(String service_performance_num) {
        this.service_performance_num = service_performance_num;
    }

    public String getEarning_money() {
        return earning_money;
    }

    public void setEarning_money(String earning_money) {
        this.earning_money = earning_money;
    }

    public List<ServiceCenterListBean> getService_center_list() {
        return service_center_list;
    }

    public void setService_center_list(List<ServiceCenterListBean> service_center_list) {
        this.service_center_list = service_center_list;
    }

    public static class ServiceCenterListBean {
        /**
         * area : 123123
         * province : 浙江
         * city : 舟山
         * district : 普陀区
         * address : 123123213
         * pic1 : /upload/service-center/2021-02-28/6b3b5dae805b37e6bf41e31639ce4605.png
         * pic2 : /upload/service-center/2021-02-28/3d9352f8604ee97ae704acc846612398.png
         * pic3 : /upload/service-center/2021-02-28/3d9352f8604ee97ae704acc846612398.png
         * code : 567276
         * created_at : 2021-02-28 00:48:11
         */

        private String area;
        private String province;
        private String city;
        private String district;
        private String address;
        private String pic1;
        private String pic2;
        private String pic3;
        private String code;
        private String created_at;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
    }
}
