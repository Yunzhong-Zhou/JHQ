package com.fone.fone.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/12/21.
 */
public class MachineDetailModel implements Serializable {
    /**
     * invest : {"id":"d057d2b8ce0b16c2f253e07106d00a29","sn":"IN1608611906505752","pay_type":2,"mill_type":3,"mill_computer_position":"中国福州","mill_production_value_fil_money":"100.0000","mill_node_number":"0x11111","mill_number":"oxk333","mill_mining_cycle":360,"hashrate":10,"status":1,"interest_fil_money":"0.0000","verify_at":"2020-12-22 12:38:26","buy_back_apply":1,"created_at":"2020-12-22 12:38:26","pay_type_title":"CNY","mill_type_title":"整机","status_title":"进行中","start_at":"2020-12-22","end_at":"1970-12-27","interest_list":[{"invest_id":"d057d2b8ce0b16c2f253e07106d00a29","fil_money":"0.0000","created_at":"2020-12-22 15:31:19"},{"invest_id":"d057d2b8ce0b16c2f253e07106d00a29","fil_money":"0.0000","created_at":"2020-12-22 15:27:07"},{"invest_id":"d057d2b8ce0b16c2f253e07106d00a29","fil_money":"0.0000","created_at":"2020-12-22 15:16:21"},{"invest_id":"d057d2b8ce0b16c2f253e07106d00a29","fil_money":"0.0000","created_at":"2020-12-22 15:11:10"}]}
     */

    private InvestBean invest;

    public InvestBean getInvest() {
        return invest;
    }

    public void setInvest(InvestBean invest) {
        this.invest = invest;
    }

    public static class InvestBean {
        /**
         * id : d057d2b8ce0b16c2f253e07106d00a29
         * sn : IN1608611906505752
         * pay_type : 2
         * mill_type : 3
         * mill_computer_position : 中国福州
         * mill_production_value_fil_money : 100.0000
         * mill_node_number : 0x11111
         * mill_number : oxk333
         * mill_mining_cycle : 360
         * hashrate : 10
         * status : 1
         * interest_fil_money : 0.0000
         * verify_at : 2020-12-22 12:38:26
         * buy_back_apply : 1
         * created_at : 2020-12-22 12:38:26
         * pay_type_title : CNY
         * mill_type_title : 整机
         * status_title : 进行中
         * start_at : 2020-12-22
         * end_at : 1970-12-27
         * interest_list : [{"invest_id":"d057d2b8ce0b16c2f253e07106d00a29","fil_money":"0.0000","created_at":"2020-12-22 15:31:19"},{"invest_id":"d057d2b8ce0b16c2f253e07106d00a29","fil_money":"0.0000","created_at":"2020-12-22 15:27:07"},{"invest_id":"d057d2b8ce0b16c2f253e07106d00a29","fil_money":"0.0000","created_at":"2020-12-22 15:16:21"},{"invest_id":"d057d2b8ce0b16c2f253e07106d00a29","fil_money":"0.0000","created_at":"2020-12-22 15:11:10"}]
         */

        private String id;
        private String sn;
        private int pay_type;
        private int mill_type;
        private String mill_computer_position;
        private String mill_production_value_fil_money;
        private String mill_node_number;
        private String mill_number;
        private String mill_mining_cycle;
        private String hashrate;
        private int status;
        private String interest_fil_money;
        private String verify_at;
        private String buy_back_apply;
        private String created_at;
        private String pay_type_title;
        private String mill_type_title;
        private String status_title;
        private String start_at;
        private String end_at;
        private List<InterestListBean> interest_list;

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

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public int getMill_type() {
            return mill_type;
        }

        public void setMill_type(int mill_type) {
            this.mill_type = mill_type;
        }

        public String getMill_computer_position() {
            return mill_computer_position;
        }

        public void setMill_computer_position(String mill_computer_position) {
            this.mill_computer_position = mill_computer_position;
        }

        public String getMill_production_value_fil_money() {
            return mill_production_value_fil_money;
        }

        public void setMill_production_value_fil_money(String mill_production_value_fil_money) {
            this.mill_production_value_fil_money = mill_production_value_fil_money;
        }

        public String getMill_node_number() {
            return mill_node_number;
        }

        public void setMill_node_number(String mill_node_number) {
            this.mill_node_number = mill_node_number;
        }

        public String getMill_number() {
            return mill_number;
        }

        public void setMill_number(String mill_number) {
            this.mill_number = mill_number;
        }

        public String getMill_mining_cycle() {
            return mill_mining_cycle;
        }

        public void setMill_mining_cycle(String mill_mining_cycle) {
            this.mill_mining_cycle = mill_mining_cycle;
        }

        public String getHashrate() {
            return hashrate;
        }

        public void setHashrate(String hashrate) {
            this.hashrate = hashrate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getInterest_fil_money() {
            return interest_fil_money;
        }

        public void setInterest_fil_money(String interest_fil_money) {
            this.interest_fil_money = interest_fil_money;
        }

        public String getVerify_at() {
            return verify_at;
        }

        public void setVerify_at(String verify_at) {
            this.verify_at = verify_at;
        }

        public String getBuy_back_apply() {
            return buy_back_apply;
        }

        public void setBuy_back_apply(String buy_back_apply) {
            this.buy_back_apply = buy_back_apply;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getPay_type_title() {
            return pay_type_title;
        }

        public void setPay_type_title(String pay_type_title) {
            this.pay_type_title = pay_type_title;
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

        public String getStart_at() {
            return start_at;
        }

        public void setStart_at(String start_at) {
            this.start_at = start_at;
        }

        public String getEnd_at() {
            return end_at;
        }

        public void setEnd_at(String end_at) {
            this.end_at = end_at;
        }

        public List<InterestListBean> getInterest_list() {
            return interest_list;
        }

        public void setInterest_list(List<InterestListBean> interest_list) {
            this.interest_list = interest_list;
        }

        public static class InterestListBean {
            /**
             * invest_id : d057d2b8ce0b16c2f253e07106d00a29
             * fil_money : 0.0000
             * created_at : 2020-12-22 15:31:19
             */

            private String invest_id;
            private String fil_money;
            private String created_at;

            public String getInvest_id() {
                return invest_id;
            }

            public void setInvest_id(String invest_id) {
                this.invest_id = invest_id;
            }

            public String getFil_money() {
                return fil_money;
            }

            public void setFil_money(String fil_money) {
                this.fil_money = fil_money;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }
    }
}
