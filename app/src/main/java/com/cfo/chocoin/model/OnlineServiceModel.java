package com.cfo.chocoin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2017/9/12.
 * 在线留言列表
 */

public class OnlineServiceModel implements Serializable {
    private List<LeaveMessageListBean> leave_message_list;

    public List<LeaveMessageListBean> getLeave_message_list() {
        return leave_message_list;
    }

    public void setLeave_message_list(List<LeaveMessageListBean> leave_message_list) {
        this.leave_message_list = leave_message_list;
    }

    public static class LeaveMessageListBean {
        /**
         * member_id : 6d3f2f6a08905ad516c3c2f94d1c629b
         * user_id :
         * content :
         * pic : /upload/leave-message/2018-06-25/f8d1e5b798e96f923de99edd60b2adc0.jpg
         * created_at : 2018-06-25 15:53:58
         */

        private String member_id;
        private String user_id;
        private String content;
        private String pic;
        private String created_at;

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
