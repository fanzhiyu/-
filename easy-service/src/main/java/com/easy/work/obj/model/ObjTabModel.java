package com.easy.work.obj.model;

import java.util.List;

/**
 * Created by apple on 18/5/30.
 */
public class ObjTabModel {

    private String tabId;

    private List<String> fieldIds;

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public List<String> getFieldIds() {
        return fieldIds;
    }

    public void setFieldIds(List<String> fieldIds) {
        this.fieldIds = fieldIds;
    }
}
