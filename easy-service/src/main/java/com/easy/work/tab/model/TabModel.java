package com.easy.work.tab.model;


import java.util.List;
import java.util.Map;

/**
 * Created by apple on 18/5/18.
 */
public class TabModel {

    private String tabId;
    private String tabName;
    private String tabSourceName;
    private String tabType;
    private String tabIndex;
    private String tabKey;
    private String tabComment;
    private String options;
    private String tabFiles;
    private List<TabFieldInfoModel> fields;
    private Map<String,Object> keyMap;

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabSourceName() {
        return tabSourceName;
    }

    public void setTabSourceName(String tabSourceName) {
        this.tabSourceName = tabSourceName;
    }

    public String getTabType() {
        return tabType;
    }

    public void setTabType(String tabType) {
        this.tabType = tabType;
    }

    public String getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(String tabIndex) {
        this.tabIndex = tabIndex;
    }

    public String getTabKey() {
        return tabKey;
    }

    public void setTabKey(String tabKey) {
        this.tabKey = tabKey;
    }

    public String getTabComment() {
        return tabComment;
    }

    public void setTabComment(String tabComment) {
        this.tabComment = tabComment;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getTabFiles() {
        return tabFiles;
    }

    public void setTabFiles(String tabFiles) {
        this.tabFiles = tabFiles;
    }

    public List<TabFieldInfoModel> getFields() {
        return fields;
    }

    public void setFields(List<TabFieldInfoModel> fields) {
        this.fields = fields;
    }

    public Map<String, Object> getKeyMap() {
        return keyMap;
    }

    public void setKeyMap(Map<String, Object> keyMap) {
        this.keyMap = keyMap;
    }
}
