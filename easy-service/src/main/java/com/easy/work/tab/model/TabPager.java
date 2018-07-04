package com.easy.work.tab.model;

import com.easy.core.annotation.Fields;
import com.easy.core.model.PagingModel;

import java.util.List;

/**
 * Created by apple on 18/5/18.
 */
public class TabPager extends PagingModel<TabPager> {
    private String tabId;
    private String tabName;
    private String tabSourceName;
    private String tabComment;
    private String tabType;
    private String createrUser;
    @Fields(name = "createTime",format = "yyyy-MM-dd")
    private String createDate;
    private String updaterUser;
    @Fields(name = "updateTime",format = "yyyy-MM-dd")
    private String updateDate;
    private String startDate;
    private String endDate;

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

    public String getTabComment() {
        return tabComment;
    }

    public void setTabComment(String tabComment) {
        this.tabComment = tabComment;
    }

    public String getTabType() {
        return tabType;
    }

    public void setTabType(String tabType) {
        this.tabType = tabType;
    }

    public String getCreaterUser() {
        return createrUser;
    }

    public void setCreaterUser(String createrUser) {
        this.createrUser = createrUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdaterUser() {
        return updaterUser;
    }

    public void setUpdaterUser(String updaterUser) {
        this.updaterUser = updaterUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
