package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/12/22.
 */
public class MyMachineModel implements Serializable {
    /**
     * fil_money : 0.0000
     * hashrate : 2
     * invest_list : [{"id":"bc7b07eead4431f25092be39d8faed94","sn":"IN1608545352565354","mill_type":2,"hashrate":1,"interest_fil_money":"0.0000","status":1,"created_at":"2020-12-21 18:09:12","mill_type_title":"购买","status_title":"进行中"},{"id":"50c67accb59d7364fdc7069a48363932","sn":"IN1608527081505657","mill_type":2,"hashrate":1,"interest_fil_money":"0.0000","status":1,"created_at":"2020-12-21 13:04:41","mill_type_title":"购买","status_title":"进行中"}]
     */

    private String fil_money;
    private String hashrate;
    private List<InvestListBean> invest_list;

    public String getFil_money() {
        return fil_money;
    }

    public void setFil_money(String fil_money) {
        this.fil_money = fil_money;
    }

    public String getHashrate() {
        return hashrate;
    }

    public void setHashrate(String hashrate) {
        this.hashrate = hashrate;
    }

    public List<InvestListBean> getInvest_list() {
        return invest_list;
    }

    public void setInvest_list(List<InvestListBean> invest_list) {
        this.invest_list = invest_list;
    }

    public static class InvestListBean {
        /**
         * id : bc7b07eead4431f25092be39d8faed94
         * sn : IN1608545352565354
         * mill_type : 2
         * hashrate : 1
         * interest_fil_money : 0.0000
         * status : 1
         * created_at : 2020-12-21 18:09:12
         * mill_type_title : 购买
         * status_title : 进行中
         */

        private String id;
        private String sn;
        private int mill_type;
        private String hashrate;
        private String interest_fil_money;
        private int status;
        private String created_at;
        private String mill_type_title;
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

        public int getMill_type() {
            return mill_type;
        }

        public void setMill_type(int mill_type) {
            this.mill_type = mill_type;
        }

        public String getHashrate() {
            return hashrate;
        }

        public void setHashrate(String hashrate) {
            this.hashrate = hashrate;
        }

        public String getInterest_fil_money() {
            return interest_fil_money;
        }

        public void setInterest_fil_money(String interest_fil_money) {
            this.interest_fil_money = interest_fil_money;
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

        public String getMill_type_title() {
            return mill_type_title;
        }

        public void setMill_type_title(String mill_type_title) {
            this.mill_type_title = mill_type_title;
        }

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }
    }
}
