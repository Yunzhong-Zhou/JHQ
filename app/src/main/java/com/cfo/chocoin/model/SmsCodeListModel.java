package com.cfo.chocoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2017/9/21.
 * 手机号国家代码集合
 */

public class SmsCodeListModel implements Serializable {
    private List<LangListBean> lang_list;
    private List<MobileStateListBean> mobile_state_list;

    public List<LangListBean> getLang_list() {
        return lang_list;
    }

    public void setLang_list(List<LangListBean> lang_list) {
        this.lang_list = lang_list;
    }

    public List<MobileStateListBean> getMobile_state_list() {
        return mobile_state_list;
    }

    public void setMobile_state_list(List<MobileStateListBean> mobile_state_list) {
        this.mobile_state_list = mobile_state_list;
    }

    public static class LangListBean {
        /**
         * lang_type : zh
         * title : 中文
         * icon : /lang/zh.jpg
         */

        private String lang_type;
        private String title;
        private String icon;

        public String getLang_type() {
            return lang_type;
        }

        public void setLang_type(String lang_type) {
            this.lang_type = lang_type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class MobileStateListBean implements Serializable{
        /**
         * code : 86
         * title : 中国
         * icon : /mobile-state-code/86.jpg
         */
        private String code;
        private String title;
        private String icon;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
