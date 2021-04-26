package com.ghzk.ghzk.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Z on 2021/4/26.
 */
public class CommonModel implements Serializable {
    /**
     * 通用列表
     */
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String id;
        private String name;
//        private String parentId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

//        public String getParentId() {
//            return parentId;
//        }

//        public void setParentId(String parentId) {
//            this.parentId = parentId;
//        }
    }
}
