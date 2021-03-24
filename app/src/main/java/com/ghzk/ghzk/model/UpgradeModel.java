package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by zyz on 2017/9/30.
 * 更新
 */

public class UpgradeModel implements Serializable {

    /**
     * version_code : 1
     * version_title : 1.0
     * download_url : http://
     */

    private String version_code;
    private String version_title;
    private String download_url;

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
