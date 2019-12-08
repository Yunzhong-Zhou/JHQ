package com.cfo.chocoin.model;

import java.io.Serializable;

/**
 * Created by zyz on 2019/6/2.
 */
public class MyRechargeModel implements Serializable {
    /**
     * wallet_addr : 啊啊啊啊
     * sn : TU1559462920534856
     * money_type : 1
     * eth_price : 0.00
     * cho_price : 0.00
     * txid : 5265461848048145526746268281449922582446
     * input_money : 8.46
     * money : 8.46
     * status : 2
     * status_rejected_cause :
     * created_at : 2019-06-02 16:08:40
     * updated_at : 2019-06-02 17:44:12
     * money_type_title : 以太坊
     * status_title : 通过
     * show_created_at : 06-02 16:08
     * show_updated_at : 06-02 17:44
     */

    private String id;
    private String wallet_addr;
    private String sn;
    private int money_type;
    private String eth_price;
    private String cho_price;
    private String txid;
    private String input_money;
    private String money;
    private int status;
    private String status_rejected_cause;
    private String created_at;
    private String updated_at;
    private String money_type_title;
    private String status_title;
    private String show_created_at;
    private String show_updated_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWallet_addr() {
        return wallet_addr;
    }

    public void setWallet_addr(String wallet_addr) {
        this.wallet_addr = wallet_addr;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getMoney_type() {
        return money_type;
    }

    public void setMoney_type(int money_type) {
        this.money_type = money_type;
    }

    public String getEth_price() {
        return eth_price;
    }

    public void setEth_price(String eth_price) {
        this.eth_price = eth_price;
    }

    public String getCho_price() {
        return cho_price;
    }

    public void setCho_price(String cho_price) {
        this.cho_price = cho_price;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getInput_money() {
        return input_money;
    }

    public void setInput_money(String input_money) {
        this.input_money = input_money;
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

    public String getStatus_rejected_cause() {
        return status_rejected_cause;
    }

    public void setStatus_rejected_cause(String status_rejected_cause) {
        this.status_rejected_cause = status_rejected_cause;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getMoney_type_title() {
        return money_type_title;
    }

    public void setMoney_type_title(String money_type_title) {
        this.money_type_title = money_type_title;
    }

    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
    }

    public String getShow_created_at() {
        return show_created_at;
    }

    public void setShow_created_at(String show_created_at) {
        this.show_created_at = show_created_at;
    }

    public String getShow_updated_at() {
        return show_updated_at;
    }

    public void setShow_updated_at(String show_updated_at) {
        this.show_updated_at = show_updated_at;
    }
}
