package com.filter.filter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/4.
 */
public class SchoolModel implements Serializable {
    /**
     * article_category_1 : {"title":"基础知识","article_list":[{"id":"a3dede1d27ec9601c8b60fede83e3bfc","article_category_id":"c647fb3291586713d6399748fa2e483e","title":"aaaaaa","cover":"/upload/article/2019-12-20/e25afb9d55640a5dff49dcf54dba5e50.jpg","digest":"","read":133,"created_at":"2019-12-20 21:42:33","url":"http://www.usdtcai.com/wechat/article/detail?id=a3dede1d27ec9601c8b60fede83e3bfc"}]}
     * article_category_2 : {"title":"进阶知识","article_list":[{"id":"47888c1d94d3bf4fc1dd53ed7656ea26","article_category_id":"44b4e3b89a09fc667e2c58aba8df62c1","title":"bbbbbb","cover":"/upload/article/2019-12-20/e2d9b09a3f9d0dc7f11f2b8717454356.jpg","digest":"","read":864,"created_at":"2019-12-20 21:42:56","url":"http://www.usdtcai.com/wechat/article/detail?id=47888c1d94d3bf4fc1dd53ed7656ea26"}]}
     * article_category_3 : {"title":"高阶知识","article_list":[{"id":"c82de9553cfd0f9dd0e7de3af2d97386","article_category_id":"839cb4c363c33a8d417776da6aa00c26","title":"cccccc","cover":"/upload/article/2019-12-20/3c72504ca74beae5fd0c35c9b596990b.jpg","digest":"","read":266,"created_at":"2019-12-20 21:43:10","url":"http://www.usdtcai.com/wechat/article/detail?id=c82de9553cfd0f9dd0e7de3af2d97386"}]}
     */

    private ArticleCategory1Bean article_category_1;
    private ArticleCategory2Bean article_category_2;
    private ArticleCategory3Bean article_category_3;

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

    public ArticleCategory3Bean getArticle_category_3() {
        return article_category_3;
    }

    public void setArticle_category_3(ArticleCategory3Bean article_category_3) {
        this.article_category_3 = article_category_3;
    }

    public static class ArticleCategory1Bean {
        /**
         * title : 基础知识
         * article_list : [{"id":"a3dede1d27ec9601c8b60fede83e3bfc","article_category_id":"c647fb3291586713d6399748fa2e483e","title":"aaaaaa","cover":"/upload/article/2019-12-20/e25afb9d55640a5dff49dcf54dba5e50.jpg","digest":"","read":133,"created_at":"2019-12-20 21:42:33","url":"http://www.usdtcai.com/wechat/article/detail?id=a3dede1d27ec9601c8b60fede83e3bfc"}]
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
             * id : a3dede1d27ec9601c8b60fede83e3bfc
             * article_category_id : c647fb3291586713d6399748fa2e483e
             * title : aaaaaa
             * cover : /upload/article/2019-12-20/e25afb9d55640a5dff49dcf54dba5e50.jpg
             * digest :
             * read : 133
             * created_at : 2019-12-20 21:42:33
             * url : http://www.usdtcai.com/wechat/article/detail?id=a3dede1d27ec9601c8b60fede83e3bfc
             */

            private String id;
            private String article_category_id;
            private String title;
            private String cover;
            private String digest;
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
         * title : 进阶知识
         * article_list : [{"id":"47888c1d94d3bf4fc1dd53ed7656ea26","article_category_id":"44b4e3b89a09fc667e2c58aba8df62c1","title":"bbbbbb","cover":"/upload/article/2019-12-20/e2d9b09a3f9d0dc7f11f2b8717454356.jpg","digest":"","read":864,"created_at":"2019-12-20 21:42:56","url":"http://www.usdtcai.com/wechat/article/detail?id=47888c1d94d3bf4fc1dd53ed7656ea26"}]
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
             * id : 47888c1d94d3bf4fc1dd53ed7656ea26
             * article_category_id : 44b4e3b89a09fc667e2c58aba8df62c1
             * title : bbbbbb
             * cover : /upload/article/2019-12-20/e2d9b09a3f9d0dc7f11f2b8717454356.jpg
             * digest :
             * read : 864
             * created_at : 2019-12-20 21:42:56
             * url : http://www.usdtcai.com/wechat/article/detail?id=47888c1d94d3bf4fc1dd53ed7656ea26
             */

            private String id;
            private String article_category_id;
            private String title;
            private String cover;
            private String digest;
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

    public static class ArticleCategory3Bean {
        /**
         * title : 高阶知识
         * article_list : [{"id":"c82de9553cfd0f9dd0e7de3af2d97386","article_category_id":"839cb4c363c33a8d417776da6aa00c26","title":"cccccc","cover":"/upload/article/2019-12-20/3c72504ca74beae5fd0c35c9b596990b.jpg","digest":"","read":266,"created_at":"2019-12-20 21:43:10","url":"http://www.usdtcai.com/wechat/article/detail?id=c82de9553cfd0f9dd0e7de3af2d97386"}]
         */

        private String title;
        private List<ArticleListBeanXX> article_list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ArticleListBeanXX> getArticle_list() {
            return article_list;
        }

        public void setArticle_list(List<ArticleListBeanXX> article_list) {
            this.article_list = article_list;
        }

        public static class ArticleListBeanXX {
            /**
             * id : c82de9553cfd0f9dd0e7de3af2d97386
             * article_category_id : 839cb4c363c33a8d417776da6aa00c26
             * title : cccccc
             * cover : /upload/article/2019-12-20/3c72504ca74beae5fd0c35c9b596990b.jpg
             * digest :
             * read : 266
             * created_at : 2019-12-20 21:43:10
             * url : http://www.usdtcai.com/wechat/article/detail?id=c82de9553cfd0f9dd0e7de3af2d97386
             */

            private String id;
            private String article_category_id;
            private String title;
            private String cover;
            private String digest;
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
