package com.filter.filter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019-12-22.
 */
public class VerifiedModel1 implements Serializable {
    private List<InlandBean> inland;
    private List<ForeignBean> foreign;

    public List<InlandBean> getInland() {
        return inland;
    }

    public void setInland(List<InlandBean> inland) {
        this.inland = inland;
    }

    public List<ForeignBean> getForeign() {
        return foreign;
    }

    public void setForeign(List<ForeignBean> foreign) {
        this.foreign = foreign;
    }

    public static class InlandBean {
        /**
         * code : 1
         * title : 身份证
         */

        private String code;
        private String title;

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
    }

    public static class ForeignBean {
        /**
         * code : 2
         * title : 护照
         */

        private String code;
        private String title;

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
    }
}
