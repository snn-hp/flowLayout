package com.flowdemo.test.myapplication;

import java.util.List;

/**
 * Created by su on 2018/4/18.
 */

public class DataBean {

    private String typeName;

    private List<ChildItem> children;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<ChildItem> getChildren() {
        return children;
    }

    public void setChildren(List<ChildItem> children) {
        this.children = children;
    }

    public static class ChildItem {
        private String value;
        private int id;
        private boolean isSelected = false;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

}
