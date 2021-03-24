package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/2/27.
 */
public class MyOrderModel implements Serializable {
    /**
     * id : 5f70bc69b4ec49ad41844ce1d6844b44
     * sn : OR1614315438985310
     * buy_type : 1
     * operation_type : 1
     * num : 1
     * price : 200.00
     * money : 200.00
     * status : 2
     * created_at : 2021-02-26 12:57:18
     * buy_type_title : 不能回购
     * operation_type_title : 代运营
     * status_title : 已付款
     */

    private String id;
    private String sn;
    private int buy_type;
    private int operation_type;
    private int num;
    private String price;
    private String money;
    private int status;
    private String created_at;
    private String buy_type_title;
    private String operation_type_title;
    private String status_title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getBuy_type() {
        return buy_type;
    }

    public void setBuy_type(int buy_type) {
        this.buy_type = buy_type;
    }

    public int getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(int operation_type) {
        this.operation_type = operation_type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getBuy_type_title() {
        return buy_type_title;
    }

    public void setBuy_type_title(String buy_type_title) {
        this.buy_type_title = buy_type_title;
    }

    public String getOperation_type_title() {
        return operation_type_title;
    }

    public void setOperation_type_title(String operation_type_title) {
        this.operation_type_title = operation_type_title;
    }

    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
    }
}
