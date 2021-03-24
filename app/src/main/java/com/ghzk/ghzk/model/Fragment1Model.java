package com.ghzk.ghzk.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2020/7/6.
 */
public class Fragment1Model implements Serializable {
    private List<CooperationBrandListBean> cooperation_brand_list;
    private List<CooperationShopListBean> cooperation_shop_list;
    private List<IndustryListBean> industry_list;
    /**
     * brand_count : 0
     * install_num : 0
     */

    private String brand_count;
    private String install_num;

    public List<CooperationBrandListBean> getCooperation_brand_list() {
        return cooperation_brand_list;
    }

    public void setCooperation_brand_list(List<CooperationBrandListBean> cooperation_brand_list) {
        this.cooperation_brand_list = cooperation_brand_list;
    }

    public List<CooperationShopListBean> getCooperation_shop_list() {
        return cooperation_shop_list;
    }

    public void setCooperation_shop_list(List<CooperationShopListBean> cooperation_shop_list) {
        this.cooperation_shop_list = cooperation_shop_list;
    }

    public List<IndustryListBean> getIndustry_list() {
        return industry_list;
    }

    public void setIndustry_list(List<IndustryListBean> industry_list) {
        this.industry_list = industry_list;
    }

    public String getBrand_count() {
        return brand_count;
    }

    public void setBrand_count(String brand_count) {
        this.brand_count = brand_count;
    }

    public String getInstall_num() {
        return install_num;
    }

    public void setInstall_num(String install_num) {
        this.install_num = install_num;
    }

    public static class CooperationBrandListBean {
        /**
         * title : 品牌1
         * logo : /upload/brand/2021-02-21/56eec3d3300b192d83e074d09d431840.jpg
         * created_at : 2021-02-21 12:39:13
         */

        private String title;
        private String logo;
        private String created_at;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

    public static class CooperationShopListBean {
        /**
         * title : 哈哈哈
         * cover : /upload/shop/2021-02-28/27f7b33d0e70bc9f17fa80070d8f8031.png
         * industry : 1
         * province : 重庆
         * city : 重庆
         * district : 湖西
         * address : 美丽·阳光家园三期(北门)
         * status : 1
         * num : 1
         * created_at : 2021-02-28 18:34:11
         * status_title : 待安装
         */

        private String title;
        private String cover;
        private int industry;
        private String province;
        private String city;
        private String district;
        private String address;
        private int status;
        private int num;
        private String created_at;
        private String status_title;

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

        public int getIndustry() {
            return industry;
        }

        public void setIndustry(int industry) {
            this.industry = industry;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
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
    }

    public static class IndustryListBean {
        /**
         * code : 1
         * title : 酒店
         */

        private int code;
        private String title;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
