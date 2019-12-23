package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2018/3/8.
 */

public class InformationModel implements Serializable {
    private List<NoticeBean> notice;
    private List<HelpBean> help;

    public List<NoticeBean> getNotice() {
        return notice;
    }

    public void setNotice(List<NoticeBean> notice) {
        this.notice = notice;
    }

    public List<HelpBean> getHelp() {
        return help;
    }

    public void setHelp(List<HelpBean> help) {
        this.help = help;
    }

    public static class NoticeBean {
        /**
         * id : e6d2fd37cf16bd76274630524226afdd
         * title : 公告11
         * cover :
         * digest : 公告11
         * url : http://192.168.0.188/wechat/article/detail?id=e6d2fd37cf16bd76274630524226afdd
         */

        private String id;
        private String title;
        private String cover;
        private String digest;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class HelpBean {
        /**
         * id : 0b4ae8abd1ce5dffadf9b62ae2133e74
         * title : 帮助中心2
         * cover :
         * digest : 帮助中心2
         * url : http://192.168.0.188/wechat/article/detail?id=0b4ae8abd1ce5dffadf9b62ae2133e74
         */

        private String id;
        private String title;
        private String cover;
        private String digest;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
