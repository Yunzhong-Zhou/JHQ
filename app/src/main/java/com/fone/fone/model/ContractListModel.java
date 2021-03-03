package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/12/23.
 */
public class ContractListModel implements Serializable {

    /**
     * invoice_list : [{"member_id":"5304cafc529e02a7838ef07167666600","type":1,"money":"100.0000","created_at":"2021-03-02 20:28:42","type_title":"电子"}]
     * invoice_money : 100.0000
     * residue_invoice_money : 100.0000
     */

    private String invoice_money;
    private String residue_invoice_money;
    private List<InvoiceListBean> invoice_list;

    public String getInvoice_money() {
        return invoice_money;
    }

    public void setInvoice_money(String invoice_money) {
        this.invoice_money = invoice_money;
    }

    public String getResidue_invoice_money() {
        return residue_invoice_money;
    }

    public void setResidue_invoice_money(String residue_invoice_money) {
        this.residue_invoice_money = residue_invoice_money;
    }

    public List<InvoiceListBean> getInvoice_list() {
        return invoice_list;
    }

    public void setInvoice_list(List<InvoiceListBean> invoice_list) {
        this.invoice_list = invoice_list;
    }

    public static class InvoiceListBean {
        /**
         * member_id : 5304cafc529e02a7838ef07167666600
         * type : 1
         * money : 100.0000
         * created_at : 2021-03-02 20:28:42
         * type_title : 电子
         */

        private String member_id;
        private int type;
        private String money;
        private String created_at;
        private String type_title;

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getType_title() {
            return type_title;
        }

        public void setType_title(String type_title) {
            this.type_title = type_title;
        }
    }
}
