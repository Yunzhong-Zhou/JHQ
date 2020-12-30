package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/12/23.
 */
public class ContractListModel implements Serializable {
    /**
     * invoice_list : [{"member_id":"ec75507f915cb412b35299b11e672fa5","type":1,"cny_money":"1000.00","created_at":"2020-12-23 18:03:23","type_title":"电子"},{"member_id":"ec75507f915cb412b35299b11e672fa5","type":2,"cny_money":"1000.00","created_at":"2020-12-23 14:54:03","type_title":"纸质"},{"member_id":"ec75507f915cb412b35299b11e672fa5","type":2,"cny_money":"1000.00","created_at":"2020-12-23 14:21:17","type_title":"纸质"}]
     * invoice_money : 447.7611
     * residue_invoice_cny_money : 184600.00
     */

    private String invoice_cny_money;
    private String residue_invoice_cny_money;
    private List<InvoiceListBean> invoice_list;

    public String getInvoice_cny_money() {
        return invoice_cny_money;
    }

    public void setInvoice_cny_money(String invoice_cny_money) {
        this.invoice_cny_money = invoice_cny_money;
    }

    public String getResidue_invoice_cny_money() {
        return residue_invoice_cny_money;
    }

    public void setResidue_invoice_cny_money(String residue_invoice_cny_money) {
        this.residue_invoice_cny_money = residue_invoice_cny_money;
    }

    public List<InvoiceListBean> getInvoice_list() {
        return invoice_list;
    }

    public void setInvoice_list(List<InvoiceListBean> invoice_list) {
        this.invoice_list = invoice_list;
    }

    public static class InvoiceListBean {
        /**
         * member_id : ec75507f915cb412b35299b11e672fa5
         * type : 1
         * cny_money : 1000.00
         * created_at : 2020-12-23 18:03:23
         * type_title : 电子
         */

        private String member_id;
        private int type;
        private String cny_money;
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

        public String getCny_money() {
            return cny_money;
        }

        public void setCny_money(String cny_money) {
            this.cny_money = cny_money;
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
