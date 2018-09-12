package com.easy.work.tab.model;

import java.util.List;

/**
 * Created by apple on 18/5/25.
 */
public class TabDataModel {

    private String tabId;

    private String params;

    private List<String> fieldIdList;

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public List<String> getFieldIdList() {
        return fieldIdList;
    }

    public void setFieldIdList(List<String> fieldIdList) {
        this.fieldIdList = fieldIdList;
    }
}
