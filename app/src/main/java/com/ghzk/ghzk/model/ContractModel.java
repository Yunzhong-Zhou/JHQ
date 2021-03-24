package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2020/12/22.
 */
public class ContractModel implements Serializable {
    /**
     * type : 0
     * type_title :
     * title :
     * number :
     * name :
     * mobile :
     * addr :
     * residue_invoice_cny_money : 120600.00
     */

    private String id;
    private String type;
    private String type_title;
    private String title;
    private String number;
    private String name;
    private String mobile;
    private String addr;
    private String url;
    private String residue_invoice_money;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_title() {
        return type_title;
    }

    public void setType_title(String type_title) {
        this.type_title = type_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getResidue_invoice_money() {
        return residue_invoice_money;
    }

    public void setResidue_invoice_money(String residue_invoice_money) {
        this.residue_invoice_money = residue_invoice_money;
    }
}
