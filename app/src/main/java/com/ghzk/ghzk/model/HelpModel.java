package com.ghzk.ghzk.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2018/6/25.
 */
public class HelpModel implements Serializable {
    /**
     * article_list : [{"id":"b9a8e721cf6a89ae2766fb73da39b7a2","article_category_id":"8751c7f0bb2e3758ce4b86f34598404e","title":"帮助1","en_title":"","digest":"","en_digest":"","cover":"/help.jpg","read":670,"created_at":"2020-03-02 12:45:29","url":"http://svn.fbiv2ray.com/wechat/article/detail?id=b9a8e721cf6a89ae2766fb73da39b7a2"}]
     * landline_number : 4008-518-588
     */

    private String landline_number;
    private List<ArticleListBean> article_list;

    public String getLandline_number() {
        return landline_number;
    }

    public void setLandline_number(String landline_number) {
        this.landline_number = landline_number;
    }

    public List<ArticleListBean> getArticle_list() {
        return article_list;
    }

    public void setArticle_list(List<ArticleListBean> article_list) {
        this.article_list = article_list;
    }

    public static class ArticleListBean {
        /**
         * id : b9a8e721cf6a89ae2766fb73da39b7a2
         * article_category_id : 8751c7f0bb2e3758ce4b86f34598404e
         * title : 帮助1
         * en_title :
         * digest :
         * en_digest :
         * cover : /help.jpg
         * read : 670
         * created_at : 2020-03-02 12:45:29
         * url : http://svn.fbiv2ray.com/wechat/article/detail?id=b9a8e721cf6a89ae2766fb73da39b7a2
         */

        private String id;
        private String article_category_id;
        private String title;
        private String en_title;
        private String digest;
        private String en_digest;
        private String cover;
        private int read;
        private String created_at;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getArticle_category_id() {
            return article_category_id;
        }

        public void setArticle_category_id(String article_category_id) {
            this.article_category_id = article_category_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getEn_title() {
            return en_title;
        }

        public void setEn_title(String en_title) {
            this.en_title = en_title;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getEn_digest() {
            return en_digest;
        }

        public void setEn_digest(String en_digest) {
            this.en_digest = en_digest;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
