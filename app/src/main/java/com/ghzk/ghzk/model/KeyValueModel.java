package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/11.
 */
public class KeyValueModel implements Serializable {
    public KeyValueModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    String key;
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
