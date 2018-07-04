package com.easy.work.tab.dto;


import com.easy.core.domain.Domain;

/**
 * Created by apple on 18/5/17.
 */
public class TabTypeDto extends Domain {

    private static final String SQL = "com.easy.work.tab.dao.TabTypeDao.";

    @Override
    public String getSqlId() {
        return SQL;
    }
}
