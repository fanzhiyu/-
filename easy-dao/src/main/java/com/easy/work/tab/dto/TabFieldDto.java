package com.easy.work.tab.dto;


import com.easy.core.domain.Domain;

import java.util.List;

/**
 * Created by apple on 18/5/17.
 */
public class TabFieldDto extends Domain{

    private static final String SQL = "com.easy.work.tab.dao.TabFieldDao.";

    private List<String> tabIds;

    private String tabComment;

    public List<String> getTabIds() {
        return tabIds;
    }

    public void setTabIds(List<String> tabIds) {
        this.tabIds = tabIds;
    }

    public String getTabComment() {
        return tabComment;
    }

    public void setTabComment(String tabComment) {
        this.tabComment = tabComment;
    }

    @Override
    public String getSqlId() {
        return SQL;
    }
}
