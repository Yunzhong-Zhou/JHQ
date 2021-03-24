package com.ghzk.ghzk.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/3/1.
 */
public class CityFriendListModel implements Serializable {
    private List<ServiceCenterListBean> service_center_list;

    public List<ServiceCenterListBean> getService_center_list() {
        return service_center_list;
    }

    public void setService_center_list(List<ServiceCenterListBean> service_center_list) {
        this.service_center_list = service_center_list;
    }

    public static class ServiceCenterListBean {
        /**
         * title :
         * area : 123123
         * province : 浙江
         * city : 舟山
         * district : 普陀区
         * address : 123123213
         * pic1 : /upload/service-center/2021-02-28/6b3b5dae805b37e6bf41e31639ce4605.png
         * pic2 : /upload/service-center/2021-02-28/3d9352f8604ee97ae704acc846612398.png
         * pic3 : /upload/service-center/2021-02-28/3d9352f8604ee97ae704acc846612398.png
         * code : 567276
         * grade : 1
         * grade_title : 一级
         */

        private String title;
        private int area;
        private String province;
        private String city;
        private String district;
        private String address;
        private String pic1;
        private String pic2;
        private String pic3;
        private String code;
        private int grade;
        private String grade_title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
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

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getGrade_title() {
            return grade_title;
        }

        public void setGrade_title(String grade_title) {
            this.grade_title = grade_title;
        }
    }
}
