package com.easy.work.tab.model;

import com.easy.core.annotation.Fields;

import java.util.List;

/**
 * Created by apple on 18/5/23.
 */
public class TabFieldInfoModel {

    private String tabId;
    private String tabFieldId;
    private String tabIsKey;
    private String tabIsNull;
    private String tabFieldName;
    private String tabFieldType;
    private String tabFieldLength;
    private String tabFieldComment;
    private String tabComment;

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public String getTabFieldId() {
        return tabFieldId;
    }

    public void setTabFieldId(String tabFieldId) {
        this.tabFieldId = tabFieldId;
    }

    public String getTabIsKey() {
        return tabIsKey;
    }

    public void setTabIsKey(String tabIsKey) {
        this.tabIsKey = tabIsKey;
    }

    public String getTabIsNull() {
        return tabIsNull;
    }

    public void setTabIsNull(String tabIsNull) {
        this.tabIsNull = tabIsNull;
    }

    public String getTabFieldName() {
        return tabFieldName;
    }

    public void setTabFieldName(String tabFieldName) {
        this.tabFieldName = tabFieldName;
    }

    public String getTabFieldType() {
        return tabFieldType;
    }

    public void setTabFieldType(String tabFieldType) {
        this.tabFieldType = tabFieldType;
    }

    public String getTabFieldLength() {
        return tabFieldLength;
    }

    public void setTabFieldLength(String tabFieldLength) {
        this.tabFieldLength = tabFieldLength;
    }

    public String getTabFieldComment() {
        return tabFieldComment;
    }

    public void setTabFieldComment(String tabFieldComment) {
        this.tabFieldComment = tabFieldComment;
    }

    public String getTabComment() {
        return tabComment;
    }

    public void setTabComment(String tabComment) {
        this.tabComment = tabComment;
    }
}
