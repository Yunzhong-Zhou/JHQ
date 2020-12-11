package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020-03-31.
 */
public class AboutModel implements Serializable {
    /**
     * about_bwin_url : https://baike.baidu.com/item/Bwin/8622720?fr=aladdin
     * official_website : https://www.bwincoin.org
     * download_url : http://
     * version_code : 1
     * version_title : 1.0
     * url : http://
     */

    private String about_bwin_url;
    private String official_website;
    private String download_url;
    private String version_code;
    private String version_title;
    private String url;

    public String getAbout_bwin_url() {
        return about_bwin_url;
    }

    public void setAbout_bwin_url(String about_bwin_url) {
        this.about_bwin_url = about_bwin_url;
    }

    public String getOfficial_website() {
        return official_website;
    }

    public void setOfficial_website(String official_website) {
        this.official_website = official_website;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getVersion_title() {
        return version_title;
    }

    public void setVersion_title(String version_title) {
        this.version_title = version_title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
