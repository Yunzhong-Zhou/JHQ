package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2018/3/8.
 */

public class InformationModel implements Serializable {
    /**
     * article_category_1 : {"title":"通知公告","article_list":[{"id":"b1268d4e66b97b8107caa2869ed24841","article_category_id":"1d81a23b7ee4d189a2d7cf89f9302352","title":"test3","en_title":"","digest":"","en_digest":"","cover":"/notice.jpg","read":863,"created_at":"2020-01-06 17:32:54","url":"https://app.ofcapital.net/wechat/article/detail?id=b1268d4e66b97b8107caa2869ed24841"},{"id":"0eff20c59c4611574bccbaf18bb0219c","article_category_id":"1d81a23b7ee4d189a2d7cf89f9302352","title":"test2","en_title":"","digest":"","en_digest":"","cover":"/notice.jpg","read":672,"created_at":"2020-01-01 15:46:09","url":"https://app.ofcapital.net/wechat/article/detail?id=0eff20c59c4611574bccbaf18bb0219c"},{"id":"1c431d7c89300e59c7231ea8b0a2fa46","article_category_id":"1d81a23b7ee4d189a2d7cf89f9302352","title":"test","en_title":"","digest":"","en_digest":"","cover":"/notice.jpg","read":911,"created_at":"2020-01-01 15:27:21","url":"https://app.ofcapital.net/wechat/article/detail?id=1c431d7c89300e59c7231ea8b0a2fa46"},{"id":"086d77c74f39f99460f5cf4eb218064f","article_category_id":"1d81a23b7ee4d189a2d7cf89f9302352","title":"test","en_title":"","digest":"","en_digest":"","cover":"/notice.jpg","read":285,"created_at":"2019-12-31 15:23:50","url":"https://app.ofcapital.net/wechat/article/detail?id=086d77c74f39f99460f5cf4eb218064f"}]}
     * article_category_2 : {"title":"帮助中心","article_list":[{"id":"c7b16ef8ee92feb37be443b513fb984b","article_category_id":"8751c7f0bb2e3758ce4b86f34598404e","title":"常见问题4\u2026\u2026常见问题4\u2026\u2026常见问题4\u2026\u2026","en_title":"","digest":"","en_digest":"","cover":"/help.jpg","read":8,"created_at":"2019-04-24 20:50:42","url":"https://app.ofcapital.net/wechat/article/detail?id=c7b16ef8ee92feb37be443b513fb984b"},{"id":"c140d9490aea935ec7b871a9d3c52b41","article_category_id":"8751c7f0bb2e3758ce4b86f34598404e","title":"常见问题3\u2026\u2026常见问题3\u2026\u2026常见问题3\u2026\u2026","en_title":"","digest":"","en_digest":"","cover":"/help.jpg","read":2,"created_at":"2019-04-24 20:49:57","url":"https://app.ofcapital.net/wechat/article/detail?id=c140d9490aea935ec7b871a9d3c52b41"}]}
     */

    private ArticleCategory1Bean article_category_1;
    private ArticleCategory2Bean article_category_2;

    public ArticleCategory1Bean getArticle_category_1() {
        return article_category_1;
    }

    public void setArticle_category_1(ArticleCategory1Bean article_category_1) {
        this.article_category_1 = article_category_1;
    }

    public ArticleCategory2Bean getArticle_category_2() {
        return article_category_2;
    }

    public void setArticle_category_2(ArticleCategory2Bean article_category_2) {
        this.article_category_2 = article_category_2;
    }

    public static class ArticleCategory1Bean {
        /**
         * title : 通知公告
         * article_list : [{"id":"b1268d4e66b97b8107caa2869ed24841","article_category_id":"1d81a23b7ee4d189a2d7cf89f9302352","title":"test3","en_title":"","digest":"","en_digest":"","cover":"/notice.jpg","read":863,"created_at":"2020-01-06 17:32:54","url":"https://app.ofcapital.net/wechat/article/detail?id=b1268d4e66b97b8107caa2869ed24841"},{"id":"0eff20c59c4611574bccbaf18bb0219c","article_category_id":"1d81a23b7ee4d189a2d7cf89f9302352","title":"test2","en_title":"","digest":"","en_digest":"","cover":"/notice.jpg","read":672,"created_at":"2020-01-01 15:46:09","url":"https://app.ofcapital.net/wechat/article/detail?id=0eff20c59c4611574bccbaf18bb0219c"},{"id":"1c431d7c89300e59c7231ea8b0a2fa46","article_category_id":"1d81a23b7ee4d189a2d7cf89f9302352","title":"test","en_title":"","digest":"","en_digest":"","cover":"/notice.jpg","read":911,"created_at":"2020-01-01 15:27:21","url":"https://app.ofcapital.net/wechat/article/detail?id=1c431d7c89300e59c7231ea8b0a2fa46"},{"id":"086d77c74f39f99460f5cf4eb218064f","article_category_id":"1d81a23b7ee4d189a2d7cf89f9302352","title":"test","en_title":"","digest":"","en_digest":"","cover":"/notice.jpg","read":285,"created_at":"2019-12-31 15:23:50","url":"https://app.ofcapital.net/wechat/article/detail?id=086d77c74f39f99460f5cf4eb218064f"}]
         */

        private String title;
        private List<ArticleListBean> article_list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ArticleListBean> getArticle_list() {
            return article_list;
        }

        public void setArticle_list(List<ArticleListBean> article_list) {
            this.article_list = article_list;
        }

        public static class ArticleListBean {
            /**
             * id : b1268d4e66b97b8107caa2869ed24841
             * article_category_id : 1d81a23b7ee4d189a2d7cf89f9302352
             * title : test3
             * en_title :
             * digest :
             * en_digest :
             * cover : /notice.jpg
             * read : 863
             * created_at : 2020-01-06 17:32:54
             * url : https://app.ofcapital.net/wechat/article/detail?id=b1268d4e66b97b8107caa2869ed24841
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

    public static class ArticleCategory2Bean {
        /**
         * title : 帮助中心
         * article_list : [{"id":"c7b16ef8ee92feb37be443b513fb984b","article_category_id":"8751c7f0bb2e3758ce4b86f34598404e","title":"常见问题4\u2026\u2026常见问题4\u2026\u2026常见问题4\u2026\u2026","en_title":"","digest":"","en_digest":"","cover":"/help.jpg","read":8,"created_at":"2019-04-24 20:50:42","url":"https://app.ofcapital.net/wechat/article/detail?id=c7b16ef8ee92feb37be443b513fb984b"},{"id":"c140d9490aea935ec7b871a9d3c52b41","article_category_id":"8751c7f0bb2e3758ce4b86f34598404e","title":"常见问题3\u2026\u2026常见问题3\u2026\u2026常见问题3\u2026\u2026","en_title":"","digest":"","en_digest":"","cover":"/help.jpg","read":2,"created_at":"2019-04-24 20:49:57","url":"https://app.ofcapital.net/wechat/article/detail?id=c140d9490aea935ec7b871a9d3c52b41"}]
         */

        private String title;
        private List<ArticleListBeanX> article_list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ArticleListBeanX> getArticle_list() {
            return article_list;
        }

        public void setArticle_list(List<ArticleListBeanX> article_list) {
            this.article_list = article_list;
        }

        public static class ArticleListBeanX {
            /**
             * id : c7b16ef8ee92feb37be443b513fb984b
             * article_category_id : 8751c7f0bb2e3758ce4b86f34598404e
             * title : 常见问题4……常见问题4……常见问题4……
             * en_title :
             * digest :
             * en_digest :
             * cover : /help.jpg
             * read : 8
             * created_at : 2019-04-24 20:50:42
             * url : https://app.ofcapital.net/wechat/article/detail?id=c7b16ef8ee92feb37be443b513fb984b
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
}
