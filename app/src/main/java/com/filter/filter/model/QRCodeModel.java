package com.filter.filter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2020/12/20.
 */
public class QRCodeModel implements Serializable {
    /**
     * head : /head/606.png
     * nickname : FIL_pour广告费
     * in_transfer_list : [{"from_member_nickname":"FIL_jsir","to_money":"10.0000","created_at":"2020-12-20 18:21:25"},{"from_member_nickname":"FIL_jsir","to_money":"10.0000","created_at":"2020-12-20 16:06:28"}]
     * out_transfer_list : [{"to_member_nickname":"FIL_pour广告费","from_money":"10.0000","created_at":"2020-12-20 18:21:25"},{"to_member_nickname":"FIL_pour广告费","from_money":"10.0000","created_at":"2020-12-20 16:06:28"}]
     */

    private String head;
    private String nickname;
    private List<InTransferListBean> in_transfer_list;
    private List<OutTransferListBean> out_transfer_list;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<InTransferListBean> getIn_transfer_list() {
        return in_transfer_list;
    }

    public void setIn_transfer_list(List<InTransferListBean> in_transfer_list) {
        this.in_transfer_list = in_transfer_list;
    }

    public List<OutTransferListBean> getOut_transfer_list() {
        return out_transfer_list;
    }

    public void setOut_transfer_list(List<OutTransferListBean> out_transfer_list) {
        this.out_transfer_list = out_transfer_list;
    }

    public static class InTransferListBean {
        /**
         * from_member_nickname : FIL_jsir
         * to_money : 10.0000
         * created_at : 2020-12-20 18:21:25
         */

        private String from_member_nickname;
        private String to_money;
        private String created_at;

        public String getFrom_member_nickname() {
            return from_member_nickname;
        }

        public void setFrom_member_nickname(String from_member_nickname) {
            this.from_member_nickname = from_member_nickname;
        }

        public String getTo_money() {
            return to_money;
        }

        public void setTo_money(String to_money) {
            this.to_money = to_money;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

    public static class OutTransferListBean {
        /**
         * to_member_nickname : FIL_pour广告费
         * from_money : 10.0000
         * created_at : 2020-12-20 18:21:25
         */

        private String to_member_nickname;
        private String from_money;
        private String created_at;

        public String getTo_member_nickname() {
            return to_member_nickname;
        }

        public void setTo_member_nickname(String to_member_nickname) {
            this.to_member_nickname = to_member_nickname;
        }

        public String getFrom_money() {
            return from_money;
        }

        public void setFrom_money(String from_money) {
            this.from_money = from_money;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
