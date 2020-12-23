package com.fone.fone.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020-03-31.
 */
public class AboutModel implements Serializable {
    /**
     * about_fil_url : https://baike.baidu.com/item/Bwin/8622720?fr=aladdin
     * official_website : https://www.filcoin.org
     * version_code : 1
     * version_title : 1.0
     * download_url : http://
     */

    private String about_fil_url;
    private String official_website;
    private String version_code;
    private String version_title;
    private String download_url;

    public String getAbout_fil_url() {
        return about_fil_url;
    }

    public void setAbout_fil_url(String about_fil_url) {
        this.about_fil_url = about_fil_url;
    }

    public String getOfficial_website() {
        return official_website;
    }

    public void setOfficial_website(String official_website) {
        this.official_website = official_website;
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

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }
}
