package com.easy.work.tab.model;

import java.util.List;

/**
 * Created by apple on 18/5/23.
 */
public class TabFieldModel {

    private String tabId;

    private List<String> tabIds;

    private List<TabFieldInfoModel> fields;

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public List<TabFieldInfoModel> getFields() {
        return fields;
    }

    public void setFields(List<TabFieldInfoModel> fields) {
        this.fields = fields;
    }

    public List<String> getTabIds() {
        return tabIds;
    }

    public void setTabIds(List<String> tabIds) {
        this.tabIds = tabIds;
    }
}
