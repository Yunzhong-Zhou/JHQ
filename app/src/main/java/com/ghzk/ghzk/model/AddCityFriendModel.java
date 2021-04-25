package com.ghzk.ghzk.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/2/28.
 */
public class AddCityFriendModel implements Serializable {
    /**
     * status : 2
     */

    private int status;
    private String status_rejected_cause;

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
}
